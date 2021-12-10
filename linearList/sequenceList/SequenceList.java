package collection.linearList.sequenceList;

import collection.linearList.LinearList;
import collection.linearList.AbstractLinearList;

import java.io.Serializable;


/**
 *
 * 顺序存储结构的线性表实现类
 * 为什么要继承抽象接口？
 *      1.抽象接口是用来提供线性表的共性方法
 *      2.一个类只能有一个抽象方法
 *      3.抽象类中的抽象方法必须被重写
 * 为什么要实现接口？
 *      接口为实现类提供行为约束，遵循线性表的规则约束
 * 为什么要实现Serializable
 *      实现该接口，就能对类和对象进行序列化
 * @data2021/8/24,10:34
 * @authorsutinghu
 */
public class SequenceList<T> extends AbstractLinearList<T>
        implements LinearList<T>, Serializable {

    /**
     * 实现类中实现了Serializable接口时，需要添加序列化
     */
    private static final long serialVersionUID = 8683452581122892189L;
    /**
     * 初始化时默认容量 设置为10 为什么是10？
     * 日常开发中涉及到分页查询一般查询为 10条数据，可能这也是JDK 之前初始设置为 10 而不是 8 或 16 的原因
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 线性表中元素的个数
     *      size在什么时候会被改变，就在哪些时候做size的统计
     *      1.当线性表新增元素时
     *      2.线性表移除元素时
     *
     */
    private int size = 0;

    /**
     * 创建一个对象时的数据元素
     * 为什么要用Object？
     *      Object能够接收所有的基本数据类型，包括抽象的
     * 为什么要用到transient关键字？
     *      1.首先要明白transient关键字的作用
     *      2.当我们实现了Serializable接口时，意味着我们的当前类的所有对象属性及成员变量都是可以进行序列化的
     *      3.transient 关键字是反序列化的标注，即我们的某个变量标注了该关键字时，当前成员将不被序列化
     *      4.接下来，我们考虑线性表由数组表示，当数组的容量和元素个数不一样时，序列化时，空的数组也会被序列化
     *      以上有必要吗？无
     *      因此我们只要按需序列化即可 writeObject 和 readObject方法提供了这样高性能的方式
     */
    transient Object[] elementData;

    /**
     * 这是一个未被new的命名
     *      它的使命是在扩容的时候 做为中间参数
     */
    private Object[] A_ELEMENTDATA = {};

    /**
     * 这是一个空的线性数组 为什么要用这个数组
     *      1.当线性表需要被转换其结果为空时，可以用这个代替
     *      2.当线性表创建时，需要为空时，可以用这个代替
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 构造方法1
     * 生产一个默认大小的线性表 即大小为10的容器
     */
    public SequenceList(){
        this.elementData = new Object[DEFAULT_CAPACITY];

    }

    /**
     * 构造方法2
     * 生产一个特定大小的线性表
     * IllegalArgumentException异常的错误
     * 告诉使用者 当前方法的异常是由于参数异常造成的
     * @param initialCapacity 初始容量
     *
     */
    public SequenceList(int initialCapacity){
        if (initialCapacity == 0){
            // 将空数据传给当前表
            this.elementData = EMPTY_ELEMENTDATA;

        } else if (initialCapacity > 0){
            // 生产一个特定大小的数组
            this.elementData = new Object[initialCapacity];

        } else {
            throw new IllegalArgumentException("不合法的参数: "+
                    initialCapacity);
        }
    }

    /**
     * 移除另一个线性表中的元素
     * @param t
     */
    @Override
    public void removeAll(LinearList<T> t) {

        Object[] objects = new Object[t.size()];
        for (int i = 0;i<t.size();i++) {
            objects[i] = t.get(i);
        }
        // 移除列表中的值
        for (int j = 0;j<objects.length;j++){
            this.remove(objects[j]);
        }
    }

    /**
     * 判断当前线性表是否包含某个元素
     * 如果包含 返回当前匹配到的第一个值的下标位置
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0;i<size;i++){
                if ((elementData[i])==null){
                    return i;
                }
            }
        }else {
            for (int i = 0;i<size;i++){
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 判断当前线性表是否包含某个元素
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {

        return indexOf(o) >= 0 ? true : false;

    }

    /**
     * 返回当前元素的个数
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 实现一个方法作为获取当前对象容量的参数
     * @return
     */
    @Override
    public int length(){
        /**
         * 记录当前线性表的容量大小
         *      length在什么时候会被改变，就在什么时候改变它的大小
         *      1.数组创建时
         *      2.数组扩容时
         *      3.数组缩小时
         */
        return elementData.length;
    }

    /**
     * 清空线性表
     *      当执行该方法时，线性表的所有元素清空
     *      线性表长度保持不变
     */
    @Override
    public void clear() {

        for (int i =0 ;i<size;i++) {
            this.elementData[i] = null;
        }

        this.size = 0;
    }

    /**
     * 归零操作
     *      源码中没有该方法
     *      该方法的作用是使当前的线性表
     *      在元素个数和长度两个方面都同时清空
     */
    @Override
    public void clearTozero(){

        this.elementData = EMPTY_ELEMENTDATA;

        this.size = 0;

    }

    @Override
    public T get(int index) {

        lengthCheck(index);

        return (T) elementData[index];
    }

    /**
     * 该方法用来判断索引是不是超出了线性表的长度 避免空指针
     * @param index
     */
    private void lengthCheck(int index) {

        if (index > elementData.length){
            throw new IndexOutOfBoundsException("索引超出容量范围,"+"当前容量大小为："
                    +elementData.length);
        }else if (index > size -1){
            throw new IndexOutOfBoundsException("索引超出线性表长度范围,"+"当前表中可用元素为："
                    +size);
        }else if (index < 0){
            throw new IndexOutOfBoundsException("索引不能为负");
        }
    }

    /**
     * 移除某个位置上的元素
     *      移除机制步骤
     *          1. 判断指定位置是否合法
     *          2. 合法则将该位置的数据之后的数据前移一位
     *          3. 最后位置的数据置为空
     * @param index
     */
    @Override
    public void remove(int index) {
        // 索引合法性判断
        lengthCheck(index);
        Object value = elementData[index];
        // 移除逻辑
        int numMove = size - index - 1;// 需要移动的目标长度
        // 如果要移除的元素是最后一个则不做拷贝处理
        if (numMove>0){
            System.arraycopy(elementData,index + 1,elementData , index ,numMove);
        }
        // 设置原有值
        elementData[--size] = null;
        System.out.println("'"+value+"'"+"已经被移除");
    }

    /**
     * 移除某个包含的元素
     *      移除机制
     *          当该元素不被包含时 退出 否则进入移除机制
     * @param o
     */
    @Override
    public void remove(Object o) {

        //移除线性表中的中间值的null值
        if (o==null){
            for (int i=0;i<size;i++){
                if ((elementData[i])==null){
                    this.remove(i);
                }
            }
        }else {
            // 该元素在线性表中时执行
            int index = this.indexOf(o);
            if (index >= 0){
                for (int i=0;i<size;i++){
                    if (o.equals(elementData[i])){
                        this.remove(i);
                    }
                }
            }else {
                System.out.println("该线性表不包含这个元素");
            }
        }
    }

    /**
     * 线性表新增元素 在末位
     *      操作步骤
     *      1. 判断数组容量是否满足需要
     *      2. 满足需要则直接新增元素 否则 扩容后新增元素
     *      3. 元素个数+1
     * @param t
     */
    @Override
    public void add(T t) {

        /**
         *  因为新增了一个元素 因此下一刻线性表的容量需求为size++
         *  先执行扩容机制 再新增元素
         */
        ensureCapacity(size+1);

        this.elementData[size++] = t;

    }

    /**
     * 在线性表指定的位置插入一个元素
     *
     * @param t
     * @param index
     */
    @Override
    public void add(T t, int index) {
        // 判断index的可用性
        lengthCheck(index);
        // 执行扩容机制 预处理
        ensureCapacity(size+1);

        /**
         *  插入机制步骤
         *        1.插入元素的位置空出来
         *        2.该元素的后面的元素整体后移一位
         *        3.将新元素插入指定的位置
         */
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = t;

        size++;
    }

    /**
     *  将两个线性表合并
     *      合并机制步骤
     *          1.确定原来的容量是否满足需要
     *          2.不满足执行扩容机制
     *          3.将线性表合并
     * @param t
     */
    @Override
    public void addAll(LinearList<T> t) {

        int numNew = t.size();

        // 因为顶层设计不同 因此用循环的方式将值取出来
        Object[] objects = new Object[numNew];
        for ( int i = 0;i<numNew;i++){
            objects[i] = t.get(i);
        }
        // 执行扩容机制 预处理
        ensureCapacity(size+numNew);

        // 合并线性表
        System.arraycopy(objects, 0, elementData, size, numNew);

        size = size + numNew;
    }

    /**
     *
     * 该方法是容量平衡机制
     *     1. 容量不能频繁的改变
     *     2. 容量要能满足需要
     *
     * @param requiredCapacity 需求容量大小
     */
    private void ensureCapacity(int requiredCapacity) {
        // 如果需求容量大于当前容量
        if (requiredCapacity > elementData.length){
            // 执行扩容机制
            expandCapacity(elementData.length);
        }
    }

    /**
     * 扩容方法
     *      扩容机制，对旧的线性表进行扩容
     *      扩容每次为当前容量的30%
     */
    private void expandCapacity(int length) {

        // 当表长度为0时 设置为默认长度
        if (length == 0){
            this.elementData = new  Object[DEFAULT_CAPACITY];
        }else {
            // 扩容机制为当前的30%
            int oldCapacity = elementData.length;
            // 使用向上取整方法 扩容30%
            int newCapacity = (int) (oldCapacity + Math.ceil(oldCapacity * 0.3));
            if (newCapacity - length < 0){
                newCapacity = length;
            }
            // 扩容时由中间变量暂时接管表元素
            A_ELEMENTDATA = elementData;
            // 重新设置容量大小
            elementData = new Object[newCapacity];
            // System.arraycopy()扩容的关键
            // src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度
            System.arraycopy(A_ELEMENTDATA,0,elementData ,0,size);
        }
    }

    /**
     * 用来序列化的方法
     * @param s
     * @throws java.io.IOException
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{

        s.defaultWriteObject();

        s.writeInt(size);

        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }
    }

    /**
     * 用来序列化的方法
     * @param s
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = new Object[1];

        s.defaultReadObject();

        s.readInt();

        if (size > 0) {
            Object[] a = elementData;

            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }
}

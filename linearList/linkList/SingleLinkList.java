package collection.linearList.linkList;

import collection.linearList.LinearList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 *  线性表链式存储结构之单链表实现类
 *  在前面学习和实现了线性表顺序存储结构的实现
 *  这种表的优点是能够快速的查找和删除对应的元素
 *  但是有个问题 每次在新增元素时
 *  都需要将后续的元素进行后移
 *  这个过程很慢因此是否有新的解决方案？
 *  以下的链式存储结构
 *  使得所有的数据存储在内存中随机存留
 *  实现了高效的增删，相较于顺序存储结构
 *  它的问题是在删除和查找时，需要遍历链表
 *  因此比较耗时，总得来说，各有利弊.
 * ----------------------------
 *
 * @data2021/8/27,12:32
 * @authorsutinghu
 */
public class SingleLinkList<T>
        extends AbstractLinkList<T>
        implements LinearList<T>, Serializable {

    /**
     * 当前链表的元素个数
     */
    transient int size = 0;

    transient static final int INDEX_ZERO = 0;
    /**
     * 定义一个头节点
     */
    transient Node<T> firstNode;

    /**
     * 定义一个尾节点
     */
    transient Node<T> lastNode;

    /**
     *  静态私有内部类——本来仅仅服务于链式存储结构中的单链模式
     *  1.用来表示本类中的节点特征
     *  2.因为这是一个单链表因此节点类只需要一个数据域和指针域
     *  3.这是单链模式下的所有节点的模板类
     */
    private static class Node<T> {

        T item;// 数据域

        Node<T> next; // 指针域

        // 构造方法
        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }

    }

    /**
     * 创建一个判断头节点是否为空的方法
     *      该方法的作用是
     *      1. 链表是否为空
     *      2. 链表能否正常使用
     * 为什么要用final
     *      1.final 修饰的局部变量必须使用之前被赋值一次才能使用
     *      2.final 修饰的成员变量在声明时没有赋值的叫“空白 final 变量”
     *      3.空白 final 变量必须在构造方法或静态代码块中初始化
     *
     *     也就是代码局部变量做了优先级处理
     *
     * @return
     */
    public T getFirst(){
        final Node<T> first = firstNode;
        // 如果头节点为空 返回异常
        if ( first == null){
            throw new NoSuchElementException();
        }
        return first.item;
    }

    /**
     * 判断是否超出索引范围
     * @return
     */
    private boolean isElementIndex(int index){
        // 为什么要小于size 大于等于0 因为索引设计为从下标0开始
        return (index < size && index >=0);
    }

    private void checkElementIndex(int index){
        if (!isElementIndex(index)){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 元素位置范围
     */
    private boolean isPositionIndex(int index){
        return (index <= size && index >=0);
    }



    /**
     * 检查索引是否超出边界的具体方法
     */
    private void checkPositionIndex(int index){
        if (!isPositionIndex(index)){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     *  索引超出边界的异常报错
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index){
        return "index="+index + ",索引超出链表元素数范围size=" + size;
    }

    /**
     * 构造器方法
     */
    public SingleLinkList(){

    }

    /**
     * 移除链表中的一组元素
     * @param t
     */
    @Override
    public void removeAll(LinearList<T> t) {
        for (int i = 0;i<t.size();i++){
            T x = t.get(i);
            if (contains(x)){
                remove(x);
            }
        }
    }

    /**
     * 判断链表是否包含某个元素
     * 并返回包含元素的节点
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {

        T x;
        for (int i=0;i<size;i++){
            x=get(i);
            if (x == o){
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断链表是否包含某个元素
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1 ? true :false ;
    }

    /**
     * 获取当前链表的元素个数
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 获取链表指定位置的元素
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        return node(index).item;
    }

    /**
     * 移除链表指定位置的元素
     * @param index
     */
    @Override
    public void remove(int index) {
        // 检查索引范围
        checkElementIndex(index);
        unlink(index);
    }

    /**
     * 使用该方法断开链表中间的值让其他地方的重新连在一起
     *      unlink逻辑步骤：
     *          1. 输入要移除的节点
     *          2. 获取当前节点的数据域和指针域的信息
     *          3. 排查当前节点的两种特殊情况——移除的节点是首节点和尾节点
     *          4. 中间节点直接移除
     *      移除策略：
     *          1. 如果元素的数量只有一个
     *              移除当前元素的操作实质是将头节点和尾节点设为空
     *          2. 如果元素的数量有两个
     *              移除当前元素的操作实质是将头节点或尾节点设为空
     *          3. 如果元素的数量有三个及以上
     *              移除当前元素的操作取前节点的指针域
     * @param index
     * @return
     */
    T unlink(int index) {

        // 检查该索引地址是否有效
        checkElementIndex(index);
        final Node<T> nowNode = node(index);
        final T element = nowNode.item;
        final Node<T> next = nowNode.next;
        final Node<T> prevNode = nodePrev(index);

        // 头节点情况
        if (prevNode == null && size < 2 ){
            // 该节点是头节点
            firstNode = null;
        } else if (prevNode == null && size >=2){

            firstNode = next;

        }else {
            prevNode.next = next;
        }

        // 尾节点情况
        if (next == null){
            // 被移除的是最后一个节点
            prevNode.next = null;
            lastNode = prevNode;
        }else {
            if (prevNode!=null){
                prevNode.next = next;
            }
        }

        size--;

        return element;
    }

    /**
     * 移除链表包含的某个元素
     * @param o
     */
    @Override
    public void remove(Object o) {
        boolean flag = contains(o);
        while (flag){
            if (indexOf(o)!=-1){
                unlink(indexOf(o));
            }
            flag = contains(o);
        }
    }

    /**
     * 在指定位置添加一个元素
     * 仅支持具有相同数据类型和线性表存储结构的数据
     * @param t
     */
    @Override
    public void add(T t, int index) {

        // 检查
        checkPositionIndex(index);
        // 如果插入的位置是头节点的话
        if ( INDEX_ZERO == index ) {
            linkToHead(t);
            // 如果插入的位置是最后一个节点的话
        }else if (index == (size-1)) {
            linkToTail(t);
        }else {
            linkBefore(t,node(index));
        }

    }

    /**
     *  这里没有添加修饰符 外部类无法引用该方法
     *  子类只有在同一个包下时 才可引用该方法
     *  为什么要这么搞？
     *      我也不知道 源码是这么搞的
     *  实现逻辑：
     *      1.
     */
    Node<T> node(int index) {
        // 检查索引范围
        checkElementIndex(index);

        // 这行代码决定了链表能从index = 0 开始索引
        Node<T> x = firstNode;

        // 单链表需要从头遍历链表所有元素 直到指定元素
        for (int i = 0;i < index ;i++){
               x = x.next;
        }

        return x;
    }

    /**
     * 获取指定节点的直接前驱
     * @param index
     * @return
     */
    Node<T> nodePrev(int index){

        checkElementIndex(index);

        if (index == 0){
            return null;
        }else {
            if (size<=1){
                return null;
            }else {
                return node(index-1);
            }
        }
    }

    /**
     *  在某个节点前插入一个元素
     *      实现逻辑：
     *          1. 节点元素next是否为空 为空时判断为最后一个节点，
     *          直接接在后面（该逻辑在该方法调用前实现）
     *          2. 节点元素next不为空时，采用中间变量接手该元素，进行串联
     *
     * @param t
     */
    public void linkBefore(T t,Node<T> tNode){

        // 采用中间变量接手下一个元素
        final Node<T> next = tNode;

        // 新元素生成节点
        final Node<T> newNode = new Node(t,next.next);

        next.next = newNode;

        size ++;

    }

    /**
     * 添加一组元素
     * 仅支持具有相同数据类型和线性表存储结构的数据
     * @param t
     */
    @Override
    public void addAll(LinearList<T> t) {
        for (int i =0 ; i < t.size();i++){
            this.linkToTail(t.get(i));
        }
    }

    /**
     * 重写抽象父类中的方法
     */
    @Override
    public void clearTozero(){

        // 只有当头节点为空时 认为链表为空
        while (firstNode != null) {
            // 取一个中间变量存放首节点 采用倒序方式清空链表
            Node<T> x = firstNode;
            int count = 1;
            System.out.println("开始清除第"+count+"个元素,值为"+x.item);
            count++;
            Node<T> next = x.next;
            // 清空当前头节点
            x.item = null;
            x.next = null;
            // 当下一个节点不为空时 将下个节点设计为头节点
            firstNode = next;
        }

        firstNode = lastNode = null;
        size = 0;
    }

    /**
     * 链表的头部新增元素
     * @param t
     */
    @Override
    void linkToHead(T t) {

        // 取一个中间变量 存放头节点，将头节点地址放空
        final Node<T> f = firstNode;

        // 新节点实例化 新节点指针指向之前的头节点
        final Node<T> newNode = new Node<>(t,f);

        // 将当前节点设置为头节点
        firstNode = newNode;

        // 如果之前的链是空的 那么当前节点是第一个节点 也是最后一个节点
        if (f == null){

            lastNode = newNode;

        }

        // 当前元素的数量增加一个
        size++;
    }

    /**
     * 链表的尾巴新增元素
     * @param t
     */
    @Override
    void linkToTail(T t) {

        // 取一个中间变量 存入最后一个节点
        final Node<T> l = lastNode;

        // 新节点实例化 前尾节点的指针指向当前元素
        final Node<T> newNode = new Node<>(t,null);

        // 当前节点做为最后一个节点
        lastNode = newNode;

        // 如果尾节点是空的 那么将该节点也作为头节点
        if (l == null){

            firstNode = newNode;

            // 如果不为空 则前尾节点指向当前尾节点
        }else {
            l.next = lastNode;
        }

        // 元素数量增加
        size++;

    }

}

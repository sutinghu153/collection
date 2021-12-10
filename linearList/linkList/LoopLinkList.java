package collection.linearList.linkList;

import collection.linearList.LinearList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * 线性表链式存储结构之循环链表实现类
 * 其核心是将尾节点指向头节点
 * @data2021/8/30,13:33
 * @authorsutinghu
 */
public class LoopLinkList<T>
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
     * 构造器方法
     */
    public LoopLinkList(){

    }
    /**
     *  静态私有内部类——本来仅仅服务于链式存储结构中的循环模式
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
     * 使用该方法断开链表中间的值让其他地方的重新连在一起 循环链表的唯一区别在于要把尾巴和头连起来
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
            lastNode = null;
        } else if (prevNode == null && size >=2){
            firstNode = next;
            lastNode.next=firstNode;
        }else {
            prevNode.next = next;
        }
        // 尾节点情况
        if (next == null){
            // 被移除的是最后一个节点
            prevNode.next = null;
            lastNode = prevNode;
            prevNode.next=firstNode;
        }else {
            if (prevNode!=null){
                prevNode.next = next;
            }
        }
        size--;
        return element;
    }

    /**
     * 判断是否超出索引范围
     * @return
     */
    private boolean isPositionIndex(int index){
        // 为什么要小于size 大于等于0 因为索引设计为从下标0开始
        return index <= size && index >=0;
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
     * 链表的头部新增元素
     * 循环链表和单链表的区别之一在于头节点永远被尾节点指向
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

        lastNode.next = firstNode;

        if (f == null){
            lastNode = newNode;
        }
        // 当前元素的数量增加一个
        size++;
    }

    @Override
    void linkToTail(T t) {
        // 取一个中间变量 存入最后一个节点
        final Node<T> l = lastNode;
        // 新节点实例化 前尾节点的指针指向当前元素
        final Node<T> newNode = new Node<>(t,firstNode);
        // 如果尾节点是空的 那么将该节点也作为头节点
        if (l == null){
            firstNode = newNode;
            // 如果不为空 则前尾节点指向当前尾节点
        }else {
            l.next = newNode;
        }
        // 当前节点做为最后一个节点
        lastNode = newNode;
        // 元素数量增加
        size++;
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

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1 ? true :false ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return node(index).item;
    }

    /**
     * 从任意一个位置开始 输出所有的元素
     * @return
     */
    public Object[] toArray(int index){
        checkElementIndex(index);
        Object[] array = new Object[size];
        Node<T> node = node(index);
        for (int i =0; i<size;i++){
            array[i] = node.item;
            node = node.next;
        }
        return array;
    }

    @Override
    public void remove(int index) {
        // 检查索引范围
        checkElementIndex(index);
        unlink(index);
    }

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

    @Override
    public void removeAll(LinearList<T> t) {
        for (int i = 0;i<t.size();i++){
            T x = t.get(i);
            if (contains(x)){
                remove(x);
            }
        }
    }

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

    @Override
    public void addAll(LinearList<T> t) {
        for (int i =0 ; i < t.size();i++){
            this.linkToTail(t.get(i));
        }
    }
}

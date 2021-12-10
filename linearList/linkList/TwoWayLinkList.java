package collection.linearList.linkList;

import collection.linearList.LinearList;

import java.io.Serializable;

/**
 * 线性表链式存储结构之双向表实现类
 * 其核心是每个节点都同时是下一节点的前驱和后继
 * @data2021/8/30,15:02
 * @authorsutinghu
 */
public class TwoWayLinkList<T>
        extends AbstractLinkList<T>
        implements LinearList<T> , Serializable {


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


    private static class Node<T> {
        // 数据域
        T item;
        // 后继节点指针域
        Node<T> next;
        // 前驱节点指针域
        Node<T> prev;
        // 构造方法
        Node(T element, Node<T> next,Node<T> prev) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

    }

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

    T unlink(Node<T> x) {

        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            firstNode = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            lastNode = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    @Override
    void linkToHead(T t) {
        // 取一个中间变量 存放头节点，将头节点地址放空
        final Node<T> f = firstNode;
        // 新节点实例化 新节点指针指向之前的头节点
        final Node<T> newNode = new Node<>(t,f,null);
        // 将当前节点设置为头节点
        firstNode = newNode;
        // 如果之前的链是空的 那么当前节点是第一个节点 也是最后一个节点
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
        final Node<T> newNode = new Node<>(t,null,l);
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

    void linkBefore(T e, Node<T> succ) {

        final Node<T> pred = succ.prev;
        final Node<T> newNode = new Node<>(e, succ,pred);
        succ.prev = newNode;
        if (pred == null){
            firstNode = newNode;
        } else{
            pred.next = newNode;
        }
        size++;
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
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = firstNode; x != null; x = x.next) {
                if (x.item == null){
                    return index;
                }
                index++;
            }
        } else {
            for (Node<T> x = firstNode; x != null; x = x.next) {
                if (o.equals(x.item)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public void remove(int index) {
        // 检查索引范围
        checkElementIndex(index);
        unlink(node(index));
    }

    @Override
    public void remove(Object o) {
        boolean flag = contains(o);
        while (flag){
            if (indexOf(o)!=-1){
                unlink(node(indexOf(o)));
            }
            flag = contains(o);
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

    /**
     * 判断是否超出索引范围
     * @return
     */
    private boolean isPositionIndex(int index){
        // 为什么要小于size 大于等于0 因为索引设计为从下标0开始
        return index <= size && index >=0;
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
     *  索引超出边界的异常报错
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index){
        return "index="+index + ",索引超出链表元素数范围size=" + size;
    }

}

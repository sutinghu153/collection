package collection.linearList.linkList;

/**
 * 本类是栈的实现类
 *  为什么要有单链表成员变量
 *  *      用单链表实现栈的方法
 *  栈是单链表的特殊形式
 *      1. 只在链尾进行增加和删除
 *      2. 只能操作一个元素
 *  为什么要用抽象类
 *      1. 使用抽象类
 * @data2021/8/31,12:32
 * @authorSUTINGHU
 */
public class Stack<T>{

    private SingleLinkList<T> stack;

    /**
     * 构造方法
     */
    Stack(){
        stack = new SingleLinkList<>();
    }

    /**
     * 查看某个元素在栈中的位置
     * @param t
     * @return
     */
    public int search(T t){
        return stack.indexOf(t);
    }

    /**
     * 查看栈顶元素但不移除
     * @return
     */
    public T peek(){
        if (stack.size == 0){
            return null;
        }
        T value = stack.get(stack.size-1);
        return value;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    /**
     *  压栈方法
     * @param t
     */
    public void push(T t){
        stack.linkToTail(t);
    }

    /**
     *  弹栈方法
     * @return
     */
    public T  pop(){
        stack.getFirst();
        T value = stack.get(stack.size-1);
        stack.remove(stack.size-1);
        return value;
    }

}

package collection.linearList.linkList;

/**
 *  本类是队列的实现类
 *  什么是队列？
 *      1. 队列是特殊的单链表
 *      2. 队列是先进先初的单链表
 *      3. 队列的元素只能从一头按顺序添加 在另一头按顺序移出
 *   为什么要用单链表作为成员变量？
 *      因为队列是特殊的单链表，因此直接使用单链表作为元素进行处理
 *  队列的方法有哪些？
 *      add         增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 *      remove      移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 *      element     返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 *      offer       添加一个元素并返回true       如果队列已满，则返回false
 *      poll        移除并返问队列头部的元素    如果队列为空，则返回null
 *      peek        返回队列头部的元素             如果队列为空，则返回null
 *      put         添加一个元素                      如果队列满，则阻塞
 *      take        移除并返回队列头部的元素     如果队列为空，则阻塞
 * @data2021/8/31,12:53
 * @authorsutinghu
 */
public class Queue<T> {

    private SingleLinkList<T> queue;

    Queue(){
        queue = new SingleLinkList<>();
    }

    public void put(T t){
        queue.linkToTail(t);
    }

    public T peek(){
        if (queue.size == 0){
            return null;
        }
        return queue.get(queue.size-1);
    }

    public T poll(){
        if (queue.size == 0){
            return null;
        }
        T value = queue.get(queue.size-1);
        queue.remove(queue.size-1);
        return value;
    }
    /**
     * 添加元素
     * @return
     */
    public boolean offer(T t){
        queue.linkToTail(t);
        return true;
    }

    /**
     * 添加元素
     * @param t
     */
    public void add(T t){
        queue.linkToTail(t);
    }

    public void remove(){
        queue.getFirst();
        queue.remove(queue.size-1);
    }

    public T element(){
        queue.getFirst();
        return queue.get(queue.size-1);
    }
}

package collection.linearList.linkList;

import collection.linearList.LinearList;
import collection.linearList.AbstractLinearList;

import java.io.Serializable;

/**
 * 这个抽象类用来表示所有的线性表的链式表的共有结构
 *      因为链式表分为：单链表、双向链表、循环链表
 * 为什么要实现 LinearList?
 *      因为LinearList是用来描述线性表的共有特征的
 *      因此需要实现它，遵循它的规则
 *  为什么要实现Serializable？
 *      这是可序列化的标记
 *  为什么要继承AbstractLinearList？
 *      线性表的共有方法部分是可以共同使用的
 *      比如isempty
 *
 * @data2021/8/27,11:44
 * @authorsutinghu
 */
public abstract class AbstractLinkList<T>
        extends AbstractLinearList<T>
        implements LinearList<T>, Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 不管是单链表、双向链表、还是循环链表
     * 本身的容量都是随机的，有多少元素就有多大容量
     * 因此该方法写在抽象类中，以进行复用
     * @return
     */
    @Override
    public int length() {
        return size();
    }

    /**
     * 不管是单链表、双向链表、还是循环链表
     * 本身的清空就是将所有节点都移除的过程
     * 因此都是从头节点开始 删除所有的子节点
     * 为了实现从【单链表】-【循环链表】-【双向链表】的过程
     * 此方法定义默认的输出
     * 具体实现方法有继承的各个子类独自实现
     */
    @Override
    public void clearTozero(){
        System.out.println("清空当前表的操作没有被实现");
    }

    /**
     *  该方法被定义为清空链表的元素 但对容器不改变
     *  这在链表中无法被认同
     *  只有静态链表中有此实现方式
     *  因此该方法不能在链表中被使用
     */
    @Override
    public void clear() {
        System.out.println("该方法在链表中限定使用，请使用[clearTozero]");
    }

    /**
     * 从链表的头部增加新元素
     * @param t
     */
    abstract void linkToHead(T t);

    /**
     * 从链表的尾部增加新元素
     * @param t
     */
     abstract void linkToTail(T t);

    /**
     * 在链表添加一个元素
     * 仅支持具有相同数据类型和线性表存储结构的数据
     * @param t
     */
    @Override
    public void add(T t) {

        linkToTail(t);// 从尾巴增加

    }

    /**
     * 向头部新增元素
     * @param t
     */
    public void addHead(T t){
        linkToHead(t);
    }


}

package collection.linearList;

import java.io.Serializable;

/**
 *
 * 这是线性表的接口
 * 线性表的接口定义了线性表所需要遵循的规则
 * 线性表：零个或多个数据元素的有限序列
 * 线性表规约
 *
 *      1.有序性、所有的元素之间存在链接关系，元素有自己的位置
 *      2.按一定顺序存放的
 *      3.第一个元素除外，所有的元素有且仅有一个前驱
 *      4.最后一个元素除外，所有的元素有且仅有一个后继
 *      5.存放的元素类型确定
 *
 * 顺序存储线性表操作方式
 *
 *      1.新表创建初始化线性表
 *      2.表中的元素的数量
 *      3.表是否为空
 *      4.获取表中的某个索引位置的元素
 *      5.在某个位置插入新的元素
 *      6.移除某个位置的元素
 *      7.清空线性表
 *      8.移除某个元素
 *      9.插入多个元素
 *      10.是否包含某个元素
 *      11.是否包含某个元素 包含的话在哪个位置
 *      12.移除另一个线性表中的元素
 *
 * @data2021/8/24,10:40
 * @authorsutinghu
 */
public interface LinearList<T> extends Serializable{

    /**
     * 12.移除另一个线性表中的元素
     * @param t
     */
    void removeAll(LinearList<T> t);
    /**
     *  11.是否包含某个元素 包含的话在哪个位置
     * @param o
     * @return
     */
    int indexOf(Object o);
    /**
     * 10.是否包含某个元素
     * @return
     */
    boolean contains(Object o);
    /**
     * 2.表中的元素的数量
     * @return
     */
    int size();

    /**
     * 获取数组的容量
     * @return
     */
    int length();

    /**
     * 3.表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     *  7.清空线性表
     */
    void clear();

    /**
     * 归零
     *   线性表的元素都去掉 并且 将线性表长度设置为0
     */
    void clearTozero();
    /**
     * 4.获取表中的某个索引位置的元素
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 6.移除某个位置的元素
     * @param index
     */
    void remove(int index);

    /**
     * 8.移除某个元素
     * @param o
     */
    void remove(Object o);

    /**
     * 5.在某个位置插入新的元素
     * @param t
     */
    void add(T t , int index);

    /**
     * 在线性表的末位新增一个新的元素
     * @param t
     */
    void add(T t);
    /**
     * 9.插入多个元素
     * @param t
     */
    void addAll(LinearList<T> t);

}

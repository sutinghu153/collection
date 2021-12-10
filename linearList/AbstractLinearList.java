package collection.linearList;

import java.io.Serializable;

/**
 * 这是线性表的抽象类
 * 由于顺序存储结构和链式存储结构的线性表有通用的特性
 * 并且顺序存储结构和链式存储结构是线性表的两个类型 共有的方法并不能完全解释这两个对象
 * 因此设计一个抽象类，共享方法
 * @data2021/8/24,11:14
 * @authorsutinghu
 */
public abstract class AbstractLinearList<T> implements LinearList<T>, Serializable {

    /**
     * 构造方法 用 protected 修饰 不被调用
     */
    protected AbstractLinearList(){}

    /**
     * 3.表是否为空
     * 该方法为所有线性表的通用方法
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() != 0 ? false : true;
    }

}

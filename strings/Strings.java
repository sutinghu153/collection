package collection.strings;

import com.sun.istack.internal.NotNull;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Arrays;

/**
 *  本类为串的实现类，对标Java中的String
 *  什么是串？
 *      串是指一个或多个序列的字符组成的有限序列，又被称为字符串
 *  为什么要实现Serializable？
 *      本来是可序列化的
 *  为什么要实现Comparable？
 *      Comparable是一个比较接口 规范所有的比较方法都使用一个入口
 *  为什么要实现CharSequence？
 *       charSequence描述的就是一个字符串，string等都以多个字符组成的串为处理单元进行处理
 *       但是这些处理单元的基本单元是字符 该接口就是规范了对字符的操作
 *       Java中的String、StringBuilder和StringBuffer都实现了该接口，该接口是统一的接口
 * @data2021/8/31,13:11
 * @authorsutinghu
 */
public class Strings
        implements Serializable,
        Comparable<Strings>,
        CharSequence {

    /**
     * 存储我们在处理字符过程中产生的字符集合
     */
    private final char value[];

    // 缓存字符串的哈希代码
    private int hash;

    // 兼容jdk 1.0.2 之后的所有版本
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     *      很多人不知道这字段是干嘛的，其实和transient一样都是控制字段的序列化的，
     *      * 只不过transient修饰的字段不被序列化，而serialPersistentFields数组里
     *      * 的字段需要序列化，如果某个字段在serialPersistentFields数组里，且被transient
     *      * 修饰，则以serialPersistentFields为准，serialPersistentFields优先级高于
     *      * transient。
     *      * 这里是一个空数组，所以String类的所有字段都不会序列化
     */
    private static final ObjectStreamField[] serialPersistentFields =
            new ObjectStreamField[0];
    /**
     * 构造方法：开局就给你一个没有大小的字符
     */
    public Strings() {
        this.value = new char[0];
    }

    /**
     * 构造方法：把你用过的源字符串传进来 我给你初始化
     * @param original
     */
    public Strings(@NotNull Strings original) {
        this.value = original.value;
        this.hash = original.hash;
    }

    public boolean isEmpty() {
        return value.length == 0;
    }

    /**
     * 构造方法：给我一个字符数据 我也能把它处理成为字符串
     * @param value
     */
    public Strings(@NotNull char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public Strings(@NotNull char value[], int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }

        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.value = Arrays.copyOfRange(value, offset, offset+count);
    }

    /**
     * 获得字符串的长度
     * @return
     */
    @Override
    public int length() {
        return value.length;
    }

    /**
     * 获得某个位置的字符
     * @param index
     * @return
     */
    @Override
    public char charAt(int index) {
        if ((index < 0) || (index >= value.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }

    /**
     * 获取字符串
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public Strings substring(int beginIndex, int endIndex) {

        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > value.length) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return ((beginIndex == 0) && (endIndex == value.length)) ? this
                : new Strings(value, beginIndex, subLen);
    }

    /**
     *  将一个开始和结束之间的字符返回出去
     * @param start
     * @param end
     * @return
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return this.substring(start, end);
    }

    /**
     * 两个字符串比较大小
     *      字符一样时越长越大
     *      字符不一样时，第一个字符越先越大
     *      使用的是自然朴素的模糊比较算法
     * @param o
     * @return
     */
    @Override
    public int compareTo(Strings o) {
        int len1 = value.length;
        int len2 = o.value.length;
        int lim = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = o.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * 跟另一个字符串作比较
     *      如果传入是值不是strings类型的值
     *          直接判断地址是否一样 一样时返回true
     *      如果传入的值是strings 则按字符比较是否一样
     *
     * @param anObject
     * @return
     */
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Strings) {
            Strings anotherString = (Strings)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i]){
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

}

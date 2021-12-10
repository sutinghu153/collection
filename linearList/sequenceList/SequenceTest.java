package collection.linearList.sequenceList;

import collection.linearList.LinearList;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * @data2021/8/24,12:49
 * @authorsutinghu
 */
public class SequenceTest {

    public static void main(String[] args) {
        List list = new ArrayList();
    }

    /**
     * 测试线性表的remove方法
     */
    public static void test4(){
        LinearList<String> linearList = new SequenceList<>();
        linearList.add("这是第1条");
        linearList.add("这是第2条");
        linearList.add("这是第3条");
        linearList.add("这是第4条");
        System.out.println("-----------------------原始数据-------------------------");
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }
        System.out.println("-----------------------移除下标为0后的数据-------------------------");
        linearList.remove(0);
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }
        System.out.println("-----------------------移除某个指定的元素-------------------------");
        linearList.remove("这是第4条");
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }
        System.out.println("-----------------------新增待移除数据-------------------------");
        LinearList<String> linearListNew = new SequenceList<>();
        linearListNew.add("这是第2条");
        linearListNew.add("这是第3条");
        System.out.println(linearListNew.size());
        System.out.println(linearListNew.length());
        for (int i = 0;i< linearListNew.size(); i++){
            System.out.println(linearListNew.get(i));
        }
        System.out.println("-----------------------整体移除后的数据-------------------------");
        linearList.removeAll(linearListNew);
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }
    }
    /**
     * 测试线性表的add方法
     */
    public static void test3(){
        LinearList<String> linearList = new SequenceList<>();
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        linearList.add("这是一");
        linearList.add("这是二");
        System.out.println(linearList.size());
        linearList.add("插入一个三",0);
        System.out.println(linearList.get(0));
        System.out.println(linearList.get(1));
        System.out.println(linearList.get(2));
        LinearList<String> linearListNew = new SequenceList<>();
        linearListNew.add("新表的值1");
        linearListNew.add("新表的值2");
        linearList.addAll(linearListNew);
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }
        linearList.remove(0);
        for (int i = 0;i< linearList.size(); i++){
            System.out.println(linearList.get(i));
        }

    }

    /**
     * 测试线性表的类型和扩容机制
     */
    public static void test2(){
        LinearList<String> linearList = new SequenceList<>(0);
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        linearList.add("i love you");
        System.out.println(linearList.size());
        System.out.println(linearList.length());
        System.out.println(linearList.get(0));
        System.out.println(linearList.isEmpty());
    }

    /**
     * 测试线性表的容量及元素个数机制
     */
    public static void test1(){
        LinearList linearList = new SequenceList();
        System.out.println("元素个数"+linearList.size());
        System.out.println("是否空"+linearList.isEmpty());
        System.out.println("清空&&归零前"+linearList.length());

        linearList.clear();
        System.out.println("清空"+linearList.length());
        linearList.clearTozero();
        System.out.println("归零"+linearList.length());
    }
}

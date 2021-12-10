package collection.linearList.linkList;

import collection.linearList.LinearList;

/**
 * @data2021/8/27,13:59
 * @authorsutinghu
 */
public class SingleLinkTest {


    public static void main(String[] args) {
        SingleLinkList<String> first = new SingleLinkList<>();
       String string = new String();
    }


    /**
     * 测试链表的包含 和 位置
     */
    public static void conAndindexTest(){
        LinearList<String> first = new SingleLinkList<>();
        first.add("first 1");
        first.add("first 2");
        first.add("first 2");
        first.add("first 2");
        first.add("first 3");
        System.out.println("链表是否包含"+"first 1"+first.contains("first 1"));
        System.out.println("链表是否包含"+"first 5"+first.contains("first 5"));
        System.out.println("链表中的第一个"+"first 2"+"位于"+first.indexOf("first 2"));
        System.out.println("链表中的第一个"+"first 3"+"位于"+first.indexOf("first 3"));
        System.out.println("链表中的第一个"+"first 5"+"位于"+first.indexOf("first 5"));
    }

    /**
     * 测试链表的移除
     */
    public static void removeTest2(){
        LinearList<String> first = new SingleLinkList<>();
        first.add("first 1");
        first.add("first 2");
        first.add("first 2");
        first.add("first 2");
        first.add("first 3");
        System.out.println(first.size());
        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
        // 移除下标
        first.remove(0);
        System.out.println("移除下标:"+first.size());

        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
        // 移除包含元素
        first.remove("first 2");
        System.out.println("移除包含元素:"+first.size());
        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
         //移除另一个链表中包含的元素
        LinearList<String> second = new SingleLinkList<>();
        second.add("second 1");
        second.add("second 2");
        second.add("second 3");
        second.add("first 2");
        second.add("first 3");
        System.out.println(second.size());
        for (int i = 0;i<second.size();i++){
            System.out.println(second.get(i));
        }
        first.removeAll(second);
        System.out.println(first.size());
        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
    }


    /**
     * 测试两个链表合并
     */
    public static void addTest2(){
        LinearList<String> first = new SingleLinkList<>();
        first.add("first 1");
        first.add("first 2");
        first.add("first 3");
        System.out.println(first.size());
        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
        LinearList<String> second = new SingleLinkList<>();
        second.add("second 1");
        second.add("second 2");
        second.add("second 3");
        System.out.println(second.size());
        for (int i = 0;i<second.size();i++){
            System.out.println(second.get(i));
        }
        first.addAll(second);
        System.out.println(first.size());
        for (int i = 0;i<first.size();i++){
            System.out.println(first.get(i));
        }
    }



    /**
     * 测试移除和清空
     */
    public static void removeTest(){
        LinearList<String> linearList = new SingleLinkList<>();
        linearList.add("first");
        linearList.add("second");
        linearList.add("third");
        linearList.add("forth");
        System.out.println(linearList.size());
        System.out.println(linearList.get(3));
        linearList.clearTozero();
        System.out.println(linearList.size());
        System.out.println(linearList.get(0));
    }


    /**
     * 链表新增元素测试
     */
    public static void addTest(){
        SingleLinkList<String> linearList = new SingleLinkList<>();
        System.out.println(linearList.size());
        linearList.add("first element");
        System.out.println(linearList.size());
        System.out.println(linearList.get(0));
        linearList.add("second to first",0);
        System.out.println(linearList.size());
        System.out.println(linearList.get(0));
        System.out.println(linearList.get(1));
        linearList.add("second to second",0);
        System.out.println(linearList.size());
        System.out.println(linearList.get(0));
        System.out.println(linearList.get(1));
        System.out.println(linearList.get(2));
        System.out.println(linearList.get(3));
    }


}

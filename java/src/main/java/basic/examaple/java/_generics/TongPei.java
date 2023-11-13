package basic.examaple.java._generics;

import java.util.ArrayList;
import java.util.List;

public class TongPei {
    public static void main(String[] args) {

        /**
         * 1、泛型并没有继承性，也就是说ArrayList<String> 不能够向上转型为 ArrayListDetail<Object>
         */
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Object> listObj = new ArrayList<Object>();
        //listObj = list;

        /**
         * 2、<?> 可以接受任意的泛型类
         */
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        printCollection1(list2);
        printCollection1(list3);

        /**
         * 3、<? extends AA>可以接受 AA 或者 AA 子类泛型类
         */

        // 这里将报错
        //ArrayListDetail<String> list4 = new ArrayListDetail<>();
        //printCollection2(list4);

        // 这里可以正常执行，因为List<BB> 在List<? extends AA>的范围内
        ArrayList<BB> list5 = new ArrayList<>();
        printCollection2(list5);

        /**
         * 4、<? super AA> 支持 AA 类以及 AA 类的父类，不限于直接父类
         */
        //这里将报错
        //ArrayListDetail<BB> list6 = new ArrayListDetail<>();
        //printCollection3(list6);

        //这个是支持的
        ArrayList<AA> list7 = new ArrayList<>();
        printCollection3(list7);

        //这个也是支持的
        ArrayList<Object> list8 = new ArrayList<>();
        printCollection3(list7);
    }

    /**
     * 任意的泛型类型都可以接受
     * @param c
     */
    public static void printCollection1(List<?> c) {
        for (Object o : c) {
            System.out.println(c);
        }
    }

    /**
     * 可以接受 AA 或者 AA 子类
     * @param c
     */
    public static void printCollection2(List<? extends AA> c) {
        for (AA o : c) {
            System.out.println(c);
        }
    }

    /**
     * 支持 AA 类以及 AA 类的父类，不限于直接父类
     * @param c
     */
    public static void printCollection3(List<? super AA> c) {
        for (Object o : c) {
            System.out.println(c);
        }
    }
}

class AA {

}

class BB extends AA {

}

class CC extends BB {

}
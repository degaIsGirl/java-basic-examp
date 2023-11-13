package basic.examaple.java._generics;

/**
 * 我们常用的占位标识符：K T V (是不是很熟悉)
 */

/**
 * 1、泛型接口
 *
 * @param <T>
 */
interface AddAble<T> {
    T add(T value);
}

/**
 * 2、泛型类
 *
 * @param <T>
 */
class MyArrayList<T> implements AddAble<T> {

    /**
     * 注意这个只是使用到了泛型，而并不是泛型方法
     *
     * @param value
     * @return
     */
    @Override
    public T add(T value) {
        return value;
    }

    /**
     * 3、这个是泛型方法<K, V>
     * 泛型方法可以自定义类型，也可以使用类中的类型
     *
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> V add(K key, V value) {
        return value;
    }
}

public class GenericDetail {
    /**
     * 4、泛型只能够是类类型，换句话说基本类型是不支持的
     */
    //正确
    MyArrayList<Integer> myArrayList = new MyArrayList<>();

    //错误, 不支持基本类型
    //MyArrayList<int> myArrayList2 = new MyArrayList<>();

    /**
     * 5、泛型类型的确认时机？
     */
    /**
     * 5.1、泛型接口的确认时机？
     * 泛型接口在实现他的泛型类，或是继承他的泛型接口中确认
     */
    //继承他的泛型接口中确认
    interface AddMap extends AddAble<String> {
    }

    //实现他的泛型类中确认
    class MyMap implements AddAble<String> {
        @Override
        public String add(String value) {
            return null;
        }
    }

    /**
     * 5.2、泛型类的确认时机？
     */
    //在实例化的时候去确定
    MyArrayList<String> myArrayList3 = new MyArrayList<>();

    public static void main(String[] args) {
        /**
         * 5.3、泛型方法的确认时机？
         */
        //在调用对应方法的时候确认
        MyArrayList<String> myArrayList4 = new MyArrayList<>();
        Integer res = myArrayList4.add("fuck", 100);

        /**
         * 如果我们没有指定
         */
    }

    /**
     * 6、泛型不能够用在静态的属性和方法中
     */
    class AddSet <T>{
        /**
         * 下面这样的写法是不允许的，其实下面的完整写法是public static final T data,
         * 因为我们可以直接使用AddSet.data 的形式去获取属性，而现在java是不支持在调用静态属性的时候，传递类型的，
         * 那么这个属性是什么类型是不确定的，这样jvm就无法为其申请内存，所以编译器直接不允许
         */
        // T data;

        /**
         * 静态方法同样不可以使用泛型，原因同泛型属性一样
         */
        // 这样写是会报错的
        /*public static <T> T debug(T ele) {
            return ele;
        }*/

        /**
         * 7、被泛型修饰的属性是不可以在表达式中初始化的，因为这个时候类型还不知道，我们无法给到对应的值
         */
        T anotherData;
    }

}

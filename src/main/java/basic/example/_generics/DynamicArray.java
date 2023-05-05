package basic.example._generics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zero
 */
public class DynamicArray<T> {
    private Integer size = 0;

    private final Integer defaultSize = 10;

    private Object[] data;


    public DynamicArray() {
        this.data = new Object[this.defaultSize];
    }

    private void ensureCapacity(int size) {
        if (this.data.length < size) {
            Integer newLength = this.data.length * 2;
            this.data = Arrays.copyOf(this.data, newLength);
        }
    }

    public void add(T ele) {
        Integer size = this.size;
        this.ensureCapacity(++size);
        this.data[this.size++] = ele;
    }

    public T set(Integer index, T value) {
        T oldValue = (T) this.data[index];
        this.data[index] = value;
        return oldValue;
    }

    public T get(Integer index) {
        return (T) this.data[index];
    }

    public Integer size() {
        return this.size;
    }

    public Integer indexOf(T value) {
        for (int start = 0; start < data.length; start++) {
            if (data[start].equals(value)) {
                return start;
            }
        }

        return -1;
    }

    public void swap(DynamicArray<?> container, int x, int y) {
        Object temp = container.data[x];
        container.data[x] = container.data[y];
        container.data[y] = temp;
    }
}

class TestDynamic {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

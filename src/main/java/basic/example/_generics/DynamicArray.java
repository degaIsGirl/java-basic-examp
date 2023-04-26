package basic.example._generics;

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

}

class TestDynamic {
    public static void main(String[] args) {
        DynamicArray<String> data = new DynamicArray<>();

        for (int start = 0; start <= 10; start++) {
            data.add("ele" + start);
        }

        Stream.of("123");

        Integer index = data.indexOf("ele4");
        System.out.println(index);
    }
}

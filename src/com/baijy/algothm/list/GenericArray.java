package com.baijy.algothm.list;

/**
 * 支持动态扩容的数组
 * @param <T> 指定的反应
 */
public class GenericArray<T> {
    private T[] array;
    private int size;
    private static int DEFAULT_CAPACITY = 16;
    // 默认加载因子
    private static float DEFAULT_LOAD_FACTOR = 0.75f;
    private float loadFactor;

    public GenericArray() {
        this(DEFAULT_CAPACITY);
    }
    public GenericArray(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public GenericArray(int capacity, float loadFactor) {
        array = (T[]) new Object[capacity];
        this.loadFactor = loadFactor;
    }

    private int getCapacity () {
        return array.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("数组下标越界：" + index + "超出大小" + size);
        }
        return array[index];
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void addFirst(T value) {
        add(0, value);
    }

    public void addLast(T value) {
        add(size, value);
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组下标越界：" + index + ", 超出大小" + size);
        }
        if (array.length * loadFactor <= size) {
            resizeCapacity(array.length * 2);
        }
        if (size - index >= 0) {
            // 从index开始的size-index个数据后移一位
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    private void resizeCapacity(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size-1);
    }

    public void removeElment(T value) {
        int index = indexOf(value);
        if (index != -1) {
            remove(index);
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("数组下标越界：" + index + ", 超出大小" + size);
        }
        if (size - index - 1 >= 0) {
            // 将index之后的数据前移一位
            System.arraycopy(array, index + 1, array, index, size - index -1);
        }
        if (array.length/4 * loadFactor >= size) {
            resizeCapacity(array.length/2);
        }
        size--;
    }

    public static void main(String[] args) {
        GenericArray<String> array = new GenericArray<>();
        array.add(0, "a");
        array.add(1, "b");
        array.add(2, "c");
        array.add(3, "d");
        array.add(4, "e");
        array.add(5, "f");
        array.add(5, "fuck");
        array.add(6, "g");
        array.add(7, "h");
        System.out.println(array.get(5));
    }
}

package ru.gb.arraylist;

import ru.gb.LList;

import java.util.Arrays;

public class MyArrayList<T> implements LList<T> {

    private T[] elements;
    private int size = 0;
    private final int defaultCapacity = 10;
    private int capacity;

    public MyArrayList() {
        elements = (T[]) new Object[defaultCapacity];
        capacity = defaultCapacity;
    }

    public MyArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
        capacity = defaultCapacity;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity >= capacity) {
            T[] old = this.elements;
            int newCapacity = (this.capacity * 3) / 2 + 1;
            this.elements = (T[]) new Object[newCapacity];
            System.arraycopy(old, 0, this.elements, 0, minCapacity);
            capacity = newCapacity;
            size = minCapacity - 1;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    @Override
    public void add(T element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size ++;
    }

    @Override
    public void add(int index, T e) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        System.arraycopy(this.elements, index, this.elements, index + 1, size - index);
        this.elements[index] = e;
        size ++;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (this.get(i) == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T elem = this.elements[index];
        System.arraycopy(this.elements, index + 1, this.elements, index, size - index -1);
        this.elements[--size] = null;
        return elem;
    }

    @Override
    public boolean remove(T element) {
        int ind = this.indexOf(element);
        if (ind >= 0) {
            this.remove(ind);
            return true;
        }
        return false;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", defaultCapacity=" + defaultCapacity +
                '}';
    }
}

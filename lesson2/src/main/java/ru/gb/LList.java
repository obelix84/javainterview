package ru.gb;

public interface LList<T> {
    T get(int index);

    void add(T element);

    void add(int i, T e);

    int indexOf(T element);

    T remove(int index);

    boolean remove(T element);

    int length();
}


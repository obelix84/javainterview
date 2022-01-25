package ru.gb.linkedlist;

import ru.gb.LList;

public class MyLinkedList<T> implements LList<T> {

    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{data=" + data +'}';
        }
    }

    private int length = 0;
    private Node<T> first;
    private Node<T> last;

    public MyLinkedList() {
        this.length = 0;
        first = null;
        last = null;
    }

    @Override
    public T get(int index) {

        if (length == 0 || length <= index)
            return null;

        //тут можно оптимизировать сделать поиск с начала или с конца списка в зависимости от нужного элемента списка
        //упрощаем, делаем поиск с начала
        Node<T> current = this.first;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();

    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<T>(element);
        if (this.first == null) {
            this.first = newNode;
        } else {
            newNode.setPrev(this.last);
            this.last.setNext(newNode);
        }
        this.last = newNode;
        length++;
    }

    @Override
    public void add(int i, T e) {

        if (length == 0 && i == 0) {
            this.add(e);
            return;
        }

        if (i >= length || i < 0) throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<T>(e);
        Node<T> current = this.first;
        if (i == 0) {
            newNode.setPrev(null);
            newNode.setNext(current);
            current.setPrev(newNode);
            this.first = newNode;
        } else if (i == length - 1) {
            newNode.setPrev(this.last.getPrev());
            newNode.setNext(this.last);
            this.last.getPrev().setNext(newNode);
            this.last.setPrev(newNode);
        } else {
            for(int j = 0; j < i; j++) {
                current = current.getNext();
            }
            current.getPrev().setNext(newNode);
            newNode.setPrev(current.getPrev());
            newNode.setNext(current);
            current.setPrev(newNode);
        }
        length++;
    }

    @Override
    public int indexOf(T element) {
        int i = 0;
        Node<T> current = this.first;
        while (current != null) {
            if (current.getData() == element) {
                return i;
            }
            current = current.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public T remove(int i) {
        if (i >= length || i < 0) throw new IndexOutOfBoundsException();

        Node<T> current = this.first;
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }

        Node<T> prev = current.getPrev();
        Node<T> next = current.getNext();
        if (prev == null) {
            this.first = next;
        } else {
            prev.setNext(next);
        }
        if (next == null) {
            this.last = prev;
        } else {
            next.setPrev(prev);
        }
        length--;
        return current.getData();
    }

    @Override
    public boolean remove(T element) {
        int i = this.indexOf(element);
        if (i >= 0) {
            this.remove(i);
            return true;
        }
        return false;
    }

    @Override
    public int length() {
        return length;
    }

    public void print() {
        System.out.print("[ ");
        int i = 0;
        Node<T> current = this.first;
        while (current != null) {
            System.out.print(current.toString() + (i < length - 1 ? ", " : ""));
            current = current.getNext();
            i++;
        }
        System.out.println(" ]");
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "length=" + length +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}

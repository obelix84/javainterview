package lists;

public class SingleList<T> {

    private Node<T> first;

    public class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + data + '}';
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.first == null) {
            this.first = newNode;
            this.first.setNext(null);
            return;
        } else {
            Node<T> next = this.first;
            while (next.getNext() != null) {
                next = next.getNext();
            }
            next.setNext(newNode);
        }
    }

    public void print() {
        System.out.print("[ ");
        int i = 0;
        Node<T> current = this.first;
        while (current != null) {
            System.out.print(current.toString() + " ");
            current = current.getNext();
            i++;
        }
        System.out.println(" ]");
    }


    //получение узла, чтобы зацикленность создать
    public Node<T> getNode(int index) {
        Node<T> next = this.first;
        for (int i = 0; i < index; i++) {
            next = next.getNext();
        }
        return next;
    }

    //добавление ноды, чтобы зацикленность создать
    public void addNode(int index, Node<T> newNode) {
        Node<T> next = this.first;
        for (int i = 0; i < index; i++) {
            if (next == null) {
                throw new IndexOutOfBoundsException();
            }
            next = next.getNext();
        }
        Node<T> tempNode = next.getNext();
        newNode.setNext(tempNode);
        next.setNext(newNode);
    }

    @Override
    public String toString() {
        return "SingleList{" +
                "first=" + first +
                '}';
    }
}

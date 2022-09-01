package com.dataart.intern.logista;

public class MyList<E> implements LinkedList<E> {

    static class Node<E> {

        private E value;
        private Node<E> prv;
        private Node<E> next;

        public Node(E value, Node<E> prv, Node<E> next) {
            this.value = value;
            this.prv = prv;
            this.next = next;
        }
    }

    private Node<E> current;
    private Node<E> first;

    public MyList(Node<E> first) {
        this.first = first;
        this.current = first;
    }

    public MyList(E value) {
        Node<E> node = new Node<>(value, null, null);
        this.first = node;
        this.current = first;
    }

    @Override
    public E first() {
        if (first == null) {
            throw new RuntimeException("No elements in list.");
        }
        return first.value;
    }

    @Override
    public E next() {
        if (current.next == null) {
            throw new ArrayIndexOutOfBoundsException("Linked list has no next value.");
        }
        current = current.next;
        return current.value;
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public E previous() {
        if (current.prv == null) {
            throw new ArrayIndexOutOfBoundsException("Linked list has no previous value.");
        }
        current = current.prv;
        return current.value;
    }

    @Override
    public boolean hasPrevious() {
        return current.prv != null;
    }

    @Override
    public void insert(E e) {
        Node<E> newNode = new Node<>(e, current, current.next);
        if (current.next == null) {
            current.next = current;
        }
        current.next.prv = newNode;
        current.next = newNode;
        current = newNode;
    }

    @Override
    public void delete(E e) {
        if (current.next == null) {
            current.next = current;
        }
        current.prv.next = current.next;
        current.next.prv = current.prv;
        current = current.next;
    }
}

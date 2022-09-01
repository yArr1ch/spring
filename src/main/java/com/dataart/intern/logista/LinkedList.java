package com.dataart.intern.logista;

public interface LinkedList<E> {

    E first();
    E next();
    boolean hasNext();
    void insert(E e);
    E previous();
    boolean hasPrevious();
    void delete(E e);
}

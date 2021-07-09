package com.example.hello.component;

public interface CustomIterator<E> {
    boolean hasNext();
    void next();
    E currentItem();
}

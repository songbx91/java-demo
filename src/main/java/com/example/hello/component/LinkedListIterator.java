package com.example.hello.component;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class LinkedListIterator<E> implements CustomIterator<E> {
    private int cursor;
    private LinkedList<E> linkedList;

    public LinkedListIterator(LinkedList<E> linkedList) {
        cursor = 0;
        this.linkedList = linkedList;
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public boolean hasNext() {
        return cursor != linkedList.size();
    }

    @Override
    public E currentItem() {
        return linkedList.get(cursor);
    }
}

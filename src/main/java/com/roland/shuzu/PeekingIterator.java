package com.roland.shuzu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PeekingIterator<E> implements Iterator<E> {

    private List<E> integers = new ArrayList<E>();
    private int current = -1;

    public PeekingIterator(Iterator<E> iterator) {
        // initialize any member here.
        while(iterator.hasNext()){
            integers.add(iterator.next());
        }
        if(integers.size() > 0){
            current = 0;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        if(current == -1){
            return null;
        }
        else{
            return integers.get(current);
        }
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public E next() {
        if(current == -1){
            return null;
        }
        else{
            return integers.get(current++);
        }
    }

    @Override
    public boolean hasNext() {
        return current != -1 && current < integers.size();
    }
}
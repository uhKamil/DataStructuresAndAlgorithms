package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            this.object = e;
        }

        E object;
        Element next = null;
    }

    Element sentinel;
    int size = 0;

    private class InnerIterator implements Iterator<E> {
        // TODO
        public InnerIterator() {
            // TODO
        }

        @Override
        public boolean hasNext() {
            // TODO
            return false;
        }

        @Override
        public E next() {
            // TODO
            return null;
        }
    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element current = sentinel;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Element(e);
        size++;
        return true;
    }

    private Element getElement(int index) throws NoSuchElementException {
        if (index < 0 || index >= size) throw new NoSuchElementException();
        Element current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub
        // tu jeszcze do konca nie wiem co zrobic
    }

    @Override
    public void clear() {
        sentinel.next = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
//        int i = 0;
//        Element current = sentinel;
//        while (current.next != null && i != index + 1) {
//            current = current.next;
//            i++;
//        }
//        return current.object;
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        return size;
    }

}


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
        Element current;

        public InnerIterator() {
            current = sentinel;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.object;
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

    private Element getElement(int index) throws NoSuchElementException {
        if (index < 0 || index >= size) throw new NoSuchElementException();
        Element current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if (index < 0 || index > size) throw new NoSuchElementException();
        Element current = sentinel;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Element node = new Element(element);
        node.next = current.next;
        current.next = node;
        size++;
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
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        Element e = getElement(index);
        E old = e.object;
        e.object = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        Element current = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (element == current.object || (element != null && element.equals(current.object))) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if (index < 0 || index >= size) throw new NoSuchElementException();
        Element current = sentinel;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E removed = current.next.object;
        current.next = current.next.next; // !!
        size--;
//        return null;
        return removed;
    }

    @Override
    public boolean remove(E e) {
        Element previous = sentinel;
        Element current = sentinel.next;

        while (current != null) {
            if (e == current.object || (e != null && e.equals(current.object))) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

}


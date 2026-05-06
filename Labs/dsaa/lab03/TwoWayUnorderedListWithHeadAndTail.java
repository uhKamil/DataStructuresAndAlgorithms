package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E> {
    Element head;
    Element tail;
    int size;

    private class Element {
        public Element(E e) {
            object = e;
        }

        public Element(E e, Element next, Element prev) {
            object = e;
            this.next = next;
            this.prev = prev;
        }

        E object;
        Element next = null;
        Element prev = null;
    }

    private class InnerIterator implements Iterator<E> {
        Element pos;

        public InnerIterator() {
            pos = head;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = pos.object;
            pos = pos.next;
            return temp;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element current;
        boolean fellOffEnd;

        public InnerListIterator() {
            current = head;
            fellOffEnd = false;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = current.object;
            current = current.next;

            if (current == null) {
                fellOffEnd = true;
            }
            return temp;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            if (fellOffEnd) {
                return tail != null;
            }
            return current != null && current.prev != null;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (fellOffEnd) {
                current = tail;
                fellOffEnd = false;
            } else {
                current = current.prev;
            }

            return current.object;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        Element newElement = new Element(e, null, tail);

        if (isEmpty()) {
            head = newElement;
        } else {
            tail.next = newElement;
        }

        tail = newElement;
        size++;

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException();
        }
        if (index == size) {
            this.add(element);
            return;
        }
        if (index == 0) {
            Element newElement = new Element(element, head, null);
            head.prev = newElement;
            head = newElement;
            size++;
            return;
        }

        Element current = getElement(index);
        Element newElement = new Element(element, current, current.prev);

        current.prev.next = newElement;
        current.prev = newElement;
        size++;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        return getElement(index).object;
    }

    // helper method to get the element at a specific index, used for get, add, set and remove operations
    private Element getElement(int index) {
        Element current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Element current = getElement(index);
        E oldValue = current.object;
        current.object = element;
        return oldValue;
    }

    @Override
    public int indexOf(E element) {
        Element current = head;
        for (int i = 0; i < size; i++) {
            if (current.object.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            E temp = head.object;
            head = null;
            tail = null;
            size--;
            return temp;
        }
        if (index == 0) {
            E temp = head.object;
            head = head.next;
            head.prev = null;
            size--;
            return temp;
        }
        if (index == size - 1) {
            E temp = tail.object;
            tail = tail.prev;
            tail.next = null;
            size--;
            return temp;
        }
        Element current = getElement(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.object;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasNext()) {
            iter.next();
        }
        String retStr = "";
        while (iter.hasPrevious()) {
            retStr += "\n" + iter.previous().toString();
        }
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if (this == other || other.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.head = other.head;
            this.tail = other.tail;
            this.size = other.size;
            other.clear();
            return;
        }
        this.tail.next = other.head;
        other.head.prev = this.tail;
        this.tail = other.tail;
        this.size += other.size;
        other.clear();
    }
}

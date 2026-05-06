package dsaa.lab07;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {
	Element sentinel;
	int size;

	private class Element {
		public Element(E e) {
			this.object = e;
		}

		public Element(E e, Element next, Element prev) {
			this.object = e;
			this.next = next;
			this.prev = prev;
		}

		// add element e after this
		public void addAfter(Element elem) {
			elem.next = this.next;
			elem.prev = this;
			this.next.prev = elem;
			this.next = elem;
		}

		// assert it is NOT a sentinel
		public void remove() {
			if (this == sentinel) return;
			this.prev.next = this.next;
			this.next.prev = this.prev;
		}

		E object;
		Element next = null;
		Element prev = null;
	}

	private class InnerIterator implements Iterator<E> {
		Element current;

		public InnerIterator() {
			current = sentinel.next;
		}

		@Override
		public boolean hasNext() {
			return current != sentinel;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E obj = current.object;
			current = current.next;
			return obj;
		}
	}

	private class InnerListIterator implements ListIterator<E> {
		Element current;

		public InnerListIterator() {
			current = sentinel.next;
		}

		@Override
		public boolean hasNext() {
			return current != sentinel;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E obj = current.object;
			current = current.next;
			return obj;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasPrevious() {
			return current.prev != sentinel;
		}

		@Override
		public E previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			current = current.prev;
			if (current == sentinel) {
				throw new NoSuchElementException();
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
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}

	public TwoWayCycledOrderedListWithSentinel() {
		sentinel = new Element(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		Comparable<E> comp = (Comparable<E>) e;
		Element current = sentinel.next;
		while (current != sentinel && comp.compareTo(current.object) >= 0) {
			current = current.next;
		}
		current.prev.addAfter(new Element(e));
		size++;
		return true;
	}

	private Element getElement(int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		Element current = sentinel.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Element getElement(E obj) {
		Element current = sentinel.next;
		while (current != sentinel) {
			if (current.object.equals(obj)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	@Override
	public boolean contains(E element) {
		return indexOf(element) != -1;
	}

	@Override
	public E get(int index) {
		return getElement(index).object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		Element current = sentinel.next;
		int i = 0;
		while (current != sentinel) {
			if (current.object.equals(element)) {
				return i;
			}
			current = current.next;
			i++;
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
		Element elem = getElement(index);
		E obj = elem.object;
		elem.remove();
		size--;
		return obj;
	}

	@Override
	public boolean remove(E e) {
		Element elem = getElement(e);
		if (elem == null) {
			return false;
		}
		elem.remove();
		size--;
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		if (this == other || other.isEmpty()) {
			return;
		}
		Element w1 = this.sentinel.next;
		Element w2 = other.sentinel.next;

		for (int i = 0; i < other.size; i++) {
			Comparable<E> comp = (Comparable<E>) w2.object;
			while (w1 != this.sentinel && comp.compareTo(w1.object) >= 0) {
				w1 = w1.next;
			}
			Element nextW2 = w2.next;
			w1.prev.addAfter(w2);
			w2 = nextW2;
		}
		this.size += other.size;
		other.clear();
	}

	public void removeAll(E e) {
		Element current = sentinel.next;
		while (current != sentinel) {
			Element nextElem = current.next;
			if (current.object.equals(e)) {
				current.remove();
				size--;
			}
			current = nextElem;
		}
	}

}
import java.util.EmptyStackException;
import java.util.Stack;

public class VTS<T> extends Stack<T> {
    int cursor;

    public VTS() {
        super();
    }

    public void traverse() {
        for (T element : this) {
            System.out.println(element);
        }
    }

    @Override
    public T peek() {
        if (this.isEmpty()) throw new EmptyStackException();
        return this.get(cursor);
    }

    public T top() {
        if (this.isEmpty()) throw new EmptyStackException();
        cursor = this.size() - 1;
        return this.get(cursor);
    }

    public T down() {
        if (this.isEmpty()) throw new EmptyStackException();
        if (cursor > 0) {
            cursor--;
            return this.get(cursor);
        } else {
            throw new IndexOutOfBoundsException("Already at the bottom of the stack.");
        }
    }

    @Override
    public T push(T item) {
        T element = super.push(item);
        cursor = this.size() - 1;
        return element;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) throw new EmptyStackException();
        T element = super.pop();
        cursor = this.size() - 1;
        return element;
    }
}

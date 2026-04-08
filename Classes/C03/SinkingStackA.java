public class SinkingStackA<T> {
    T[] arr;
    int top;
    int capacity;

    @SuppressWarnings("unchecked")
    public SinkingStackA(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be greater than 0.");
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.top = -1;
    }

    public void push(T item) {
        if (top < capacity - 1) {
            arr[++top] = item;
        } else {
            for (int i = 0; i < top; i++) {
                arr[i] = arr[i + 1];
            }
            arr[top] = item;
        }
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        T item = arr[top];
        arr[top] = null;
        top--;
        return item;
    }
}



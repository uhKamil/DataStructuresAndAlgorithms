import java.util.Iterator;
import java.util.NoSuchElementException;

public class c01_1 {
    public static class StepIterator<T> implements Iterator<T> {

        private final Iterator<T> baseIterator;
        private final int k;
        private T nextValidItem;

        public StepIterator(Iterator<T> baseIterator, int k) {
            if (k <= 0) {
                throw new IllegalArgumentException("");
            }
            this.baseIterator = baseIterator;
            this.k = k;

            advance();
        }

        private void advance() {
            nextValidItem = null;
            int count = 0;

            while (baseIterator.hasNext()) {
                count++;
                T currentItem = baseIterator.next();

                if (count == k) {
                    nextValidItem = currentItem;
                    return;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextValidItem != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No elements (step is too large)");
            }

            T itemToReturn = nextValidItem;
            advance();

            return itemToReturn;
        }
    }
}
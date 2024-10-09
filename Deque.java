// public class Deque<Item> implements Iterable<Item> {
public class Deque<Item> {

    private int capacity = 4;

    private Item[] deque;       // deque of elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    // construct an empty deque
    @SuppressWarnings("unchecked")
    public Deque() {
        deque = (Item[]) new Object[capacity];
        n = 0;
        first = capacity / 2;
        last = capacity / 2;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (first == 0) {
            resize(capacity*2);
        }
        deque[--first] = item;
        n++;
        printDeque();
    }

    // add the item to the back
    public void addLast(Item item) {
        if (last == capacity - 1) {
            resize(capacity *2);
        }
        deque[++last] = item;
        n++;
        printDeque();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item firstItem = deque[first];
        deque[first++] = null;
        printDeque();
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item lastItem = deque[last];
        deque[last--] = null;
        printDeque();
        return lastItem;
    }

    // return an iterator over items in order from front to back
    // public Iterator<Item> iterator() {}

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        assert newCapacity >= n;
        Item[] copy = (Item[]) new Object[newCapacity];
        int start = newCapacity/2 - n/2;
        for (int i = 0; i < n; i++) {
            copy[start + i] = deque[first + i];
        }
        deque = copy;
        first = start;
        last  = start + n;

        // debug
        printDeque();
    }

    private void printDeque() {
        System.out.println("\narray: ");
        for (Item item : deque) {
            System.out.print(item);
            System.out.print(" ");
            System.out.println();
        }
        System.out.println("first: " + first);
        System.out.println("last: " + last);
        System.out.println("n: " + n);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> myDeque = new Deque<>();
        myDeque.addFirst(5);
        myDeque.addFirst(4);
        myDeque.addFirst(3);
        myDeque.addFirst(2);
        myDeque.addFirst(1);
        myDeque.addLast(9);
        myDeque.addLast(8);
        myDeque.addLast(7);
        myDeque.addLast(6);


    }
}
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;       
    private int n;          
    private int first;     
    private int last;       

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        queue = (Item[]) new Object[8]; // 8 as initial capacity
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[(first + i) % queue.length];
        }
        queue = copy;
        first = 0;
        last  = n;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (n == queue.length) resize(2 * queue.length);  
        queue[last++] = item;                        // add item
        if (last == queue.length) last = 0;          // questionnable wrap-around
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        exch(StdRandom.uniformInt(n+1), last);
        Item item = queue[last];
        queue[last] = null;
        return item;
    }

    private void exch(int i1, int i2) {
        Item temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return queue[StdRandom.uniformInt(first, last + 1)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            exch(StdRandom.uniformInt(i, last+1), i);
            return queue[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> myQueue = new RandomizedQueue<>();
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.enqueue(4);
        myQueue.enqueue(5);
        for (Integer integer : myQueue) {
            System.out.println(integer);
        }
        // System.out.println(myQueue.dequeue());
        // System.err.println(myQueue.sample());
        // System.out.println(myQueue.dequeue());
        // for (Integer integer : myQueue) {
        //     System.out.println(integer);
        // }
        // System.out.println(myQueue.dequeue());
        // System.out.println(myQueue.dequeue());
        // System.out.println(myQueue.dequeue());
    }

}
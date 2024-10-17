import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created on Tue Oct 15 2024
 * @author Harry Ouyang attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;       
    private int n;               

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[8]; // 8 as initial capacity
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == queue.length) resize(2 * queue.length);  
        queue[n++] = item;                      
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        exch(StdRandom.uniformInt(n), n-1);
        Item item = queue[n-1];
        queue[--n] = null;
        return item;
    }

    private void exch(int i1, int i2) {
        // System.out.println("exchanging " + i1 + " with " + i2);
        Item temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniformInt(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private int[] order;

        public ArrayIterator() {
            order = StdRandom.permutation(n);
        }

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            // System.out.println("\nlast: " + last);
            if (!hasNext()) throw new NoSuchElementException();
            return queue[order[i++]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> myQueue = new RandomizedQueue<>();
        myQueue.enqueue("a");
        myQueue.enqueue("b");
        myQueue.enqueue("c");
        myQueue.enqueue("d");
        myQueue.enqueue("e");
        // for (String integer : myQueue) {
        //     System.out.println(integer);
        // }
        System.out.println(myQueue.dequeue());
        System.err.println(myQueue.sample());
        System.out.println(myQueue.dequeue());
        System.out.println("printing");
        for (String integer : myQueue) {
            System.out.println(integer);
        }
        // System.out.println(myQueue.dequeue());
        // System.out.println(myQueue.dequeue());
        // System.out.println(myQueue.dequeue());
    }

}
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created on Mon Oct 14 2024
 * @author Harry Ouyang attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow");
        }
        Item item = first.item;
        if (n == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow");
        }
        Item item = last.item;
        if (n == 1) {
            last = null;
            first = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<>();
        myDeque.addFirst("second");
        myDeque.addLast("third");
        myDeque.addFirst("first");
        System.out.println("removing, currently of size " + myDeque.size());
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.removeFirst());
        System.out.println("printing");
        for (String string : myDeque) {
            System.out.println(string);
        }
        myDeque.removeFirst();
        System.out.println("is empty? " + myDeque.isEmpty());
    }
}
import edu.princeton.cs.algs4.StdIn;

/**
 * Created on Tue Oct 15 2024
 * @author Harry Ouyang attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus.
 */

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> list = new RandomizedQueue<>();
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            list.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            System.out.println(list.dequeue());
        }
    }
}

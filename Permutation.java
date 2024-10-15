import edu.princeton.cs.algs4.StdIn;

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

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        int n = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (k >= n) {
                queue.enqueue(item);
            } else {
                double p = (double) k / n;
                double d = StdRandom.uniform();
                if (d < p) {
                    queue.dequeue();
                    queue.enqueue(item);
                }
            }
            n++;
        }
        for (String s : queue) {
            StdOut.println(s);
        }
    }
}

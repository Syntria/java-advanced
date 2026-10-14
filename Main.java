import java.io.*;
import java.util.*;

public class Main {
    static class BoundedBuffer {
        private final int[] slots;
        private int head = 0;    // index of the oldest item
        private int count = 0;   // items currently stored

        BoundedBuffer(int capacity) { slots = new int[capacity]; }

        // Store v after the newest item. Block while the buffer is full.
        void put(int v) throws InterruptedException {
            // TODO
        }

        // Remove and return the oldest item. Block while the buffer is empty.
        int take() throws InterruptedException {
            // TODO
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cap = Integer.parseInt(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());
        String line = br.readLine();
        int[] items = new int[n];
        StringTokenizer st = new StringTokenizer(line == null ? "" : line);
        for (int i = 0; i < n; i++) items[i] = Integer.parseInt(st.nextToken());

        BoundedBuffer buf = new BoundedBuffer(cap);
        int[] taken = new int[n];
        Thread producer = new Thread(() -> {
            try {
                for (int v : items) buf.put(v);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) taken[i] = buf.take();
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        StringBuilder sb = new StringBuilder("taken:");
        long sum = 0;
        for (int v : taken) { sb.append(' ').append(v); sum += v; }
        System.out.println(sb);
        System.out.println("sum: " + sum);
    }
}

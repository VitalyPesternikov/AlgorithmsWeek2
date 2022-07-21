import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private int capacity;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 10;
        items = (Item[]) new Object[capacity];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (++size >= capacity) {
            Item[] newItems = (Item[]) new Object[capacity * 2];
            System.arraycopy(items, 0, newItems, 0, size - 1);
            items = newItems;
            capacity = capacity * 2;
        }
        items[size - 1] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(size);
        Item removedItem = items[randomIndex];
        items[randomIndex] = items[size - 1];
        items[--size] = null;
        if (size < capacity / 4) {
            Item[] newItems = (Item[]) new Object[capacity / 2];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
            capacity = capacity / 2;
        }
        return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        return items[StdRandom.uniform(size)];
    }

    private class RandomIterator implements Iterator<Item> {

        private final int[] indexes;
        private int itemsLeftCount;

        public RandomIterator() {
            indexes = new int[size];
            for (int i = 0; i < indexes.length; i++) {
                indexes[i] = i;
            }
            itemsLeftCount = size;
        }

        @Override
        public boolean hasNext() {
            return itemsLeftCount > 0;
        }

        @Override
        public Item next() {
            if (itemsLeftCount == 0) throw new NoSuchElementException();
            int randomIndex = StdRandom.uniform(itemsLeftCount);
            Item nextItem = items[indexes[randomIndex]];
            indexes[randomIndex] = indexes[--itemsLeftCount];
            return nextItem;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
        for (int i = 0; i < 25; i++) {
            queue.enqueue(i + "");
        }
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.sample());
        }
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
    }

}

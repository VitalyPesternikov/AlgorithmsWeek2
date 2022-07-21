import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private int size;

    private class Node {
        private Item item;

        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (size == 0) {
            last = node;
        } else {
            Node oldFirst = first;
            oldFirst.prev = node;
            node.next = oldFirst;
        }
        first = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (size == 0) {
            first = node;
        } else {
            Node oldLast = last;
            oldLast.next = node;
            node.prev = oldLast;
        }
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node oldFirst = first;
        Node newFirst = first.next;
        if (newFirst != null) {
            newFirst.prev = null;
        } else {
            last = null;
        }
        first = newFirst;
        size--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node oldLast = last;
        Node newLast = last.prev;
        if (newLast != null) {
            newLast.next = null;
        } else {
            first = null;
        }
        last = newLast;
        size--;
        return oldLast.item;
    }

    private class DirectIterator implements Iterator<Item> {

        private Node currentNode;

        public DirectIterator() {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (currentNode == null) throw new NoSuchElementException();
            Node oldCurrent = currentNode;
            currentNode = currentNode.next;
            return oldCurrent.item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DirectIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst("first");
        System.out.println(deque.isEmpty());
        deque.addLast("last");
        System.out.println(deque.size());
        deque.addFirst("first");
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());
        for (String s : deque) {
            System.out.println(s);
        }
    }

}

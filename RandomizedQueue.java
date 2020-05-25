import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// ARRAY IMPLEMENTATION
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {

        q = (Item[]) new Object[1];
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

        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == q.length) {
            resize(2 * q.length, q.length);
        }

        q[size] = item; // places item in back
        size++;
    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int rand = StdRandom.uniform(size);
        Item item = q[rand]; // sets item to random result
        q[rand] = q[size - 1]; // sets item at random position to item at back (keeps all items together)
        q[size - 1] = null; // sets item at front to null;

        size--; // changes size

        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2, q.length);
        }

        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return q[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        rq.enqueue(3);
        rq.size();
        rq.size();
        rq.enqueue(0);

    }

    private void resize(int capacity, int prev) {

        Item[] temp = (Item[]) new Object[capacity]; // set temp array to new capacity

        for (int i = 0; i < prev; i++) {
            temp[i] = q[i];
        }

        q = temp;
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        // POTENTIAL FIX: SHUFFLE ITEMS

        private int i = size;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            return q[--i];
        }
    }

}

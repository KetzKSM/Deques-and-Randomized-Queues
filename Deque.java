// LINKED LIST IMPLEMENTATION

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {

        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front (Stack push())
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back (Queue enqueue())
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front (pop and dequeue)
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item; // set item to value of first node
        size--;

        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item; // set item to value of last node
        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deck = new Deque<Integer>();

        deck.addFirst(1);
        deck.removeLast();
        deck.addLast(3);
        deck.removeLast();
        deck.addFirst(3);
        deck.addFirst(34);
        deck.removeFirst();
        deck.removeLast();

    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

}

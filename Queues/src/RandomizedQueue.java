import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int lastItemIndex; // Last item in the Queue
    private int length;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        lastItemIndex = -1;
        length = 4;
        items = (Item[]) new Object[length];
    }

    private void resizeItems(int size) {
        Item[] tempItems = (Item[]) new Object[size];
        for (int i = 0; i <= lastItemIndex; i++) // Copies the array
            tempItems[i] = items[i];

        length = size;
        items = tempItems;

    }

    private void checkForNullArgument(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Argument is null");
    }

    private void queueIsEmptyException() {
        if (!(lastItemIndex >= 0))
            throw new java.util.NoSuchElementException("Queue is empty");
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return !(lastItemIndex + 1 > 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return lastItemIndex + 1;
    }

    // add the item
    public void enqueue(Item item) {
        checkForNullArgument(item);
        if (lastItemIndex + 1 == length)
            resizeItems(2 * length);
        items[lastItemIndex + 1] = item;
        lastItemIndex++;
    }

    private int getRandomIndex() {
        return StdRandom.uniform(0, lastItemIndex + 1);
    }

    // remove and return a random item
    public Item dequeue() {
        queueIsEmptyException();
        int index = getRandomIndex();
        Item returnItem = items[index];
        items[index] = items[lastItemIndex];
        items[lastItemIndex] = null;
        lastItemIndex--;
        if (lastItemIndex > 0 && lastItemIndex <= length / 4)
            resizeItems(length / 2);
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        queueIsEmptyException();
        return items[getRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current = 0;
        private final int[] randomizedQueueIndexes = StdRandom.permutation(lastItemIndex + 1);

        public boolean hasNext() {
            return (current <= lastItemIndex);
        }

        public void remove() {
            throw new UnsupportedOperationException("Iterator.remove() is not supported");
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("There is no next element");
            int rngItemIndex = randomizedQueueIndexes[current];
            Item item = items[rngItemIndex];
            current++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> testRNGQueue = new RandomizedQueue<Integer>();
        if (testRNGQueue.isEmpty())
            System.out.println("CORRECT: isEmpty()");
        System.out.println("Testing Enqueue, added from 0 to 9");
        for (int i = 0; i < 10; i++) {
            testRNGQueue.enqueue(i);
        }
        if (testRNGQueue.size() == 10)
            System.out.println("CORRECT: size()");
        System.out.println("Testing Iterator");
        for (int i : testRNGQueue) {
            System.out.println(i);
        }
        System.out.println("Testing sample:");
        System.out.println(testRNGQueue.sample());
        System.out.println("Testing dequeue, removed:");
        System.out.println(testRNGQueue.dequeue());
        System.out.println("Testing dequeue, removed::");
        System.out.println(testRNGQueue.dequeue());
        System.out.println("RE-Testing Iterator");
        for (int i : testRNGQueue) {
            System.out.println(i);
        }
        System.out.println("Removing all elements");
        int length = testRNGQueue.size();
        for (int i = 0; i < length; i++) {
            testRNGQueue.dequeue();
        }
        if (testRNGQueue.isEmpty())
            System.out.println("CORRECT: isEmpty()");
        testRNGQueue.enqueue(1);
        for (int i : testRNGQueue) {
            System.out.println(i);
        }
    }
}

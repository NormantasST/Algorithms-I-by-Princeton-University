import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node head; // first element of deque
    private Node tail; // last element of deque
    private int size;

    private class Node {
        // 48bytes per Node
        Item item;
        Node next;
        Node prev;

        public Node(Item _item) {
            this.item = _item;
        }
    }

    private void CheckIllegalArgument(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Argument is null");

    }

    private void CheckSizeArgument() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("deque is empty");

    }

    // construct an empty deque
    public Deque() {
        // next is first element in Linked List, prev is last in Linked List of head
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return !(size > 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void createFirstElement(Item item) {
        Node firstElement = new Node(item);
        head = firstElement;
        tail = firstElement;
    }

    private void resetDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    // add the item to the front
    public void addFirst(Item item) {
        CheckIllegalArgument(item);
        if (isEmpty()) {
            createFirstElement(item);
        } else {
            Node newFirst = new Node(item);
            newFirst.next = head;
            head.prev = newFirst;
            head = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        CheckIllegalArgument(item);
        if (isEmpty()) {
            createFirstElement(item);
        } else {
            Node newLast = new Node(item);
            newLast.prev = tail;
            tail.next = newLast;
            tail = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        CheckSizeArgument();
        size--;
        Item returnItem = head.item; // Copies ref to first node
        if (!isEmpty()) {
            head = head.next;
            head.prev = null;
        } else
            resetDeque();
        return returnItem;

    }

    // remove and return the item from the back
    public Item removeLast() {
        CheckSizeArgument();
        size--;
        Item returnItem = tail.item; // Copies ref to first node
        if (!isEmpty()) {
            tail = tail.prev;
            tail.next = null;
        } else
            resetDeque();
        return returnItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Deque Iterator.remove() is not supported");
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("There is no next element");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        Deque<Integer> testDeque = new Deque<Integer>();
        System.out.println("---------------------------------");
        if (testDeque.isEmpty())
            System.out.println("Deque is empty -> isEmpty() Correct");
        else
            System.out.println("Deque is NOT empty -> isEmpty() Incorrect");
        System.out.println("---------------------------------");
        System.out.println("Filling the deque with elements");
        for (int i = 1; i <= size; i++) {
            if (i % 2 == 0)
                testDeque.addLast(i);
            else
                testDeque.addFirst(i);
        }

        System.out.println("---------------------------------");
        System.out.println("Size of deque:");
        System.out.println(testDeque.size());
        if (testDeque.size() == size)
            System.out.println("size() Correct");

        System.out.println("---------------------------------");
        System.out.println("testing Iterator");
        for (int i : testDeque)
            System.out.println(i);

        System.out.println("---------------------------------");
        System.out.println("Testing RemoveFirst, RemoveLast");
        System.out.println("Removed firstItem from deque: ");
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed firstItem from deque: ");
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed firstItem from deque: ");
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed lastItem from deque: ");
        System.out.println(testDeque.removeLast());
        System.out.println("Removed lastItem from deque: ");
        System.out.println(testDeque.removeLast());
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed firstItem from deque: ");
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed firstItem from deque: ");
        System.out.println(testDeque.removeFirst());
        System.out.println("Removed lastItem from deque: ");
        System.out.println(testDeque.removeLast());
        System.out.println("Removed lastItem from deque: ");
        System.out.println(testDeque.removeLast());

        System.out.println("---------------------------------");
        System.out.println("RE-testing Iterator");
        for (int i : testDeque)
            System.out.println(i);
        System.out.println(testDeque.size());
        testDeque.addFirst(1);
        testDeque.addFirst(2);
        testDeque.addLast(3);
        System.out.println("---------------------------------");
        System.out.println("RE-testing Iterator");
        for (int i : testDeque)
            System.out.println(i);
    }
}

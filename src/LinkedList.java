
import java.util.NoSuchElementException;

/**
 * This class is an implementation of a linked list. The user can create a linked list, insert elements
 * either on the end of the list or at a desired index, remove elements at index i and return elements at i.
 *
 * @author Mitch James
 * @param <T> The generic datatype that the linked list will hold (e.g Strings, ints, objects etc.)
 */
public class LinkedList<T> {
    private Link<T> head;
    private Link<T> tail;
    private int listLength;

    /**
     * The constructor method for creating a linked list
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.listLength = 0;
    }

    /**
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /**
     * @return The length of the list, if the list is empty the length is 0.
     */
    public int length() {
        return this.listLength;
    }

    /**
     * To insert data into the linked list at index i. If the index is out bounds, i.e i is less than 0
     * or greater than length - 1 then it is assumed that the new item will be inserted into the position of
     * the head or tail respectively.
     *
     * @param i an index in the linked list
     * @param item the data to be inserted
     */
    public void insertAt(int i, T item) {
        Link<T> link = new Link<>(item, null);

        if (this.isEmpty() || i > this.listLength - 1) {
            this.addLink(link);
        } else {
            if (i <= 0) {
                link.setNext(this.head);
                this.head = link;
            } else {
                Link<T> target = this.linkAt(i);
                Link<T> previous = this.previousLink(i);

                previous.setNext(link);
                link.setNext(target);
            }
        }

        this.listLength ++;

    }

    /**
     *
     * @param index of link to be returned
     * @return Link at index
     * @throws IndexOutOfBoundsException index parameter is outside of list
     */
    private Link<T> linkAt(int index) throws IndexOutOfBoundsException {
        if(isInvalidIndex(index)) {
            throw new IndexOutOfBoundsException("Index outside of list");
        }

        Link<T> curLink = head;
            for (int i = 0; i < index; i++) {
                curLink = curLink.getNext();
            }
        return curLink;
    }

    /**
     * @param i index of Link
     * @return the value stored in Link at index i
     */
    public T itemAt(int i) {
        return linkAt(i).getItem();
    }

    /**
     * @param i index of target Link
     * @return Link that points to Link at index i, i.e the link before i.
     * @throws NoSuchElementException index parameter either points outside of list or to the head
     */
    private Link<T> previousLink(int i) throws NoSuchElementException {
        if (i < 1 || isInvalidIndex(i)) {
            throw new NoSuchElementException("Index outside of list. Head of list doesn't have previous link");
        }

        Link<T> prev = head;
        for (int j = 0; j < i - 1; j++) {
            prev = prev.getNext();
        }
        return prev;
    }

    /**
     * @param i index of target link
     * @return The item stored in the Link before the Link at index i.
     */
    public T previousItem(int i) {
        return previousLink(i).getItem();
    }

    /**
     * @param i index of link to be removed
     * @throws IndexOutOfBoundsException out of bounds or list is empty.
     */
    public void removeAt(int i) throws IndexOutOfBoundsException {
        if(isInvalidIndex(i) || isEmpty()) {
            throw new IndexOutOfBoundsException("Index is either out of bounds or the list is empty");
        }

        if(i == 0) {
            this.removeHead();
        } else if (i == this.listLength - 1) {
            this.removeTail();
        } else {
            Link<T> previous = this.previousLink(i);
            Link<T> current = linkAt(i);

            previous.setNext(current.getNext());
            current.setNext(null);
        }

        this.listLength--;
    }

    /**
     * @param link to be added onto the end of the list
     */
    private void addLink(Link<T> link) {
        if (!this.isEmpty()) {
            this.tail.setNext(link);
            this.tail = link;
        } else {
            this.head = link;
            this.tail = link;
            link.setNext(null);
        }
        this.listLength ++;
    }

    /**
     * Removes tail of the linked list.
     * @throws NoSuchElementException if the list is empty
     */
    public void removeTail() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty");
        }

        if (this.listLength > 1) {
            Link<T> old = this.tail;
            this.tail = previousLink(listLength - 1);
            old.setNext(null);
            this.tail.setNext(null);
        } else {
            this.head.setNext(null);
            this.head = null;
            this.tail = null;
        }
    }

    /**
     * Removes head of the linked list
     * @throws NoSuchElementException if the list is empty.
     */
    public void removeHead() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (this.listLength > 1) {
            Link<T> old = this.head;
            this.head = old.getNext();
            old.setNext(null);
        } else {
            this.head.setNext(null);
            this.head = null;
            this.tail = null;
        }
    }

    /**
     * @param item to be added onto the end of the list
     */
    public void addItem(T item) {
        Link<T> link = new Link<>(item, null);
        this.addLink(link);
    }

    /**
     * @param i index to which we are checking the validity of.
     * @return False if the index falls within the bounds of the list, true otherwise.
     */
    private boolean isInvalidIndex(int i) {
        return (i > this.listLength - 1 || i < 0);
    }

}

/**
 * Class to model a Link in a linked list. The link stores an element and the next link in the chain.
 *
 * @author Mitch James
 * @param <T> Generic type that is stored in the link
 */
public class Link<T> {
    private Link<T> nextLink;
    private T item;

    /**
     * @param element data to be stored in the link.
     * @param next pointer to another link.
     */
    public Link(T element, Link<T> next){
        this.setItem(element);
        this.setNext(next);
    }

    /**
     * @return data stored in the Link
     */
    public T getItem() {
        return this.item;
    }

    /**
     * @return the link that this link points to
     */
    public Link<T> getNext() {
        return this.nextLink;
    }

    /**
     * @param item data to be stored in the link.
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * @param nextLink the link this link will point to.
     */
    public void setNext(Link<T> nextLink) {
        this.nextLink = nextLink;
    }


}

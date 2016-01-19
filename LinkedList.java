import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *A class to represent a linked list of nodes with the node class as a non-static inner class..
 *@author Elyh Lapetina & EECS 132 Class
 */
public class LinkedList<T> implements Iterable<T> {
  
  /** the first node of the list, or null if the list is empty */
  private Node front;
  
  /** the last node of the list, or null if the list is empty */
  private Node back;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    front = null;
    back = null;
  }
  
  /** 
   * Returns true if the list is empty.
   * @return  true if the list has no nodes
   */
  public boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the reference to the first node of the linked list.
   * @return the first node of the linked list
   */
  protected Node getFront() {
    return front;
  }
  
  /**
   * Sets the first node of the linked list.
   * @param node  the node that will be the head of the linked list.
   */
  protected void setFront(Node node) {
    front = node;
  }
  
  /**
   * Returns the reference to the last node of the linked list.
   * @return the last node of the linked list
   */
  protected Node getBack() {
    return back;
  }
  
  /**
   * Sets the last node of the linked list.
   * @param node the node that will be the last node of the linked list
   */
  protected void setBack(Node node) {
    back = node;
  }
  
  
  /**
   * Add an element to the head of the linked list.
   * @param element  the element to add to the front of the linked list
   */
  public void addToFront(T element) {
    if(isEmpty() == false){
      setFront(new Node(element, null, this.getFront()));
      getFront().getNext().setPrevious(getFront());
    } else {
      setFront(new Node(element, null, null));
      setBack(getFront());
    }
  }
  
  /**
   * Add an element to the tail of the linked list.
   * @param element  the element to add to the tail of the linked list
   */
  public void addToBack(T element) {
    if(isEmpty() == false){
      setBack(new Node(element, getBack(), null));
      getBack().getPrevious().setNext(getBack());
    } else {
      setFront(new Node(element, null, null));
      setBack(getFront());
    }
  }
  
  /**
   * Remove and return the element at the front of the linked list.
   * @return the element that was at the front of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromFront() throws NoSuchElementException{
    if (isEmpty()) {
      throw new NoSuchElementException();
    } else {
      T x = getFront().getElement();
      setFront(getFront().getNext());
      return x;
    }
  }
  
  /**
   * Remove and return the element at the back of the linked list.
   * @return the element that was at the back of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromBack() throws NoSuchElementException{
    if (isEmpty()) {
      throw new NoSuchElementException();
    } else if(getBack().getPrevious() == null) {
      T x = getBack().getElement();
      setBack(getBack().getPrevious());
      setFront(null);
      return x;
    } else {
      T x = getBack().getElement();
      setBack(getBack().getPrevious());
      getBack().setNext(null);
      return x;
    }
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    Node nodeptr = getFront();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }
  
  /** Print the contents of the list */
  public void print() {
    Node nodeptr = getFront();
    
    /**Iterates untill last item in list*/
    while (nodeptr != null) {
      System.out.print(nodeptr.getElement() + " ");
      nodeptr = nodeptr.getNext();
    }
    System.out.println();
  }
  
  /** print the list, using a foreach loop
    *  @param list  the list to pring
    */
  public static void printList(Iterable<?> list) {
    
    /**Goes through ever object in list*/
    for (Object element : list)
      System.out.print(element + " ");
    System.out.println();
  }
  
  
 
  /**
   *Removes node at deisred location 
   *@param node Node to be removed. 
   */
  public void remove(Node node){          
    if(getBack() == node && getFront() == node){
      setBack(null);
      setFront(null);
    }
    if(getBack() == node){
      setBack(node.getPrevious());
    }
    
    if(getFront() == node){
      setFront(node.getNext());
    }
    
    if(node.getNext() != null){
      
    }
    
    if(node.getPrevious() != null){
      
    }
    
    node.getNext().setPrevious(node.getPrevious());
    node.getPrevious().setNext(node.getNext());
  }
  
  /** return an iterator for this list
    * @return the iterator for the list
    */
  public Iterator<T> iterator() {
    return new Iterator<T>() {   // this creates an anonymous class that implements Iterator
      private Node nodeptr = getFront();   // the first node of the list
      
      /* overrides the Iterator method to return true if there are any more
       * elements in the iteration
       */
      public boolean hasNext() {
        return nodeptr != null;
      }
      
      /* overrides the Iterator method to return the next element in the 
       * iteration and increments the nodeptr to the next node
       */
      public T next() {
        T save = nodeptr.getElement();
        nodeptr = nodeptr.getNext();
        return save;
      }
      
      
      public void remove(){
      }
      
    };
  }
  
  /**
   * The node of a double linked list.
   */
  public class Node {
    /**
     * The element stored in the node.
     */
    private T element;
    
    /**
     * A pointer to the next node in the list.
     */
    private Node next;
    
    /**
     * A pointer to the previous node of the list.
     */
    private Node previous;
    
    /**
     * The constructor.  Creates a node and puts it into place in the double linked list.
     * @param element  the element to be stored in the node
     * @param previous the node that will be before this node in the list, or null if no node is before this one
     * @param next  the node that will be after this node in the list, or null of no node will be after this node
     */
    public Node(T element, Node previous, Node next) {
      this.element = element;
      this.next = next;
      this.previous = previous;
      if (next != null) {
        next.setPrevious(this);
      }
      if (previous != null) {
        previous.setNext(this);
      }
    }
    
    /**
     * Get the element stored in this node.
     * @return the element stored in the node
     */
    public T getElement() {
      return element;
    }
    
    /**
     * Gets the node that is before this node in the list.
     * @return  a reference to the node that comes before this node in the list
     */
    public Node getPrevious() {
      return previous;
    }
    
    /**
     * Gets the node that is after this node in the list.
     * @return  a reference to the node that comes after this node in the list
     */
    public Node getNext() {
      return next;
    }
    
    /**
     * Sets the reference to the node that will be after this node in the list.
     * @param node  a reference to the node that will be after this node in the list
     */
    public void setNext(Node node) {
      this.next = node;
    }
    
    /**
     * Sets the reference to the node that will be before this node in the list.
     * @param node  a reference to the node that will be before this node in the list
     */
    public void setPrevious(Node node) {
      this.previous = node;
    }
  }
}
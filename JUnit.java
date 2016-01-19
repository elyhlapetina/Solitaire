import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;
/**Testing class for HW5. This class tests each method with several set-ups.
  *The overloaded methods are tested in the same test.
  * @author Elyh Lapetina
  */
public class JUnit {
  
  /**This helper method compares cards.
   * 
   */
  public void compareCards(Card firstCard,Card secondCard){
    
    assertTrue(firstCard.getCurrentSuit().compareTo(secondCard.getCurrentSuit()) == 0);
    assertTrue(firstCard.getCurrentFace().compareTo(secondCard.getCurrentFace()) == 0);
    assertTrue(firstCard.getCurrentSuit().getColor().compareTo(secondCard.getCurrentSuit().getColor()) == 0);
    
  }
  
  /**Helper method to get if two piles are the same
    */
  public void comparePiles(Pile pile1, Pile pile2){
    Pile.Node nodeptr = pile1.getFront();
    Pile.Node nodechecker = pile2.getFront();
    
    /**Loops to end of pile*/
    for(int i = 0; nodeptr != null; i++){
      compareCards(nodeptr.getElement(), nodechecker.getElement());
      nodeptr = nodeptr.getNext();
      nodechecker = nodechecker.getNext();
      
    }
  }
  
  
  /**Test deck constructors and methods
    */
  @Test
  public void testDeck(){
    //Test creation of custom deck
    Deck d = new Deck(Card.Face.Two, Card.Face.Four, Card.Suit.Spades, Card.Suit.Hearts);
    compareCards(d.get(0), new Card(Card.Suit.Spades, Card.Face.Two));
    compareCards(d.get(1), new Card(Card.Suit.Spades, Card.Face.Three));
    compareCards(d.get(2), new Card(Card.Suit.Spades, Card.Face.Four));
    compareCards(d.get(3), new Card(Card.Suit.Hearts, Card.Face.Two));
    compareCards(d.get(4), new Card(Card.Suit.Hearts, Card.Face.Three));
    compareCards(d.get(5), new Card(Card.Suit.Hearts, Card.Face.Four));
    assertTrue(d.getMinFace().compareTo(Card.Face.Two)==0);
    assertTrue(d.getMaxFace().compareTo(Card.Face.Four)==0);
    assertTrue(d.getNumberSuits() == 2);
    d.turnCardsOver();
    
    /**Loops to end of deck*/
    for(int i = 0; i < d.size(); i++){
      assertTrue(! d.get(i).isFaceUp());
    }
  }
  

  /**
   * Tests the addToFront method of DoubleLinkedList.
   */
  @Test
  public void testAddToFront() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addToFront(3);
    list.addToFront(2);
    list.addToFront(1);
    LinkedList.Node head = list.getFront();
    LinkedList.Node tail = list.getBack();
    
    assertEquals("Testing first node of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
    
    assertEquals("Testing node at back of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
  }

  /**
   * Tests the addToBack method of DoubleLinkedList.
   */
  @Test
  public void testAddToBack() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addToBack(1);
    list.addToBack(2);
    list.addToBack(3);
    LinkedList.Node head = list.getFront();
    LinkedList.Node tail = list.getBack();
      
    assertEquals("Testing last node of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
    
    assertEquals("Testing node at front of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
  }
  
  /**
   * Tests the removeFromFront method of DoubleLinkedList.
   */
  @Test
  public void testRemoveFromFront() throws NoValidMoveException{
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addToFront(1);
    list.addToFront(2);
    list.addToFront(3);
    assertEquals("Removing element of list", new Integer(3), list.removeFromFront());
    assertEquals("Removing a second element", new Integer(2), list.removeFromFront());
    assertEquals("Removing a third element", new Integer(1), list.removeFromFront());
    assertEquals("Removed last element of list", true, list.isEmpty());
    try {
      list.removeFromFront();
      fail("Removing from empty list did not throw an exception.");
    }
    catch (java.util.NoSuchElementException e) {
      /* everything is good */
    }
    catch (Exception e) {
      fail("Removing from empty list threw the wrong type of exception.");
    }
    
    list.addToBack(6);
    list.addToBack(7);
    assertEquals("Removing element added to back of list", new Integer(6), list.removeFromFront());
    assertEquals("Removing second element added to back", new Integer(7), list.removeFromFront());
  }
  
  /**
   * Tests the removeFromBack method of DoubleLinkedList.
   */
  @Test
  public void testRemoveFromBack() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addToBack(5);
    list.addToFront(4);
    list.addToBack(6);
    
    assertEquals("Removing element from back of list", new Integer(6), list.removeFromBack());
    assertEquals("Removing second element from back of list", new Integer(5), list.removeFromBack());
    assertEquals("Removing element from back that was added to front", new Integer(4), list.removeFromBack());
    assertEquals("Removing last element of list", true, list.isEmpty());
    try {
      list.removeFromBack();
      fail("Removing from empty list did not throw an exception.");
    }
    catch (java.util.NoSuchElementException e) {
      /* everything is good */
    }
    catch (Exception e) {
      fail("Removing from empty list threw the wrong type of exception.");
    }
  }
  
  /**
   * Tests the linked list iterator.
   */
  @Test
  public void testListIterator() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (int i = 5; i > 0; i--)
      list.addToFront(i);
    
    /* checking that we get out what we put it */
    int i = 1;
    for (Integer x: list)
      assertEquals("Testing value returned by iterator", new Integer(i++), x);
    
    if (i != 6)
      fail("The iterator did not run through all the elements of the list");
  }
  
  /**
   *Tests flipPileOver method in Pile
   */
  @Test
  public void testflipPileOver(){
    
    /**Test Many*/
    Pile p = new Pile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    p.flipPileOver();

    
    Pile.Node nodeptr = p.getFront(); 
    /**Loops to end of pile*/
    for(int i = 0; nodeptr.getNext() != null; i++){
      assertTrue(!nodeptr.getElement().isFaceUp());
      nodeptr = nodeptr.getNext();
    }
     
    /**Test one*/
    p.clearPile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.flipPileOver();
    nodeptr = p.getFront();
    
    /**Loops to end of pile*/
    for(int i = 0; nodeptr.getNext() != null; i++){
      assertTrue(!nodeptr.getElement().isFaceUp());
      nodeptr = nodeptr.getNext();
    }
    
    /**Test Empty*/
    p.clearPile();
    try{
    p.flipPileOver();
    } catch(Exception e){
      assertTrue(true);
    }
  }
  
  /**
   *Tests flipFaceUp method in Pile
   */
  @Test
  public void testflipPileFaceUp(){
    /**Test Many*/
    Pile p = new Pile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    p.flipPileOver();
    p.flipPileFaceUp();
    Pile.Node nodeptr = p.getFront(); 
    
    /**Loops to end of pile*/
    for(int i = 0; nodeptr.getNext() != null; i++){
      assertTrue(nodeptr.getElement().isFaceUp());
      nodeptr = nodeptr.getNext();
    }
    
    /**Test one*/
    p.clearPile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.flipPileOver();
    p.flipPileFaceUp();
    
    /**Loops to end of pile*/
    for(int i = 0; nodeptr.getNext() != null; i++){
      assertTrue(nodeptr.getElement().isFaceUp());
      nodeptr = nodeptr.getNext();
    }
    
    /**Test Empty*/
    p.clearPile();
    try{
    p.flipPileFaceUp();
    } catch(Exception e){
      assertTrue(true);
    } 
  }
  
  /**
   *Tests clearPile method in Pile
   */
  @Test
  public void testClearPile(){
    Pile p = new Pile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    p.clearPile();
    assertTrue(p.getFront() == null);
    assertTrue(p.getBack() == null);
  }
  
  
  /**
   *Tests getBottomShown method in Pile
   */
  @Test
  public void testGetBottomShown(){
    
    /**Test many*/
    Pile p = new Pile();
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    p.addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    p.addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    p.flipPileFaceUp();
    compareCards(p.getBottomShown().getElement(), new Card(Card.Suit.Spades, Card.Face.Four));
    
    /**Test one*/
    p.flipPileFaceUp();
    p.getFront().getElement().setFaceUp(false);
    compareCards(p.getBottomShown().getElement(), new Card(Card.Suit.Hearts, Card.Face.Three));
    
    /**Test zero*/
    p.flipPileFaceUp();
    p.getFront().getElement().setFaceUp(false);
    compareCards(p.getBottomShown().getElement(), new Card(Card.Suit.Hearts, Card.Face.Three));
    
    
    p.flipPileOver();
    try{
      compareCards(p.getBottomShown().getElement(), null);
    } catch(Exception e){
      assertTrue(true);
    }
  
  }
  
  /**
   *Tests intilizeGame method in Solitaire
   */
  @Test 
  public void testIntializeGame(){
    
    Solitaire s = new Solitaire(new Deck(), 7);
    
    /**Test first middle last of piles*/
    for(int i = 0; i < s.getPiles().length; i++){
      assertTrue(s.getPiles()[i].length() == i + 1);  
    }
    
    /**Test first middle last of foundations*/
    for(int i = 0; i < s.getFoundations().length; i++){
      assertTrue(s.getFoundations()[i].length() == 0);
    }

    /**Tests if remaining cards have been dealt to the stock*/
    assertTrue(s.getStock().length() == 24);
    
  }
  
  /**
   *Tests checkSuits method in Solitaire
   */
  @Test
  public void testcheckSuits(){
    Solitaire c = new Solitaire(new Deck(), 7);
    assertTrue(c.checkSuits(new Card(Card.Suit.Spades, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    assertTrue(!c.checkSuits(new Card(Card.Suit.Hearts, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    
  }
  
  /**
   *Tests checkLowerCard method in Solitaire
   */
  @Test
  public void testcheckLowerCard(){
    Solitaire c = new Solitaire(new Deck(), 7);
    assertTrue(c.checkLowerCard(new Card(Card.Suit.Spades, Card.Face.Three), new Card(Card.Suit.Spades, Card.Face.Four)));
    assertTrue(!c.checkLowerCard(new Card(Card.Suit.Hearts, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    
  }
    
  /**
   *Tests higherCard method in Solitaire
   */
  @Test
  public void testcheckHigherCard(){
    Solitaire c = new Solitaire(new Deck(), 7);
    assertTrue(c.checkHigherCard(new Card(Card.Suit.Spades, Card.Face.Five), new Card(Card.Suit.Spades, Card.Face.Four)));
    assertTrue(!c.checkHigherCard(new Card(Card.Suit.Hearts, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    
  }
  
  /**
   *Tests oppositeColor method in Solitaire
   */
  @Test
  public void checkOppositeColor(){
    Solitaire c = new Solitaire(new Deck(), 7);
    assertTrue(c.checkOppositeColor(new Card(Card.Suit.Hearts, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    assertTrue(!c.checkOppositeColor(new Card(Card.Suit.Spades, Card.Face.Four), new Card(Card.Suit.Spades, Card.Face.Four)));
    
  }
  
  
  /**
   *Tests gameWon method in Solitaire
   */
  @Test 
  public void testGameWon(){
    
    Solitaire s = new Solitaire(new Deck(), 7);
    
    s.getFoundations()[0].addToBack(new Card(Card.Suit.Hearts, Card.Face.King));
    s.getFoundations()[1].addToBack(new Card(Card.Suit.Hearts, Card.Face.King));
    s.getFoundations()[2].addToBack(new Card(Card.Suit.Hearts, Card.Face.King));
    s.getFoundations()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.King));
      
    assertTrue(s.checkGameWon());
    
    s.getFoundations()[2].addToBack(new Card(Card.Suit.Hearts, Card.Face.Queen));
    
    assertTrue(!s.checkGameWon());
  }
  
  
  /**
   *Tests moveStockToTableau method in Solitaire
   */
  @Test
  public void testMoveStockToTableau(){
    Solitaire s = new Solitaire(new Deck(), 7);
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    /**Test 0*/
    s.getStock().clearPile();
    s.moveStockToTableau();
    assertTrue(s.getTableau().isEmpty());
    assertTrue(s.getStock().isEmpty());
    
    /**Test 1*/
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.moveStockToTableau();
    assertTrue(s.getTableau().length() == 1);
    assertTrue(s.getStock().isEmpty());
    
    /**Test Many*/
    s.getTableau().clearPile();
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.moveStockToTableau();
    assertTrue(s.getTableau().length() == 2);
    assertTrue(s.getStock().isEmpty());
    
    /**Test Many*/
    s.getTableau().clearPile();
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getStock().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.moveStockToTableau();
    assertTrue(s.getTableau().length() == 3);
    assertTrue(s.getStock().isEmpty());
  }
  
  
  /**
   *Tests moveTableauToStock method in Solitaire
   */
  @Test
  public void testMoveTableauToStock(){
    Solitaire s = new Solitaire(new Deck(), 7);
    
    /**Clears all piles*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    /**Test 0*/
    s.getStock().clearPile();
    s.moveTableauToStock();
    assertTrue(s.getStock().isEmpty());
    assertTrue(s.getTableau().isEmpty());
    
    /**Test 1*/
    s.getStock().clearPile();
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.moveTableauToStock();
    assertTrue(s.getStock().length() == 1);
    assertTrue(s.getTableau().isEmpty());
    
    /**Test Many*/
    s.getStock().clearPile();
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Queen));
    s.moveTableauToStock();
    assertTrue(s.getStock().length() == 4);
    assertTrue(s.getTableau().isEmpty());
    
  }
  
  /**
   *Tests movePileToFoundation method in Solitaire
   */
  @Test
  public void testmovePileToFoundation() throws NoValidMoveException{
    
    Solitaire s = new Solitaire(new Deck(), 7);
    
    /**Clears all piles*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }

    s.getFoundations()[1].addToBack(new Card(Card.Suit.Hearts, Card.Face.Ace));
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Two));
    s.getTableau().getFront().getElement().setFaceUp(true);
    
    s.getTableau().addToFront(new Card(Card.Suit.Spades, Card.Face.Ace));
    s.getTableau().getFront().getElement().setFaceUp(true);
    
    s.getTableau().addToFront(new Card(Card.Suit.Spades, Card.Face.Ace));
    
    s.getPiles()[0].addToBack(new Card(Card.Suit.Clubs, Card.Face.Ace));
    
    s.getPiles()[0].clearPile();
    s.getPiles()[0].addToBack(new Card(Card.Suit.Clubs, Card.Face.Ace));
    s.getPiles()[0].getBack().getElement().setFaceUp(true);
    
    s.getPiles()[0].addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    s.getPiles()[0].getBack().getElement().setFaceUp(true);
    
    try{
      /**Test 0*/
      s.movePileToFoundation(-1);
      /**Test 1*/
      s.movePileToFoundation(-1);
      /**Test Many*/
      s.movePileToFoundation(-1);
      /**Test Many*/
      s.movePileToFoundation(1);
      /**Test 0*/
      s.movePileToFoundation(1);
    } catch(NoValidMoveException e){
      assertTrue(true);
    }
    
    compareCards(s.getFoundations()[0].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.Ace));
    compareCards(s.getFoundations()[1].getBack().getElement(), new Card(Card.Suit.Hearts, Card.Face.Three));
    compareCards(s.getFoundations()[2].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.Ace));

  }
  
  /**
   *Tests moveTableauToActive method in Solitaire
   */
  @Test
  public void testMoveTableauToActive(){
  
    Solitaire s = new Solitaire(new Deck(), 7);
    
    /**Clears all piles*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    
    s.getPiles()[0].addToBack(new Card(Card.Suit.Hearts, Card.Face.Four));
    s.getPiles()[0].getBack().getElement().setFaceUp(true);
    
    s.getPiles()[0].addToBack(new Card(Card.Suit.Spades, Card.Face.Three));
    s.getPiles()[0].getBack().getElement().setFaceUp(true);
    
       
    s.getTableau().addToFront( new Card(Card.Suit.Clubs, Card.Face.Ace));
    s.getTableau().getFront().getElement().setFaceUp(true);
    
    s.getTableau().addToFront(new Card(Card.Suit.Spades, Card.Face.Ace));
    s.getTableau().getFront().getElement().setFaceUp(true);
    
    s.getTableau().addToFront(new Card(Card.Suit.Hearts, Card.Face.Two));
    s.getTableau().getFront().getElement().setFaceUp(true);

    try{
      /**Low card to empty*/
      s.moveTableauToActive(1);
      /**Middle card to valid*/
      s.moveTableauToActive(1);
      /**Card to invalid*/
      s.moveTableauToActive(1);
    } catch(NoValidMoveException e){
      assertTrue(true);
    }
    compareCards( s.getPiles()[0].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.Ace));
    
  }
  
  /**
   *Tests activeToActive method in Solitaire
   */
  @Test
  public void activeToActive(){
    
    Solitaire s = new Solitaire(new Deck(), 7);
    
    /**Test 0*/
    /**Clear piles for next test*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    s.getPiles()[2].addToBack(new Card(Card.Suit.Spades, Card.Face.King));
    s.getPiles()[2].flipPileFaceUp();
    
    try{
      s.moveActiveToActive(3, 4);
    } catch(Exception e){
      
    }
    assertTrue(s.getPiles()[2].isEmpty());
    compareCards(s.getPiles()[3].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.King));
  
    /**Test 1*/
    /**Clear piles for next test*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    s.getPiles()[2].addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    s.getPiles()[2].flipPileFaceUp();
    s.getPiles()[3].flipPileFaceUp();
    
    try{
      s.moveActiveToActive(3, 4);
    } catch(Exception e){
      
    }
    assertTrue(s.getPiles()[2].isEmpty());
    compareCards(s.getPiles()[3].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.Four));
    

    /**Test many with final pile empty*/
    /**Clear piles for next test*/
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
    
    s.getPiles()[2].addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    s.getPiles()[2].addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    s.getPiles()[2].flipPileFaceUp();
    s.getPiles()[3].flipPileFaceUp();
    
    try{
      s.moveActiveToActive(3, 4);
    } catch(Exception e){
      
    }
    assertTrue(s.getPiles()[2].isEmpty());
    compareCards(s.getPiles()[3].getBack().getElement(), new Card(Card.Suit.Hearts, Card.Face.Three));
    
    
    
    /**Test move that does not work*/
    /**Clear piles for next test*/
 
    for(int i = 0; i < s.getPiles().length; i++){
      s.getPiles()[i].clearPile(); 
    }
  
    s.getPiles()[2].addToBack(new Card(Card.Suit.Spades, Card.Face.Four));
    s.getPiles()[2].addToBack(new Card(Card.Suit.Hearts, Card.Face.Three));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Seven));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Spades, Card.Face.Six));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Hearts, Card.Face.Five));
    s.getPiles()[3].addToBack(new Card(Card.Suit.Spades, Card.Face.Ten));
    s.getPiles()[2].flipPileFaceUp();
    s.getPiles()[3].flipPileFaceUp();
    
    try{
      s.moveActiveToActive(3, 4);
    } catch(Exception e){
      assertTrue(true);
    }
    
    compareCards(s.getPiles()[3].getBack().getElement(), new Card(Card.Suit.Spades, Card.Face.Ten));
    compareCards(s.getPiles()[2].getBack().getElement(), new Card(Card.Suit.Hearts, Card.Face.Three));

  } 
}
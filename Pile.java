/**
 *This class represents that a pile in the game of solitare.
 *@author Elyh Lapetina  
 */
public class Pile extends LinkedList<Card>{

  /**Flips pile over*/
  public void flipPileOver(){
    Node currentCard = getFront();
    
    /**Iterates through pile untill there is nothing left*/
    for(int i = 0; currentCard != null; i++){
      currentCard.getElement().setFaceUp(false);
      currentCard = currentCard.getNext();  
    }
  }
  
  /**
   *Flips Pile Faceup. 
   */
  public void flipPileFaceUp(){
    
    Node currentCard = getFront();
    
    /**Iterates through the entire pile*/
    for(int i = 0; currentCard != null; i++){
      currentCard.getElement().setFaceUp(true);
      currentCard = currentCard.getNext();  
    }
  }
  
  /**Clears the pile.
   */
  public void clearPile(){
    setFront(null);
    setBack(null);
  }
 
  /**This returns the bottom most faceup card within the pile
    *@return Node Returns the node at which the first shown is.
    */
  public Node getBottomShown(){
    boolean foundFaceUp = false;
    
    Node nodeptr = getFront();
    
    if(nodeptr.getElement().isFaceUp()){
      return nodeptr;
    }
    
    /**Goes through pile untill a card is not faceUp*/
    while(nodeptr.getElement().isFaceUp() != true){
      nodeptr = nodeptr.getNext();
      foundFaceUp = true;
    }
    
    if(foundFaceUp == false){
      return null;
    }
    return nodeptr;
  }
}
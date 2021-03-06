import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

/**This class represents a deck of cards. This is the basis for solitaire.
  *This class extends ArrayList that holds type cards and only cards.
  *@author Elyh Lapetina
  */


public class Deck extends ArrayList<Card>{
  
  /**Number of suits in the deck*/
  private int numberSuits = 0;
  
  /**Stores value of minFace*/
  private Card.Face minFace = null;
  
  /**Stores value of maxFace*/
  private Card.Face maxFace = null;
  
  /**Creates a custom deck.
    *@param minFace Minimum face value for deck of cards.
    *@param maxFace Maximum face value for deck of cards.
    *@param suitsFromArgs Desired suits in deck
    */
  public Deck(Card.Face minFace, Card.Face maxFace, Card.Suit... suitsFromArgs){
    /**Goal of this loop is to go through entire array of arguements pass from run*/
    for(int i = 0; i < suitsFromArgs.length; i++){
      for(Card.Face face : Card.Face.values()){
        if(face.compareTo(maxFace) <= 0 && face.compareTo(minFace) >= 0){
          this.add(new Card(suitsFromArgs[i], face));
        }
      }
    }
    
    this.minFace = minFace;
    this.maxFace = maxFace;
    numberSuits = suitsFromArgs.length; 
  }
  
  /**Creates a standard deck
    */
  public Deck(){
    this(Card.Face.Ace, Card.Face.King, Card.Suit.Spades, Card.Suit.Hearts, Card.Suit.Diamonds, Card.Suit.Clubs);
  }

  /**Getter method for minFace
    *@return minFace Minimum face in the deck
    */
  public Card.Face getMinFace(){
    return minFace;
  }
  
  /**Getter method for maxFace
    *@return maxFace Maximum face in the deck
    */
  public Card.Face getMaxFace(){ 
    return maxFace;
  }
  
  /**Getter method for number of suits in the deck
    *@return numberSuits Number of suits in the deck.
    */
  public int getNumberSuits(){
    return numberSuits;
  }
  
  /**Shuffles the deck of cards so that they are in a random order
    * 
    */
  public void shuffle(){
 
    
    int r = 0; //Holds a random value
    
    /**Goal of this loop is to go through entire deck up untill the last card. Switches out cards as it goes along*/
    for(int i = size() - 1; i > 0; i--){
      r = (int)(Math.random() * (double)i);
      Card tempCard = get(i);
      set(i, get(r));
      set(r, tempCard);
    }
  }
  
  /**Turns all cards in deck over
    * 
    */
  public void turnCardsOver(){
    
    /**Goes through each item in deck and turns them over*/
    for(int i = 0; i < size(); i++){
      get(i).setFaceUp(false);
    }
  }
  
}
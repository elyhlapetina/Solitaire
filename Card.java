/**This class represents a card in a deck. This is the basis for solitaire.
  *@author Elyh Lapetina
  */

public class Card {
  
  /**Creates a subclass suit with which the card is represented by.
    */
  enum Suit {
    Spades("Spades"), Hearts("Hearts"), Diamonds("Diamonds"), Clubs("Clubs");
    
    /**Representation of suit name as string*/
    private String name;
    
    /**Constructor for Suit.
      *@param name type of suit you want
      */
    private Suit(String name) {
      this.name = name;
    }
    
    /**Returns color of suit.
      *@return string Type of color of suit as string
      */
    public String getColor() {
      if (name.compareTo("Spades") == 0) {
        return "Black";
      } else if (name.compareTo("Hearts") == 0) {
        return "Red";
      } else if (name.compareTo("Diamonds") == 0) {
        return "Red";
      } else {
        return "Black";
      }
    }
    
    /**Returns type of suit.
      *@return string Type of suit as string
      */
    public String toString() {
      if (name.compareTo("Spades") == 0) {
        return "Spades";
      } else if (name.compareTo("Hearts") == 0) {
        return "Hearts";
      } else if (name.compareTo("Diamonds") == 0) {
        return "Diamonds";
      } else if (name.compareTo("Clubs") == 0) {
        return "Clubs";
      } else {
        return null;
      }
    }
    
    /**Returns the suit of the card by a string representation of name
      *@param suitName Suit name represented by string
      *@return suit reuturns type of suit.
      */
    public static Suit getSuitByName(String suitName) {
      if (suitName.compareTo("Spades") == 0) {
        return Spades;
      } else if (suitName.compareTo("Hearts") == 0) {
        return Hearts;
      } else if (suitName.compareTo("Diamonds") == 0) {
        return Diamonds;
      } else if (suitName.compareTo("Clubs") == 0) {
        return Clubs;
      } else {
        return null;
      }
      
    }
    
  }
  
  /**Creates a subclass face with which the card is represented by.
    */
  enum Face {
    Ace("Ace"), Two("Two"), Three("Three"), Four("Four"), Five("Five"), Six("Six"), Seven("Seven"), Eight("Eight"), Nine("Nine"), Ten("Ten"), Jack("Jack"), Queen("Queen"), King("King");                                                                            
                                                                                                                                                   
    /**String represenation of face*/
    private String face;
    
    /**Constructor for face
      *@param face type  you want face to be.*/
    private Face(String face) {
      this.face = face;
    }
    
    /**Returns card face type as a string.
      *@return String string representation of face.
      */
    public String toString() {
      if (face.compareTo("Ace") == 0) {
        return "Ace";
      } else if (face.compareTo("Two") == 0) {
        return "Two";
      } else if (face.compareTo("Three") == 0) {
        return "Three";
      } else if (face.compareTo("Four") == 0) {
        return "Four";
      } else if (face.compareTo("Five") == 0) {
        return "Five";
      } else if (face.compareTo("Six") == 0) {
        return "Six";
      } else if (face.compareTo("Seven") == 0) {
        return "Seven";
      } else if (face.compareTo("Eight") == 0) {
        return "Eight";
      } else if (face.compareTo("Nine") == 0) {
        return "Nine";
      } else if (face.compareTo("Ten") == 0) {
        return "Ten";
      } else if (face.compareTo("Jack") == 0) {
        return "Jack";
      } else if (face.compareTo("Queen") == 0) {
        return "Queen";
      } else {
        return "King";
      }
      
    }
    
    /**Returns the Suit class type
      *@param faceValue Face value in string form.
      *@return Face Face of card
      */
    public static Face getFaceByName(String faceValue) {
      if (faceValue.compareTo("Ace") == 0) {
        return Ace;
      } else if (faceValue.compareTo("2") == 0) {
        return Two;
      } else if (faceValue.compareTo("3") == 0) {
        return Three;
      } else if (faceValue.compareTo("4") == 0) {
        return Four;
      } else if (faceValue.compareTo("5") == 0) {
        return Five;
      } else if (faceValue.compareTo("6") == 0) {
        return Six;
      } else if (faceValue.compareTo("7") == 0) {
        return Seven;
      } else if (faceValue.compareTo("8") == 0) {
        return Eight;
      } else if (faceValue.compareTo("9") == 0) {
        return Nine;
      } else if (faceValue.compareTo("10") == 0) {
        return Ten;
      } else if (faceValue.compareTo("Jack") == 0) {
        return Jack;
      } else if (faceValue.compareTo("Queen") == 0) {
        return Queen;
      } else if (faceValue.compareTo("King") == 0) {
        return King;
      } else {
        return null;
      }
    }
  }
  
  /**Current Suit of card.*/
  private Suit currentSuit = null;
  
  /**Current face of card.*/
  private Face currentFace = null;
  
  /**Stores if card is faceup*/
  private boolean faceUp = false;
  
  /**Constructor of card*/
  public Card(Suit currentSuit, Face currentFace) {
    this.currentFace = currentFace;
    this.currentSuit = currentSuit;
  }
  
  /**Getting method for suit.
   * @return the currentSuit
   */
 public Suit getCurrentSuit() {
   return currentSuit;
 }
 
 /**Getter method for face
  *@return the currentFace
  */
 public Face getCurrentFace() {
   return currentFace;
 }
 
 /**Getter method for faceup
  * @return the faceUp
  */
 public boolean isFaceUp() {
   return faceUp;
 }
 
 /**Sets card faceUp.
  * @param faceUp
  * the faceUp to set
  */
 public void setFaceUp(boolean faceUp) {
   this.faceUp = faceUp;
 }
 
 /**Returns the string representation of the card.
   *@return String  Representation of card in string form. 
   */
 public String toString(){
   if(isFaceUp()){
     return currentSuit.toString() + "_" + currentFace.toString();
   } else {
     return "X";
   }
 }
 
}
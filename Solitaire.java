import java.util.Scanner;
import java.util.ArrayList;

/**
 *The base class for the soliatire game. When created all moves are made through this class.
 *@author Elyh Lapetina  
 */
public class Solitaire{
  
  /**Deck to be used for game*/
  private Deck deck;
  
  /**Array of active piles*/
  private Pile[] piles;
  
  /**Array of foundations*/
  private Pile[] foundations;
  
  /**The stock pile*/
  private Pile stock = new Pile();
  
  /**The tableau party*/
  private Pile tableau = new Pile();

  /**Scanner class to get user input*/
  private Scanner scanner = new Scanner(System.in);
  
  
  /**Creates a solitair game based on a specicific deck
    *@param deck deck you want to use.
    *@param numActivePiles Active piles you want in your game.
    */
  public Solitaire(Deck deck, int numActivePiles){
    
    this.deck = deck;
    
    this.piles = new Pile[numActivePiles];
    
    this.foundations = new Pile[deck.getNumberSuits()];
    
    //intializes piles
    for(int i = 0; i < piles.length; i++){
      piles[i] = new Pile();
    }
  
    //intializes foundations
    for(int i = 0; i < getFoundations().length; i++){
      getFoundations()[i] = new Pile();
    }
                                                    
    intializeGame();
  }
  
  
  /**Getter method for foundations
    *@return foundations array that holds foundations.
    */
  public Pile[] getFoundations(){
    return foundations;
  }
  
  /**Getter method for piles
    *@return piles array that holds piles.
    */
  public Pile[] getPiles(){
    return piles; 
  }
  
  /**Getter method for stock
    *@return stock Pile that holds stock cards
    */
  public Pile getStock(){
    return stock;
  }
  
  /**Getter method for tableau
    *@return tableau Pile that holds tableau.
    */
  public Pile getTableau(){
    return tableau;
  }
  
  /**Getter method for tableau
    *@return deck Deck that holds valid cards for play.
    */
  public Deck getDeck(){
   return deck; 
  }
  
  /**Method that runs the game, checks for inputs
    *@throws NoValidMoveException Throws exception when no valid move is able to be made
    */
  public void playGame()  throws NoValidMoveException {
    
    if(checkGameWon()){
      System.out.println("You won!!!");
      System.exit(0); 
    }
    printBoard();      
    int firstPile = 0;
    int secondPile = 0;      
    String s = scanner.next();
    try{    
      try{ 
        firstPile = Integer.parseInt(s);
        try{
          s = scanner.next();
          secondPile = Integer.parseInt(s);
          moveActiveToActive(firstPile, secondPile);
        } catch(Exception e){
          if(s.equalsIgnoreCase("f")){
            movePileToFoundation(firstPile);
          } else {
            System.out.println("Not a valid entry, Try again.");
          }
        }      
      } catch (Exception e) {
        if(s.equalsIgnoreCase("q")){
          System.exit(0);
        } else if(s.equalsIgnoreCase("r")){
          
          for(int i = 0; i < getPiles().length; i++){
            getPiles()[i].clearPile();
          }
          
          for(int i = 0; i < getFoundations().length; i++){
            getFoundations()[i].clearPile();
          }
          
          getStock().clearPile();
          getTableau().clearPile();
          getDeck().turnCardsOver();
          intializeGame();

        } else if(s.equalsIgnoreCase("s")){
          s = scanner.next();
          if(s.equalsIgnoreCase("t")){
            moveStockToTableau();  
          } else {
            System.out.println("Not a valid entry, Try again.");
          }
          
        } else if(s.equalsIgnoreCase("t")) {
          
          if(scanner.hasNextInt()){
            secondPile = scanner.nextInt();
            moveTableauToActive(secondPile);
          } else {
            s = scanner.next();
            if(s.equalsIgnoreCase("s")){
              moveTableauToStock();
            } else if(s.equalsIgnoreCase("f")){
              movePileToFoundation(-1);
            } else {
              System.out.println("Not a valid entry, Try again.");
            }
          }
        }
      }  
      playGame();
    }catch(Exception e){
      System.out.println("Not a valid entry, Try again.");
      playGame();
    }
  }
  
  /**Prints the board.
   */
  public void printBoard(){
    
    /**Loops through each foundation and prints*/
    for(int i = 0; i < getFoundations().length; i++){
      System.out.print("F" + (i+1) + ": ");
      getFoundations()[i].print();
      
    }
    System.out.println(" ");
    
    /**Loops through each pile and prints*/
    for(int i = 0; i < getPiles().length; i++){
      System.out.print("P" + (i+1) + ": ");
      getPiles()[i].print();
      
    }
    System.out.println(" ");
    
    System.out.print("Stock: ");
    getStock().print();
    System.out.println(" ");
    System.out.print("Tableau: ");
    getTableau().print();
  }
  
  /**Intailizes the game by dealing the cards
    */
  public void intializeGame(){
    
    
    /**Stores the Location in Deck for dealing*/
    int placeHolder = 0;
    
    deck.shuffle();
    
    placeHolder = deck.size() - 1;
    
    /**Loops through each pile and deals cards to it*/
    for(int i = 0; i < piles.length; i++){
      /**Deals number of cards equal to pile number*/
      for(int j = 0; j <= i; j++){
        if(j == 0){
          getPiles()[i].addToFront(deck.get(placeHolder));
          getPiles()[i].getFront().getElement().setFaceUp(true);
          placeHolder--;
        } else {
          getPiles()[i].addToFront(deck.get(placeHolder));
          placeHolder--;
        }
      }
    }
    
    /**Adds remaining cards to stock*/
    while(placeHolder >= 0){
      getStock().addToFront(deck.get(placeHolder));
      placeHolder--;
    }
  }
  
 
  
  /**Moves three cards from Stock to Tableau, if there are less than three cards it moves them all.
    */
  public void moveStockToTableau() {
    if(getTableau().getFront() != null){
      getTableau().flipPileOver();
    }
    
    if(getStock().length() > 3){
      /**Loops through last three of stock*/
      for(int i = 0; i < 3; i++){
        getTableau().addToFront(getStock().getFront().getElement());
        getStock().removeFromFront();
        getTableau().getFront().getElement().setFaceUp(true);
      } 
    } else if (getStock().getFront() == null){
      
    } else { 
      /**Loops through last cards of stock*/
      for(int i = 0; getStock().getFront() != null; i++){  
        getTableau().addToFront(getStock().getFront().getElement());
        getStock().removeFromFront();   
        getTableau().getFront().getElement().setFaceUp(true);
      } 
    }
  }
  
  
  /**Moves all cards from tableau back to stock
    */
  public void moveTableauToStock() {
    /**Loops through all cards in Tableau*/
    for(int i = 0;  getTableau().getFront() != null; i++){
      getStock().addToFront(getTableau().getFront().getElement());
      getStock().getFront().getElement().setFaceUp(false);
      getTableau().removeFromFront();
    }
  }
  
  
  /**Moves Pile to foundation dynamically.
    *@param pileNumber Pile number you want to move
    *@throws NoValidMoveException Throws exception when no valid move is able to be made 
    */
  public void movePileToFoundation(int pileNumber)  throws NoValidMoveException{   
    boolean cardNotPlaced = true;
    
    if(pileNumber > getPiles().length || (pileNumber < 1 && pileNumber != -1))
      throw new NoValidMoveException();
    
    if(pileNumber == -1){
      if(getTableau().getFront().getElement().getCurrentFace().compareTo(deck.getMinFace()) == 0){
        /**Loops through all foundations checking if element can be placed*/
        for(int i = 0; i < getFoundations().length; i++){
          if(getFoundations()[i].getFront() == null && cardNotPlaced){
            cardNotPlaced = false;
            getFoundations()[i].addToFront(getTableau().getFront().getElement());
            getTableau().removeFromFront();
            getTableau().getFront().getElement().setFaceUp(true);
          }
        }
      } else {
         /**Loops through all foundations checking if element can be placed*/
        for(int i = 0; i < getFoundations().length; i++){
          if(getTableau().getFront() != null){
            if(checkSuits(getTableau().getFront().getElement(), getFoundations()[i].getBack().getElement())){
              if(checkHigherCard(getTableau().getFront().getElement(), getFoundations()[i].getBack().getElement())){
                getFoundations()[i].addToBack(getTableau().getFront().getElement()); 
                getTableau().removeFromFront();
                if(getTableau().getFront() != null){
                  getTableau().getFront().getElement().setFaceUp(true);
                }
              }
            }
          }
        }
      }
    } else {
      if(getPiles()[pileNumber - 1].getBack().getElement().getCurrentFace().compareTo(deck.getMinFace()) == 0){
         /**Loops through all foundations checking if element can be placed*/
        for(int i = 0; i < getFoundations().length; i++){
          if(getFoundations()[i].getFront() == null && cardNotPlaced){
            cardNotPlaced = false;
            getFoundations()[i].addToFront(getPiles()[pileNumber - 1].getBack().getElement());
            getPiles()[pileNumber - 1].removeFromBack();
            if(getPiles()[pileNumber - 1].getBack() != null)
              getPiles()[pileNumber - 1].getBack().getElement().setFaceUp(true);
          }
        }
      } else {
         /**Loops through all foundations checking if element can be placed*/
        for(int i = 0; i < getFoundations().length; i++){
          if(getFoundations()[i].getBack() != null){
            if(checkSuits(getPiles()[pileNumber - 1].getBack().getElement(), getFoundations()[i].getBack().getElement())){
              if(checkHigherCard(getPiles()[pileNumber - 1].getBack().getElement(), getFoundations()[i].getBack
                                   ().getElement())){
                getFoundations()[i].addToBack(getPiles()[pileNumber - 1].getBack().getElement());
                getPiles()[pileNumber - 1].removeFromBack();
                getPiles()[pileNumber - 1].getBack().getElement().setFaceUp(true);
                
              }
            }
          }
        }
      }
    }
  }
  
  /**Moves top card from Tableau to Active pile
    *@param pileNumber Number of pile you want to move to
    *@throws NoValidMoveException Throws exception when no valid move is able to be made 
    */
  public void moveTableauToActive(int pileNumber) throws NoValidMoveException{
    
    if(getTableau().getFront().getElement().getCurrentFace().compareTo(deck.getMaxFace()) == 0 && getPiles()[pileNumber - 1].isEmpty()){
      
      getPiles()[pileNumber - 1].addToBack(getTableau().getFront().getElement());
      getTableau().removeFromFront();
      
    } else {
      if(getTableau().isEmpty()){
        throw new NoValidMoveException();
      } else if(checkLowerCard(getTableau().getFront().getElement(), getPiles()[pileNumber - 1].getBack().getElement()) && checkOppositeColor(getTableau().getFront().getElement(), getPiles()[pileNumber - 1].getBack().getElement())){
        getPiles()[pileNumber - 1].addToBack(getTableau().getFront().getElement());
        getTableau().removeFromFront();
        getTableau().getFront().getElement().setFaceUp(true);
        
      } else {
        throw new NoValidMoveException();
      }
    }
  }
  
  
  /**Moves active pile to another active pile
    *@param pileNumber1 Pile to be moved
    *@param pileNumber2 Pile to be move onto 
    *@throws NoValidMoveException Throws exception when no valid move is able to be made
    */
  
  public void moveActiveToActive(int pileNumber1, int pileNumber2) throws NoValidMoveException{
    
    //Checks if moving highest card to empty pile is valid
    if(getPiles()[pileNumber1 - 1].getBottomShown().getElement().getCurrentFace().compareTo(deck.getMaxFace()) == 0 && getPiles()[pileNumber2 - 1].isEmpty()){
      getPiles()[pileNumber2 - 1].addToBack(getPiles()[pileNumber1 - 1].getBottomShown().getElement()); 
      if(getPiles()[pileNumber1 - 1].getBottomShown().getNext() == null){      
        getPiles()[pileNumber1 - 1].removeFromBack();
        getPiles()[pileNumber1 - 1].getBack().getElement().setFaceUp(true);  
      } else if(getPiles()[pileNumber1 - 1].getBottomShown().getElement().getCurrentFace().compareTo(deck.getMaxFace()) == 0 && getPiles()[pileNumber2 - 1].isEmpty()){
        if(getPiles()[pileNumber1 - 1].getBottomShown().getPrevious() == null){
          getPiles()[pileNumber1 - 1] = getPiles()[pileNumber2 - 1];
          getPiles()[pileNumber1 - 1].clearPile();
        }
      } else {  
        getPiles()[pileNumber1 - 1].remove(getPiles()[pileNumber1 - 1].getBottomShown());
        getPiles()[pileNumber1 - 1].getBack().getElement().setFaceUp(true);
        moveActiveToActive(pileNumber1, pileNumber2);    
      }
    } else {
    
      /**Checks if card you are moving is the opposite color and if it is lower than the other card*/
      if(checkLowerCard(getPiles()[pileNumber1 - 1].getBottomShown().getElement(), getPiles()[pileNumber2 - 1].getBack().getElement()) && 
         checkOppositeColor(getPiles()[pileNumber1 - 1].getBottomShown().getElement(), getPiles()[pileNumber2 - 1].getBack().getElement())){

        //Cycles through face up cards
        try{
          while(getPiles()[pileNumber1 - 1].getBottomShown() != null){
            
            //If the first faceup card is the last card
            if(getPiles()[pileNumber1 - 1].getBottomShown().getNext() == null){
          
              getPiles()[pileNumber2 - 1].addToBack(getPiles()[pileNumber1 - 1].getBottomShown().getElement());
              
              //If the first faceup card is the first card of the pile
              if(getPiles()[pileNumber1 - 1].getBack().getPrevious() == null){
                getPiles()[pileNumber1 - 1].removeFromBack();
              } else {
                getPiles()[pileNumber1 - 1].setBack(getPiles()[pileNumber1 - 1].getBottomShown().getPrevious());
                getPiles()[pileNumber1 - 1].getBack().setNext(null); 
                
              }
              
            } else if(getPiles()[pileNumber1 - 1].getBottomShown().getPrevious() == null){
              getPiles()[pileNumber2 - 1].addToBack(getPiles()[pileNumber1 - 1].getBottomShown().getElement());
              
              getPiles()[pileNumber1 - 1].removeFromFront();
              
            } else {
              getPiles()[pileNumber2 - 1].addToBack(getPiles()[pileNumber1 - 1].getBottomShown().getElement());
              getPiles()[pileNumber1 - 1].getBottomShown().getNext().setPrevious(getPiles()[pileNumber1 - 1].getBottomShown().getPrevious());
              getPiles()[pileNumber1 - 1].getBottomShown().getPrevious().setNext(getPiles()[pileNumber1 - 1].getBottomShown().getNext());
              
            }            
          }
        } catch(Exception e){ 
          
        }
        getPiles()[pileNumber1 - 1].getBack().getElement().setFaceUp(true); 
      }
    }
  }
  
  
  /**Checks if the input cards have matching suits
    *@return boolean True if cards are same suit
    */
  public static boolean checkSuits(Card topCard, Card bottomCard){
    if(topCard.getCurrentSuit().compareTo(bottomCard.getCurrentSuit()) == 0){
      return true;
    } else {
      return false;
    }
  }
  
  /**Checks if the top card is lower face value than bottom card
    *@param topCard Card you want on top.
    *@param bottomCard Card you want on bottom.
    *@return boolean True if topCard is lower.
    */
  public static boolean checkLowerCard(Card topCard, Card bottomCard){
    if(topCard.getCurrentFace().compareTo(bottomCard.getCurrentFace()) == -1){
      return true;
    } else {
      return false;
    }
  }
  
  /**Checks if the top card is higher face value than bottom card
    *@param topCard Card you want on top.
    *@param bottomCard Card you want on bottom.
    *@return boolean True if topCard is higher.
    */
  public static boolean checkHigherCard(Card topCard, Card bottomCard){
    if(topCard.getCurrentFace().compareTo(bottomCard.getCurrentFace()) == 1){
      return true;
    } else {
      return false;
    }
  }
  
  /**Checks if the top card is opposite color from bottom card.
    *@param topCard Card you want on top.
    *@param bottomCard Card you want on bottom.
    *@return boolean True if cards are opposite colors
    */
  public static boolean checkOppositeColor(Card topCard, Card bottomCard){
    if(topCard.getCurrentSuit().getColor().compareTo(bottomCard.getCurrentSuit().getColor()) == 0){
      return false;
    } else {
      return true;
    }
  }
  
  /**Check if game has been won.
    *@return boolean True of all foundations have highest card on them.
    */
  public boolean checkGameWon(){
   
    /**Loops through all foundations*/
    for(int i = 0; i < getFoundations().length; i++){
      if(getFoundations()[i].getBack() == null || getFoundations()[i].getBack().getElement().getCurrentFace().compareTo(deck.getMaxFace()) != 0){
          return false;
      }
    }
    return true;
  }
  
  
  
  /**Main Method, Runs game.
    *@param args inputs from user 
    *@throws NoValidMoveException Throws exception when no valid move is able to be made
    */
  public static void main(String[] args) throws NoValidMoveException{
    if (args.length == 0) { // starting Solitaire with standard settings
      Solitaire s = new Solitaire(new Deck(), 7);
      s.playGame();
    } else { 
      
      Card.Suit[] suitsFromArgs = new Card.Suit[args.length - 3];
      
      /**Loops through  argument array and gets the enter suit to pass to deck constructor*/
      for(int i = 0; i < args.length - 3; i++){
        suitsFromArgs[i] = Card.Suit.getSuitByName(args[i + 3]);
      }
      
      Solitaire s = new Solitaire(new Deck(Card.Face.getFaceByName(args[1]), Card.Face.getFaceByName(args[2]), suitsFromArgs), Integer.parseInt(args[0]));
      s.playGame();
    }
  }
 
}




import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class ChessBoard extends Application {
    GridPane root = new GridPane(); //were going to put everything on this gridpane
    int firstInput = -2; //for storing first input from user
    int secondInput = -2; //for storing second input from user
    boolean isWhite = true; //keeps track of whose turn it is
    ArrayList<Piece> Pieces = new ArrayList<Piece>(); //stores all the pieces
    int chosenX; //For storing X values user gives us
    int chosenY; //For storing Y values user gives us
    int chosenIndex; //Storing piece corresponding to chosen x and y values
    Boolean flag = false; //will force user to repeat the turn if their move isnt valid
    boolean check = false; //whether or not the player was in check at start of turn
    int counter = 0; //counts how many attempted moves the player has made while in check
    
    
    final int size = 8; 
    int[] row = new int[8];
    int[] col = new int[8]; //number of columns and rows in the gridpane
    
    public void start(Stage primaryStage) {
    	 
    		//creating and adding pieces to the arraylist
    	    King kingW = new King(4,7,'w');
    	    King kingB = new King(4,0,'b');
    	    Queen queenW = new Queen (3,7,'w');
    	    Queen queenB = new Queen (3,0,'b');
    	    Bishop bishopW = new Bishop (2,7,'w');
    	    Bishop bishopW2 = new Bishop (5,7,'w');
    	    Bishop bishopB = new Bishop (2,0,'b');
    	    Bishop bishopB2 = new Bishop  (5,0,'b');
    	    Knight knightW = new Knight (1,7,'w');
    	    Knight knightW2 = new Knight (6,7,'w');
    	    Knight knightB = new Knight (1,0,'b');
    	    Knight knightB2 = new Knight (6,0,'b');
    	    Rook rookW = new Rook (0,7,'w');
    	    Rook rookW2  = new Rook (7,7, 'w');
    	    Rook rookB = new Rook (0,0,'b');
    	    Rook rookB2 = new Rook (7,0,'b');

    	    Pieces.add(kingW); //index 0
    	    Pieces.add(queenW); //index 1
    	    Pieces.add(bishopW); //index 2
    	    Pieces.add(bishopW2); //index 3
    	    Pieces.add(knightW); //index 4
    	    Pieces.add(knightW2); //index 5
    	    Pieces.add(rookW); //index 6
    	    Pieces.add(rookW2); //index 7
    	    Pieces.add(kingB); //index 8
    	    Pieces.add(queenB); //index 9
    	    Pieces.add(bishopB); //index 10
    	    Pieces.add(bishopB2); //index 11
    	    Pieces.add(knightB); //index 12
    	    Pieces.add(knightB2); //index 13
    	    Pieces.add(rookB); //index 14
    	    Pieces.add(rookB2); //index 15

    	    //adding all the white pawns in a for loop
    	    for (int i  = 0; i < 8; i++){
    	        Pieces.add(new Pawn(i,6,'w'));
    	    }

    	    //adding all the black pawns in a for loop
    	    for (int i  = 0; i < 8; i++){
    	        Pieces.add(new Pawn(i,1,'b'));
    	    }
    	    

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                //creates squares to add to gridpane
            	Rectangle square = new Rectangle();
                
            	Color color;
               
                //sets color for square
                if ((row + col) % 2 == 0) color = Color.WHITE;
                else color = Color.BROWN;
                
                
                square.setFill(color);
                
                root.add(square, col, row);
                
                //binds the square to be 1/8th the size of the window at all times
                square.widthProperty().bind(root.widthProperty().divide(size));
                
                square.heightProperty().bind(root.heightProperty().divide(size));
               
            }
        }
        
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
            	//create a new button to add
            	Button temp = new Button("");
            	//set size for button
            	temp.minWidthProperty().bind(root.widthProperty().divide(8));
            	temp.minHeightProperty().bind(root.heightProperty().divide(8));
            	//make the button transparent
            	temp.setStyle("-fx-background-color: transparent;");
            	
            	//numbers the buttons according to position
            	int numButton= col + 8*row;
                temp.setId("" + numButton);
                
                
                temp.setOnAction(new EventHandler<ActionEvent>() {
                	int chosenX; //For storing X values user gives us
                    int chosenY; //For storing Y values user gives us
                    int chosenIndex; //Storing piece corresponding to chosen x and y values
                    Boolean flag = false; //will force user to repeat the turn if their move isnt valid
                    boolean check = false; //whether or not the player was in check at start of turn
                    int counter = 0; //counts how many attempted moves the player has made while in check
                    @Override
                    public void handle(ActionEvent e) {
                        System.out.println("id(" + temp.getId()  + ") =  " + numButton);
                        if (firstInput == -2) {
                        	firstInput = numButton; //stores
                        } else {//enters this else statement once we have two inputs
                        	secondInput = numButton;
                        	
                        	if (Pieces.get(0).getAlive() && Pieces.get(8).getAlive()) {
                        		if(isWhite) {
                                    if (check == false && inCheck(Pieces, 'w', -1, 0, 0)){
                                        check = true; //sets check to true if your in check before making a move
                                        counter = 0; //reset the counter in case it was used before
                                    }
                                            
                                           if (check){
                                               System.out.println("White King is in Check!"); //tells us the king is in check in console
                                           }
                                            flag = false;
                                            //take in coordinates of piece
                                            chosenX = firstInput%8;//gets x value for first input
                                            chosenY = firstInput/8;//gets y value for first input
                                            System.out.printf("x: %d, y: %d%n", chosenX, chosenY);
                                            chosenIndex = foundPiece(Pieces, chosenX, chosenY); //find the piece with coordinates chosen
                                            if (chosenIndex == -1 || Pieces.get(chosenIndex).getColor() == 'b') {
                                                //if its not valid print out this message
                                                System.out.println("not valid please try again");
                                            }
                                            //only enters this if which gets the location to move to if a valid piece was chosen
                                            if (!(chosenIndex == -1 || Pieces.get(chosenIndex).getColor() == 'b')) {
                                                //take in coordinates to move to
                                                chosenX = secondInput%8;//gets x value from 2nd input
                                                chosenY = secondInput/8;//gets y value from second input
                                                System.out.printf("x: %d, y: %d%n", chosenX, chosenY);
                                                flag = isValidMaster(Pieces, chosenX, chosenY, chosenIndex); //checks if move is valid
                                                if (!flag) {
                                                    //if it wasn't valid print out this message
                                                    System.out.println("not valid please try again");
                                                }
                                                if (flag) {
                                                    if (inCheck(Pieces, 'w', chosenIndex, chosenX, chosenY)){ //if you are in check
                                                        if (check){
                                                            //print the following message and increment counter if you were in check at the start of your turn
                                                            System.out.println("This move would leave you in check!");
                                                            counter++;
                                                            System.out.println("You have " + (3 - counter) + " tries left to find a valid move");
                                                            flag = false;
                                                        }else {
                                                            System.out.print("this move would put you in check!");
                                                            flag = false;
                                                        }
                                                    }
                                                }
                                            }
                                    if (flag && counter < 3) {
                                    	isWhite = false; //sets it so its blacks turn if you made a move
                                    }
                                    
                                    if (counter < 3) {
                                    	if (flag) {
                                        if (foundPiece(Pieces, chosenX, chosenY) != -1) {
                                            //if there is another piece occupying the spot it will kill it moving it off the board
                                            if (chosenIndex != foundPiece(Pieces, chosenX, chosenY)) {
                                                Pieces.get(foundPiece(Pieces, chosenX, chosenY)).kill();
                                            }
                                        }
                                        //following moves the piece
                                        //its important to note that this comes after killing because otherwise the piece would kill itself
                                        Pieces.get(chosenIndex).setX(chosenX);
                                        Pieces.get(chosenIndex).setY(chosenY);
                                        
                                        //the following for loop just checks if there are any pieces on a space and draws them on
                                        for (Node node : root.getChildren()) {
                                        	if (node instanceof Button) {
                                            	((Button) node).setGraphic(null);
                                            }
                                        	for (int i = 0; i < Pieces.size(); i++) {
                                            if (GridPane.getColumnIndex(node) == Pieces.get(i).getX() && GridPane.getRowIndex(node) == Pieces.get(i).getY()) {
                                                    if (node instanceof Button) {
                                                    	char repre = Pieces.get(i).Represent();
                                                    	switch (repre){
                                                    		case 'p':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BP.gif"))));
                                                    			break;
                                                    		case 'k':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BK.gif"))));
                                                    			break;
                                                    		case 'q':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BQ.gif"))));
                                                    			break;
                                                    		case 'r':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BR.gif"))));
                                                    			break;
                                                    		case 'n':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BN.gif"))));
                                                    			break;	
                                                    		case 'b':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BB.gif"))));
                                                    			break;
                                                    		case 'P':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WP.png"))));
                                                    			break;	
                                                    		case 'K':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WK.png"))));
                                                    			break;
                                                    		case 'Q':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WQ.png"))));
                                                    			break;
                                                    		case 'R':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WR.png"))));
                                                    			break;
                                                    		case 'N':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WN.png"))));
                                                    			break;	
                                                    		case 'B':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WB.png"))));
                                                    			break;
                                                    		default:
                                                    	}
                                                    }
                                                } 
                                            }
                                        }
                                    	}
                                    } else {
                                        Pieces.get(0).kill(); //kills the king if you failed to exit check
                                        //following displays game over screen
                                        Rectangle square = new Rectangle();
                                        square.setFill(Color.WHITE);
                                        root.add(square, 0, 0);
                                        square.widthProperty().bind(root.widthProperty());
                                        square.heightProperty().bind(root.heightProperty());
                                        Text t = new Text();
                                        t.setText("              Black Player Wins!");
                                        t.setFont(Font.font ("Verdana", 20));
                                        t.setFill(Color.BLACK);
                                        root.add(t, 1, 0);
                                    }
                        		} else if(!isWhite) {//if its blacks turn do all this stuff
                                    if (check == false && inCheck(Pieces, 'b', -1, 0, 0)){
                                        check = true; //sets check to true if your in check before making a move
                                        counter = 0; //reset the counter in case it was used before
                                    }
                                            
                                           if (check){
                                               System.out.println("Black King is in Check!");
                                           }
                                            flag = false;
                                            //take in coordinates of piece
                                            chosenX = firstInput%8;
                                            chosenY = firstInput/8;
                                            System.out.printf("x: %d, y: %d%n", chosenX, chosenY);
                                            chosenIndex = foundPiece(Pieces, chosenX, chosenY); //find the piece with coordinates chosen
                                            if (chosenIndex == -1 || Pieces.get(chosenIndex).getColor() == 'w') {
                                                //if its not valid print out this message
                                                System.out.println("not valid please try again");
                                            }
                                            //only enters this if which gets the location to move to if a valid piece was chosen
                                            if (!(chosenIndex == -1 || Pieces.get(chosenIndex).getColor() == 'w')) {
                                                //take in coordinates to move to
                                                chosenX = secondInput%8;
                                                chosenY = secondInput/8;
                                                System.out.printf("x: %d, y: %d%n", chosenX, chosenY);
                                                flag = isValidMaster(Pieces, chosenX, chosenY, chosenIndex); //checks if move is valid
                                                if (!flag) {
                                                    //if it wasn't valid print out this message
                                                    System.out.println("not valid please try again");
                                                }
                                                if (flag) {
                                                    if (inCheck(Pieces, 'b', chosenIndex, chosenX, chosenY)){ //if you are in check
                                                        if (check){
                                                            //print the following message and increment counter if you were in check at the start of your turn
                                                            System.out.println("This move would leave you in check!");
                                                            counter++;
                                                            System.out.println("You have " + (3 - counter) + " tries left to find a valid move");
                                                            flag = false;
                                                        }else {
                                                            System.out.print("this move would put you in check!");
                                                            flag = false;
                                                        }
                                                    }
                                                }
                                            }
                                    if (flag && counter < 3) {
                                    	isWhite = true;
                                    }
                                    
                                    if (counter < 3) {
                                    	if (flag) {
                                        if (foundPiece(Pieces, chosenX, chosenY) != -1) {
                                            //if there is another piece occupying the spot it will kill it moving it off the board
                                            if (chosenIndex != foundPiece(Pieces, chosenX, chosenY)) {
                                                Pieces.get(foundPiece(Pieces, chosenX, chosenY)).kill();
                                            }
                                        }
                                        //following moves the piece
                                        //its important to note that this comes after killing because otherwise the piece would kill itself
                                        Pieces.get(chosenIndex).setX(chosenX);
                                        Pieces.get(chosenIndex).setY(chosenY);
                                        
                                      //the following for loop just checks if there are any pieces on a space and draws them on
                                        for (Node node : root.getChildren()) {
                                        	if (node instanceof Button) {
                                            	((Button) node).setGraphic(null);
                                            }
                                        	for (int i = 0; i < Pieces.size(); i++) {
                                            if (GridPane.getColumnIndex(node) == Pieces.get(i).getX() && GridPane.getRowIndex(node) == Pieces.get(i).getY()) {
                                                    if (node instanceof Button) {
                                                    	char repre = Pieces.get(i).Represent();
                                                    	switch (repre){
                                                    		case 'p':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BP.gif"))));
                                                    			break;
                                                    		case 'k':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BK.gif"))));
                                                    			break;
                                                    		case 'q':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BQ.gif"))));
                                                    			break;
                                                    		case 'r':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BR.gif"))));
                                                    			break;
                                                    		case 'n':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BN.gif"))));
                                                    			break;	
                                                    		case 'b':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BB.gif"))));
                                                    			break;
                                                    		case 'P':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WP.png"))));
                                                    			break;
                                                    		case 'K':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WK.png"))));
                                                    			break;
                                                    		case 'Q':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WQ.png"))));
                                                    			break;
                                                    		case 'R':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WR.png"))));
                                                    			break;
                                                    		case 'N':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WN.png"))));
                                                    			break;	
                                                    		case 'B':
                                                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WB.png"))));
                                                    			break;
                                                    		default:
                                                    	}
                                                    }
                                                } 
                                            }
                                        }
                                    	}
                                    } else {
                                        Pieces.get(8).kill(); //kills black king if failed to find a way out of check
                                        //creates the game over screen
                                        Rectangle square = new Rectangle();
                                        square.setFill(Color.WHITE);
                                        root.add(square, 0, 0);
                                        square.widthProperty().bind(root.widthProperty());
                                        square.heightProperty().bind(root.heightProperty());
                                        Text t = new Text();
                                        t.setText("              White Player Wins!");
                                        t.setFont(Font.font ("Verdana", 20));
                                        t.setFill(Color.BLACK);
                                        root.add(t, 0, 0);
                                    }
                        		}
                        	} 
                        	secondInput = -2; //reset the inputs
                        	firstInput = -2;
                        }    
                    }   	
                	});
                root.add(temp, col, row, 1, 1); //adds the buttons to the gridpane
        }
        }
        
        //draws the boardstate before any moves are made
        for (Node node : root.getChildren()) {
        	for (int i = 0; i < Pieces.size(); i++) {
            if (GridPane.getColumnIndex(node) == Pieces.get(i).getX() && GridPane.getRowIndex(node) == Pieces.get(i).getY()) {
                    if (node instanceof Button) {
                    	char repre = Pieces.get(i).Represent();
                    	switch (repre){
                    		case 'p':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BP.gif"))));
                    			break;
                    		case 'k':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BK.gif"))));
                    			break;
                    		case 'q':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BQ.gif"))));
                    			break;
                    		case 'r':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BR.gif"))));
                    			break;
                    		case 'n':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BN.gif"))));
                    			break;	
                    		case 'b':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/BB.gif"))));
                    			break;
                    		case 'P':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WP.png"))));
                    			break;
                    		case 'K':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WK.png"))));
                    			break;
                    		case 'Q':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WQ.png"))));
                    			break;
                    		case 'R':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WR.png"))));
                    			break;
                    		case 'N':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WN.png"))));
                    				break;	
                    		case 'B':
                    			((Button) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/art/WB.png"))));
                    			break;
                    		default:
                    	}
                    }
                }
            }
        }
        
        primaryStage.setTitle("Computer Science II Final Project");
        primaryStage.setScene(new Scene(root, 400, 400));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }
    


    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    /**
    *
    * @param search is the arraylist of pieces to search through
    * @param x x of piece you are looking for
    * @param y y of piece you are looking for
    * @return returns index of piece you are looking for
    */
   static int foundPiece(ArrayList<Piece> search, int x, int y){
       for (int i = 0; i < search.size(); i++){
           //loops through the elements of search
           if (search.get(i).getX() == x){
               if (search.get(i).getY() == y){
                   // if the x and y of the piece given correspond with that of the piece at index i
                   // it will return the index at which this coresspondance was found
                   return i;
               }
           }
       }
       //if none of the elements correspond it return -1 indicating that none of them did
       // -1 is chosen because there is no -1 index
       return -1;
   }
    /**
    *
    * @param Pieces Arraylist of all the Pieces
    * @param x x you want to move to
    * @param y y you want to move to
    * @param index index of piece you want to move
    * @return returns wether or not the move you want to make is valid
    */
   static Boolean isValidMaster(ArrayList<Piece> Pieces, int x, int y, int index){
       if (x == Pieces.get(index).getX() && y == Pieces.get(index).getY()){
           return false; //returns false if the piece didnt move
       }
       if (Pieces.get(index) instanceof Pawn){
           //pawns have special rules so it has to go through a different helper method
           return isValidPawn(Pieces, x , y, index);
       }
       int[] path = Pieces.get(index).path(x,y); //get the path the piece will take
       int intersect;
       if (path == null){
           return false; //if the location is impossible the path will be null
       } else{
           for (int i = 0; i < path.length-1; i+=2){
               //loops through the ordered pair in the path its taking
               intersect = foundPiece(Pieces, path[i], path[i+1]);
               //intersect becomes the index of a piece in the same position as the current ordered pair
               if (intersect != -1){
                   if (i == path.length - 2){ //if its at the last point in the path (cant kill a piece otherwise)
                       //return false if piece is of same color, true otherwise
                       return (Pieces.get(intersect).getColor() != Pieces.get(index).getColor());
                   }
                   return false; //if its not in the last point return false
               }
           }
       }
       //if no issues were found with collisions return true
       return true;
   }
    
    /**
     * helper method for isValidMaster which takes in the same parameters and output but is used specifically for pawns
     */
    static Boolean isValidPawn(ArrayList<Piece> Pieces, int x, int y, int index){
        if (Pieces.get(index).getColor() == 'w'){
            if (y - Pieces.get(index).getY() < -1){ //if its moving more than 1 space up
                if (Pieces.get(index).getY() != 6){ //return false if its not at its starting position
                    return false;
                }
            }
        }
        if (Pieces.get(index).getColor() == 'b'){
            if (y - Pieces.get(index).getY() > 1){ //if its moving more than one space down
                if (Pieces.get(index).getY() != 1){ //return false if its not at its starting position
                    return false;
                }
            }
        }

        int[] path = Pieces.get(index).path(x,y); //makes sure that its actually for a pawn if it is path is just {x,y}
        int intersect;
        if (path == null){ //returns false if the position was invalid
            return false;
        }
        intersect = foundPiece(Pieces, path[0], path[1]); //checks if theres a piece in the position its moving to
        if (path[0] == Pieces.get(index).getX()){ //if its moving straight forward
            return (intersect == -1); //return false if theres a piece there, true otherwise
        } else{ //if it is moving diagonally
            if (intersect == -1){
                return false; //return false if there is not a piece there
            }
            //return false if the piece is of the same color, true if it isnt
            return (!(Pieces.get(intersect).getColor() == Pieces.get(index).getColor()));
        }
    }
    /**
    *
    * @param Pieces arraylist of pieces
    * @param color color of king to check
    * @param moved if a piece is being moved this is the index
    * @param x x to move the "moved" piece to
    * @param y y to move the "moved" piece to
    * @return wether or not the color piece is in check
    */
   static Boolean inCheck(ArrayList<Piece> Pieces, char color, int moved, int x, int y) {
       int originalX = 0; 
       int originalY = 0;
       if (moved != -1){
    	 //store the original values for the piece before we move it
           originalX = Pieces.get(moved).getX();
           originalY = Pieces.get(moved).getY();
           Pieces.get(moved).setX(x);
           Pieces.get(moved).setY(y);
       }
       //loops through all the pieces and sees if theres a black piece able to capture the white king
       if (color == 'w') {
           for (int i = 0; i < Pieces.size(); i++) {
               if (isValidMaster(Pieces, Pieces.get(0).getX(), Pieces.get(0).getY(), i)){
                   if (moved == -1 || (Pieces.get(i).getX() != Pieces.get(moved).getX() || Pieces.get(i).getY() != Pieces.get(moved).getY())) {
                       if (moved != -1) {
                           Pieces.get(moved).setX(originalX);
                           Pieces.get(moved).setY(originalY);
                       }
                       return true;
                   }
               }
           }
           if (moved != -1){
               Pieces.get(moved).setX(originalX);
               Pieces.get(moved).setY(originalY);
           }
           return false;
       }
     //loops through all the pieces and sees if theres a white piece able to capture the black king
       for (int i = 0; i < Pieces.size(); i++) {
           if (isValidMaster(Pieces, Pieces.get(8).getX(), Pieces.get(8).getY(), i)){
               if (moved == -1 || (Pieces.get(i).getX() != Pieces.get(moved).getX() || Pieces.get(i).getY() != Pieces.get(moved).getY())) {
                   if (moved != -1) {
                       Pieces.get(moved).setX(originalX);
                       Pieces.get(moved).setY(originalY);
                   }
                   return true;
               }
           }
       }
       if (moved != -1){
           Pieces.get(moved).setX(originalX);
           Pieces.get(moved).setY(originalY);
       }
       return false;
   }
    
}

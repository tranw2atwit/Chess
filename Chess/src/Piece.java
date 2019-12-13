public abstract class Piece {

    private int x,y; //position of piece
    private Boolean alive; //wether or not its alive
    private char color; //color of piece

    /**
     * Constructor with 3 args for x y and color
     * @param x sets x
     * @param y sets y
     * @param color sets color
     */
    public Piece(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;

    }

    /**
     * constructor with no args calls Piece(0,0,'w')
     */
    public Piece(){
        this(0,0,'w');
    }




    /**
     * following is self explanatory, sets and gets for variables
     */
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public Boolean getAlive(){
        return alive;
    }

    public char getColor(){
        return color;
    }

    /**
     * sets alive to false and moves the piece off the board
     */
    public void kill(){
        alive = false;
        x = -1;
        y = -1;
    }

    /**
     * @param toX X piece is moving to
     * @param toY Y piece is moving to
     * @return returns the path it must take
     */
    public abstract int[] path(int toX, int toY);

    /**
     * @param toX x to move to
     * @param toY y to move to
     * @return if the position is a valid move for the piece
     */
    public abstract boolean isValid(int toX, int toY);

    /**
     * @return the character which will visually represent the piece
     * Capitals represent white and lowercase is black
     */
    public abstract char Represent();
}

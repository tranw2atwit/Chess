public class Pawn extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public Pawn(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Pawn(){
        this(0,0,'w');
    }

    @Override
    public int[] path(int toX, int toY){
        //returns toX and toY as an array if valid since it moves directly to the spot
        if (isValid(toX, toY)){
            return new int[] {toX, toY};
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!((toX < 8 && toY < 8) && (toX >= 0 && toY >= 0))){
            return false; //returns false if its out of bounds
        }
        if (toX - x < 2 && toX- x > -2) { //if its moving less than 2 spaces sideways in either direction
            if (color == 'w') {
                return (y - toY < 3); //returns whether it moved 2 or fewer spaces up if white
            }
            if (color == 'b') {
                return (toY - y < 3); //returns whether it moved 2 or fewer down if black
            }
        }
        return false; //returns false if it moved more than 1 space sideways
    }


    @Override
    public void setX(int x){
        this.x = x;
    }
    @Override
    public int getX(){
        return x;
    }
    @Override
    public void setY(int y){
        this.y = y;
    }
    @Override
    public int getY(){
        return y;
    }
    @Override
    public Boolean getAlive(){
        return alive;
    }
    @Override
    public void kill(){
        alive = false;
        x = -1;
        y = -1;
    }
    @Override
    public char getColor(){
        return color;
    }
    @Override
    public char Represent(){
        if (color == 'w'){
            return 'P';
        }
        return 'p';
    }
}

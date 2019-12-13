public class Knight extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public Knight(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Knight(){
        this(0,0,'w');
    }

    @Override
    public int[] path(int toX, int toY){
        //just checks if its valid and returns toX and toY in an array since the knight moves directly to a spot
        if (isValid(toX, toY)){
            return new int[] {toX, toY};
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!((toX < 8 && toY < 8) && (toX >= 0 && toY >= 0))){
            //returns false if its outside of the board
            return false;
        }
        //returns true if its moving 2 spots in x and 1 in y
        if ((toX - 2 == x || toX + 2 == x) && (toY - 1 == y || toY + 1 == y)){
            return true;
        }
        //returns true if its moving 1 spot in x and 2 in y
        if ((toX - 1 == x || toX + 1 == x) && (toY - 2 == y || toY + 2 == y)){
            return true;
        }
        //returns false if neither if was true
        return false;
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
    public char Represent() {
        if (color == 'w') {
            return 'N';
        }
        return 'n';
    }

}

public class King extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public King(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public King(){
        this(0,0,'w');
    }

    @Override
    public int[] path(int toX, int toY){
        if (isValid(toX, toY)){
            // returns toX and toY as an array if its valid since king moves directly to spot
            return new int[] {toX, toY};
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!((toX < 8 && toY < 8) && (toX >= 0 && toY >= 0))) {
            return false;
        }
        //returns true if its moving less than 2 spaces in either direction
        return (toX - x < 2 && toX- x > -2 && toY - y < 2 && toY - y > -2);
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
        System.out.println(color + " king was killed");
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
            return 'K';
        }
        return 'k';
    }
}

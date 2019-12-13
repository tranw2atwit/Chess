import java.util.ArrayList;

public class Rook extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public Rook(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Rook(){
        this(0,0,'w');
    }

    public int[] path(int toX, int toY){
        int xNow = x;
        int yNow = y;

        ArrayList<Integer> pos = new ArrayList<>();
        if(isValid(toX, toY)){
            while(xNow != toX || toY != yNow){ //loops until its in the final position
                if(toX-xNow > 0) {
                    xNow += 1;
                } else if(toX - xNow < 0){
                    xNow -= 1;
                }

                if(toY-yNow > 0) {
                    yNow += 1;
                } else if (toY - yNow < 0) {
                    yNow -= 1;
                }
                //adds/subtracts from either the x and y until it reaches the final location and adds it to the arraylist each time
                pos.add(xNow);
                pos.add(yNow);
            }

            int[] back = new int[pos.size()]; //creates an array the same size as the arraylist
            for(int i = 0; i < pos.size(); i++){ //copies elements from arraylist to array
                back[i] = pos.get(i);
            }
            return back; //returns the array
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!(toX < 8 && toX >= 0 && toY < 8 && toY >= 0)){
            return false; //returns false if outside bounds
        }
        return (toX == x || toY == y); //returns true if either the x or y is unchanged
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
            return 'R';
        }
        return 'r';
    }
}

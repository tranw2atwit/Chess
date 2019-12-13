import java.util.ArrayList;

public class Bishop extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public Bishop(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Bishop(){
        this(0,0,'w');
    }

    public int[] path(int toX, int toY){
        int xNow = x;
        int yNow = y;

        ArrayList<Integer> pos = new ArrayList<>();
        if(isValid(toX, toY)){ //if the destination is valid
            while(xNow != toX){ //loops until it reaches its destination
                if(toX-xNow > 0) {
                    xNow += 1;
                }
                else
                    xNow -= 1;

                if(toY-yNow > 0) {
                    yNow += 1;
                }
                else
                    yNow -= 1;
                //moves closer to the final position in a diagonal path and adds the x and y to the arraylist each time
                pos.add(xNow);
                pos.add(yNow);
                }

            int[] back = new int[pos.size()]; //change it into an array from an arraylist
            for(int i = 0; i < pos.size(); i++){
                back[i] = pos.get(i); //store values from arraylist into array
            }
            return back; //return the array of moves
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!(toX < 8 && toX >= 0 && toY < 8 && toY >= 0)){
            return false; //returns false if its outside of the bounds of the map
        }
        int changeX = Math.abs(toX - x);
        int changeY = Math.abs(toY - y);
        return(changeX == changeY); //if the change in x and y position is the same returns true
        //change x only = change y if moving diagonally
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
            return 'B';
        }
        return 'b';
    }
}

import java.util.ArrayList;

public class Queen extends Piece{
    private int x,y;
    private Boolean alive;
    private char color;

    public Queen(int x, int y, char color){
        alive = true;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Queen(){
        this(0,0,'w');
    }

    public int[] path(int toX, int toY){
        int xNow = x;
        int yNow = y;

        ArrayList<Integer> pos = new ArrayList<>();
        if(isValid(toX, toY)){
            while(xNow != toX || toY != yNow){ //loops while its not at the final position
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
                //adds/subtracts from the x and y until it reaches the final position and then adds the x and y values to arraylist
                pos.add(xNow);
                pos.add(yNow);
            }

            int[] back = new int[pos.size()]; //creates an array the size of the arraylist
            for(int i = 0; i < pos.size(); i++){
                back[i] = pos.get(i);//adds contents of arraylist to the array
            }
            return back; //return the array for the path of ordered pairs
        }
        return null;
    }

    @Override
    public boolean isValid(int toX, int toY) {
        if (!(toX < 8 && toX >= 0 && toY < 8 && toY >= 0)){
            return false; //returns false if out of bounds
        }
        if (toX == x || toY == y){
            return true; //if either the x or y didnt change return true (rook movement)
        }
        int changeX = Math.abs(toX - x);
        int changeY = Math.abs(toY - y);
        return(changeX == changeY);
        //returns true if change in y was equal to change in x (bishop movement)
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
            return 'Q';
        }
        return 'q';
    }
}

public class Position {
    public int x;
    public int y;
    public int[] posArray = {this.y,this.y};

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int[] getPos(){
        return this.posArray;
    }

    public void setPos(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void positionrn(){
        System.out.println(this.x+"  "+this.y);
    }

    //movement methods
    public void moveNorth(){
        this.y -=1; 
    }

    public void moveSouth(){
        this.y +=1;
    }

    public void moveEast(){
        this.x +=1;
    }

    public void moveWest(){
        this.x -=1;
    }
}

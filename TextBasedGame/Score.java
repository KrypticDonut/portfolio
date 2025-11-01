
public class Score {
    private final int PUZZLE_VALUE= 10;
    private double Score; 
    private int startingScore;
    private int roomsVisited;
    private int puzzlesSolved = 0;
    private int[] listOfVisitedRooms = new int[10];

    public Score(int startingScore){
        this.startingScore = startingScore;
        this.listOfVisitedRooms[0] = 0; //to avoid error that listOfVisitedRooms[0] = 0 at the start
        this.visitRoom();
    }

    public void visitRoom(){
        //should only incriment this by 1 if the room hasn't already
        this.roomsVisited +=1;
        this.Score += 1;
    }

    public void addRoomToVisited(int roomIndex){
        boolean hasVisitedRoom =false; 
        for(int i=0; i<this.listOfVisitedRooms.length;i++){
            if( this.listOfVisitedRooms[i] == roomIndex){
                hasVisitedRoom = true;
                break;
            } 
        }
        if(!hasVisitedRoom){
            this.listOfVisitedRooms[this.roomsVisited] = roomIndex;
            this.visitRoom();
        }
    }

    public void solvePuzzle(){
        this.puzzlesSolved +=1;
    }

    public double getScore(){
        return this.startingScore - this.roomsVisited +(this.puzzlesSolved)*(this.PUZZLE_VALUE);
    }
}

public class Map {
    private final int width;
    private final int height;
    private final char EMPTY = '.';
    private final char[][] Map;
    

    public Map(int width, int height){
        this.width = width;
        this.height = height;
        this.Map = new char [height][width];
    }

    public void placeRoom(Position pos,char symbol){
        this.Map[pos.y][pos.x] =  symbol;
    }

    public String display(){
        String result = "";
        for(int i = 0; i<(this.height);i++){
            for(int j = 0; j<(this.width); j++){
                result = result +this.Map[i][j];
            }
        }
        return result;    
    }

    public void createInitialMap(char Symbol, int[] startingPoint){
        String temp = "";
        for(int i = 0; i<(this.height);i++){
            for(int j = 0; j<(this.width); j++){
                temp = temp+".";           
            }
            temp = temp + ".";
        }
    }
}

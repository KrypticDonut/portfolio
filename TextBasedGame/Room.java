public class Room{
    private String name;
    private Position position;
    private String description;
    private char symbol;

    public Room(String name, String description, char symbol, Position position){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public Position getPosition(){
        return null;
    }

    public String getDescription(){
        return this.description ;
    }
}
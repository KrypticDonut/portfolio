import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        //extra varibles 
        boolean isGameRunning =  true;
        int startingRoom = 0;
        int currentRoom = 0;
        String[][] maps = new String[10][5];
        int[][] doors = {{5,5,1,0},{5,1,2,1},{5,3,3,2},{3,5,6,3},{2,5,4,0},{3,4,5,4},{3,3,7,5},{3,5,9,6},{3,5,8,7},{1,5,9,8}};// {x pos,y pos, goes to this rom index, current room index}
        boolean[] openDoors = {false,false,false,false,false,false,false,false,false,false,false};
        String[][] chests = {{"key","key","key","note4"},{"key"},{"note1"},{"note2"},{"note3","key"},{"teleporter"}};
        int[][] chestPosition = {{5,1,0},{5,1,2},{3,2,3},{3,2,6},{3,2,7},{3,2,9}}; //{xpos,ypos,roomindex}
        int[][] buttons= {{1,3,3},{5,3,3},{1,3,6},{5,3,6}};
        boolean[] buttonState = {false,false,false,false};
        boolean[] puzzles = {false,false,false};
        String[][] description = {{"look note1","hint: or gate"},{"look key","rusty and old seemingly unfit for this places"},{"look exitGauntlet","rusty glove like object"},{"look teleporter","futuristic device to teleport you places"},{"look note2","hint: and gate"},{"look note3","enter the name of the cheat code: upupdowndownleftrightleftrightbastart"},{"look note4","complete 3 puzzles to gain an exit gauntlet to escape"}};
        boolean hasGauntlet = false;


        //declaring objects
        Scanner input = new Scanner(System.in);
        Score score = new Score(0); //the starting score is 0 
        Position playerPos = new Position(1,1);
        Inventory playerInventory = new Inventory();
        Room[] Rooms = new Room[10];
        Map[] map = new Map[10];
        
        //creating the instances of the room class aka the rooms
        Rooms[0] = new Room("0", "Very dark mysterious room with stone walls", '0', playerPos);
        Rooms[1] = new Room("1", "Stone walls in a spiral", '1', playerPos);
        Rooms[2] = new Room("2", "Very dark mysterious room with a wall inbetween and a chest", '2', playerPos);
        Rooms[3] = new Room("3", "Futuristic room filled with technology and 2 buttons?", '3', playerPos);
        Rooms[4] = new Room("4", "Stone labyrinth", '4', playerPos);
        Rooms[5] = new Room("5", "Very dark mysterious room with a spiralling wall and a chest", '5', playerPos);
        Rooms[6] = new Room("6", "Futuristic room filled with technology and 2 buttons?", '6', playerPos);
        Rooms[7] = new Room("7", "Dark empty room with achest and a door", '7', playerPos);
        Rooms[8] = new Room("8", "Twisting stone walls", '8', playerPos);
        Rooms[9] = new Room("9", "Blinding white room with a chest in the middle ", '9', playerPos);
        
        //creating the maps of the rooms 
        //S is the starting point
        //. is a free space
        //# is a wall
        //D is a door
        //C is a chest
        //B are buttons
        //E is an exit to finish the game
        maps[0] = new String[]{"#######","#..S#C#","#.###.#","#.....#","#.##.##","#.D#.D#","#######"};
        maps[1] = new String[]{"#######","#...#D#","#.#.#.#","#.#S#.#","#.###.#","#.....#","#######"};
        maps[2] = new String[]{"#######","#....C#","#.##S#.#","#.###D#","#.##..#","#....##","#######"};
        maps[3] = new String[]{"#######","#..S..#","#..C..#","#B...B#","#.....#","#..D..#","#######"};
        maps[4] = new String[]{"#######","#..S..#","#.###.#","#.###.#","#.#D#.#","#.....#","#######"};
        maps[5] = new String[]{"#######","#S....#","#.###.#","#.#D..#","#.#####","#....C#","#######"};
        maps[6] = new String[]{"#######","#..S..#","#..C..#","#B...B#","#.....#","#..D..#","#######"};
        maps[7] = new String[]{"#######","#..S..#","#..C..#","#.....#","#.....#","#..D..#","#######"};
        maps[8] = new String[]{"#######","#....S#","#.#####","#.....#","#####.#","#D....#","#######"};
        maps[9] = new String[]{"#######","#..S..#","#..C..#","#.....#","#.....#","#..E..#","#######"};

        //creating the instaances of the map class so theres a different map for each room
        map[0] = new Map(7,7);
        map[1] = new Map(7,7);
        map[2] = new Map(7,7);
        map[3] = new Map(7,7);
        map[4] = new Map(7,7);
        map[5] = new Map(7,7);
        map[6] = new Map(7,7);
        map[7] = new Map(7,7);
        map[8] = new Map(7,7);
        map[9] = new Map(7,7);
        
        //now we need to create the initial map for each room 
        map[0].createInitialMap(Rooms[0].getSymbol(),getStartingPoint(maps[0]));
        map[1].createInitialMap(Rooms[1].getSymbol(),getStartingPoint(maps[1]));
        map[2].createInitialMap(Rooms[2].getSymbol(),getStartingPoint(maps[2]));
        map[3].createInitialMap(Rooms[3].getSymbol(),getStartingPoint(maps[3]));
        map[4].createInitialMap(Rooms[4].getSymbol(),getStartingPoint(maps[4]));
        map[5].createInitialMap(Rooms[5].getSymbol(),getStartingPoint(maps[5]));
        map[6].createInitialMap(Rooms[6].getSymbol(),getStartingPoint(maps[6]));
        map[7].createInitialMap(Rooms[7].getSymbol(),getStartingPoint(maps[7]));
        map[8].createInitialMap(Rooms[8].getSymbol(),getStartingPoint(maps[8]));
        map[9].createInitialMap(Rooms[9].getSymbol(),getStartingPoint(maps[9]));
        
        //load the first room 
        currentRoom = setRoom(startingRoom ,maps[startingRoom],playerPos, score);

        //main game loop
        while(isGameRunning){
            System.out.println("What would you like to do?");
            String action = input.nextLine();
            if(action.equals("move north")){
                if(checkMove(playerPos, maps[currentRoom], 0,-1)){
                    playerPos.moveNorth(); 
                    map[currentRoom].placeRoom(playerPos, Rooms[currentRoom].getSymbol());
                }else{
                    System.out.println("You cannot move there");
                }
            }else if (action.equals("move south")){
                if(checkMove(playerPos, maps[currentRoom], 0,1)){
                    playerPos.moveSouth(); 
                    map[currentRoom].placeRoom(playerPos,Rooms[currentRoom].getSymbol());
                }else{
                    System.out.println("You cannot move there");
                }
            }else if (action.equals("move east")){
                if(checkMove(playerPos, maps[currentRoom], 1,0)){
                    playerPos.moveEast();
                    map[currentRoom].placeRoom(playerPos,Rooms[currentRoom].getSymbol());
                }else{
                    System.out.println("You cannot move there");
                }
            }else if (action.equals("move west")){
                if(checkMove(playerPos, maps[currentRoom], -1,0)){
                    playerPos.moveWest(); 
                    map[currentRoom].placeRoom(playerPos,Rooms[currentRoom].getSymbol());
                }else{
                    System.out.println("You cannot move there");
                }
            }else if(action.equals("look")){
                Rooms[currentRoom].getDescription();
            }else if (action.equals("quit")) {
                isGameRunning = false;
            }else if (action.equals("help")) {
                help();
            }else if (action.equals("score")) {
                System.out.println(score.getScore());
            }else if (action.equals("inventory")) {
                System.out.println(playerInventory.displayInventory());
            }else if (action.equals("map")) {
                viewMap(map[currentRoom].display());
            }else if (action.equals("symbol")) {
                Rooms[currentRoom].getSymbol();
            }else if (action.equals("position")) {
                playerPos.positionrn();
            }else if(action.equals("open chest")){
                int[] tempChestPosition = searchForSymbol(playerPos, maps[currentRoom], 'C');
                if(tempChestPosition != null){ 
                    chests = openChest(chestPosition, currentRoom, chests, playerInventory, tempChestPosition);
                }else{
                    System.out.println("There is no chest nearby");
                }
            }else if(action.equals("open door")){
                int[] doorPosition =searchForSymbol(playerPos, maps[currentRoom], 'D');
                if(doorPosition != null){
                    int newRoomindex = openDoor(playerPos, doors, currentRoom, openDoors, doorPosition, playerInventory);
                    if(newRoomindex != -1){
                        currentRoom = setRoom(newRoomindex, maps[newRoomindex], playerPos, score);
                    } 
                }else{
                    System.out.println("There is no door nearby");
                }
            }else if (action.equals("use key")){
                int[] doorPosition = searchForSymbol(playerPos, maps[currentRoom], 'D');
                if(doorPosition != null){
                    if(useKey(playerInventory) == true){
                        for(int i = 0; i<doors.length;i++){
                            if(doors[i][0] == doorPosition[0] && doors[i][1] == doorPosition[1]){
                                openDoors[i] = true;
                                System.out.println("You have succesfully unlocked a door");
                                break;
                            }
                        }
                    }else{
                        System.out.println("You do not have a key");
                    }
                }else{
                    System.out.println("There is no door nearby");
                }
            }else if(action.equals("press button")){
                int[] buttonPosition  =  searchForSymbol(playerPos, maps[currentRoom], 'B');
                if(buttonPosition != null){
                    int buttonIndex = findButtonIndex(buttonPosition,buttons,currentRoom);
                    if(buttonIndex != -1){
                        buttonState[buttonIndex] = changeButtonState(buttonIndex, buttonState);
                    }
                } 
            }else if(action.equals("konami code") && currentRoom == 7 && puzzles[2] == false){
                System.out.println("Congratulations puzzle complete");
                score.solvePuzzle();
                puzzles[2] = true;
                openDoors[8] = true;
            }else if(checkIfNote(description, action) !=-1){
                System.out.println(description[checkIfNote(description, action)][1]);
            }else if(action.equals("teleport") && playerInventory.hasItem("teleporter") != -1){
                int choice  =-1;
                while(choice <0 || choice >9){
                    System.out.println("Which room would you like to teleport to? ");
                    choice = input.nextInt();
                    input.nextLine();
                } 
                currentRoom = setRoom(choice, maps[choice], playerPos, score);
            }else if(action.equals("exit")){
                int[] exitPlatform = searchForSymbol(playerPos, maps[currentRoom], 'E');
                if(exitPlatform != null){
                    isGameRunning = false;
                    System.out.println("Congratulations you have beaten the game your score was "+score.getScore());
                }
            }else if(lookDetail(action)){
                
            }else{
                System.out.println("Invalid Command");
            }
            

            if((buttonState[0] == true || buttonState[1] == true) && puzzles[0] != true){
                System.out.println("Congratulations puzzle complete");
                score.solvePuzzle();
                puzzles[0] = true;
                openDoors[3] = true;  
            }
            if((buttonState[2] == true && buttonState[3] == true) && puzzles[1] != true){
                System.out.println("Congratulations puzzle complete");
                score.solvePuzzle();
                puzzles[1] = true;
                openDoors[7] = true;
            }
            if(puzzles[0] == true && puzzles[1] == true && puzzles[2] && hasGauntlet == false){
                System.out.println("Congratulations on completing all of challenges you have achieved an exit gauntlet");
                playerInventory.addItem("exitGauntlet");
                hasGauntlet = true;
            }
        }
    }
    
    public static void help(){
        System.out.println("Helpful stuff is said in order to  help on your journey");
        System.out.println("To read item decription do look (itemname)'");
        System.out.println("To move in a direction say move then the cardinal direction you want to go");
        System.out.println("In order to open a chest write 'open chest'");
        System.out.println("In order to open most doors you will need a key to use the key say 'use key'");
        System.out.println("Once you have used the key to open a door to go through the door say 'open door'");
        System.out.println("Once you have got the teleporter say 'teleport' then enter it will ask you which room you want to go to");
        System.out.println("The command 'quit' will stop the code ");
        System.out.println("When you have got the exit gauntlet go to the exit platform and enter 'exit' to finish the game");
    }

    public static void viewMap(String mapString){
        int count = 1;
        for(int i = 0; i< 49; i++){
            System.out.print(mapString.charAt(i));
            if(count  == 7){
                count = 0;
                System.out.println();
            }
            count++;
        }
    }

    public static int checkIfNote(String[][] notes, String note){
        for(int i=0;i<notes.length; i++){

            if(notes[i][0].equals(note)){
                return i;
            }
        }
        return -1;
    }

    public static boolean checkMove(Position playerPos, String[] roomMap, int num1, int num2){
        char currentSymbol = (roomMap[playerPos.y+num2]).charAt(playerPos.x+num1);
        if(currentSymbol == '#'){
            System.out.println("There is a wall in the way");
            return false; 
        }else if(currentSymbol == 'C'){
            System.out.println("There is a chest in the way");
            return false;
        }else if(currentSymbol == 'D'){
            System.out.println("There is a door in the way");
            return false;
        }else if(currentSymbol == '.'){
            return true;
        }else if(currentSymbol == 'B'){
            System.out.println("There is a button in the way");
            return false; 
        }else if(currentSymbol == 'E'){
            System.out.println("There is an exit point in the way");
            return false; 
        }else{
            return false;
        }
    }

    public static boolean lookDetail(String object){
        if (object.equals("look chest")){
            System.out.println("wooden chest with a silver lock");
            return true;
        }else if(object.equals("look button")) {
            System.out.println("tall white stand with a big red button");
            return true;
        }else if (object.equals("look door")) {
            System.out.println("Tall pill lke structure with no handle");
            return true;
        }else if (object.equals("look exit")){
            System.out.println("A platform which fits some sort of medal");
            return true;
        }
        return false;
    }

    public static int[] searchForSymbol(Position playerPos, String[] roomMap, char symbol){ 
        //looks at the characters on every side of the player
        if ((roomMap[playerPos.y+1]).charAt(playerPos.x) == symbol) {
            return  new int[] {playerPos.x,playerPos.y+1};
        }else if ((roomMap[playerPos.y]).charAt(playerPos.x+1) == symbol) {
            return  new int[] {playerPos.x+1,playerPos.y};
        }else if ((roomMap[playerPos.y]).charAt(playerPos.x-1) == symbol) {
            return new int[] {playerPos.x-1,playerPos.y};
        }else if ((roomMap[playerPos.y-1]).charAt(playerPos.x) == symbol) {
            return new int[] {playerPos.x,playerPos.y-1};
        }else{
            return null;
        }
    }

    public static void look(){

    }

    public static int setRoom(int newRoom, String[] roomMap,Position playerPos, Score score){
        //we need to find the new point for the player to go to
        int[] startingPoint = getStartingPoint(roomMap);

        //visit room to add score
        score.addRoomToVisited(newRoom);

        //set the position of the player in the new room 
        playerPos.setPos(startingPoint[0], startingPoint[1]);

        System.out.println("You are now in room "+ newRoom);
        //return the integer for the new room
        return newRoom;  
    }

    //this is the function to use a key to unlock a door
    public static boolean useKey(Inventory playerInventory){
        if(playerInventory.hasItem("key") != -1){
            playerInventory.removeItem("key");
            return true;
        }
        return false;
    }

    public static int openDoor(Position playerPos,int[][] doors, int currentRoom, boolean[] openDoors, int[] doorPosition,Inventory inventory){
        int[] tempDoorInfo;
        int doorIndex = 0;
        for(int i = 0; i<doors.length;i++){
            if(doors[i][3] == currentRoom && doors[i][0] == doorPosition[0] && doors[i][1] == doorPosition[1]){
                tempDoorInfo = doors[i];
                doorIndex = i;
                break;
            }
        }
        if(openDoors[doorIndex] == false){
            System.out.println("The door is not open you may need to use a key");
            return -1;
        }else{
            System.out.println("You open the door");
            return doors[doorIndex][2];
        }
    }

    public static int findChestIndex(char symbol, char[] chestKeys){
        for(int i=0; i<chestKeys.length;i++){
            if(chestKeys[i] == symbol){
                return i;
            }
        }
        return -1;
    }

    public static String[][] openChest(int[][] chestPosition, int currentRoom, String[][] chests,  Inventory playerInventory, int[] tempChestPosition){
        for(int i = 0; i<chestPosition.length; i++){
            if(currentRoom == chestPosition[i][2] && tempChestPosition[0] == chestPosition[i][0]  && tempChestPosition[1] == chestPosition[i][1]){
                for(int j = 0; j<chests[i].length;j++){
                    if(chests[i][j] != null){
                        playerInventory.addItem(chests[i][j]);
                        System.out.println("added "+chests[i][j]+" to inventory");
                        //cant add a break here since there could be more items in the chest
                        chests[i][j] = null;
                    }
                }
            }
        }
        return chests;
    }

    //this gets the starting point of each room
    public static int[] getStartingPoint(String[] roomMap){
        int[] startingPoint = new int[2];
        for(int i = 0; i< roomMap.length; i++){
            for(int j=0; j<roomMap[i].length();j++){
                if (roomMap[i].charAt(j) == 'S') {
                    startingPoint = new int[] {j,i}; // if colums are i and rows are j then (j,i) is equivilant to (x,y)
                }
            }
        }
        return startingPoint;
    }

    public static int findButtonIndex(int[] buttonPosition, int[][] buttons, int currentRoom){
        for(int i = 0; i<buttons.length; i++){
            if(buttons[i][0] == buttonPosition[0] && buttons[i][1] == buttonPosition[1] && buttons[i][2] == currentRoom){
                return i;
            }
        }
        return -1;
    }

    public static boolean changeButtonState(int buttonIndex,boolean[] buttonState){
        System.out.println("Buton state changed");
        System.out.println("button state from "+buttonState[buttonIndex]+" to "+!(buttonState[buttonIndex]));
        return !(buttonState[buttonIndex]);
    }

}
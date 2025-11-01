import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        System.out.println(distance("0111110101","0101000001"));
    }

    public static int distance(String x, String y){
        //first check whether they are equals
        if(x.equals(y)){
            return 0;
        }

        //create the hashmap for storing info about adjacent vertices
        HashMap<String,String> adjacentVertices = new HashMap<String,String>();

        //making a hashmap to store the results of each operation
        HashMap<String, String> operations = new HashMap<String,String>();
        operations.put("110","001");
        operations.put("011","100");
        operations.put("101","110");

        //create the queue
        ArrayList<String> queue =  new ArrayList<String>();
        int frontPointer = 0;
        int backPointer = 0;

        //create the extra varibles
        boolean found = false;

        //add x to the queue 
        queue.add(x);
        backPointer++;
        adjacentVertices.put(x,null);

        while(!found){
            //first we want to check whats at the start of our queue
            String currentNode = queue.get(frontPointer);
            frontPointer++;

            //now we want to find the adjacent nodes
            for(int i = 0; i<currentNode.length()-2;i++){
                String tempString = currentNode.substring(i,i+3);
                String value =  operations.getOrDefault(tempString,"default");


                //we want to see if we can add a new node to the adjacency list
                //only if there is no other nodes accesing it already
                if(!value.equals("default")){

                    //extra varibles to keep the code readable
                    String left = currentNode.substring(0, i);
                    String right = currentNode.substring(i+3,currentNode.length());
                    String newNode = left+value+right;
                    
                    //add this to the queue and hash map
                    queue.add(newNode);
                    adjacentVertices.put(newNode,currentNode);

                    //check if this is the desitnation node
                    if(newNode.equals(y)){
                        found = true;
                        break;
                    }
                }
            }
            if(frontPointer == queue.size()){
                System.out.println("Not possible");
                return -1;
            }    
        } 
        
        
        //now we need to count the number of edges to the end
        
        int count = -1;
        boolean isDone= false;
        String current = y;
        while(!isDone){
            current = adjacentVertices.get(current);                
            System.out.println(current);
            count++;
            if(current == null){
                isDone = true;
            }
        }
        
        return count;
        
    }
}
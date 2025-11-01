import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    // Question 1a
    public static boolean reachable1(String x, String y){
        int countx = 0;
        int county = 0;
        for(int i = 0; i<x.length(); i++){
            if (x.charAt(i) == '1') {
                countx++;
            }
        }
        for(int i = 0;i<y.length();i++){
            if (y.charAt(i) == '1') {
                county++;
            }
        }
        return (county == countx);
    }

    // Question 1b
    public static int distance1(String x, String y){
        if(!reachable1(x, y)){ //we need to check it is possible to find a distance
            return -1;
        }
        int[] arrayOfCases = new int[x.length()];
        for(int i=0; i<x.length();i++){
            if(x.charAt(i) == y.charAt(i)){
                arrayOfCases[i] = 0;
            }else if( x.charAt(i) == '0' && y.charAt(i) == '1'){
                arrayOfCases[i] = 1;
            }else if( x.charAt(i) == '1'  && y.charAt(i) == '0'){
                arrayOfCases[i] = 2;
            }
        }     

        int numOfSteps = 0;
        for(int i=0;i<arrayOfCases.length;i++){
            if(arrayOfCases[i] == 1){
                for(int j = i;j<arrayOfCases.length;j++){
                    if(arrayOfCases[j] == 2){
                        numOfSteps = numOfSteps + j-i;
                        arrayOfCases[i] = 0;
                        arrayOfCases[j] = 0;
                        break;
                    }
                }
            }else if(arrayOfCases[i] == 2){
                for(int j = i;j<arrayOfCases.length;j++){
                    if(arrayOfCases[j] == 1){
                        numOfSteps = numOfSteps + j-i;
                        arrayOfCases[i] = 0;
                        arrayOfCases[j] = 0;
                        break;
                    }
                }
            }
        }                                       
        return numOfSteps;
    }

    // Question 2
    public static int distance2(String x, String y){
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

        //create the extra varibles
        boolean found = false;

        //add x to the queue 
        queue.add(x);
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
                return -1;
            }    
        } 


        //now we need to count the number of edges to the end

        int count = -1;
        boolean isDone= false;
        String current = y;
        while(!isDone){
            current = adjacentVertices.get(current);        
            count++;
            if(current == null){
                isDone = true;
            }
        }

        return count;

    }
}


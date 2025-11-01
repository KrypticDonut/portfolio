public class Inventory {
    final int MAX_ITEMS = 10;
    String[] inventory = new String[MAX_ITEMS];
    int numberOfItems = 0;
    public Inventory(){
    }

    public void addItem(String item){
        for(int i =0; i< this.MAX_ITEMS; i++){
            if(this.inventory[i] == null){
                this.inventory[i] = item;
                break;
            }
        }
    }

    public int hasItem(String item){
        for(int i = 0; i < this.MAX_ITEMS; i++){
            System.out.println(this.inventory[i]);
            if(this.inventory[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    public void removeItem(String item){
        for(int i = 0; i<this.inventory.length; i++){
            if(this.inventory[i].equals(item)){
                this.inventory[i] = "";
                break;
            }
        }
    }

    public String displayInventory(){
        System.out.println("The items in your inventory are:");
        String temp = "";
        for(int i = 0; i < this.MAX_ITEMS; i++){
            if(this.inventory[i] != null) {
                if(!this.inventory[i].contentEquals("")){
                    temp = temp+ this.inventory[i]+" ";
                }
            }else{
                break;
            }
        }
        return temp;
    }

}


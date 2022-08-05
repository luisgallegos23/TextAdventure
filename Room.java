import java.util.ArrayList;

public class Room {
    //description of the Room
    private String description;
    //name of the monster
    private String name;
    // creates two arraylist that serves for each rooms exits and directions
    private ArrayList<String> exits = new ArrayList<String>();
    private ArrayList<Room> neighbors = new ArrayList<Room>();

    // the treasure, weapon, or monster in the room if any
    private Treasure treasure;
    private Weapon weapon;
    private Monster monster;

    //sets the name and description of the room
    public Room (String name,String description){
        this.name =  name;
        this.description = description;
    }
    //returns the description of the room
    public String getDescription(){return description;}
    //returns the name of the room
    public String  getName(){return name;}
    //serves to add neighbor rooms and exits to each room
    public void addNeighbor(String direction, Room neighbor){
        exits.add(direction);
        neighbors.add(neighbor);
        }
    //returns the list of exits of the room in a string
    public String listExits(){ return exits.toString();}

    // returns the room in the exit stated
    public Room getNeighbor(String direction) {
        int i = exits.indexOf(direction);
        if (i>-1){
            return neighbors.get(i);
        }else{
            return null;
        }
    }
    //sets a treasure in the room
    public void setTreasure(Treasure treasure){this.treasure = treasure;}
    //returns the treasure in the room
    public Treasure getTreasure(){return treasure;}
    //sets a monster in the room
    public void setMonster(Monster monster){ this.monster = monster;}
    //returns the monster in the room
    public Monster getMonster(){return monster;}
    //sets a weapon in the room
    public void setWeapon(Weapon weapon){this.weapon = weapon;}
    //returns the weapon in the room
    public Weapon getWeapon(){return weapon;}

}


public class Monster {

    //a description of the monster
    private String description;

    // the monster's name
    private String name;
    //the power of the monster
    private int value;
    //sets the name, value, and description of the monster
    public Monster(String name, int value,String description){
        this.name =  name;
        this.description = description;
        this.value = value;
    }
    //returns the description of the monster
    public String getDescription() { return description;}
    //returns the name of the monster
    public String getName() {return name;}
    //returns strength/armor of the monster
    public int getArmor() { return value; }


}

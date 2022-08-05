public class Weapon {
    //the description of the weapon
    private String description;
    // the name of the weapon
    private String name;
    //the damage done by the weapon
    private int value;
    //sets the name, value and the description of the weapon
    public Weapon (String name,int value,String description){
        this.name =  name;
        this.description = description;
        this.value = value;
    }
    //returns the description of the weapon
    public String getDescription() { return description;}
    //returns the name of the weapon
    public String getName() {return name;}
    //returns the damage done by the weapon
    public int getDamage() { return value; }
}

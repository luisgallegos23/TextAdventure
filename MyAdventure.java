public class MyAdventure {
    public static void main(String[] args) {
        new MyAdventure().run();
    }

    /** Damage done by the best weapon the player has picked up. */
    private int bestWeaponDamage;

    /** The room where the player currently is. */
    private Room currentRoom;

    /** Total value of all treasures taken by the player. */
    private int score;

    public MyAdventure() {
        // Create rooms
        Room MessHall = new Room("MessHall",
                "the MessHall where all the campers come to gather and socialize");
        Room Firepit = new Room("Fire Pit", "a place for gathering in the middle of camp");
        Room  Cabin3= new Room("Cabin 3", "an abandoned cabin that was once the place of a horrible accident");
        Room GreatHouse = new Room("Great House",
                "a large house home to the Camp Leaders and where the main office is housed");
        Room Darkwoods =  new Room("The Dark Woods","a dark forrest where light does not penetrate and is filled with dangerous creatures ");
        Room Theater = new Room ("Amphitheater", "the amphitheater with a stage filled with mannequins and costumes ");
        // Create connections
        MessHall.addNeighbor("south", Firepit);
        MessHall.addNeighbor("west", Darkwoods);
        Darkwoods.addNeighbor("east",MessHall);
        MessHall.addNeighbor("east", Theater);
        Theater.addNeighbor("west",MessHall);
        Firepit.addNeighbor("north", MessHall);
        Firepit.addNeighbor("west", Cabin3);
        Cabin3.addNeighbor("east",Firepit);
        Firepit.addNeighbor("south", GreatHouse);
        GreatHouse.addNeighbor("north", Firepit);
        // Create and install monsters
        Darkwoods.setMonster(new Monster("demon", 4, "a demon with large fangs and bat wings flying"));
        Firepit.setMonster(new Monster("Death", 19,"Death dressed in a black hood with a scythe at his side"));
        Cabin3.setMonster(new Monster("cerberus",15,"a three headed dog named cerberus who guards the gates of death"));
        GreatHouse.setMonster(new Monster("Anubis",29,"a god of death named Anubis who has the head of a jackle and the body of a human"));
        // Create and install treasures
        Theater.setTreasure(new Treasure("orb", 10,
                "a golden orb that carries some weight to it"));
        Firepit.setTreasure(new Treasure("book", 25,
                "a book wrapped in black leather filled with great spells and secretes of death"));
        Cabin3.setTreasure(new Treasure("feather", 15,"a pure white feather glowing with energy"));
        GreatHouse.setTreasure(new Treasure("eye",50,"the eye of Horus made of gold and a blue Jewel"));
        // Create and install weapons
        Theater.setWeapon(new Weapon("axe", 10, "a mighty battle axe"));
        Darkwoods.setWeapon(new Weapon("sword", 20,
                " a sword made of glittering bronze"));
        Firepit.setWeapon(new Weapon("scythe",30,"a scythe with a long curved blade that belongs to death" ));
        // The player starts in the entrance, armed with a sword
        currentRoom = MessHall;
        bestWeaponDamage = 3;
    }

    /**
     * Attacks the specified monster and prints the result. If the monster is
     * present, this either kills it (if the player's weapon is good enough) or
     * results in the player's death (and the end of the game).
     */
    public void attack(String name) {
        Monster monster = currentRoom.getMonster();
        if (monster != null && monster.getName().equals(name)) {
            if (bestWeaponDamage > monster.getArmor()) {
                System.out.println("You strike it dead!");
                currentRoom.setMonster(null);
            } else {
                System.out.println("Your blow bounces off harmlessly.");
                System.out.println("The " + monster.getName() + " slices you in half!");
                System.out.println();
                System.out.println("GAME OVER");
                System.exit(0);
            }
        } else {
            System.out.println("There is no " + name + " here.");
        }
    }

    /** Moves in the specified direction, if possible. */
    public void go(String direction) {
        Room destination = currentRoom.getNeighbor(direction);
        if (destination == null) {
            System.out.println("You can't go that way from here.");
        } else {
            currentRoom = destination;
        }
    }

    /** Handles a command read from standard input. */
    public void handleCommand(String line) {
        String[] words = line.split(" ");
        if (words[0].equals("go")) {
            go(words[1]); //switched the if statement to make the player able to go in different directions without having to kill the monster
        }else if (currentRoom.getMonster() != null
                && !(words[0].equals("attack") || words[0].equals("look"))) {
            System.out.println("You can't do that with unfriendlies about.");
            listCommands();
        } else if (words[0].equals("attack")) {
            attack(words[1]);
        } else if (words[0].equals("look")) {
            look();
        } else if (words[0].equals("take")) {
            take(words[1]);
        } else {
            System.out.println("I don't understand that.");
            listCommands();
        }
    }

    /** Prints examples of possible commands as a hint to the player. */
    public void listCommands() {
        System.out.println("Examples of commands:");
        System.out.println("  attack demon");
        System.out.println("  go east");
        System.out.println("  look");
        System.out.println("  take orb");
    }

    /** Prints a description of the current room and its contents. */
    public void look() {
        System.out.println("You are in " + currentRoom.getDescription() + ".");
        if (currentRoom.getMonster() != null) {
            System.out.println("There is "
                    + currentRoom.getMonster().getDescription() + " here.");
        }
        if (currentRoom.getWeapon() != null) {
            System.out.println("There is "
                    + currentRoom.getWeapon().getDescription() + " here.");
        }
        if (currentRoom.getTreasure() != null) {
            System.out.println("There is "
                    + currentRoom.getTreasure().getDescription() + " here.");
        }
        System.out.println("Exits: " + currentRoom.listExits());
    }

    /** Runs the game. */
    public void run() {
        listCommands();
        System.out.println();
        while (true) {
            System.out.println("You are in the " + currentRoom.getName() + ".");
            System.out.print("> ");
            handleCommand(StdIn.readLine());
            System.out.println();
        }
    }

    /** Attempts to pick up the specified treasure or weapon. */
    public void take(String name) {
        Treasure treasure = currentRoom.getTreasure();
        Weapon weapon = currentRoom.getWeapon();
        if (treasure != null && treasure.getName().equals(name)) {
            currentRoom.setTreasure(null);
            score += treasure.getValue();
            System.out.println("Your score is now " + score + " out of 100.");
            if (score == 100) {
                System.out.println();
                System.out.println("YOU WIN!");
                System.exit(0);
            }
        } else if (weapon != null && weapon.getName().equals(name)) {
            currentRoom.setWeapon(null);
            if (weapon.getDamage() > bestWeaponDamage) {
                bestWeaponDamage = weapon.getDamage();
                System.out.println("You'll be a more effective fighter with this!");
            }
        } else {
            System.out.println("There is no " + name + " here.");
        }
    }
}

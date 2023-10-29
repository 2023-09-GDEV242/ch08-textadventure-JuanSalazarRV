import java.util.Stack;
/**
 *  This class is the main class of the "Lost in mall" application. 
 *  "Lost in mall" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery and find keys so they can win the game. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Juan J salazar
 * @version 2023.10.25
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Room roomStacks[];
    private int top;
    private int timeHours;
    private int timeMin;
    private final int TIME_LIMIT = 6;
    private int hungerLevel;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomStacks = new Room[1000];
        top = -1;
        hungerLevel = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room spawnPoint, hallwayEast, hallwayEastSecondFloor, hallwayEastThirdFloor, pink, pandora, macys, macysSecondFloor, 
        macysThirdFloor, hallwayWest, hallwayWestSecondFloor, hallwayWestThirdFloor, hottopic, 
        gamestop, bloomingdales,bloomingdalesSecondFloor, bloomingdalesThirdFloor, 
        hallwayNorth, att, apple, secondFloor,  forever21, claires, lego, starbucks, thirdFloor, cinema, garage, 
        holister, foodQuarter, exit, restroom;
        
        Item pandoraItems[] = {new Item("ring",1)};
        
        
      
        // create the rooms
        spawnPoint = new Room("in the main entrance of the mall(1st floor)");
        hallwayEast = new Room("in the east hallway of the mall(1st floor)");
        hallwayEastSecondFloor = new Room("in the east hallway of the mall(2nd floor)");
        hallwayEastThirdFloor = new Room("in the east hallway of the mall(3rd floor)");
        hallwayWest = new Room("in the west hallway of the mall(1st floor)");
        hallwayWestSecondFloor = new Room("in the west hallway of the mall(2nd floor)");
        hallwayWestThirdFloor = new Room("in the west hallway of the mall(3rd floor)");
        hallwayNorth = new Room("in the north hallway of the mall(1st floor)");
        pink = new Room("in the clothing store, Pink");
        pandora = new Room("in the jewery store, Pandora");
        macys = new Room("in the store of Macy's");
        macysSecondFloor = new Room("in the second floor of Macy's");
        macysThirdFloor = new Room("in the third floor of Macy's");
        hottopic = new Room("in HotTopic");
        gamestop = new Room("in the game store of GameStop");
        bloomingdales= new Room("in the store of Bloomingdale's");
        bloomingdalesSecondFloor = new Room("in the second floor of Bloomingdale's");
        bloomingdalesThirdFloor = new Room("in the third floor of Bloomingdales");
        att = new Room("in the phone store, AT&T");
        apple = new Room("in the phone store, Apple");
        secondFloor = new Room("at the top of the stairs of the second floor of the mall");
        forever21 = new Room("in the clothing store, Forever 21");
        claires = new Room("in the store of Claire's");
        lego = new Room("in the Lego store");
        starbucks = new Room("in the coffee store, Starbucks");
        thirdFloor = new Room("at the top of the stairs of the third floor of the mall");
        cinema = new Room("in the cinema");
        garage = new Room("in the clothing store, Garage");
        holister = new Room("in the clothing store, Holister");
        foodQuarter = new Room("in the food quarter of the mall");
        exit = new Room("in the main exit");
        restroom = new Room("in the restrooms by the food quarter");
        
        // add items
        
        pandora = addToRoom(pandora, pandoraItems);
        
        // initialise room exits
        spawnPoint.setExit("east", hallwayEast);
        spawnPoint.setExit("north", hallwayNorth);
        spawnPoint.setExit("west", hallwayWest);
        spawnPoint.setExit("south", exit);
        
        hallwayEast.setExit("north", pink);
        hallwayEast.setExit("east", macys);
        hallwayEast.setExit("south", pandora);
        hallwayEast.setExit("west", spawnPoint);

        hallwayNorth.setExit("west", apple);
        hallwayNorth.setExit("east", att);
        hallwayNorth.setExit("south", spawnPoint);
        hallwayNorth.setExit("north", secondFloor);
        
        hallwayWest.setExit("west", bloomingdales);
        hallwayWest.setExit("east", spawnPoint);
        hallwayWest.setExit("south", gamestop);
        hallwayWest.setExit("north", hottopic);
        
        exit.setExit("north", spawnPoint);
        
        pink.setExit("south", hallwayEast);
        pandora.setExit("north", hallwayEast);
        macys.setExit("west", hallwayEast);
        macys.setExit("north", macysSecondFloor);
        
        macysSecondFloor.setExit("north", macysThirdFloor);
        macysSecondFloor.setExit("west", hallwayEastSecondFloor);
        macysSecondFloor.setExit("south", macys);
        
        macysThirdFloor.setExit("west", hallwayEastThirdFloor);
        macysThirdFloor.setExit("south", macysSecondFloor);
        
        apple.setExit("east", hallwayNorth);
        att.setExit("west", hallwayNorth);
                
        hottopic.setExit("south", hallwayWest);
        gamestop.setExit("north", hallwayWest);
        
        bloomingdales.setExit("east", hallwayWest);
        bloomingdales.setExit("north", bloomingdalesSecondFloor);
        
        bloomingdalesSecondFloor.setExit("north", bloomingdalesThirdFloor);
        bloomingdalesSecondFloor.setExit("east", hallwayWestSecondFloor);
        bloomingdalesSecondFloor.setExit("south", bloomingdales);
        
        bloomingdalesThirdFloor.setExit("east", hallwayWestThirdFloor);
        bloomingdalesThirdFloor.setExit("south", bloomingdalesSecondFloor);
        
        //second floor
        secondFloor.setExit("east", hallwayEastSecondFloor);
        secondFloor.setExit("north", thirdFloor);
        secondFloor.setExit("west", hallwayWestSecondFloor);
        secondFloor.setExit("south", hallwayNorth);
        
        hallwayEastSecondFloor.setExit("north", starbucks);
        hallwayEastSecondFloor.setExit("east", macysSecondFloor);
        hallwayEastSecondFloor.setExit("south", lego);
        hallwayEastSecondFloor.setExit("west", secondFloor);
        
        hallwayWestSecondFloor.setExit("north", forever21);
        hallwayWestSecondFloor.setExit("east", secondFloor);
        hallwayWestSecondFloor.setExit("south", claires);
        hallwayWestSecondFloor.setExit("west", bloomingdalesSecondFloor);
        
        starbucks.setExit("south", hallwayEastSecondFloor);
        lego.setExit("north", hallwayEastSecondFloor);
        
        forever21.setExit("south", hallwayWestSecondFloor);
        claires.setExit("north", hallwayWestSecondFloor);

        //third floor
        thirdFloor.setExit("east", hallwayEastThirdFloor);
        thirdFloor.setExit("west", hallwayWestThirdFloor);
        thirdFloor.setExit("south", secondFloor);
        
        hallwayEastThirdFloor.setExit("north", holister);
        hallwayEastThirdFloor.setExit("east", macysThirdFloor);
        hallwayEastThirdFloor.setExit("south", foodQuarter);
        hallwayEastThirdFloor.setExit("west", thirdFloor);
        
        hallwayWestThirdFloor.setExit("north", cinema);
        hallwayWestThirdFloor.setExit("east", thirdFloor);
        hallwayWestThirdFloor.setExit("south", garage);
        hallwayWestThirdFloor.setExit("west", bloomingdalesThirdFloor);

        holister.setExit("south", hallwayEastThirdFloor);
        foodQuarter.setExit("north", hallwayEastThirdFloor);
        foodQuarter.setExit("west", restroom);
        
        cinema.setExit("south", hallwayWestThirdFloor);
        garage.setExit("north", hallwayWestThirdFloor);
        
        restroom.setExit("east", foodQuarter);

        currentRoom = spawnPoint;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if(timeHours >= TIME_LIMIT)
            {
                System.out.println("Time is up! Its "+ TIME_LIMIT+ " am");
                finished = true;
            }
            
            else if(hungerLevel == 100)
            {
                System.out.println("You have die due to starvation.");
                finished = true;
            }
            
            else{
                Command command = parser.getCommand();
                finished = processCommand(command);
                timeMin += 5;
                checkHungerLevels();
                if(timeMin == 60)
                {
                    timeHours++;
                    timeMin = 0;
                }
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to 'lost in mall'");
        System.out.println("You have been trap inside the mall after closing");
        System.out.println("You need to find a way to get out before they open at "+ TIME_LIMIT + " AM");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                System.out.println("You have eaten now, you are not hungry anymore.");
                hungerLevel = 0;
                break;
                
            case BACK:
                goBack();
                break;
                
            case TIME:
                printTime();
                break;
               
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at a close mall.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    /**
     * prints the current time.
     */
    private void printTime()
    {

        if(timeMin < 10)
            System.out.printf("It's %d:0%d A.M",timeHours, timeMin);
        else
            System.out.printf("It's %d:%d A.M",timeHours, timeMin);
        System.out.println();
        System.out.println("You have " + (TIME_LIMIT - timeHours) + " hours left");
    }
    
    /**
     * prints the description of the current room
     */
    private void look()
    {   
     System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    /**
     * checks whether the player is hungry or not.
     */
    private void checkHungerLevels()
    {
        if(hungerLevel == 50)
            System.out.println("You are getting hungry, do not forget to eat");
        else if(hungerLevel == 85)
            System.out.println("You are extremely hungry, you should eat something now");
        
        hungerLevel += 5;
            
    }
    
    /**
     * add a items to the room
     * @param room The room in which the item is going to be add
     * @param item[] The item(s) that are going to be add
     * 
     * @return The room with the items in it
     */
    private Room addToRoom(Room room, Item item[])
    {
        for(int i = 0; i < item.length; i++)
        {
            room.addItem(item[i]);
        }
        return room;
    }
    
    /**
     * Goes back to the previous room.
     */
    
    private void goBack()
    {
        currentRoom = pop();
        
        if(currentRoom != null)
            System.out.println(currentRoom.getLongDescription());
        
    }
    /**
     * Adds or "push" a room to an stack.
     */
    private void push(Room room)
    {
        if(top == roomStacks.length - 1)
            System.out.println("Max capacity of room stacks have been reached!!");
        else
            roomStacks[++top] = room;
    }
    
    /**
     * deletes or "pop" the room at the top of the stack
     * @return room at top only if exits. If not, it returns null.
     */
    private Room pop()
    {
        if(top < 0)
            return null;
        else
            return 
                roomStacks[top--];
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

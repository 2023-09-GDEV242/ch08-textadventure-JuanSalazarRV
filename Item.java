
/**
 * This class creates items for the game.
 *
 * @author Juan J salazar
 * @version 2023.10.29
 */
public class Item
{
    
    private String itemDescription;
    private int itemWeight;
    

    /**
     * no-arg constructor
     */
    public Item()
    {
        itemWeight = 0;
        itemDescription = "";
    }
    
    /**
     * constructor
     * @param description The initial description of an item
     * @param weight The initial weight of an item
     */
    public Item(String description, int weight)
    {     
        itemDescription = description;
        itemWeight = weight;
    }

    /**
     * It prints the description of the item plues weight in pounds.
     */
    public String getDescription()
    {       
        return itemDescription + "\nWeight: " + itemWeight + "lb.";
    }
}

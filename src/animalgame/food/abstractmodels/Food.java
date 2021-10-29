package animalgame.food.abstractmodels;

import java.io.Serializable;

/**
 *
 */
public abstract class Food implements Serializable {
    private String name;
    private int weight;

    /**
     * Constructor for the Food class
     * Initialize name and weight decided by parameters.
     * @param name Name of this food
     * @param weight Gender of this food
     */
    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * return the name of this food
     * @return name as String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Adds weight to this food
     * @param weight int to add it to value
     */
    public void addWeight(int weight){
    this.weight += weight;
    }

    /**
     * Return the weight of this food
     * @return weight as int
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Remove weight from this food
     * @param weight int to remove it from value
     */
    public void removeWeight(int weight){
        this.weight -= weight;
    }
}
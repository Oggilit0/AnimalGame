package animalgame.food.abstractmodels;

import java.io.Serializable;

/**
 *
 */
public abstract class Food implements Serializable {
    private String name;
    private int weight;

    /**
     *
     * @param name
     * @param weight
     */
    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     *
     * @param weight
     */
    public void addWeight(int weight){
    this.weight += weight;
    }

    /**
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void removeWeight(int weight){
        this.weight -= weight;
    }
}


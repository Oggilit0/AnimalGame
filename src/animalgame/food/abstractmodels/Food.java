package animalgame.food.abstractmodels;

import java.io.Serializable;

public abstract class Food implements Serializable {
    private String name;
    private int weight;

    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName(){
        return this.name;
    }

    public void addWeight(int weight){
    this.weight += weight;
    }

    public int getWeight() {
        return weight;
    }

    public void removeWeight(int weight){
        this.weight -= weight;
    }
}


package animalgame.food;

import animalgame.food.abstractmodels.Food;

/**
 * Waffles class is a subclass that inherit from Food.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Waffles extends Food {
    /**
     * Constructor for the Waffles class
     * Initialize name and weight decided by parameters.
     * @param name Name of this food
     * @param weight Weight of this food
     */
    public Waffles(String name, int weight) {
        super(name, weight);
    }
}

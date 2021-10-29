package animalgame.food;

import animalgame.food.abstractmodels.Food;

/**
 * Sausage class is a subclass and inherit from Food.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Sausage extends Food {
    /**
     * Constructor for the Sausage class
     * Initialize name and weight decided by parameters.
     * @param name Name of this food
     * @param weight Gender of this food
     */
    public Sausage(String name, int weight) {
        super(name, weight);
    }
}

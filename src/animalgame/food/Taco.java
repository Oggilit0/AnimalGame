package animalgame.food;

import animalgame.food.abstractmodels.Food;

/**
 * Sausage class is a subclass that inherit from Food.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Taco extends Food {
    /**
     * Constructor for the Taco class
     * Initialize name and weight decided by parameters.
     * @param name Name of this food
     * @param weight Weight of this food
     */
    public Taco(String name, int weight) {
        super(name, weight);
    }
}

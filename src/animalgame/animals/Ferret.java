package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;

/**
 *
 */
public class Ferret extends Animal {
    /**
     * Constructor for the Ferret class.
     * Initialize this animal starting price and maximum age.
     * Initialize name and gender decided by parameters.
     * @param name Name of this animal
     * @param gender Gender of this animal
     */
    public Ferret(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(2250);
        super.setMaxAge(9);
    }

    /**
     * @param foodToEat as food object
     * @return food preference as a boolean
     */
    @Override
    public boolean eat(Food foodToEat) {
        if (foodToEat instanceof Waffles) {
            System.out.println("Your ferret doesn´t eat: " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}



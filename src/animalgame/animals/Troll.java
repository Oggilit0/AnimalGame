package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.abstractmodels.Food;

/**
 * Troll class is a subclass and inherit from Animal.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Troll extends Animal {
    /**
     * Constructor for the Troll class.
     * Initialize this animal starting price and maximum age.
     * Initialize name and gender decided by parameters.
     * @param name Name of this animal
     * @param gender Gender of this animal
     */
    public Troll(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(800);
        super.setMaxAge(7);
    }

    /**
     * If foodToEat instance of a food the animal doesn't eat returns false and an output to the console.
     * If true the animal eats the food object it is given.
     * @param foodToEat as food object
     * @return food preference as a boolean
     */
    @Override
    public boolean eat(Food foodToEat) {
        if(foodToEat instanceof Sausage){
            System.out.println("Your troll doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}

package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Taco;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;

/**
 *
 */
public class PolarBear extends Animal {
    /**
     * Constructor for the PolarBear class.
     * Initialize this animal starting price and maximum age.
     * Initialize name and gender decided by parameters.
     * @param name Name of the animal
     * @param gender Gender of the animal
     */
    public PolarBear(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(1500);
        super.setMaxAge(20);
    }

    /**
     * @param foodToEat as food object
     * @return food preference as a boolean
     */
    @Override
    public boolean eat(Food foodToEat) {
        if(foodToEat instanceof Taco || foodToEat instanceof Waffles){
            System.out.println("Your polar bear doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}

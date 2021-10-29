package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;

/**
 *
 */
public class Dragon extends Animal {
    /**
     * Constructor for the Mexican_Alligator_Lizard class.
     * Initialize this animal starting price and maximum age.
     * Initialize name and gender decided by parameters.
     * @param name Name of this animal
     * @param gender Gender of this animal
     */
    public Dragon(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(4000);
        super.setMaxAge(10);
    }

    /**
     * @param foodToEat as food object
     * @return food preference as a boolean
     */
    @Override
    public boolean eat(Food foodToEat) {
        if(foodToEat instanceof Sausage || foodToEat instanceof Waffles){
            System.out.println("Your Dragon doesn't eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}


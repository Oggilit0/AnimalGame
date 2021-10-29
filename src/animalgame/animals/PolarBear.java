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
     *
     * @param name
     * @param gender
     */
    public PolarBear(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(1500);
        super.setMaxAge(20);
    }

    /**
     *
     * @param foodToEat as food object
     * @return
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

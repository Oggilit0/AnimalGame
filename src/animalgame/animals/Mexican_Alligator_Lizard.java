package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;

/**
 *
 */
public class Mexican_Alligator_Lizard extends Animal {
    /**
     *
     * @param name
     * @param gender
     */
    public Mexican_Alligator_Lizard(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(4000);
        super.setMaxAge(10);
    }

    /**
     *
     * @param foodToEat as food object
     * @return
     */
    @Override
    public boolean eat(Food foodToEat) {
        if(foodToEat instanceof Sausage || foodToEat instanceof Waffles){
            System.out.println("Your mexican alligator lizard doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}


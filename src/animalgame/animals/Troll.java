package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.abstractmodels.Food;

/**
 *
 */
public class Troll extends Animal {
    /**
     *
     * @param name
     * @param gender
     */
    public Troll(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(800);
        super.setMaxAge(7);
    }

    /**
     *
     * @param foodToEat as food object
     * @return
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

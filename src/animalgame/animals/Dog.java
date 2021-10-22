package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Fish;
import animalgame.food.Grass;
import animalgame.food.abstractmodels.Food;

public class Dog extends Animal {

    public Dog(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }

    @Override
    public boolean eat(Food foodToEat) {
        if (foodToEat instanceof Grass || foodToEat instanceof Fish) {
            System.out.println("Your dog doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }

    }

}

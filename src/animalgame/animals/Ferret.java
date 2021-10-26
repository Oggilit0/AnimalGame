package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;


public class Ferret extends Animal {

    public Ferret(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }

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



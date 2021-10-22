package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Grass;
import animalgame.food.Meat;
import animalgame.food.abstractmodels.Food;
import animalgame.utilities.ProgramUtils;


public class Cat extends Animal {

    public Cat(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }

    @Override
    public boolean eat(Food foodToEat) {
        if (foodToEat instanceof Grass ) {
            System.out.println("Your cat doesn´t eat: " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}



package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Fish;
import animalgame.food.abstractmodels.Food;

public class PolarBear extends Animal {

    public PolarBear(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }

    @Override
    public boolean eat(Food foodToEat) {
        if(foodToEat instanceof Fish){
            System.out.println("Your horse doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }

    }


}

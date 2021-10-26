package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.Taco;
import animalgame.food.Waffles;
import animalgame.food.abstractmodels.Food;

public class Mexican_Alligator_Lizard extends Animal {

    public Mexican_Alligator_Lizard(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }

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


package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.Taco;
import animalgame.food.abstractmodels.Food;

public class Giraffe extends Animal {

    public Giraffe(String name, Gender gender) {
        super(name, gender);
        super.setAnimalPrice(1000);
        super.setMaxAge(15);
    }

    @Override
    public boolean eat(Food foodToEat) {
        if (foodToEat instanceof Sausage || foodToEat instanceof Taco) {
            System.out.println("Your giraffe doesn´t eat " + foodToEat.getName());
            return false;
        }else{
            return true;
        }
    }
}

package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

public class Dog extends Animal {

    public Dog(String name, int value, int maxAge, Gender gender, Player owner) {
        super(name, value, maxAge, gender, owner);
    }
}

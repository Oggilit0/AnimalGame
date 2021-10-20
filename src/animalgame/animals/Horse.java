package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

public class Horse extends Animal {

    public Horse(String name, int value, int maxAge, Gender gender) {
        super(name, value, maxAge, gender);
    }
}

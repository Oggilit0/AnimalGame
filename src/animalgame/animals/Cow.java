package animalgame.animals;

import animalgame.Player;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

public class Cow extends Animal {

    public Cow(String name, int value, int maxAge, Gender gender, Player owner) {
        super(name, value, maxAge, gender, owner);
    }
}

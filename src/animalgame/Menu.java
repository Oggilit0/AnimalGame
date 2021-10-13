package animalgame;

import java.util.Collections;

public class Menu {
    private Game currentGame;

    public Menu(Game currentGame){
        this.currentGame = currentGame;
    }

    public Object playerAnimalsAsMenu(){

        return(ProgramUtils.menuBuilder("Player animals","Cat","Cow","Dog","Horse","Snake"));
    }



    public void roundMenu(){

        switch(ProgramUtils.menuBuilder("Choose one","Shop","Feed animals","Mate animals")){
            case 1:
                shopMenu();
                break;
            case 2:
                playerAnimalsAsMenu();
                feedAnimalsMenu();
                break;
            case 3:
                playerAnimalsAsMenu();
                break;
            default:
                roundMenu();
        }
    }

    public void shopMenu(){

        switch(ProgramUtils.menuBuilder("Shop","Buy animals","Buy Food","Sell animals")){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }
    }

    public void feedAnimalsMenu(){


        int i = 1;
        for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()){
            System.out.println(i + ".\t"+ animal.getClass() + " :  namn:" +animal.getName());
            i++;
        }

    }

    public void mateAnimalsMenu(){

        switch(ProgramUtils.menuBuilder("Shop","Buy animals","Buy Food","Sell animals")){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }
    }


}

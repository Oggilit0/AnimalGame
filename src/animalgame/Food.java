package animalgame;

public abstract class Food {
    private String name;
    private int weight;

    protected Food(String name, int weight) {
        this.name = name;
        this.weight = weight;

    }

    public String getName(){
        
        return this.name;

    }

    public void addWeight(int weight){
    this.weight += weight;
    }

    public int getWeight() {
        return weight;
    }
}


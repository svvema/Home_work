import java.util.ArrayList;

public class Box<T extends Fruit> {
        private ArrayList<T> vault = new ArrayList<T>();
    public <T> Box() {
    }
    public void addFruit(T fruit){
        vault.add(fruit);
    }
    public float getBoxWeight() {
        if (vault.size()==0)return 0; else
        return vault.size()*vault.get(0).getWeight();
        }
    public boolean compare(Box box){
        return Math.abs(this.getBoxWeight() - box.getBoxWeight())<0.0001;
    }
    public ArrayList<T> getVault() {
        return vault;
    }
    public void moveFruits(Box<T> to){
        to.getVault().addAll(this.getVault());
        this.getVault().clear();
    }
}

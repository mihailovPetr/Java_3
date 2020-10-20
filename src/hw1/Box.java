package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits;

    public Box(){
        this.fruits = new ArrayList<>();
    }
    public Box(List<T> fruits){
        this.fruits = new ArrayList<>(fruits);
    }
    public Box(T... fruits){
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public void add(T... fruits){
        this.fruits.addAll(Arrays.asList(fruits));
    }
    public void add(List<T> fruits){
        this.fruits.addAll(fruits);
    }
    public float getWeight() {
        if (fruits.size() == 0) {
            return 0;
        }
        return fruits.size() * (fruits.get(0).getWeight());
    }

    public boolean compare(Box<?> another){
        return Math.abs(getWeight() - another.getWeight()) < 0.0001;
    }

    public void clearBox(){
        fruits.clear();
    }
    public void pourOver(Box<T> dest){
        if (this == dest){
            return;
        }
        dest.add(fruits);
        clearBox();
    }

    @Override
    public String toString() {
        return fruits.toString();
    }
}

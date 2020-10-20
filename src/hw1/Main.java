package hw1;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 2, 4, 3, 2, 6, 3};
        changeElements(arr, 0, 7);

        ArrayList r = asArrayList(1, 2, 3, 4, 5);
        System.out.println(r);


        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 90; i++) {
            appleBox.add(new Apple());
        }

        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 60; i++) {
            orangeBox.add(new Orange());
        }
        System.out.println(appleBox.compare(orangeBox));

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox.pourOver(orangeBox1);

        System.out.println(orangeBox1);

    }


    public static <T> void changeElements(T[] arr, int n1, int n2) {
        T t = arr[n2];
        arr[n2] = arr[n1];
        arr[n1] = t;
    }

    public static <T> ArrayList<T> asArrayList(T... arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }


}

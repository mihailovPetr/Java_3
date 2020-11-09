package hw7;

public class Main {

    public static void main(String[] args) {
        Testing.start(Main.class);
    }

    @Test(priority = 3)
    public static void test1(){
        System.out.println("test1");
    }
    @Test(priority = 2)
    public static void test2(){
        System.out.println("test2");
    }
    @Test(priority = 1)
    public static void test3(){
        System.out.println("test3");
    }
    @Test(priority = 6)
    public static void test4(){
        System.out.println("test4");
    }
}

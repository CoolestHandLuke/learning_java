package practice;

interface MyInterface {

    int myFavoriteNumber = 420;

    void instanceMethod1();
    void instanceMethod2();

    static void staticMethod() {
        System.out.println("I have to be implemented here or I will not compile!");
    }

    default void defaultMethod() {

    }

    private void privateMethod() {

        System.out.println("Can't do shit with this one.");
    }

}


public class Main implements MyInterface{

    @Override
    public void instanceMethod1() {

        System.out.println("Hello, World!");

    }

    @Override
    public void instanceMethod2() {
        
    }

    public static void main(String[] args) {

        MyInterface anObject = new Main();
        anObject.instanceMethod1();
        int newNum = MyInterface.myFavoriteNumber;
        System.out.println(newNum);
    }

    
}




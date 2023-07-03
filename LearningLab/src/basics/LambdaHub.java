import java.util.*;

/* Lambda expressions provides implementation of functional interface. 
Basically the interface should have only one abstract method, hence functional interface. 
*/
class LambdaHub {
  public static void main(String[] args) {
    /* Allowed as when compiler tries to convert the lamda expression to an instance of functional interface. 
    Java converts the lambda expression to an instane of anonymous class that implements Animal.
    */
    Animal animal = () -> {
      System.out.println("hey there from lambda");
    };

    animal.speak();

    // Anonymous Inner classes - Lambda expressions are a better way to implement functional interface rather than anon inner classes.
    Animal animal2 = new Animal() {
      public void speak() {
        System.out.println("hey from anonymous inner class");
      }
    };

    animal2.speak();

    // Using Lambda expressions
    doSomething(() -> {
      System.out.println("hey there from a much coooler lambda!");
    });
  }

  public static void doSomething(Animal animal) {
    animal.speak();
  }
}

/* The annotation @FunctionalInterface is not compulsory but is a best practice so that compiler knows that the interface is a functional interface and it will enforce it on the interface.
Functional Interface a.k.a S.A.M(Single Abstract Method) interfaces.
*/
@FunctionalInterface
interface Animal {
  public void speak();
}

import java.util.*;
import java.util.function.*;

class CollectionsHub {
  public static void main(String[] args) {
    List<String> list = List.of("satyam", "kumar", "is", "working", "from", "home");

    /* forEach is a default method in Iterable interface which takes a parameter of functional interface called Consumer.
    An implementation of functional interface consumer is provided by implementing the accept method.
    */
    Consumer<String> consumer = new Consumer<>() {
      public void accept(String s) { System.out.println(s); }
    };
    // list.forEach(consumer);

    /* This can be futher simplified to */
    // list.forEach(new Consumer<>() {
    //   public void accept(String s) { System.out.println(s); }
    // });

    /* This can be further simplified to */
    // Consumer<String> consumer2 = (value) -> { System.out.println(value); };
    // list.forEach(consumer2);

    /* Finally the highest simplification is */
    // list.forEach(string -> System.out.println(string));

    /* It can be even more simplified using Method Reference */
    list.forEach(System.out::println);
  }
}



package basics;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class OptionalsDemo {
  public static void main(String[] args) {
    OptionalsLab obj = new OptionalsLab();
    obj.runLab();
  }
}

/**
 * Optional is a container which either has some data or does not. It is a final class (cannot be extended or inherited). It
 * is used to deal with NullPointerException.
 */
class OptionalsLab {
  public void runLab() {
    System.out.println("<----- Creating Optional Objects ----->");
    // Creating an empty Optional object using static empty() method.
    Optional<String> optional1 = Optional.empty();
    System.out.println(optional1);

    // Creating optional with value using static of() method. However, we cannot pass Null object to of() method else it will
    // return NullPointerException during Runtime (Unchecked Exception)
    Optional<String> optional2 = Optional.of("hello world");
    System.out.println(optional2);

    // However if we are expecting null values as well, then we can use ofNullable() method.
    String str1 = null; // String str = "some value";
    Optional<String> optional3 = Optional.ofNullable(str1);
    System.out.println(optional3);

    System.out.println("<----- Checking Value Presence: isPresent() & isEmpty() ----->");
    // We can check if there is a value present in an Optional using the isPresent() method which will return true if wrapped
    // value is not Null. The opposite can be done with isEmpty() method.
    System.out.println("optional1 is present/empty ? " + optional1.isPresent() + "/" + optional1.isEmpty());
    System.out.println("optional2 is present/empty ? " + optional2.isPresent() + "/" + optional2.isEmpty());
    System.out.println("optional3 is present/empty ? " + optional3.isPresent() + "/" + optional3.isEmpty());

    System.out.println("<----- Conditional Action: ifPresent() ----->");
    // The ifPresent() method enables us to run some code on the wrapped values if it's found to be non-null. Before Optional
    // we would do ->
    String str2 = "hey there!";
    if(str2 != null) {
      System.out.println("str2 is not null");
    }

    // This approach is length and prone to errors. What if we forget to perform the null check in future ? Also, what if a
    // null value finds a way into the code ? Hence using Optional we can perform an action on an object only if it is present.
    Optional<String> optional4 = Optional.of(str2);
    optional4.ifPresent(name -> System.out.println("The length of string is " + name.length()));

    System.out.println("<----- Default Value: orElse() & orElseGet() ----->");
    // The orElse() method returns the wrapped value if it is present, or it's argument otherwise, which acts as a default value.
    String str3 = null;
    String str4 = Optional.ofNullable(str3).orElse("I do not know who I am!");
    System.out.println(str4);

    // The orElseGet() method also returns the wrapped value but instead of taking a value to return if the Optional value is
    // not present, It takes a supplier functional interface, which is invoked and returns the value of the invocation.
    String str5 = Optional.ofNullable(str3).orElseGet(() -> "I am a functional interface & I dont know who I am!");
    System.out.println(str5);

    System.out.println("<----- Difference between orElse and orElseGet() ----->");
    // when Null value is present, both the methods show same behaviour
    String str6 = Optional.ofNullable(str3).orElse(getMyDefault());
    String str7 = Optional.ofNullable(str3).orElseGet(this::getMyDefault);

    // When the value is not Null, orElseGet() method does not invoke the getMyDefault() method since the
    // container value is present. However, the orElse() method whether the value is present or not, the getMyDefault()
    // method is invoked and one redundant object is created. This is expensive if getMyDefault() has to make API calls.
    String str8 = Optional.ofNullable(str2).orElse(getMyDefault());
    String str9 = Optional.ofNullable(str2).orElseGet(this::getMyDefault);

    System.out.println("<----- Exceptions: orElseThrow() ----->");
    // orElseThrow() method just like orElse() and orElseGet() returns the wrapped value if present and apart from that adds a
    // new approach for handling an absent value. Instead of returning a default value when the wrapped value is not present,
    // it throws an Exception. In case of no-arg, it throws a NoSuchElementException else for a different exception we need
    // to explicitly specify it.
    String str10 = Optional.ofNullable(str2).orElseThrow(IllegalArgumentException::new);

    System.out.println("<----- Returning Values: get() ----->");
    // get() method helps to retrieve the wrapped value only if the wrapped object is not Null, else it will throw a
    // NoSuchElementException. This method does not handle the Null case and works against the objectives of Optionals.
    Optional<String> optional5 = Optional.ofNullable(str2);
    System.out.println(optional5.get());

    System.out.println("<----- Conditional Return: filter() ----->");
    // filter() method helps to run inline test on the wrapped value. It takes a predicate as an arg and returns an Optional
    // Object. If the wrapped value passes the predicate test then the Optional is returned as-is. However if the predicate
    // returns false then an empty Optional is returned.
    Bag bag = new Bag(150.5);

    // If we want to buy a bag and only care about it's price should be in range of 100-200. Traditional approach involves a
    // lot of defensive null checks
    if(bag != null && bag.getPrice() != null && bag.getPrice() >= 100.0 && bag.getPrice() <= 200.0) {
      System.out.println("Bag Price is in Range of 100-200");
    }

    // Using Optionals we can replace unnecessary if statements
    Boolean boolean1 = Optional.ofNullable(bag).map(Bag::getPrice).filter(p -> p>=100 && p<=200).isPresent();
    System.out.println("Is the Bag price in Range? " + boolean1);

    System.out.println("<----- Transforming Values: map() ----->");
    // The map() method takes an existing value performs a computation using this value and returns the result of the
    // computation wrapped inside an Optional Object. Practical Use case would be to clean up data using map() before passing
    // it forward to filter() method.
    int len = Optional.ofNullable(str2).map(String::length).orElse(0);
    System.out.println(len);

    System.out.println("<----- Transforming Values: flatMap() ----->");
    // map() method only transforms values when they are unwrapped whereas flatMap() takes a wrapped value and unwraps it
    // before transforming it.
    Person person = new Person("satyam", 25);
    Optional<Person> personOptional = Optional.of(person);

    // using map(), wraps the return value in an Optional making it a nested Optional. We unwrap it twice using orElseThrow()
    // and orElse() one after another.
    Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
    Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
    String name1 = nameOptional.orElse("");
    System.out.println(name1);

    // using flatMap(), the extra optional wrapper that getName() method returns is implicitly removed.
    String name2 = personOptional.flatMap(Person::getName).orElse("");
    System.out.println(name2);

    System.out.println("<----- Chaining Optionals - Incomeplete ----->");
    // The downside to below approach is all getEmpty(), getHello() and getBye() methods are always executed regardless of
    // where a non-empty Optional appears in Stream.
    Optional<String> optionalFound1 = Stream.of(getEmpty(), getHello(), getBye())
            .filter(Optional::isPresent).map(Optional::get).findFirst();
    System.out.println(optionalFound1);

    // To lazily evaluate our stream of Optionals, we make use of Method Reference and Supplier Interface
//    Optional<String> optionalFound2 = Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
//            .map(Supplier::get).filter(Optional::isPresent).map(Optional::get).findFirst();
//    Optional<String> optionalFound2 = Stream.of(this::getEmpty, this::getHello, this::getBye);
//            .map(Supplier::get).filter(Optional::isPresent).map(Optional::get).findFirst();
//    System.out.println(optionalFound2);

    System.out.println("<----- Java-9: or() Method ----->");
    // The or() method is used when our Optional is empty, and we want to execute some other action that also returns an
    // Optional. If our first optional has a defined value, the lambda passed to the or() method will not be invoked,
    // otherwise the lambda expression is calculated and returned.
    String str11 = null;
    Optional<String> optional6 = Optional.ofNullable(str11);
    Optional<String> optional7 = optional6.or(() -> Optional.of("default value"));
    System.out.println(optional7);

    System.out.println("<----- Java-9: ifPresentOrElse() Method Incomplete ----->");

  }

  public String getMyDefault() {
    System.out.println("Inside the getMyDefault() method");
    return "Default Value!";
  }

  public Optional<String> getEmpty() {
    System.out.println("Inside the getEmpty() method");
    return Optional.empty();
  }
  public Optional<String> getHello() {
    System.out.println("Inside the getHello() method");
    return Optional.of("hello");
  }
  public Optional<String> getBye() {
    System.out.println("Inside the getBye() method");
    return Optional.of("bye");
  }
  public Optional<String> createOptional(String input) {
    if (input == null || "".equals(input) || "empty".equals(input)) { return Optional.empty(); }
    return Optional.of(input);
  }
}

class Bag {
  public Double price;
  Bag(double price) { this.price = price; }
  public Double getPrice() {return this.price;}
}

class Person {
  private String name;
  private int age;
  private String password;
  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public Optional<String> getName() { return Optional.ofNullable(name); }
  public Optional<Integer> getAge() { return Optional.ofNullable(age); }
  public Optional<String> getPassword() { return Optional.ofNullable(password); }
}

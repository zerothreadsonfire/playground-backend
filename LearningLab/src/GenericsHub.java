import java.util.*;

/**
 * Helps in compile time checking and provides type safety against casting.
 */
class GenericsHub {
  public static void main(String[] args) {
    Printer<String> p1 = new Printer<>("hey there!");
    Printer<Integer> p2 = new Printer<>(23);

    p1.print();
    p2.print();
  }
}

class Printer<T> {
  T printer;

  Printer(T printer) {
    this.printer = printer;
  }

  public void print() {
    System.out.println(printer);
  } 
}
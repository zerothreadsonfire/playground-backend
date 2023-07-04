package basics;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class StreamsHub {
  public static void main(String[] args) {
    List<Employee> list = List.of(new Employee(1, "satyam", "kumar", 500, "govo"), 
    new Employee(2, "ajay", "jadeja", 600, "msft"), new Employee(3, "dan", "brown", 900, "books"));
    

    /* The Stream.of() method creates and returns a sequential stream of given items. Else collection.stream() can also be used since stream() is a default method in Collection interface.
    */
    Stream<List<Employee>> stream = Stream.of(list);

    /* forEach is Terminal Operation. It returns void, it can be used when we want to iterate over every value of it and modify it and add to some other collection.
    */
    // list.stream().forEach(val -> System.out.println(val));
    // list.stream().forEach(val -> new Employee(val.id, val.fname, val.lname, val.salary, val.project));

    /* Map
    Map is intermediate operation that transforms each element in a stream by applying a specified function to it. It can be used to convert a stream of objects from one type to another or just extract a prop or field from each of the objects in a stream. It returns a stream itself and we should ensure we are returning non-void in the map method.
    */
    // list.stream().map((employee) -> employee.project.toUpperCase()).forEach(value -> System.out.println(value));
    // OR
    list.stream().map((employee) -> employee.project).map(value -> value.toUpperCase()).forEach(value -> System.out.println(value));

    /* Collect 
    It allows us to repackage elements to some D.S and applying some additional logic, concatenating them, etc. on data elements. The strategy for this operation is provided via the Collector interface implementation.
    Collectors.toList() - It is used for collecting all Stream elements into a List instance
    Collectors.toSet() - It is used for collecting all Stream elements into a Set instance
    */
    Set<Employee> newset = list.stream().collect(Collectors.toSet());
    System.out.println(newset);

    System.out.println("<---- Collectors toMap() ---->");
    /** 
     * Collectors.toMap() - It is used for collecting all Stream elements into a Map instance. It takes a key mapper and a value mapper.
     * 
    */
    // Map<String, Integer> map = list.stream().map(employee -> employee.fname)
    //   .collect(Collectors.toMap(Function.identity(), String::length));
    Map<String, Float> map = list.stream().collect(Collectors.toMap(emp -> emp.lname, emp -> emp.salary));
    System.out.println(map);

    System.out.println("<---- Collectors collectingAndThen() ---->");
    /** 
     * Collectors.collectingAndThen() - It is used for collecting all Stream elements into a Map instance. It takes a key mapper and a value mapper.
     * 
    */
    list.stream().collect(Collectors.toMap(emp -> emp.lname, emp -> emp.salary));
    // System.out.println(map);

    System.out.println("<---- Filter ---->");
    /** Filter - replacement for if-ele condition. Intermediate opn. It accepts a Predicate which is a Functional Interface
     * with SAM test() that returns a boolean.
    */
    list.stream().filter(employee -> employee.salary <= 800).forEach(value -> System.out.println(value));
    
    System.out.println("<---- FindFirst ---->");
    /* FindFIrst - FindFirst instance of item. It is terminal opn. Returns an Optional - it may have the value or not have the value as well.
    */
    Employee firstEmp = list.stream().filter(employee -> employee.salary <= 400).findFirst().orElse(null);
    System.out.println(firstEmp);

    System.out.println("<---- FlatMap ---->");
    /* FlatMap - It is an intermediate opn.
    */
    List<List<List<String>>> multilist = List.of(List.of(List.of("s1","s2"), List.of("s3","s4")), List.of(List.of("s5","s6"), List.of("s7","s8")));
    System.out.println(multilist);
    multilist.stream().flatMap(values -> values.stream()).forEach(System.out::println);
    // OR
    // multilist.stream().flatMap(Collection::stream).forEach(System.out::println);

    /* Joining
    */ 
    String finalstr = 
    multilist.stream().flatMap(values -> values.stream().flatMap(Collection::stream)).collect(Collectors.joining("::"));
    System.out.println(finalstr);


    System.out.println("<---- Short Circuit Operations ---->");
    /** Short Circuit Operations 
     * Skip(n) method skips the first n elements in a stream. It cant be negative & if it is higher than size of stream it returns empty stream.
     * Limit(n) method limits the stream length to size n. It cannot have more than n items. 
     * Unlike skip() which consumes the entire stream, limit() doesn't consume any more than the required items (n) and simpply returns the resulting stream. Hence limit() is a short circuit operation.
     * */  
    list.stream().skip(1).limit(1).forEach(value -> System.out.println(value));

    System.out.println("<---- Infinite to Finite ---->");
    /** Finite 
     * generate() - Returns an infinite sequential unordered stream where each element is generated by the provided Supplier.
     * iterate() - Returns an infinite sequential ordered Stream produced by iterative application of a function f to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
     */
    // Stream.generate(Math::random).limit(2).forEach(System.out::println);
    Stream.iterate(5, i -> i+2).limit(3).forEach(System.out::println);

    System.out.println("<---- Sorting ---->");
    /** Sorting
     * sorted() - With no-args it sorts acc to natural order whereas with arg it expects a comparator for sorting.
     */
    // list.stream().map(value -> value.fname).sorted().forEach(System.out::println);
    list.stream().map(value -> value.fname).sorted((o1,o2) -> o2.compareToIgnoreCase(o1)).forEach(System.out::println);
    
    
    System.out.println("<---- Aggregation ---->");
    /** Aggregation - Min/Max
     */
    Employee salaryEmp = list.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
    System.out.println(salaryEmp);

    System.out.println("<---- Accumulation ---->");
    /** Accumulation
     * reduce()
     */
    float sumsalary = list.stream().map(employee -> employee.getSalary()).reduce(0.0f, Float::sum);
    System.out.println(sumsalary);
    
  }
}

class Employee {
  int id;
  String fname;
  String lname;
  float salary;
  String project;
  Employee() {}
  Employee(int id, String fname, String lname, float salary, String project) {
    this.id = id; this.fname = fname; this.lname = lname; this.salary = salary; this.project = project;
  }

  public float getSalary() { return this.salary; }

  public String toString() {
    return "["+id+", "+fname+", "+lname+", "+salary+", "+project+"]";
  }
}
import java.util.*;

class ClassesHub {
  public static void main(String[] args) {
    // Student s1 = new Student(1, "satyam", 23);
    Student s1 = new Student();
    // s1.fn();
    // System.out.println(s1);
  }
}

class Student {
  int id;
  String name;
  int age;

  // Student s1 = new Student();
  
  // Student(int id, String name, int age) {
  //   this.id = id;
  //   this.name = name;
  //   this.age = age;
  // }

/* toString() method is defined in the Object class and all classes inherit from Object class. 
  The toString() method returns the String representation of an object. Hence we return custom string representation we can override the method and give it our own implementation.

  The method has public access specifier by default and we cannot change it.
*/ 
  public String toString() {
    return "["+id+", "+name+", "+age+"]";
  }

}






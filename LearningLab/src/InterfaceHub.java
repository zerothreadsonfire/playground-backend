import java.util.*;

class InterfaceHub {
  public static void main(String[] args) {
    parent c = new superchild();
    System.out.println(c.getName());
  }
}

interface parent {
  public void speak();
  public default String getName() {
    return "this is default method inside interface parent";
  }
}

abstract class child implements parent {
  public void childspeaks() {
    System.out.println("child");
  }
}

class superchild extends child {
  public void speak() {
    System.out.println("I am speaking inside superchild");
  }

  public String getName() {
    return "this is default method inside superchild";
  }
}


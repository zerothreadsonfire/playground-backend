package basics;

class AnonymousInnerClassHub {
  public static void main(String[] args) {
    /*  A class is created but its name is decided by compiler, which extends extends/iplements person class/interface and provides the implementation of speak() method.
    An object of anonymous inner class is created and if referenced by variable 'parent', a reference variable of Parent Interface type. 
    */ 
    ParentInterface parent = new ParentInterface() {
      public void speak() {
        System.out.println("hello from anon inner class");
      }
    };

    parent.speak();

    /* Again a new class with name decided by compiler is created and it extends 'basics.ParentClass' and overrides the doSomething() method by providing its own implementation of it. Then an object of the new class is created. It is important to note that here basics.ParentClass is concrete.
    */
    ParentClass parentClass = new ParentClass() {
      public void doSomething() {
        System.out.println("concrete class new method implementation");
      }
    };

    parentClass.doSomething();

    // The same happens in case of Abstract class as well.
    ParentAbstractClass parentAbstractClass = new ParentAbstractClass() {
      static final int x = 10; // anon inner class can only have static constants and not just static members.

      public void doSomethingWierd() {
        System.out.println("abstract class method implementation");
      }
    };

    parentAbstractClass.doSomethingWierd();
  }
}

interface ParentInterface {
  public void speak();
}

class ParentClass {
  public void doSomething() {
    System.out.println("I dont know what to do!");
  }
}

abstract class ParentAbstractClass {
  abstract public void doSomethingWierd();
}
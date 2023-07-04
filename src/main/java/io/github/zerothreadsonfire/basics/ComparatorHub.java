import java.util.*;

/* Java Comparator/Comparable Interface are used to compare objects that aren't directly comparable(not-primitives) or when we are working with custom types.
A swap or change between two values happens if output is +1, which indicates that in [a,b] => a-b => a>b. Hence [b,a] is result.
*/
class ComparatorHub {
  public static void main(String[] args) {
    // Comparable Driver
    Student2 s1 = new Student2(1, "satyam", 24);
    Student2 s2 = new Student2(1, "satyam", 23);
    Student2 s3 = new Student2(1, "satyam", 22);
    Student2 s4 = new Student2(1, "satyam", 25);
    Vector<Student2> v = new Vector<Student2>();
    v.add(s1); v.add(s2); v.add(s3); v.add(s4);
    Collections.sort(v);
    System.out.println(v);

    // Comparator Driver
    Player p1 = new Player(1, "satyam", 23);
    Player p2 = new Player(2, "kumar", 22);
    Player p3 = new Player(3, "hello", 21);
    Vector<Player> v2 = new Vector<Player>();
    v2.add(p1); v2.add(p2); v2.add(p3);
    // Collections.sort(v2, new PlayerAgeComparator());
    // Collections.sort(v2, new PlayerRankingComparator());
    System.out.println(v2);

    /** Comparator.comparing takes a method reference and not a lambda since it is not comparing a & b, instead given an object which part of object do you want to comapre.
     * Hence a key extractor is expected as a param to comparing() method.
     */

    Comparator<Player> comparator = Comparator.comparing(Player::getRanking);
    Collections.sort(v2, comparator);
  }
}

// Comparable Example
class Student2 implements Comparable<Student2> {
  int id;
  String name;
  int age;

  Student2(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public int compareTo(Student2 s) {
    // System.out.println(this.age +"-" + s.age);
    // We use Integer.compare() instead of x-y to avoid Integer overflow problem which might come due to INT_MAX - (-1)
    return Integer.compare(this.age, s.age);
  }

  public String toString() {
    return "["+id+", "+name+", "+age+"]";
  }
}

// Comparator Example 
class Player {
  int ranking;
  String name;
  int age;

  Player(int ranking, String name, int age) {
    this.ranking = ranking;
    this.name = name;
    this.age = age;
  }

  public int getRanking() { return this.ranking; }

  public String toString() {
    return "["+ranking+", "+name+", "+age+"]";
  }
}

class PlayerRankingComparator implements Comparator<Player> {
  public int compare(Player p1, Player p2) {
    return p1.ranking - p2.ranking;
  }
}

class PlayerAgeComparator implements Comparator<Player> {
  public int compare(Player p1, Player p2) {
    return p1.age - p2.age;
  }
}

import java.util.*;

class MethodReferenceHub {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("satyma", "kumar");
    list.forEach((value) -> {
      System.out.println(value);
    });
  }
}
package basics;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Both Supplier and Callable are Functional Interfaces, which are similar in structure but different in use. Both return a
 * typed value and do not take any argument. The execution context is the discriminant.
 */
public class CallableSupplierDemo {
    public static void main(String[] args) {
        /**
         * Supplier is a FunctionalInterface that produces results without accepting any inputs. The results produced each
         * time can be same or different depending on the logic provided. The interface contains one SAM called get(), which
         * would hold the logic.
         */
        Supplier<Integer> randomSupplier = () -> new Random().nextInt();

        int count = 5;
        while(count-- > 0) System.out.println(randomSupplier.get());
    }
}



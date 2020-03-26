package fi.oulu.softwareconstruction.defensiveprogramming.Demo6Assertions;

public class App {
    public static int sum(int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += i;
        }
        return sum;
    }
    
    public static int sumWithAsserts(int k) {
        int sum = 0;
        assert sum == 0;
        //assert k >= 0;
        for (int i = 0; i < k; i++) {
            assert i >= 0;
            int oldSum = sum;
            sum += i;
            assert oldSum + i == sum;
            assert sum >= 0;
        }
        return sum;
    }
    
    public static int sumWithException(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k was negative");
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += i;
            if (sum < 0) {
                throw new IllegalArgumentException("k was too big and overflow");
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(sum(10));
        System.out.println(sum(0));
        System.out.println(sum(-10));
        System.out.println(sum(1000));
        System.out.println(sum(80000));
        System.out.println(sum(1000000));
        
        System.out.println(sumWithAsserts(10));
        System.out.println(sumWithAsserts(0));
        System.out.println(sumWithAsserts(-10));
        System.out.println(sumWithAsserts(1000));
        System.out.println(sumWithAsserts(80000));
        System.out.println(sumWithAsserts(1000000));
        
        System.out.println(sumWithException(10));
        System.out.println(sumWithException(0));
        //System.out.println(sumWithException(-10));
        System.out.println(sumWithException(1000));
        System.out.println(sumWithException(80000));
        System.out.println(sumWithException(1000000));
    }
}
package challenge4;

public class fibonacciMethod {
    public static void main(String[] args) {

        int digit = 12, t1 = 0, t2 = 1;
        System.out.print("First " + digit + " terms: ");

        for (int i = 1; i <= digit; ++i)
        {
            System.out.print(t1 + " + ");

            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }
    }
}

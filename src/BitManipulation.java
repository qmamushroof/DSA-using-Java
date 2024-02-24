import java.util.*;

public class BitManipulation {
    public static void odd_or_even(int number) {
        if ((number & 1) == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }
    }

    public static int get_ith_bit(int number, int position) {
        int bitMask = 1 << position;
        if ((number & bitMask) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int set_ith_bit(int number, int position) {
        int bitMask = 1 << position;
        return number | bitMask;
    }

    public static int clear_ith_bit(int number, int position) {
        int bitMask = ~(1 << position);
        return number & bitMask;
    }

    // another way to set and clear
    public static int update_ith_bit(int number, int position, int newBit) {
        number = clear_ith_bit(number, position);
        int bitMask = newBit << position;
        return number | bitMask;
    }

    public static int clear_last_i_bits(int number, int i) {
        int bitMask = ~0 << i;
        return number & bitMask;
    }

    public static int clear_bits_in_range(int number, int ll, int ul) {
        int a = ~0 << (ul + 1);
        int b = (1 << ll) - 1;
        int bitMask = a | b;
        return number & bitMask;
    }

    public static boolean check_if_power_of_2(int number) {
        return (number & (number - 1)) == 0;
    }

    // came in google interview
    public static int count_set_bits(int number) {
        int count = 0;
        while (number > 0) {
            if ((number & 1) == 1) {
                count++;
            }
            number = number >> 1;
        }
        return count;
    }

    public static long power(long base, long power) {
        long ans = 1;
        while (power > 0) {
            if ((power & 1) == 1) {
                ans = ans * base;
            }
            base = base * base;
            power = power >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // odd_or_even(n);
        // System.out.println(get_ith_bit(10, 3));
        // System.out.println(set_ith_bit(10, 2));
        // System.out.println(clear_ith_bit(14, 2));
        // System.out.println(update_ith_bit(14, 2, 0));
        // System.out.println(clear_last_i_bits(11, 4));
        // System.out.println(clear_bits_in_range(10, 2, 4));
        // System.out.println(check_if_power_of_2(256));
        // System.out.println(count_set_bits(10));
        System.out.println(power(2, 5));
    }
}

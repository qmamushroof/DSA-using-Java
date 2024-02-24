// import java.util.*;

public class Array {

    public static void reverse_array(int args[]) {
        int start = 0;
        int end = args.length - 1;
        int temp;
        while (start < end) {
            temp = args[start];
            args[start] = args[end];
            args[end] = temp;

            start++;
            end--;
        }
    }

    public static void find_pair(int args[]) {
        // int total_pairs = args.length * (args.length - 1) / 2;
        int count = 0;
        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                System.out.print("(" + args[i] + "," + args[j] + ")");
                count++;
            }
            System.out.println();
        }
        System.out.println("Total pairs: " + count);
    }

    public static void print_subarray(int args[]) {
        // int total_subarray = args.length * (args.length + 1) / 2;
        int maxSum = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < args.length; i++) {
            for (int j = i; j < args.length; j++) {
                sum = 0;
                for (int k = i; k <= j; k++) {
                    System.out.print(args[k] + " ");
                    sum += args[k];
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Max sum: " + maxSum);
    }

    public static int max_subarray_sum_using_prefix(int args[]) {
        int maxSum = Integer.MIN_VALUE;
        int prefix[] = new int[args.length];
        int sum;
        prefix[0] = args[0];
        for (int i = 1; i < args.length; i++) {
            prefix[i] = prefix[i - 1] + args[i];
        }

        for (int i = 0; i < args.length; i++) {
            for (int j = i; j < args.length; j++) {
                // for (int k = i; k <= j; k++) {
                // System.out.print(args[k] + " ");
                // sum += args[k];
                // if (sum > maxSum) {
                // maxSum = sum;
                // }
                // }
                sum = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int max_subarray_sum_using_Kadanes_Algo(int args[]) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int standard = Integer.MIN_VALUE, i = 0;

        for (i = 0; i < args.length; i++) {
            standard = Math.max(standard, args[i]);
        }
        standard = Math.min(standard, 0);

        for (i = 0; i < args.length; i++) {
            sum += args[i];
            if (sum < standard) {
                sum = standard;
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static int trapped_rainwater(int args[]) {
        int total_water = 0, i;
        int left_max[] = new int[args.length];
        int right_max[] = new int[args.length];

        left_max[0] = args[0];
        for (i = 1; i < args.length; i++) {
            left_max[i] = Math.max(left_max[i - 1], args[i]);
        }

        right_max[args.length - 1] = args[args.length - 1];
        for (i = args.length - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], args[i]);
        }

        for (i = 0; i < args.length; i++) {
            total_water += Math.min(left_max[i], right_max[i]) - args[i];
        }

        return total_water;
    }

    public static int trade_stocks(int args[]) {
        int max_profit = 0;
        int cost_price = Integer.MAX_VALUE;
        int i;

        for (i = 0; i < args.length; i++) {
            if (args[i] > cost_price) {
                max_profit = Math.max(max_profit, args[i] - cost_price);
            } else {
                cost_price = args[i];
            }
        }

        return max_profit;
    }

    public static void main(String[] args) {
        // int numbers[] = { -1, -2, -3, -4, -5 };

        // int numbers[] = { 1, -2, 6, -1, 3 };

        // reverse_array(numbers);
        // for(int i=0;i<numbers.length;i++){
        // System.out.print(numbers[i]+" ");
        // }

        // find_pair(numbers);

        // print_subarray(numbers);

        // System.out.println(max_subarray_sum_using_prefix(numbers));

        // System.out.println(max_subarray_sum_using_Kadanes_Algo(numbers));

        // int height[] = { 4, 2, 0, 6, 3, 2, 5 };
        // System.out.println(trapped_rainwater(height));

        int price[]={7,1,5,3,6,4};
        System.out.println(trade_stocks(price));
    }
}
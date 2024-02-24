import java.util.*;

public class Strings {

    public static boolean check_palindrome(String args) {
        int n = args.length();
        for (int i = 0; i < n / 2; i++) {
            if (args.charAt(i) != args.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String laregst_string_lexicographically(String args[]) {
        String largest = args[0];
        for (int i = 0; i < args.length; i++) {
            if (largest.compareTo(args[i]) < 1) {
                largest = args[i];
            }
        }
        return largest;
    }

    public static String first_letters_to_uppercase(String line) {
        StringBuilder sb = new StringBuilder("");
        int i = 0;
        if (line.charAt(i) != ' ') {
            sb.append(Character.toUpperCase(line.charAt(0)));
            i++;
        } else
            sb.append(line.charAt(i));
        for (; i < line.length(); i++) {
            if (line.charAt(i) == ' ' && i < line.length() - 1) {
                sb.append(line.charAt(i));
                i++;
                sb.append(Character.toUpperCase(line.charAt(i)));
            } else {
                sb.append(line.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String compress_string(String line) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < line.length(); i++) {
            Integer repeated = 1;
            sb.append(line.charAt(i));
            while (i < line.length() - 1 && line.charAt(i) == line.charAt(i + 1)) {
                repeated++;
                i++;
            }
            if (repeated > 1) {
                sb.append(repeated.toString());
            }
        }
        return sb.toString();
    }

    public static float calculate_displacement(String path) {
        int x = 0, y = 0;
        for (int i = 0; i < path.length(); i++) {
            char dir = path.charAt(i);
            if (dir == 'N')
                y++;
            else if (dir == 'E')
                x++;
            else if (dir == 'W')
                x--;
            else if (dir == 'S')
                y--;
        }
        return (float) (Math.sqrt(x * x + y * y));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        // System.out.println(check_palindrome(str));
        // System.out.println(calculate_displacement(str));

        // String fruits[] = { "apple", "mango", "banana" };
        // System.out.println(laregst_string_lexicographically(fruits));

        // System.out.println(first_letters_to_uppercase(str));

        System.out.println(compress_string(str));
    }
}

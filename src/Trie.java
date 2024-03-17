// aka Retrieval tree or Prefix tree
public class Trie {
    public static class Node {
        Node children[] = new Node[26];
        boolean endOfWord = false;
        int subBranches; // needed only for the prefix problem

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            subBranches = 1;
        }
    }

    public static Node root = new Node();

    public static void insert(String word) { // O(Length of largest word)
        Node current = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            } else {
                current.children[index].subBranches++; // needed only for the prefix problem
            }
            current = current.children[index];
        }
        current.endOfWord = true;
    }

    public static boolean search(String word) { // O(length)
        Node current = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.endOfWord == true;
    }

    public static void array_to_trie(String array[]) {
        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    // word break problem
    public static boolean word_break(String key) { // O(length)
        if (key.length() == 0) {
            return true;
        }

        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && word_break(key.substring(i))) {
                return true;
            }
        }
        return false;
    }

    public static void shortest_unique_prefix(Node root, String ans) {
        if (root == null) {
            return;
        }
        if (root.subBranches == 1) {
            // str += root.children[i];
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                shortest_unique_prefix(root.children[i], ans + (char) (i + 'a'));
            }
        }
    }

    public static boolean starts_with(String prefix) { // O(L)
        Node current = root;
        for (int level = 0; level < prefix.length(); level++) {
            int index = prefix.charAt(level) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }

    // made for count_unique_substrings
    public static void find_all_suffixes(String word) {
        for (int i = 0; i < word.length(); i++) {
            String suffix = word.substring(i);
            insert(suffix);
        }
    }

    // made for count_unique_substrings
    public static int count_nodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        for (int i = 0; i < 26; i++) {
            count += count_nodes(root.children[i]);
        }
        return count;
    }

    public static int count_unique_substrings(String word) {
        find_all_suffixes(word);
        return count_nodes(root);
    }

    public static String ans = "";

    public static void longest_word_with_all_prefixes(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }
        for (int i = 0; i < 26; i++) { // reverse loop for lexicographically larger
            if (root.children[i] != null && root.children[i].endOfWord == true) {
                char letter = (char) (i + 'a');
                temp.append(letter);
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longest_word_with_all_prefixes(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        // String words[] = { "the", "a", "there", "their", "any", "thee" };
        // String words[] = { "i", "like", "sam", "samsung", "mobile", "ice" };
        // String key = "ilikesamsung";
        // array_to_trie(words);
        // System.out.println(word_break(key));

        // System.out.println(search("an"));
        // System.out.println(search("a"));
        // System.out.println(search("ther"));
        // System.out.println(search("their"));

        // String words[] = { "zebra", "dog", "duck", "dove" };
        // array_to_trie(words);
        // root.subBranches=-1;
        // shortest_unique_prefix(root, "");
        // System.out.println(starts_with("zeb"));

        // System.out.println(count_unique_substrings("ababa"));

        String words[] = { "a", "banana", "ap", "app", "appl", "apply" };
        array_to_trie(words);
        longest_word_with_all_prefixes(root, new StringBuilder(""));
        System.out.println(ans);
    }
}

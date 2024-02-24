// aka Retrieval tree or Prefix tree
public class Trie {
    public static class Node {
        Node children[] = new Node[26];
        boolean endOfWord = false;

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    public static Node root = new Node();

    public static void insert(String word) { // O(Length of largest word)
        Node current = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
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

    public static void main(String[] args) {
        // String words[] = { "the", "a", "there", "their", "any", "thee" };
        String words[] = { "i", "like", "sam", "samsung", "mobile", "ice" };
        String key = "ilikesamsung";
        array_to_trie(words);
        System.out.println(word_break(key));

        // System.out.println(search("an"));
        // System.out.println(search("a"));
        // System.out.println(search("ther"));
        // System.out.println(search("their"));

    }
}

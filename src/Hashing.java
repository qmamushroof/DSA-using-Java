
// LinkedHashMap maintains the insertion order by using doubley linked list
// TreeMap(O(logn) for put,get,remove) sorts keys. Uses RedBlack trees instead of arrays or linked lists
// Null values not allowed in TreeSet
import java.util.HashMap;
import java.util.HashSet;
// import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class Hashing {
    static class HashMaps<K, V> { // HashMap implementation asked at Uber
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int linkedListSize = 0, arraySize;
        private LinkedList<Node> buckets[];

        @SuppressWarnings("unchecked")
        public HashMaps() {
            this.arraySize = 4;
            this.buckets = new LinkedList[4];
            for (int i = 0; i < buckets.length; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hash_function(K key) {
            int hashedValue = key.hashCode();
            return Math.abs(hashedValue) % arraySize;
        }

        private int search_in_LinkedLisk(K key, int bucketIdx) {
            LinkedList<Node> ll = buckets[bucketIdx];
            int dataIdx = 0;
            for (int i = 0; i < ll.size(); i++) {
                Node node = ll.get(i);
                if (node.key == key) {
                    return dataIdx;
                }
                dataIdx++;
            }
            return -1;
        }

        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets;
            arraySize *= 2;
            buckets = new LinkedList[arraySize];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.remove();
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) { // O(lambda)=O(1)
            int bucketIdx = hash_function(key);
            int dataIdx = search_in_LinkedLisk(key, bucketIdx);

            if (dataIdx != -1) {
                Node node = buckets[bucketIdx].get(dataIdx);
                node.value = value;
            } else {
                buckets[bucketIdx].add(new Node(key, value));
                linkedListSize++;
            }

            double lambda = (double) linkedListSize / arraySize;
            if (lambda > 2.0) {
                rehash();
            }
        }

        public boolean contains_key(K key) { // O(lambda)=O(1)
            int bucketIdx = hash_function(key);
            int dataIdx = search_in_LinkedLisk(key, bucketIdx);

            return dataIdx != -1;
        }

        public V get(K key) { // O(lambda)=O(1)
            int bucketIdx = hash_function(key);
            int dataIdx = search_in_LinkedLisk(key, bucketIdx);

            if (dataIdx != -1) {
                Node node = buckets[bucketIdx].get(dataIdx);
                return node.value;
            } else {
                return null;
            }
        }

        public V remove(K key) { // O(lambda)=O(1)
            int bucketIdx = hash_function(key);
            int dataIdx = search_in_LinkedLisk(key, bucketIdx);

            if (dataIdx != -1) {
                Node node = buckets[bucketIdx].remove(dataIdx);
                linkedListSize--;
                return node.value;
            } else {
                return null;
            }
        }

        public ArrayList<K> key_set() {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> ll = buckets[i];
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return linkedListSize == 0;
        }
    }

    // find all elements that appear more than n/3 times
    public static ArrayList<Integer> majority_element(int arr[]) { // O(n)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key) > arr.length / 3) {
                list.add(key);
            }
        }
        return list;
    }

    // O(n) but in a rare worst case O(n^2) if the buket index become the same
    public static boolean is_anagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
        }
        for (int i = 0; i < str2.length(); i++) {
            if (map.get(str2.charAt(i)) != null) {
                if (map.get(str2.charAt(i)) == 1) {
                    map.remove(str2.charAt(i));
                } else {
                    map.put(str2.charAt(i), map.get(str2.charAt(i)) - 1);
                }
            }
        }
        return map.isEmpty();
    }

    public static int count_distinct_elements(int arr[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        return set.size();
    }

    public static HashSet<Integer> union(int arr1[], int arr2[]) { // O(n+m)
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        return set;
    }

    public static HashSet<Integer> intersection(int arr1[], int arr2[]) { // O(n+m)
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            if (set.contains(arr2[i])) {
                result.add(arr2[i]);
            }
        }
        return result;
    }

    // Find itinerary for tickets problem
    public static ArrayList<String> start_to_end(HashMap<String, String> map) { // O(n)
        HashMap<String, String> reverseMap = new HashMap<>();
        for (String key : map.keySet()) {
            reverseMap.put(map.get(key), key);
        }

        String start = "";
        for (String key : map.keySet()) {
            if (!reverseMap.containsKey(key)) {
                start = key;
                break;
            }
        }

        ArrayList<String> list = new ArrayList<>();
        list.add(start);
        for (String key : map.keySet()) {
            list.add(map.get(start));
            start = map.get(start);
        }
        return list;
    }

    public static ArrayList<Integer> largest_subarray_with_0_sum(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0, length = 0, start = 0;
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (map.containsKey(sum)) {
                length = Math.max(length, j - map.get(sum));
                start = map.get(sum) + 1;
            } else {
                map.put(sum, j);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = start; i <= length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public static int count_subarray_sum_equal_to_k(int arr[], int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0;
        map.put(0, 1);
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            count += map.getOrDefault(sum, count);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        // HashMaps<String, Integer> map = new HashMaps<>();
        // map.put("Bangladesh", 16);
        // map.put("India", 150);
        // map.put("Pakistan", 20);
        // map.put("China", 140);
        // System.out.println(map);
        // System.out.println(map.get("Bangladesh"));
        // System.out.println(map.containsKey("India"));
        // int population = map.get("China");
        // System.out.println(population);
        // System.out.println(map.remove("Pakistan"));
        // System.out.println(map.size());
        // System.out.println(map.isEmpty());

        // Set needed for iterating
        // Set<String> keys = map.keySet(); // could have used entrySet instead which
        // returns pairs
        // for (String key : keys) {
        // System.out.println(key + ":" + map.get(key));
        // }

        // ArrayList<String> keys = map.key_set();
        // for (String key : keys) {
        // System.out.println(key);
        // }
        // System.out.println(map.get("China"));
        // System.out.println(map.remove("China"));
        // System.out.println(map.get("China"));

        // int arr[] = { 1, 3, 2, 5, 1, 3, 1, 5, 1 };
        // System.out.println(count_distinct_elements(arr));
        // System.out.println(majority_element(arr));
        // System.out.println(is_anagram("races", "cares"));

        // HashSet<String> cities = new HashSet<>();
        // cities.add("Dhaka");
        // cities.add("Chittagong");
        // cities.add("Khulna");
        // cities.add("Rajshahi");
        // Iterator<String> it = cities.iterator();
        // while (it.hasNext()) {
        // System.out.println(it.next());
        // }
        // for (String city : cities) {
        // System.out.println(city);
        // }

        // int arr1[] = { 7, 3, 9 }, arr2[] = { 6, 3, 9, 2, 9, 4 };
        // System.out.println(union(arr1, arr2));
        // System.out.println(intersection(arr1, arr2));

        // HashMap<String, String> tickets = new HashMap<>();
        // tickets.put("China", "Bangladesh");
        // tickets.put("Malaysia", "Denmark");
        // tickets.put("Germany", "China");
        // tickets.put("Denmark", "Germany");
        // System.out.println(start_to_end(tickets));

        // int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
        // System.out.println(largest_subarray_with_0_sum(arr));

        int arr[] = { 10, 2, -2, -20, 10 };
        int k = -10;
        System.out.println(count_subarray_sum_equal_to_k(arr, k));

    }
}
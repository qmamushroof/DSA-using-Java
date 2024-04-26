import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

public class Graph {

    static class Edge implements Comparable<Edge> {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.weight - e2.weight;
        }
    }

    public static void create_graph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) { // converting from null to empty
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        // graph[1].add(new Edge(1, 3, 40));
        graph[1].add(new Edge(1, 2, -4));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 1, 50));
        // graph[2].add(new Edge(2, 3, 2));
        // graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 0, 40));
        // graph[3].add(new Edge(3, 1, 40));
        // graph[3].add(new Edge(3, 2, 50));
        graph[3].add(new Edge(3, 4, 4));
        // graph[3].add(new Edge(3, 5, 3));

        // graph[4].add(new Edge(4, 1, -1));
        graph[4].add(new Edge(4, 3, 2));
        // graph[4].add(new Edge(4, 5, 5));
        // graph[4].add(new Edge(4, 5, 2));

        // graph[5].add(new Edge(5, 0, 2));
        // graph[5].add(new Edge(5, 2, 2));
        // graph[5].add(new Edge(5, 6, 2));
        // graph[5].add(new Edge(5, 3, 2));
        // graph[5].add(new Edge(5, 4, 2));

        // graph[5].add(new Edge(6, 5, 2));
    }

    public static void create_graph(ArrayList<Edge> edges) {

        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));

        edges.add(new Edge(1, 3, 40));

        edges.add(new Edge(2, 3, 50));
    }

    public static ArrayList<Integer> BFS(ArrayList<Edge> graph[]) {
        ArrayList<Integer> bfsResult = new ArrayList<>();
        boolean visited[] = new boolean[graph.length];
        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                bfsResult.addAll(BFS_util(graph, visited));
            }
        }
        return bfsResult;
    }

    // helper functions used to handle disconnected components
    public static ArrayList<Integer> BFS_util(ArrayList<Edge> graph[], boolean visited[]) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        q.add(0);
        while (!q.isEmpty()) {
            int currentNode = q.remove();
            if (!visited[currentNode]) {
                answer.add(currentNode);
                visited[currentNode] = true;
                for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
                    Edge e = graph[currentNode].get(neighbourIdx);
                    q.add(e.destination);
                }
            }
        }
        return answer;
    }

    public static ArrayList<Integer> DFS(ArrayList<Edge> graph[]) {
        ArrayList<Integer> dfsResult = new ArrayList<>();
        boolean visited[] = new boolean[graph.length];
        for (int node = 0; node < visited.length; node++) {
            if (!visited[node]) {
                DFS_util(graph, node, visited, dfsResult);
            }
        }
        return dfsResult;
    }

    // helper functions used to handle disconnected components
    public static void DFS_util(ArrayList<Edge> graph[], int currentNode, boolean visited[],
            ArrayList<Integer> answer) {
        answer.add(currentNode);
        visited[currentNode] = true;
        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            if (!visited[e.destination]) {
                DFS_util(graph, e.destination, visited, answer);
            }
        }
    }

    public static void print_neighbours(int node, ArrayList<Edge> graph[]) {
        for (int neighbourIdx = 0; neighbourIdx < graph[node].size(); neighbourIdx++) {
            Edge e = graph[node].get(neighbourIdx);
            System.out.println(e.destination);
        }
    }

    public static boolean has_path(ArrayList<Edge> graph[], int start, int goal, boolean visited[]) {
        visited[start] = true;
        if (start == goal) {
            return true;
        }
        for (int neighbourIdx = 0; neighbourIdx < graph[start].size(); neighbourIdx++) {
            Edge e = graph[start].get(neighbourIdx);
            if (!visited[e.destination] && has_path(graph, e.destination, goal, visited)) {
                return true;
            }
        }
        return false;
    }

    public static boolean has_cycle(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                if (has_cycle_util(graph, visited, node, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean has_cycle_util(ArrayList<Edge> graph[], boolean visited[], int node, int parent) {
        visited[node] = true;

        for (int neighbourIdx = 0; neighbourIdx < graph[node].size(); neighbourIdx++) {
            Edge e = graph[node].get(neighbourIdx);
            if (!visited[e.destination]) {
                if (has_cycle_util(graph, visited, e.destination, node)) {
                    return true;
                }
            } else if (visited[e.destination] && e.destination != parent) {
                return true;
            }
        }

        return false;
    }

    // Acyclic always bipartite
    // Even cyclic always bipartite
    // Odd cyclic never bipartite
    public static boolean is_bipartite(ArrayList<Edge> graph[]) {
        int category[] = new int[graph.length]; // -1(unvisited and unmarked), 0(1st colour), 1(2nd colour)
        for (int node = 0; node < category.length; node++) {
            category[node] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int node = 0; node < graph.length; node++) { // BFS
            if (category[node] == -1) {
                q.add(node);
                category[node] = 0;

                while (!q.isEmpty()) {
                    int currentNode = q.remove();

                    for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
                        Edge e = graph[currentNode].get(neighbourIdx);
                        if (category[e.destination] == -1) {
                            int selectedCategory = (category[currentNode] == 0 ? 1 : 0);
                            category[e.destination] = selectedCategory;
                            q.add(e.destination);
                        } else if (category[currentNode] == category[e.destination]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean has_cycle_in_directed_graph(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        boolean NowInStack[] = new boolean[graph.length];

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                if (has_cycle_util(graph, visited, node, NowInStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    // for directed graphs
    public static boolean has_cycle_util(ArrayList<Edge> graph[], boolean visited[], int currentNode,
            boolean NowInStack[]) {
        visited[currentNode] = true;
        NowInStack[currentNode] = true;

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            if (NowInStack[e.destination]) {
                return true;
            }
            if (!visited[currentNode] && has_cycle_util(graph, visited, e.destination, NowInStack)) {
                return true;
            }
        }

        NowInStack[currentNode] = false;
        return false;
    }

    public static ArrayList<Integer> topological_sort(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();

        ArrayList<Integer> list = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                topological_sort_util(graph, node, visited, st);
            }
        }

        while (!st.empty()) {
            list.add(st.pop());
        }

        return list;
    }

    public static void topological_sort_util(ArrayList<Edge> graph[], int currentNode, boolean visited[],
            Stack<Integer> st) { // modified DFS
        visited[currentNode] = true;

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            int neighbour = e.destination;
            if (!visited[e.destination]) {
                topological_sort_util(graph, neighbour, visited, st);
            }
        }
        st.push(currentNode);
    }

    public static int[] calculate_indegree(ArrayList<Edge> graph[]) {
        // ArrayList<Integer> indegree = new ArrayList<>();
        int indegree[] = new int[graph.length];
        for (int node = 0; node < graph.length; node++) {
            for (int neighbourIdx = 0; neighbourIdx < graph[node].size(); neighbourIdx++) {
                Edge e = graph[node].get(neighbourIdx);
                int neighbour = e.destination; // indegree.set(neighbour, indegree.get(neighbour) + 1);
                indegree[neighbour]++;
            }
        }
        return indegree;
    }

    // Kahn's algo
    public static ArrayList<Integer> topological_sort_BFS(ArrayList<Edge> graph[]) {
        ArrayList<Integer> answer = new ArrayList<>();

        int indegree[] = calculate_indegree(graph);

        Queue<Integer> q = new LinkedList<>();
        for (int node = 0; node < indegree.length; node++) {
            if (indegree[node] == 0) {
                q.add(node);
            }
        }

        while (!q.isEmpty()) {
            int currentNode = q.remove();
            answer.add(currentNode);

            for (int node = 0; node < graph[currentNode].size(); node++) {
                Edge e = graph[currentNode].get(node);
                int neighbour = e.destination;
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    q.add(neighbour);
                }
            }
        }
        return answer;

    }

    public static void print_path(ArrayList<Edge> graph[], int source, int target, String path) { // O(V^V)
        if (source == target) {
            System.out.println(path + target);
            return;
        }
        for (int neighbourIdx = 0; neighbourIdx < graph[source].size(); neighbourIdx++) {
            Edge e = graph[source].get(neighbourIdx);
            int neighbour = e.destination;
            print_path(graph, neighbour, target, path + source);
        }
    }

    static class Pair implements Comparable<Pair> {
        int node, distanceOrCost;

        Pair(int node, int distanceOrCost) {
            this.node = node;
            this.distanceOrCost = distanceOrCost;
        }

        @Override
        public int compareTo(Pair obj) {
            return this.distanceOrCost - obj.distanceOrCost; // ascending sort w.r.t distance
        }
    }

    // not applicable for negative edged graphs
    public static ArrayList<Integer> dijkstra(ArrayList<Edge> graph[], int source) {
        ArrayList<Integer> distance = new ArrayList<>(); // int distance[] = new int[graph.length];
        boolean visited[] = new boolean[graph.length];

        // initialize target node's distances with infinity
        for (int node = 0; node < graph.length; node++) {
            if (node != source) {
                distance.add(Integer.MAX_VALUE); // distance[node] = Integer.MAX_VALUE;
            } else {
                distance.add(0);
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue();
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            if (!visited[current.node]) {
                visited[current.node] = true;

                for (int node = 0; node < graph[current.node].size(); node++) {
                    Edge e = graph[current.node].get(node);
                    int u = e.source;
                    int v = e.destination;
                    int weight = e.weight;

                    if (distance.get(u) + weight < distance.get(v)) { // if (distance[u] + weight < distance[v])
                        distance.set(v, distance.get(u) + weight); // distance[v] = distance[u] + weight;
                        pq.add(new Pair(v, distance.get(v)));
                    }
                }
            }
        }
        return distance;
    }

    // works for negative edges but not for negative cycles
    public static ArrayList<Integer> bellmanford(ArrayList<Edge> graph[], int source) { // O(nlogn)
        ArrayList<Integer> distance = new ArrayList<>();
        for (int node = 0; node < graph.length; node++) {
            if (node != source) {
                distance.add(Integer.MAX_VALUE);
            } else {
                distance.add(0);
            }
        }

        int vertices = graph.length;
        for (int iteration = 0; iteration < vertices - 1; iteration++) {
            for (int node = 0; node < graph.length; node++) {
                for (int edgeIdx = 0; edgeIdx < graph[node].size(); edgeIdx++) {
                    Edge e = graph[node].get(edgeIdx);
                    int u = e.source;
                    int v = e.destination;
                    int weight = e.weight;
                    if (distance.get(u) != Integer.MAX_VALUE && distance.get(u) + weight < distance.get(v)) {
                        distance.set(v, distance.get(u) + weight);
                    }
                }
            }
        }
        return distance;
    }

    // MST Prim's algo
    public static ArrayList<Pair> Prim(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        ArrayList<Pair> edges = new ArrayList<>();

        pq.add(new Pair(0, 0));
        int cost = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            if (!visited[current.node]) {
                edges.add(current);
                visited[current.node] = true;
                cost += current.distanceOrCost;

                for (int neighbourIdx = 0; neighbourIdx < graph[current.node].size(); neighbourIdx++) {
                    Edge e = graph[current.node].get(neighbourIdx);
                    pq.add(new Pair(e.destination, e.weight));
                }
            }
        }
        System.out.println("Cost = " + cost);
        return edges;
    }

    public static void print_pairs(ArrayList<Pair> edges) {
        for (int i = 0; i < edges.size(); i++) {
            System.out.println("Node:" + edges.get(i).node + " Weight:" + edges.get(i).distanceOrCost);
        }
    }

    public static void create_graph(int matrix[][], ArrayList<Edge> graph[]) {
        for (int node = 0; node < graph.length; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int i = 0; i < matrix.length; i++) {
            int source = matrix[i][0];
            Edge e = new Edge(source, matrix[i][1], matrix[i][2]);
            graph[source].add(e);
        }
    }

    static class Info_cheapest_flight_within_k_stops {
        int node, cost, stops;

        Info_cheapest_flight_within_k_stops(int source, int cost, int stops) {
            this.node = source;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public static int cheapest_flight_within_k_stops(int vertices, int flights[][], int start, int end, int k) {
        ArrayList<Edge> graph[] = new ArrayList[vertices];
        create_graph(flights, graph);

        int expense[] = new int[vertices];
        // initialize nodes with infinity
        for (int node = 0; node < expense.length; node++) {
            if (node != start) {
                expense[node] = Integer.MAX_VALUE;
            }
        }

        Queue<Info_cheapest_flight_within_k_stops> q = new LinkedList<>();
        q.add(new Info_cheapest_flight_within_k_stops(start, 0, 0));

        while (!q.isEmpty()) {
            Info_cheapest_flight_within_k_stops current = q.remove();
            if (current.stops > k) {
                break;
            }

            for (int neighbourIdx = 0; neighbourIdx < graph[current.node].size(); neighbourIdx++) {
                Edge e = graph[current.node].get(neighbourIdx);
                int v = e.destination;
                int weight = e.weight;

                if (current.cost + weight < expense[v] && current.stops <= k) {
                    expense[v] = current.cost + weight;
                    q.add(new Info_cheapest_flight_within_k_stops(v, expense[v], current.stops + 1));
                }
            }
        }

        return expense[end];
    }

    static class Edge_connecting_cities implements Comparable<Edge_connecting_cities> {
        int destination, cost;

        Edge_connecting_cities(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge_connecting_cities e2) {
            return this.cost - e2.cost;
        }
    }

    public static int connecting_cities_with_minimum_cost(int cities[][]) {
        PriorityQueue<Edge_connecting_cities> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[cities.length];

        pq.add(new Edge_connecting_cities(0, 0));
        int minCost = 0;

        while (!pq.isEmpty()) {
            Edge_connecting_cities current = pq.remove();
            if (!visited[current.destination]) {
                minCost += current.cost;
                visited[current.destination] = true;

                for (int neighbourIdx = 0; neighbourIdx < cities[current.destination].length; neighbourIdx++) {
                    if (cities[current.destination][neighbourIdx] != 0) {
                        pq.add(new Edge_connecting_cities(neighbourIdx, cities[current.destination][neighbourIdx]));
                    }
                }
            }
        }
        return minCost;
    }

    static int disjointSetSize = 7;
    static int parent[] = new int[disjointSetSize];
    static int rank[] = new int[disjointSetSize];

    static class Disjoint_set {
        public static void init() {
            for (int element = 0; element < disjointSetSize; element++) {
                parent[element] = element;
            }
        }

        public static int find(int member) {
            if (member == parent[member]) {
                return member;
            }
            return parent[member] = find(parent[member]);
        }

        public static void union(int elementA, int elementB) {
            int parentA = find(elementA);
            int parentB = find(elementB);
            if (rank[parentA] == rank[parentB]) {
                parent[parentB] = parentA;
                rank[parentA]++;
            } else if (rank[parentA] > rank[parentB]) {
                parent[parentB] = parentA;
            } else
                parent[parentA] = parentB;
        }
    }

    // MST
    public static int Kruskals(ArrayList<Edge> edges, int vertices) {
        Disjoint_set.init();
        Collections.sort(edges);
        int minCost = 0;
        int count = 0;
        for (int i = 0; count < vertices - 1; i++) {
            Edge e = edges.get(i);

            int parA = Disjoint_set.find(e.source);
            int parB = Disjoint_set.find(e.destination);
            if (parA != parB) {
                Disjoint_set.union(parA, parB);
                minCost += e.weight;
                count++;
            }
        }
        return minCost;
    }

    public static int[][] flood_fill(int img[][], int startRow, int startCol, int fillColour) { // O(rows*cols)
        boolean visited[][] = new boolean[img.length][img[0].length];
        flood_fill_util(img, startRow, startCol, fillColour, visited, img[startRow][startCol]);
        return img;
    }

    public static void flood_fill_util(int img[][], int row, int col, int fillColour, boolean visited[][],
            int originalColour) {
        if (row < 0 || col < 0 || row > img.length || col > img.length || visited[row][col]
                || img[row][col] != originalColour) {
            return;
        }
        img[row][col] = fillColour;

        flood_fill_util(img, row, col - 1, fillColour, visited, originalColour);// left
        flood_fill_util(img, row, col + 1, fillColour, visited, originalColour);// right
        flood_fill_util(img, row - 1, col, fillColour, visited, originalColour);// up
        flood_fill_util(img, row + 1, col, fillColour, visited, originalColour);// down
    }

    // for Kosaraju's algo
    public static void topological_sort(ArrayList<Edge> graph[], int currentNode, boolean visited[],
            Stack<Integer> st) {
        visited[currentNode] = true;

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            int neighbour = e.destination;
            if (!visited[neighbour]) {
                topological_sort(graph, neighbour, visited, st);
            }
        }
        st.push(currentNode);
    }

    // for Kosaraju's algo
    public static void DFS(ArrayList<Edge> graph[], int currentNode, boolean visited[]) {
        visited[currentNode] = true;
        System.out.print(currentNode + " ");

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            int neighbour = e.destination;
            if (!visited[neighbour]) {
                DFS(graph, neighbour, visited);
            }
        }
    }

    // for SCC(Strongly Connected Components)
    public static void kosaraju(ArrayList<Edge> graph[], int vertices) {
        // 1.
        Stack<Integer> st = new Stack<>();
        boolean visited[] = new boolean[vertices];
        for (int node = 0; node < vertices; node++) {
            if (!visited[node]) {
                topological_sort(graph, node, visited, st);
            }
        }

        // 2.
        ArrayList<Edge> transpose[] = new ArrayList[vertices];
        for (int node = 0; node < graph.length; node++) {
            visited[node] = false;
            transpose[node] = new ArrayList<Edge>();
        }
        for (int node = 0; node < vertices; node++) {
            for (int neighbourIdx = 0; neighbourIdx < graph[node].size(); neighbourIdx++) {
                Edge e = graph[node].get(neighbourIdx);
                int neighbour = e.destination;
                transpose[neighbour].add(new Edge(e.destination, e.source, e.weight));
            }
        }

        // 3.
        while (!st.isEmpty()) {
            int currentNode = st.pop();
            if (!visited[currentNode]) {
                System.out.print("SCC: ");
                DFS(transpose, currentNode, visited);
                System.out.println();
            }
        }

    }

    // for Tarjan's algo
    public static void DFS_print_bridge(ArrayList<Edge> graph[], int currentNode, int parent, int discoveryTime[],
            int lowestNeighbourTime[], boolean visited[], int time) {
        visited[currentNode] = true;
        discoveryTime[currentNode] = lowestNeighbourTime[currentNode] = ++time;

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            int neighbour = e.destination;
            if (neighbour == parent) {
                continue;
            } else if (!visited[neighbour]) {
                DFS_print_bridge(graph, neighbour, currentNode, discoveryTime, lowestNeighbourTime, visited,
                        time);
                lowestNeighbourTime[currentNode] = Math.min(lowestNeighbourTime[currentNode],
                        lowestNeighbourTime[neighbour]);
                if (discoveryTime[currentNode] < lowestNeighbourTime[neighbour]) {
                    System.out.println(currentNode + "_____" + neighbour);
                }
            } else {
                lowestNeighbourTime[currentNode] = Math.min(lowestNeighbourTime[currentNode],
                        discoveryTime[neighbour]);
            }
        }
    }

    // Tarjan's algo
    public static void print_bridge(ArrayList<Edge> graph[]) {
        int discoveryTime[] = new int[graph.length];
        int neighbourLowestDiscoveryTime[] = new int[graph.length];
        int time = 0;
        boolean visited[] = new boolean[graph.length];

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                DFS_print_bridge(graph, node, -1, discoveryTime, neighbourLowestDiscoveryTime, visited, time);
            }
        }
    }

    // for Tarjan's algo
    // O(V+E)
    public static void DFS_print_articulation_point(ArrayList<Edge> graph[], int currentNode, int parent,
            int discoveryTime[],
            int lowestNeighbourTime[], boolean visited[], int time, boolean AP[]) {

        visited[currentNode] = true;
        discoveryTime[currentNode] = lowestNeighbourTime[currentNode] = ++time;
        int children = 0;

        for (int neighbourIdx = 0; neighbourIdx < graph[currentNode].size(); neighbourIdx++) {
            Edge e = graph[currentNode].get(neighbourIdx);
            int neighbour = e.destination;

            if (neighbour == parent) {
                continue;
            } else if (visited[neighbour]) {
                lowestNeighbourTime[currentNode] = Math.min(lowestNeighbourTime[currentNode], discoveryTime[neighbour]);
            } else {
                DFS_print_articulation_point(graph, neighbour, currentNode, discoveryTime, lowestNeighbourTime, visited,
                        time, AP);
                lowestNeighbourTime[currentNode] = Math.min(lowestNeighbourTime[currentNode],
                        lowestNeighbourTime[neighbour]);
                if (parent != -1 && discoveryTime[currentNode] <= lowestNeighbourTime[neighbour]) {
                    AP[currentNode] = true;
                }
                children++;
            }
        }
        if (parent == -1 && children > 1) {
            AP[currentNode] = true;
        }
    }

    // Tarjan's algo
    public static void print_articulation_point(ArrayList<Edge> graph[]) {
        int discoveryTime[] = new int[graph.length];
        int lowestNeighbourTime[] = new int[graph.length];
        int time = 0;
        boolean visited[] = new boolean[graph.length];
        boolean AP[] = new boolean[graph.length];

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                DFS_print_articulation_point(graph, node, -1, discoveryTime, lowestNeighbourTime, visited, time, AP);
            }
        }

        for (int node = 0; node < graph.length; node++) {
            if (AP[node]) {
                System.out.println(node);
            }
        }
    }

    public static void main(String[] args) {
        // int flights[][] = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600
        // }, { 2, 3, 200 } };
        // System.out.println(cheapest_flight_within_k_stops(4, flights, 0, 3, 1));
        // ArrayList<Edge> graph[] = new ArrayList[vertices]; // null array of
        // arraylists
        // create_graph(graph);

        // int cities[][] = {
        // { 0, 1, 2, 3, 4 },
        // { 1, 0, 5, 0, 7 },
        // { 2, 5, 0, 6, 0 },
        // { 3, 0, 6, 0, 0 },
        // { 4, 7, 0, 0, 0 } };
        // System.out.println(connecting_cities_with_minimum_cost(cities));

        // Disjoint_set.init();
        // System.out.println(Disjoint_set.find(3));
        // Disjoint_set.union(1, 3);
        // System.out.println(Disjoint_set.find(3));
        // Disjoint_set.union(2, 4);
        // Disjoint_set.union(3, 6);
        // Disjoint_set.union(1, 4);
        // System.out.println(Disjoint_set.find(3));
        // System.out.println(Disjoint_set.find(4));
        // Disjoint_set.union(1, 5);

        // int vertices = 4;
        // ArrayList<Edge> edges = new ArrayList<>();
        // create_graph(edges);
        // System.out.println(Kruskals(edges, vertices));

        int vertices = 5;
        ArrayList<Edge> graph[] = new ArrayList[vertices];
        create_graph(graph);
        print_articulation_point(graph);
    }
}

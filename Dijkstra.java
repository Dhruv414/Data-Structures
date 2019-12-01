package Implementations;
import java.util.*;
import java.io.*;

public class Dijkstra {
    static class Node implements Comparable<Node>{
        int i;
        int dist;
        ArrayList<ArrayList<Integer>> adj;
        public Node(int i){
            dist = Integer.MAX_VALUE;
            this.i = i;
            adj = new ArrayList<>();
        }

        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }
    static Node[] list;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        list = new Node[n+1];
        for(int x = 1; x<=n; x++){
            list[x] = new Node(x);
        }
        for(int x = 0; x<m; x++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ArrayList<Integer> a1 = new ArrayList<>();
            ArrayList<Integer> a2 = new ArrayList<>();
            a1.add(x2);
            a1.add(w);
            a2.add(x1);
            a2.add(w);
            list[x1].adj.add(a1);
            list[x2].adj.add(a2);
        }

        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] prev = new int[n+1];
        Node source = list[1];
        source.dist = 0;
        pq.add(source);

        while(!pq.isEmpty()){
            Node s = pq.poll();
            visited[s.i] = true;
            for(ArrayList<Integer> k : s.adj){
                if(!visited[k.get(0)]){
                    if(s.dist + k.get(1) < list[k.get(0)].dist){
                        list[k.get(0)].dist = s.dist + k.get(1);
                        pq.add(list[k.get(0)]);
                        prev[k.get(0)] = s.i;
                    }
                }
            }
        }
        //System.out.println(list[n].dist);
        if(list[n].dist == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            int i = n;
            ArrayList<Integer> path = new ArrayList<Integer>();
            while (i != 1){
                path.add(i);
                i = prev[i];
            }
            path.add(1);

            for(int x = path.size()-1; x>= 0; x--){
                System.out.print(path.get(x) + " ");
            }
            System.out.println();
            //System.out.println(new StringBuilder(s).reverse().toString());
        }
    }
}
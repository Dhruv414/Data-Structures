package Implementations;

import java.util.*;
import java.io.*;

public class UnionFindDisjointSet {

    static class dsu{
        int[] sizes;
        int[] id;
        int numComponents;

        public dsu(int s){
            numComponents = s;
            sizes = new int[s];
            id = new int[s];

            for(int i = 0; i<s; i++){
                id[i] = i;
                sizes[i] = 1;
            }
        }

        public int find(int p){
            int root = p;
            while(root != id[root]){
                root = id[root];
            }
            while(p != root){
                int next = id[p];
                id[p] = root;
                p = next;
            }
            return root;
        }

        public boolean connected(int p, int q){
            return find(p) == find(q);
        }

        public int getNumComponents(int p){
            return sizes[find(p)];
        }

        public void unify(int p, int q){
            int r1 = find(p);
            int r2 = find(q);
            if(r1 == r2) return;
            if(sizes[r1] < sizes[r2]){
                sizes[r2] += sizes[r1];
                id[r1] = r2;
            }
            else{
                sizes[r1] += sizes[r2];
                id[r2] = r1;
            }
            numComponents--;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
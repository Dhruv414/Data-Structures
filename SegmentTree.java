package Implementations;

import java.util.*;
import java.io.*;

public class SegmentTree {

    static class st{
        int n;
        int[] data;
        public st(int arr[]) {
            n = arr.length;
            data = new int[2 * n];
            for (int i = n; i < 2 * n; i++) {
                data[i] = arr[i - n]; // copy arr to 2nd half of data (leaves)
            }
            for (int i = n - 1; i >= 1; i--) {
                data[i] = Math.min(data[2 * i], data[2 * i + 1]); // the children of the node
            }
        }
        public void update(int i, int v){
            int index = i + n; // b/c is leaf node
            data[index] = v;
            while(index > 1){
                index /= 2;
                data[index] = Math.min(data[2*index], data[2*index+1]);
            } // recomputing the part of the tree that uses this value
        }
        public int min(int l, int r){
            int left = l + n;
            int right = r + n; // getting the leaf nodes
            int min = Integer.MAX_VALUE;
            while(left < right){
                if(left % 2 == 1){ // means it is a right child node == have to stop / cant go up
                    min = Math.min(min, data[left]);
                    left++;
                }
                if(right % 2 == 1){
                    right -= 1;
                    min = Math.min(min, data[right]);
                }
                left /= 2;
                right /= 2; // going up the tree
            }
            return min;
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
package Implementations;

import java.util.*;
import java.io.*;

public class FenwickTree {
    static long[] bit;

    public static void update(int i, int val){
        while(i < bit.length){
            bit[i] += val;
            i += i & +i;
        }
    }

    public static long sum(int i){
        long sum = 0;
        while(i != 0){
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    public static long sum(int i, int j){
        return sum(j) - sum(i-1);
    }

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
        int[] arr = new int[5];
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
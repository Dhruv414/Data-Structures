package Implementations;

import java.util.*;
import java.io.*;

public class KMP {


    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        String s = sc.nextLine();
        int n = s.length();
        int[] prefixFunction = new int[n];
        char[] arr = s.toCharArray();
        for(int i = 1; i<n; i++){
            int j = prefixFunction[i-1];
            while(j > 0 && arr[i] != arr[j]){
                j = prefixFunction[j-1];
            }
            if(arr[j] == arr[i]){
                j++;
            }
            prefixFunction[i] = j;
        }

        int[] ans = new int[n+1];

        for(int i = 0; i<n; i++){
            ans[prefixFunction[i]]++;
        }
        for(int i = n-1; i>0; i--){
            ans[prefixFunction[i-1]] += ans[i];
        }
        for(int i = 0; i<=n; i++){
            ans[i]++;
        }
        //System.out.println(Arrays.toString(ans));
        //System.out.println(Arrays.toString(prefixFunction));
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
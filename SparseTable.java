package Implementations;

import java.util.*;
import java.io.*;

public class SparseTable {
    static class SparseTab{
        static int[] arr;
        static int[][] st;
        static int[] log;

        public SparseTab(int n, int[] array){
            arr = array.clone();

            log = new int[n+1];
            int maxLog = 0;
            for(int i = 2; i<=n; i++){
                log[i] = log[i/2] + 1;
                maxLog = Math.max(maxLog, log[i]);
            } //will precompute the log values

            st = new int[maxLog + 1][n];

            for(int i = 0; i < n; i++){
                st[0][i] = arr[i];
            }

            for(int row = 1; row < st.length; row++){
                for(int i = 0; i + (1 << row) <= n; i++){
                    st[row][i] = Math.min(st[row-1][i], st[row - 1][i + (1 << row - 1)]); // 1 << row - 1 gives the range length of the row before it
                }
            }
        }

        public int minq(int i, int j){
            int k = log[j - i];
            return Math.min(st[k][i], st[k][j - (1 << k)]);
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new FileWriter("SparseTable.in");
        //StringTokenizer st = new StringTokenizer(br.readLine());
        //PrintWriter pw = new PrintWriter("SparseTable.out");

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int x =0; x<n; x++){
            arr[x] = sc.nextInt();
        }

        SparseTab st = new SparseTab(n, arr);
        System.out.println(st.minq(1,3));


    }
}
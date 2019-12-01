package Implementations;

import java.util.*;
import java.io.*;

public class NumberTheoryLibrary {

    static long MODULO = (long)1e9+7;

    static class Triplet{
        long x;
        long y;
        long gcd;

        public Triplet(long x, long y, long g){
            this.x = x;
            this.y = y;
            gcd = g;
        }
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static long lcm(int a, int b){
        return a*(b/gcd(a,b));
    }

    public static Triplet extendedGCD(long a, long b){
        if(b == 0){
            return new Triplet(1, 0, a);
        }
        else{
            long x;
            long y;
            long g;
            Triplet t = extendedGCD(b,a % b);
            x = t.x;
            y = t.y;
            g = t.gcd;
            return new Triplet(y, x - (a/b)*y, g);
        }
    }

    public static boolean isPrime(long n){
        if(n == 1){
            return false;
        }
        if(n == 2){
            return true;
        }
        if(n % 2 == 0){
            return false;
        }
        for(int i = 3; i*i <= n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static long modpow(long a, long b){
        if(b == 0){
            return 1;
        }
        if(a == 0){
            return 0;
        }
        if(b == 1){
            return a;
        }
        if(b % 2 == 0){
            return ((modpow(a,b/2) % MODULO) * (modpow(a,b/2) % MODULO)) % MODULO;
        }
        return (modpow(a,b-1) % MODULO * a) % MODULO;
    }


    public static ArrayList<Long> factors(long n){ // returns a list of the prime factors of the number n
        ArrayList<Long> fact = new ArrayList<>();
        for(long i = 2; i*i <= n; i++){
            while(n % i == 0){
                fact.add(i);
                n/=i;
            }
        }
        if(n > 1){
            fact.add(n);
        }
        return fact;
    }
    // can also use sieve of eratosthenes for precomputing prime

    public static long totient(long n){ // returns the number of integers from 1...n that are coprime (gcd(a,b) = 1) to n
        ArrayList<Long> primeFactors = factors(n);
        int i = 0;
        long coprimes = 1;
        while(i < primeFactors.size()){
            long prime = primeFactors.get(i);
            int count = 1;
            i++;
            while(i < primeFactors.size() && primeFactors.get(i) == prime){
                count++;
                i++;
            }
            coprimes = ((coprimes % MODULO) * ((modpow(prime,count-1) % MODULO) * (prime - 1)) % MODULO) % MODULO;
        }
        return coprimes;
    }

    // Euler's theorem states that x^totient(m) mod m = 1 for all positive coprime integers x and m, therefore if m is prime, totient(m) = m-1 and x^(m-1) mod m = 1
    // Fermat's Little Theorem ^^ Also implies: x^n mod m = x^(n mod (m-1)) mod m, useful when n is very large

    public static long modinv(int x, int m){
        return modpow(x, totient(m) - 1); // if m is prime, then simply return x ^ (m - 2)
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
        /*int n = sc.nextInt();
        pw.println(factors(n));
        pw.println(isPrime(n));
        Triplet tp = extendedGCD(30,12);
        pw.println(tp.x + " " + tp.y);
        */

        long a = sc.nextLong();
        long b = sc.nextLong();
        //pw.println(modpow(a,b));
        //pw.println(totient(10));
        Triplet tp = extendedGCD(a,b);
        pw.println(tp.x + " " + tp.y);
        pw.close();
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
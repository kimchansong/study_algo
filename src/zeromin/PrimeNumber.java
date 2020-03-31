package kr.co.miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimeNumber {
    static int MAX_N = 4000000;
    static boolean[] check = new boolean[MAX_N+1];
    static int[] prime = new int[320000]; //10^6이 대략 78000개정도니까 여유있게....
    static int count=0;

    public static void eratos(){

        int size = (int)Math.sqrt(MAX_N);

        for(int i = 2; i <= size; i++) {
            if(check[i]) continue;
            for(int j = i+i; j<=MAX_N;j+=i) {
                check[j] = true;
            }
        }

        for(int i = 2; i <= MAX_N; i++) {
            if(!check[i]) {
                prime[count++] = i;
             }
        }

    }
    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int ans = 0;
        int sum = 0;
        eratos();
        int start=0, end= 0;
        while(end <= count){
            if(sum < n){
                sum+= prime[end++];
                continue;
            }
            if(sum == n){
                ++ans;
            }
            sum -= prime[start++];
        }

        System.out.println(ans);
    }
}

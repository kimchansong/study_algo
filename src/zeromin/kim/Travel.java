package zeromin.kim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Travel {
    static int N, M, K ;
    static int a,b,c;
    static int path[][];
    static int dp[][];



    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][N+1]; //
        path = new int[N+1][N+1];

        for(int i= 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); //a���� b�� �̵��ϴ� �׷�
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken()); //c�� ����
            if(a<b){
                path[a][b] = Math.max(path[a][b], c); //�ߺ��� ��ΰ� ������ ���մ� ������
            }
        }
        Arrays.stream(dp).forEach(a->Arrays.fill(a,-1));
//        for (int i = 0; i <= N; i++) {
//            Arrays.fill(dp[i], -1);
//        }
        //dp[i][j]  i�� ������� �����ϰ� �װ�����j �� �̿������� ������ �ִ� �ִ� ����
        System.out.println(point(1,1));
        br.close();
    }


    static int point(int n, int count){
        if(n == N){return 0;}
        if(count == M){ return Integer.MIN_VALUE;}
        if(dp[n][count] > -1){
            return dp[n][count];
        }
        int result = Integer.MIN_VALUE;

        for(int i = n+1; i<=N; i++){
            if(path[n][i] >0){
                result = Math.max(result, point(i, count+1) + path[n][i]);
            }
        }
        return dp[n][count] = result;
    }

}

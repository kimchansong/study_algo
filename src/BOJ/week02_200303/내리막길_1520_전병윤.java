import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][] cache;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int M, N;
    static int Answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M + 1][N + 1];
        cache = new int[M + 1][N + 1];
        for(int i = 1; i <= M; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }
        br.close();
        Answer = dp(1, 1);
        System.out.println(Answer);
    }

    static int dp(int m, int n){
        if(m == M && n == N) return 1;
        if(cache[m][n] != -1) return cache[m][n];
        int ret = 0;
        for(int k = 0 ; k < d.length; k++){
            if(m + d[k][0] == 0 || n + d[k][1] == 0 || m + d[k][0] == M + 1 || n + d[k][1] == N + 1) continue;
            if(map[m][n] > map[m + d[k][0]][n + d[k][1]]) {
                ret += dfs(m + d[k][0], n + d[k][1]);
            }
        }
        cache[m][n] = ret;
        return ret;
    }
}

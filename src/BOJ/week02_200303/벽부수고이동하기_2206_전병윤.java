package BOJ.week02_200303;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj02206 {

    static int[][] A;
    static int[][] D;
    static boolean[][] visited;
    static int delta[][] = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new int[N + 2][M + 2];
        D = new int[N + 2][M + 2];
        visited = new boolean[N + 1][M + 1];

        for (int j = 1 ; j <= M; j++) {
            A[0][j] = A[N+1][j] = -1;
        }

        for(int i = 1; i <= N ; i++){
            String line = br.readLine();
            A[i][0] = A[i][M+1] = -1;
            for(int j = 1 ; j <= M ; j++){
                int value = line.charAt(j - 1) - '0';
                A[i][j] = value;
                D[i][j] = Integer.MAX_VALUE;
            }
        }
        D[N][M] = 1;
        visited[N][M] = true;
        go(0, N, M);
        System.out.println(D[1][1] == Integer.MAX_VALUE ? -1 : D[1][1]);
    }

    static void go(int count, int n, int m){
        if(A[n][m] == -1) return ;
        if(count == 2) return ;

        for(int i = 0 ; i < delta.length ; i++){
            int row = n + delta[i][0];
            int col = m + delta[i][1];

            if(row == 1 && col == 1) {
                D[row][col] = Math.min(D[row][col], D[n][m] + 1);
                return;
            }
            if(A[row][col] == -1 || visited[row][col]) continue;
            if(count == 1 && A[row][col] == 1) continue;

            D[row][col] = D[n][m] + 1;
            visited[row][col] = true;
            if(A[row][col] == 1){
                go(count + 1, row, col);
            }
            else {
                go(count, row, col);
            }
            visited[row][col] = false;
        }
    }
}

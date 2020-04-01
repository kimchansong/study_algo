

import javax.swing.*;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bishop {

    static int N;
    static int dx[] = {-1, -1, 1, 1};
    static int dy[] = {-1, 1, -1, 1};

    static int[][] map, colors;
    static boolean[] visited;
    static boolean[][] visited2;
    static int[] ans = new int[2];

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        colors = new int[N][N];
        visited = new boolean[N * N];
        visited2 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        colors[i][j] = 1;
                    }
                } else {
                    if (j % 2 != 0) {
                        colors[i][j] = 1;
                    }
                }
            }
        }


        dfs(-1, 0, 1);
        dfs(-1, 0, 0);
        System.out.println(ans[0] +ans[1]);

        br.close();
    }



    //public static void dfs(int row, int col, int cnt, int color) {
    public static void dfs(int index, int cnt, int color) {
        if (ans[color] < cnt) {
            ans[color] = cnt;
        }
        for (int i = index+1; i < N*N; i++) {
            int row = i/N;
            int col = i%N;

                if (colors[row][col] != color) {
                    continue;
                }
                if (map[row][col] == 1) {
                    if (check(row, col)) {
                        visited[i] = true;
                        dfs(i,cnt + 1, color);
                    }
                }
        }
        if(index == -1) return;
        visited[index] = false;

    }

    public static boolean check(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            int v = nx * N + ny;
            for (int j = 1; j < N; j++) {
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (visited[v]) {
                        return false;
                    }
                }
                nx += dx[i];
                ny += dy[i];
                v = nx * N + ny;
            }
        }
        return true;
    }

}

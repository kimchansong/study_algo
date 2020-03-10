package zeromin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1520 {
    static class Info{
        int x;
        int y;
        int cnt;
        Info(int x, int y){this.x= x;this.y= y;}
    }
    static int[] moveX = new int[]{-1, 1, 0, 0};
    static int[] moveY = new int[]{0, 0, -1, 1};
    static int M, N;
    static int[][] map;
    static int [][] visited;
    static int result;
    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Info> stack = new ArrayDeque<>(1000);

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        visited = new int[M+1][N+1];

        for(int i =1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j]=-1;
            }
        }


        //System.out.println(dfs(1,1));
        result = 0;
        stack.push(new Info(1,1));

        while(!stack.isEmpty()){

            Info temp = stack.pop();
            int x = temp.x;
            int y = temp.y;
            int cnt = temp.cnt;

            if(x== M && y==N) {
                result += 1;
                continue;
            }
            if(visited[x][y]!=-1) {
                result += visited[x][y];
                continue;
            }


            for(int i=0; i<4; i++){
                int dx = x+ moveX[i];
                int dy= y + moveY[i];
                if( (1<=dx) && (dx<=M) &&(1<=dy) && (dy<=N) && map[x][y] >map[dx][dy]){
                    stack.push(new Info(dx,dy));
                }
            }

        }
//        for(int i =0;i<M;i++){
//            for(int j= 0; j<N;j++){
//                System.out.print(visited[i][j] +" ");
//            }
//            System.out.println();
//        }
        System.out.println(result);
    }
    static int dfs(int x, int y) {


        if (x == M && y == N) return 1;
        if(visited[x][y] != -1) return visited[x][y];
        int visit = 0;

        for (int i = 0; i < 4; i++) {

            int movedX = x + moveX[i];

            int movedY = y + moveY[i];

            if (1 <= movedX && movedX <= M && 1 <= movedY && movedY <= N  && map[x][y] > map[movedX][movedY]) {

                visit += dfs(movedX, movedY);

            }
        }
        visited[x][y] = visit;
        return visit;
    }
}

/*
4 5
        50 45 37 32 30
        35 50 40 20 25
        30 30 25 17 28
        27 24 22 15 10

 */
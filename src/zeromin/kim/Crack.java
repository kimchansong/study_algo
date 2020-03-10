package zeromin.kim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Crack {
    private static int N, M;
    private static int[][] map;
    private static int[][] visited;

    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static int minDist;
    static class Info{
        int x;
        int y;
        int dist;
        int k;
        Info(int x, int y , int dist, int k){
            this.x = x;this.y=y; this.dist = dist ; this.k= k;
        }
    }

    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for(int j= 1; j<=M; j++){
                map[i][j] =  Integer.parseInt( tmp.substring(j-1,j) );
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        //System.out.println(Arrays.deepToString(map));
        minDist = -1;
        bfs(1,1);
        System.out.println(minDist);
    }

    public static void bfs(int x, int y){
        ArrayDeque<Info> q = new ArrayDeque<>(1000);
        q.offer(new Info(1,1,1,0));
        visited[x][y] = 0;

        while(!q.isEmpty()){
            Info cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int dist = cur.dist;
            int curK = cur.k;

            if(curX == N && curY == M){
                minDist = dist;
                return;
            }

            for(int i= 0; i<4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if (nx > N || ny > M || nx <= 0 || ny <= 0) continue; //踰붿쐞諛�
                if (visited[nx][ny]<=curK) continue;
                if (map[nx][ny] == 0) {
                    visited[nx][ny]= curK;
                    q.offer(new Info(nx,ny,dist+1, curK));
                }else{
                    if(curK==0){
                        visited[nx][ny] = curK+1;
                        q.offer(new Info(nx,ny,dist+1, curK+1));
                    }
                }
            }
        }
        return;
    }

}

package zeromin.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어_16236_김영민 {
    static class Info{
        int x,y;
        public Info(int x, int y){
            this.x = x; this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1, 0};
    static int[] dy = {0,-1,0,1};
    static int babySize=2;
    static int result;
    static Info start ;
    static ArrayList<Info> arr = new ArrayList<>();

    public static void main(String...args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i= 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int  j=0 ; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9) {
                    start = new Info(i,j);
                    map[i][j]=0;
                }
            }
        }
        int exp=0;
        while(true){
            boolean find = bfs(); //최단거리 먹이 찾아서 arr에 넣고
            if(!find) {
            	break;
            }else{            	
            	arr.sort((i1,i2)->{if(i1.x<=i2.x) {return i1.x-i2.x;}else{return i2.y-i1.y;}}); //arr우선순위 높은순으로 먹어서
                Info v = arr.get(0);
                map[v.x][v.y] = 0;
                exp++;
                if(exp == babySize){
                    babySize++;
                    exp = 0;
                }
                arr.clear(); //먹었으니까 초기화하고
                start = v;   //상어 위치 바꾸고 반복
            }
        }
        
        System.out.println(result);

        br.close();
    }
    public static boolean bfs() {
        ArrayDeque<Info> q = new ArrayDeque<>(1000);
        Arrays.stream(visited).forEach(a->Arrays.fill(a,false));

        q.offer(start);
        visited[start.x][start.y] = true;
        int dist = 0;
        while(!q.isEmpty()) {
            dist++;
            int size = q.size();
            for(int s = 0; s<size;s++){ //단계별로 처리하기 위해서 같은 거리에서 들어있는 q에 대해서 모두 처리하고 다음 이동거리에 있는 큐를 처리하기.
                Info temp = q.poll();
                for(int i=0; i<4; i++) {
                    int nx = temp.x + dx[i];
                    int ny = temp.y + dy[i];
                    if(nx >=N || ny >=N || nx<0|| ny<0) continue;
                    if(visited[nx][ny] ) continue;
                    if(map[nx][ny] > babySize) continue;
                    if(map[nx][ny] !=0 && map[nx][ny]< babySize){
                        arr.add(new Info(nx, ny));
                    }
                    q.offer(new Info(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            if(arr.size() !=0){ //같은 이동거리안의 먹이는 arr에 있음.
                result += dist;
                return true;
            }
        }
        return false;
    }
}





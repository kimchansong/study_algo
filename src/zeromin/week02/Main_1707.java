package zeromin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1707 {
    static boolean[] marked;
    static boolean[] color;
    static boolean isTwoColor = true;
    static ArrayList<ArrayList<Integer>> arr;
    static int tc, v, e;


    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        tc = Integer.parseInt(st.nextToken());
        for(int t = 0; t<tc; t++) {
            isTwoColor = true;
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            arr= new ArrayList<>();
            for(int i =0; i<=v ; i++){
                arr.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                arr.get(start).add(end);
                arr.get(end).add(start);
            }

            marked = new boolean[v+1];
            color = new boolean[v+1];

            for(int s = 1; s<=v; s++){
                if(!isTwoColor) break;
                if(!marked[s]){
                    //dfs(s);
                    bfs(s);
                }
            }
            if(isTwoColor == true)
                System.out.println("YES");
            else
                System.out.println("NO");

        }

    }

    static void dfs(int v){
        marked[v] = true;
        for(int w: arr.get(v)){
            if(!marked[w]) {
                color[w] = !color[v];
                dfs(w);
            }else if(color[w] == color[v]){
                isTwoColor = false;
                return;
            }
        }
    }
    static void bfs(int v){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(v);
       // marked[v] = true;
        while(!q.isEmpty() && isTwoColor){
            v = q.poll();
            marked[v] = true;
            for(int w: arr.get(v)){
                if(!marked[w]){
                    color[w] = !color[v];
                    q.offer(w);
                }else if(color[w] == color[v]){
                    isTwoColor = false;
                }
            }
        }
    }
}

/*
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2

 */
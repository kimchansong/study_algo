package kr.co.miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DPath {
    static class Pair implements Comparable<Pair>{
        int dist;
        int pos;
        Pair(int pos, int dist){
            this.dist=dist; this.pos=pos;
        }

        @Override
        public int compareTo(Pair pair) {
            return this.dist-pair.dist;
        }

    }

    static int[] dist;
    static PriorityQueue<Pair> pq;
    static int[][] adj;
    static boolean[] visit;

    static int V, E;
    static int s1, s2;

    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1];
        adj = new int[V+1][V+1];
        visit = new boolean[V+1];

        for(int i = 0; i < E; i++){
            st= new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj[a][b] = c;
            adj[b][a] = c;
        }
        st = new StringTokenizer(br.readLine());
        s1 = Integer.parseInt(st.nextToken());
        s2 = Integer.parseInt(st.nextToken());

        pq= new PriorityQueue<Pair>();
        int result1 = path(s1, s2);
        pq.clear();

        int result2 = path(s2, s1);
        if(result1 == -1 || result2 == -1){
            System.out.println("-1");
        }else{
            if(result1 > result2){
                System.out.println(result2);
            }else{
                System.out.println(result1);
            }
        }
        br.close();
    }


    public static int dijkstra(int start, int end)
    {
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Pair( start, 0));
        Arrays.fill(visit, false);
        while (!pq.isEmpty())
        {
            Pair temp = pq.poll();
            int here = temp.pos;
            int cost = temp.dist;
            if(visit[here]){
                continue;
            }
            visit[here] = true;
            if (dist[here] > cost) {
                dist[here]= cost;
                for (int i = 1; i <= V; i++) {
                    if (adj[here][i] != 0){
                        pq.offer(new Pair(i, adj[here][i]+cost));
                    }
                }
            }
        }
        if(dist[end] == Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[end];
        }
    }

    public static int path(int s1, int s2){
        int result = 0;
        int temp;

        temp = dijkstra(1, s1);
        if(temp == -1){
            return temp;
        }else{
            result+= temp;
        }

        temp = dijkstra(s1,s2);
        if(temp ==-1){
            return temp;
        }else{
            result+= temp;
        }

        temp = dijkstra(s2,V);
        if(temp == -1){
            return temp;
        }else{
            result+=temp;
        }
        return result;
    }

}

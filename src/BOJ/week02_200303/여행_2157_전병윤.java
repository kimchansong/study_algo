package BOJ.week02_200303;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int K;
    static int Result;
    static ArrayList<Map<Integer, Integer>> routes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Result = 0;
        routes = new ArrayList<Map<Integer, Integer>>();
        for(int i = 0; i <= N; i++) {
            routes.add(new HashMap<Integer, Integer>());
        }
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int taste = Integer.parseInt(st.nextToken());

            if(start > end) continue;
            if(routes.get(start).getOrDefault(end, 0) < taste){
                if(routes.get(start).containsKey(end)){
                    routes.get(start).remove(end);
                }
                routes.get(start).put(end, taste);
            }
        }

        go(1, 1, 0);
        System.out.println(Result);
    }

    static int go(int m, int n, int sum) {
        if(n == N) {
            Result = Math.max(sum, Result);
            return Result;
        }
        if(m == M) return -1;

        for(Map.Entry<Integer, Integer> entry : routes.get(n).entrySet()) {
            int end = entry.getKey();
            if(n > end) continue;
            int taste = entry.getValue();
            go(m + 1, end, sum + taste);
        }
        return -1;
    }
}
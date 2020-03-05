package BOJ.week01_200303;

import java.util.Scanner;

public class 내리막길_1520_김찬송 {
	static int M;
	static int N;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static boolean visited[][];
	static int dist[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		int map[][] = new int[M][N];
		visited = new boolean[M][N];
		dist = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs(map, 0, 0);
		System.out.println(dist[0][0]);
	}

	static int dfs(int map[][], int y, int x) {
		if (y == M - 1 && x == N - 1) {
			return 1;
		}

		if (!visited[y][x]) {
			visited[y][x] = true;
			for (int i = 0; i < 4; i++) {
				int ty = y + dy[i];
				int tx = x + dx[i];
				if (ty > -1 && tx > -1 && ty < M && tx < N) {
					if(map[y][x]>map[ty][tx])
						dist[y][x] += dfs(map, ty, tx);
				}
			}
		}
		return dist[y][x];
	}

}

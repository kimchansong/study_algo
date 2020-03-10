package BOJ.week02_200303;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽부수고이동하기_2206_김찬송 {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int N;
	static int M;
	static boolean isOk;
	static class Node {
		int y, x, cnt, distory;

		public Node(int y, int x, int cnt, int distory) {
			// TODO Auto-generated constructor stub
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.distory = distory;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int map[][] = new int[N][M];
		int dist[][][] = new int[N][M][2];
		dist[0][0][0] = 1;
		dist[0][0][1] = 1;
		for (int i = 0; i < N; i++) {
			String input = sc.next();
			String line[] = input.split("");
			for (int j = 0; j < line.length; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		bfs(map, dist);
		if(!isOk) {
			System.out.println(-1);
		}else {
			System.out.println(Math.max(dist[N-1][M-1][0], dist[N-1][M-1][1]));
		}
	}

	static void bfs(int map[][], int dist[][][]) {
		Queue<Node> q = new LinkedList()<Node>();
		q.add(new Node(0, 0, 1, 1));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.y == N - 1 && n.x == M - 1) {
				isOk = true;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int ty = n.y + dy[i];
				int tx = n.x + dx[i];
				if (ty > -1 && tx > -1 && ty < N && tx < M) {
					if (map[ty][tx] == 1) { // 벽일때
						if(n.distory == 1) {
							dist[ty][tx][0] = n.cnt + 1;
							q.add(new Node(ty, tx, n.cnt + 1, 0));
						}
					} else { // 벽이 아닐때
						if(dist[ty][tx][n.distory]==0) {
							dist[ty][tx][n.distory] = n.cnt + 1;
							q.add(new Node(ty, tx, n.cnt + 1, n.distory));
						}
					}
				}
			}
		}
	}
}

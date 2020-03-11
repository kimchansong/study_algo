package woonjae.babyshark;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N = 0;
	static int[][] map;

	static int SHARK = 9;
	static int pattern1[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 우선순위
																			// 상
																			// 좌
																			// 우
																			// 하
	static boolean visited[][];

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			N = sc.nextInt();
			map = new int[N][N];

			int starty = 0;
			int startx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == SHARK) {
						startx = i;
						starty = j;
						map[i][j] = 0;
					}
				}
			}

			System.out.println(dfs(startx, starty, 2, 0));

		}
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static int dfs(int x, int y, int K /* 크기 */, int n /* 먹은 갯수 */) {

		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N][N];
		visited[x][y] = true;
		q.add(new Node(x, y, 0));
		int min = Integer.MAX_VALUE;
		int dx = Integer.MAX_VALUE;
		int dy = Integer.MAX_VALUE;

		boolean flag = false;
		loop: while (!q.isEmpty()) {

			Node v = q.poll();
			boolean eatable = (map[v.x][v.y] > 0 && map[v.x][v.y] < K);
			if (flag || eatable) {
				// 현재 위치가 먹을 수도 있음
				// 현재 위치는 최단거리로 찾은거고, 현재 큐에 있는 내용들 중에
				// 현위치보다 우선순위가 높을수있음
				if (!eatable) {
					continue;
				}
				if (dx > v.x || (dx == v.x && dy > v.y)) {
					if (map[v.x][v.y] > 0 && min >= v.count) {
						dx = v.x;
						dy = v.y;
						min = v.count;
					}
					flag = true;
				}
				continue loop;
			}

			for (int i = 0; i < pattern1.length; i++) {
				int nx = v.x + pattern1[i][0];
				int ny = v.y + pattern1[i][1];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= K) {
					visited[nx][ny] = true;
					// 방문가능
					q.add(new Node(nx, ny, v.count + 1));
				}
			}
		}

		if (min > 0 && min < Integer.MAX_VALUE) {
			if (K == n + 1) {
				K = K + 1;
				n = -1;
			}
			map[dx][dy] = 0;
			// print();
			//System.out.println();
			return dfs(dx, dy, K, n + 1) + min;
		} else {
			return 0;
		}
	}

	static class Node {
		public Node(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		int x;
		int y;
		int count;

	}

}

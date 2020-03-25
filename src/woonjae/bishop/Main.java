package woonjae.bishop;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int ANSWER = 0;
	static int K;
	static int MAP[][];
	static int visited[][];

	static int PATTERN[][] = { { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			K = sc.nextInt();

			MAP = new int[K][K];
			visited = new int[K][K];
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					MAP[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					dfs(i, j, 1);
					clearBishop(1);
				}
			}
			System.out.println(ANSWER);
		}
	}

	private static void dfs(int x, int y, int bishop) {
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(bishop);
//		System.out.println();

		if (x >= K) {
			return;
		}
		if (y >= K) {
			return;
		}

		if (visited[x][y] > 0) {
			return;
		}

		if (MAP[x][y] == 0) {
			return;
		}
		bishopCheck(x, y, bishop);
		for (int i = 0; i < visited.length; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();

		ANSWER = Math.max(ANSWER, bishop);

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				dfs(i, j, bishop + 1);
				clearBishop(bishop + 1);
			}
		}

	}

	private static void bishopCheck(int x, int y, int bishop) {

		for (int k = 0; k < PATTERN.length; k++) {
			int nx = x;
			int ny = y;
			while (nx >= 0 && ny >= 0 && nx < K && ny < K) {
				if (visited[nx][ny] == 0) {
					visited[nx][ny] = bishop;
				}
				nx = nx + PATTERN[k][0];
				ny = ny + PATTERN[k][1];
			}
		}
	}

	private static void clearBishop(int bishop) {
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				if (visited[i][j] == bishop) {
					visited[i][j] = 0;
				}
			}
		}

	}

	static class Node {

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		int x, y;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}
}

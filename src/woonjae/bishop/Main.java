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
			// blackDfs(x, y, bishop);
			int white = dfs(0, 0, 1);
			int black = dfs(0, 1, 1);
			System.out.println(white);
			System.out.println(black);

		}
	}

	private static void print() {
		for (int k = 0; k < visited.length; k++) {
			System.out.println(Arrays.toString(visited[k]));
		}
		System.out.println();
	}

	private static int dfs(int x, int y, int bishop) {
		int value = 0;
		if (y >= K) {
			// 현재 화이트 면 다음 블랙
			y = (y + 1) % 2;
			x = x + 1;
		}

		if (x >= K) {
			return 0;
		}
//		System.out.println("x " + x);
//		System.out.println("y " + y);
//		System.out.println(MAP[x][y]);
//		System.out.println(visited[x][y]);
//		System.out.println();

		if (MAP[x][y] != 0 && visited[x][y] == 0) {
			bishopCheck(x, y, bishop);
//			System.out.println("앙");
			value = Math.max(dfs(x, y + 2, bishop + 1), bishop);
			clearBishop(bishop);
		}
		value = Math.max(dfs(x, y + 2, bishop), value);
		clearBishop(bishop);
//		System.out.println(value);
		return value;
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

}

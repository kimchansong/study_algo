package woonjae.flower;

import java.util.Scanner;

public class Main {
	static int map[][];

	static int visted[][];
	static int answer = Integer.MAX_VALUE;

	static int pattern[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static int k;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			k = sc.nextInt();

			map = new int[k][k];
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < k; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			visted = new int[k][k];

			for (int i = 1; i < k; i++) {
				for (int j = 1; j < k; j++) {
					dfsFind(j, i, 1, 0);
					removeNode(1);
				}
			}

			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
	}

	private static void dfsFind(int x, int y, int depth, int value) {
		if (value > answer) {
			return;
		}

//		System.out.println("x " + x);
//		System.out.println("y " + y);
//		System.out.println("value " + value);
//		System.out.println("depth " + depth);
//		// System.out.println("newValue " + newValue);
//		System.out.println();

		int nDept = depth;
		int newValue = isRightPosition(x, y, depth);
		if (newValue >= 0) {
			nDept += 1;
		} else {
			return;
		}
		if (newValue + value > answer) {
			return;
		}

		if (depth == 3) {
			value = newValue + value;
			answer = Math.min(answer, value);
			return;
		}

		for (int i = y; i < k; i++) {
			for (int j = 1; j < k; j++) {
				dfsFind(j, i, nDept, newValue + value);
				removeNode(nDept);
			}
		}

	}

	private static void removeNode(int depth) {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (visted[j][i] == depth) {
					visted[j][i] = 0;
				}
			}
		}
	}

	private static int isRightPosition(int x, int y, int depth) {
		if (checkedRanage(x, y)) {
			for (int i = 0; i < pattern.length; i++) {
				int nx = x + pattern[i][0];
				int ny = y + pattern[i][1];
				if (!checkedRanage(nx, ny)) {
					return -1;
				}
			}
			int value = map[y][x];
			visted[y][x] = depth;
			for (int i = 0; i < pattern.length; i++) {
				int nx = x + pattern[i][0];
				int ny = y + pattern[i][1];
				visted[ny][nx] = depth;
				value += map[ny][nx];
			}
			return value;
		} else {
			return -1;
		}
	}

	private static boolean checkedRanage(int nx, int ny) {
		return nx > -1 && ny > -1 && nx < k && ny < k && visted[ny][nx] == 0;
	}
}

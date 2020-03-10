package woonjae.flower;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p1 {

	static int xlength;
	static int ylength;
	static int map[][];
	static int visited[][];

	static int pattern[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int k;
	static int answer = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {

		try (Scanner sc = new Scanner(System.in)) {
			k = sc.nextInt();
			xlength = k;
			ylength = k;

			// endpoint
			int x = xlength - 1;
			int y = ylength - 1;

			map = new int[ylength][xlength];
			visited = new int[ylength][xlength];

//			for (int i = 0; i < ylength; i++) {
//				for (int j = 0; j < xlength; j++) {
//					map[i][j] = sc.nextInt();
//				}
//			}

			Queue<Node> q = new LinkedList<Node>();

			dfs(0, 0, 1);
			// System.out.println(visited[0][0]);
			System.out.println(answer);
		}
	}

	private static void dfs(int x, int y, int depth) {

		// visited[0][0] = 3;

		if (x >= xlength)
			return;
		if (y >= ylength) {
			dfs(x + 1, 0, depth);
			return;
		}
		for (int i = 0; i < pattern.length; i++) {
			int nx = x + pattern[i][0];
			int ny = y + pattern[i][1];
			if (isCorrectPosition(nx, ny)) {
				visited[nx][ny] = depth;
			}
		}

		if (depth == 3) {

		} else {
			if (isCorrectPosition(x, y + 1))
				dfs(x, y + 1, depth + 1);
			else
				dfs(x + 1, 0, depth + 1);
		}

	}

	private static boolean isCorrectPosition(int nx, int ny) {
		return nx > -1 && ny > -1 && nx < xlength && ny < ylength && visited[ny][nx] != 0;
	}

	private static void delete(int depth) {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				if (visited[i][j] == depth) {
					visited[i][j] = 0;
				}
			}
		}
	}

	static class Node {
		Node() {
		}

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x = 0;
		int y = 0;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

}

package woonjae.monkey;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p1 {

	static int xlength;
	static int ylength;
	static int map[][];

	static int k;
	static int answer = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {

		try (Scanner sc = new Scanner(System.in)) {
			k = sc.nextInt();
			xlength = sc.nextInt();
			ylength = sc.nextInt();

			// endpoint
			int x = xlength - 1;
			int y = ylength - 1;

			map = new int[ylength][xlength];
			boolean visited[][][] = new boolean[ylength][xlength][k + 1];

			for (int i = 0; i < ylength; i++) {
				for (int j = 0; j < xlength; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(0, 0, k, 0));

			// ÎßêÌèº ?ù¥?èô
			int pattern1[][] = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
					{ -1, -2 } };
			// ?õê?à≠?ù¥?èº ?ù¥?èô
			int pattern2[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
			visited[0][0][k] = true;

			while (!q.isEmpty()) {
				Node d = q.poll();
				if (d.x == x && d.y == y) {
					answer = d.count;
					break;
				}

				// ÎßêÌèº
				if (d.k > 0) {
					move(visited, q, pattern1, d);
				}

				// ?õê?à≠?ù¥ ?èº
				for (int i = 0; i < pattern2.length; i++) {
					int nx = d.x + pattern2[i][0];
					int ny = d.y + pattern2[i][1];
					int nk = d.k;

					if (isCorrectPosition(visited, nx, ny, nk)) {
						visited[ny][nx][nk] = true;
						q.add(new Node(nx, ny, nk, d.count + 1));
					}
				}

			}

			System.out.println(answer);
		}
	}

	private static void move(boolean[][][] visited, Queue<Node> q, int[][] pattern1, Node d) {
		for (int i = 0; i < pattern1.length; i++) {
			int nx = d.x + pattern1[i][0];
			int ny = d.y + pattern1[i][1];
			int nk = d.k - 1;

			if (isCorrectPosition(visited, nx, ny, nk)) {
				visited[ny][nx][nk] = true;
				q.add(new Node(nx, ny, nk, d.count + 1));
			}
		}
	}

	private static boolean isCorrectPosition(boolean[][][] visited, int nx, int ny, int nk) {
		return nx > -1 && ny > -1 && nx < xlength && ny < ylength && map[ny][nx] == 0 && !visited[ny][nx][nk];
	}

	static class Node {
		Node() {
		}

		Node(int x, int y, int k, int count) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.count = count;
		}

		int x = 0;
		int y = 0;
		int k = 0;
		int count = 0;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", k=" + k + ", count=" + count + "]";
		}

	}

}

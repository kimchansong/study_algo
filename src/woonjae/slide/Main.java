package woonjae.slide;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static int M;
	static int[][] MAP;
	static int pattern[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			N = sc.nextInt();
			M = sc.nextInt();

			MAP = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					MAP[i][j] = 0;
				}
			}

			Queue<Node> node = new LinkedList<>();
			node.add(new Node(0, 0));
			while (!node.isEmpty()) {
				Node n = node.poll();

				int x = n.x;
				int y = n.y;
				for (int i = 0; i < pattern.length; i++) {
					int nx = x + pattern[i][0];
					int ny = y + pattern[i][1];
					if (nx > -1 && ny > -1 && nx < M && ny < N && MAP[y][x] > MAP[ny][nx]) {

					}
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

	}

}

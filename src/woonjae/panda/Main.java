package woonjae.panda;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int K;
	static int MAP[][];
	static int A[][];
	static int PATTERN[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static int answer = 1;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			K = sc.nextInt();

			MAP = new int[K][K];
			A = new int[K][K];
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					MAP[i][j] = sc.nextInt();
				}
			}
			Queue<Node> node = new PriorityQueue<>((n, m1) -> {
				return n.k - m1.k;
			});

			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					node.add(new Node(i, j, MAP[i][j]));
				}
			}

			while (!node.isEmpty()) {
				Node n = node.poll();
				boolean flag = false;
				// System.out.println("x " + n.x);
				// System.out.println("y " + n.y);
				for (int k = 0; k < PATTERN.length; k++) {
					int nx = n.x + PATTERN[k][0];
					int ny = n.y + PATTERN[k][1];
					if (nx >= 0 && ny >= 0 && nx < K && ny < K && MAP[n.x][n.y] > MAP[nx][ny]) {

						A[n.x][n.y] = Math.max(A[n.x][n.y], A[nx][ny] + 1);
						answer = Math.max(answer, A[n.x][n.y]);
						flag = true;
					}
//
//					if (input[y][x] > input[y + dy[k]][x + dx[k]]) {
//						dp[y][x] = Math.max(dp[y][x], dp[y + dy[k]][x + dx[k]] + 1);
//						max = Math.max(max, dp[y][x]);
//						check = true;
//					}

					for (int i = 0; i < K; i++) {
						System.out.println(Arrays.toString(A[i]));
					}
					System.out.println();
				}
				if (!flag) {
					A[n.x][n.y] = 1;
				}
			}

			System.out.println(answer);

		}

	}

	static class Node {

		public Node(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}

		int x, y, k;
	}
}

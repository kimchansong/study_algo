package woonjae.monkey;

import java.util.LinkedList;
import java.util.Scanner;

public class p2 {
	static int[] dx = { -1, 1, 0, 0, -1, -2, 1, 2, -1, -2, 1, 2 };
	static int[] dy = { 0, 0, -1, 1, -2, -1, -2, -1, 2, 1, 2, 1 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int k = s.nextInt();

		int mx = s.nextInt();
		int my = s.nextInt();

		boolean matrix[][] = new boolean[my][mx];

		for (int y = 0; y < my; y++) {
			for (int x = 0; x < mx; x++) {
				matrix[y][x] = (s.nextInt() == 1);
			}
		}

		bfs(matrix, my, mx, k);
	}

	static void bfs(boolean[][] matrix, int my, int mx, int k) {
		LinkedList<Integer> qy = new LinkedList<>();
		LinkedList<Integer> qx = new LinkedList<>();
		LinkedList<Integer> qk = new LinkedList<>();

		boolean[][][] visit = new boolean[my][mx][k + 1];

		visit[0][0][k] = true;
		qy.add(0);
		qx.add(0);
		qk.add(k);

		int result = 0;
		while (!qx.isEmpty()) {
			int qSize = qx.size();

			for (int i = 0; i < qSize; i++) {
				int nowY = qy.pop();
				int nowX = qx.pop();
				int nowK = qk.pop();

				// Ï¢ÖÎ£åÏ°∞Í±¥
				if (nowX == mx - 1 && nowY == my - 1) {
					System.out.println(result);
					return;
				}

				for (int j = 0; j < dx.length; j++) {
					int nextY = nowY + dy[j];
					int nextX = nowX + dx[j];
					int nextK = nowK;

					// ÎßêÏù¥?èô
					if (j >= 4) {
						nextK--;

						// ?ã§ ?çº?úºÎ©?
						if (nextK < 0) {
							continue;
						}
					}

					// Î™ªÍ??äî Í≥≥Ïù¥Î©?
					if (nextY < 0 || nextX < 0 || nextY >= my || nextX >= mx) {
						continue;
					}

					// ?û•?ï†Î¨ºÏù¥Í±∞ÎÇò Î∞©Î¨∏?ñà?úºÎ©?
					if (matrix[nextY][nextX] || visit[nextY][nextX][nextK]) {
						continue;
					}

					// Î∞©Î¨∏
					visit[nextY][nextX][nextK] = true;
					qy.add(nextY);
					qx.add(nextX);
					qk.add(nextK);
				}
			}

			result++;
		}

		System.out.println(-1);
	}
}
package woonjae.bipartite;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

	static int testcase = 0;

	static int point = 0;
	static int line = 0;

	static int RED = -1;

	static int MAX = 20000;
	static int[] visited = new int[MAX + 1];
	static boolean[][] map = new boolean[MAX + 1][MAX + 1];

	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			testcase = sc.nextInt();
			loop: while (testcase-- > 0) {
				point = sc.nextInt();
				line = sc.nextInt();

				for (int i = 0; i <= point; i++) {
					for (int j = 0; j <= point; j++) {
						map[i][j] = false;
					}
				}

				for (int i = 1; i < point; i++) {
					visited[i] = 0;
				}

				// visited = new int[point + 1];
				for (int i = 0; i < line; i++) {
					int x = sc.nextInt();
					int y = sc.nextInt();
					map[x][0] = true; // 해당 행에 찾을것이 있다
					map[y][0] = true; // 해당 행에 찾을것이 있다
					map[x][y] = true;
					map[y][x] = true;
				}
				for (int i = 1; i <= point; i++) {
					if (visited[i] == 0 && !bfs(i, RED)) {
						System.out.println("NO");
						continue loop;
					}
				}

				System.out.println("YES");
			}

		}

	}

	private static boolean bfs(int start, int color) {

		q.offer(start);
		visited[start] = color;

		while (!q.isEmpty()) {
			int startV = q.poll();
			if (map[startV][0]) { // 행에 찾을것이 있다면
				for (int i = 1; i <= point && map[startV][i]; i++) {
					// 인접 노드믄
					if (visited[i] == 0) { // 방문한 적 없으면
						q.offer(i);
						visited[i] = (visited[startV] * -1);
					} else if (visited[i] + visited[startV] != 0) {
						// 방문은 했는데 색이 다르면
						q.clear();
						return false;
					}
				}
			}
		}

		return true;
	}
}

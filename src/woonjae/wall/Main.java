package woonjae.wall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int xlength;
	static int ylength;
	static int map[][];

	static int k = 1;
	static int answer = -1;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// k = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			ylength = Integer.parseInt(st.nextToken());
			xlength = Integer.parseInt(st.nextToken());

			map = new int[ylength][xlength];
			boolean visited[][][] = new boolean[ylength][xlength][k + 1];

			for (int i = 0; i < ylength; i++) {
				st = new StringTokenizer(br.readLine());
				String dd = st.nextToken();
				for (int j = 0; j < xlength; j++) {
					map[i][j] = Integer.parseInt(dd.substring(j, j + 1));
				}
			}

			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(0, 0, k, 1));

			//
			int pattern1[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
			visited[0][0][k] = true;

			while (!q.isEmpty()) {
				Node d = q.poll();
				if (d.x == xlength - 1 && d.y == ylength - 1) {
					answer = d.count;
					break;
				}

				// 패턴 무브
				for (int i = 0; i < pattern1.length; i++) {
					int nx = d.x + pattern1[i][0];
					int ny = d.y + pattern1[i][1];
					int nk = d.k;

					if (isCorrectPosition(visited, nx, ny, nk)) {
						if (map[ny][nx] == 0) {
							visited[ny][nx][nk] = true;
							q.add(new Node(nx, ny, nk, d.count + 1));
						}

						// 벽뚫기 가능
						if (map[ny][nx] == 1 && nk > 0) {
							visited[ny][nx][nk - 1] = true;
							q.add(new Node(nx, ny, nk - 1, d.count + 1));
						}
					}

				}

			}

			System.out.println(answer);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static boolean isCorrectPosition(boolean[][][] visited, int nx, int ny, int nk) {
		return nx > -1 && ny > -1 && nx < xlength && ny < ylength && !visited[ny][nx][nk];
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

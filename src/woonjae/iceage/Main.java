package woonjae.iceage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int K, N;
	static int MAP[][];
	static int NMAP[][];
	static boolean visited[][];
	static int PATTERN[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static int answer = 1;

	static Queue<Node> move = new LinkedList<>();
	static Queue<Node> ice = new LinkedList<>();

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			K = sc.nextInt();
			N = sc.nextInt();

			MAP = new int[K][N];
			NMAP = new int[K][N];
			visited = new boolean[K][N]; // 빙산이 10이하임

			for (int i = 0; i < K; i++) {
				for (int j = 0; j < N; j++) {
					MAP[i][j] = sc.nextInt();
					if (MAP[i][j] > 0) {
						ice.add(new Node(i, j));
					}

				}
			}

			int age = 0;
			while (!ice.isEmpty()) {

//				for (int i = 0; i < MAP[age].length; i++) {
//					System.out.println(Arrays.toString(MAP[age][i]));
//				}
//				System.out.println();
				// 시작점
				// 삭제하지 않고 꺼냄
				Node dd = ice.peek();

				move.add(dd);
				visited[dd.x][dd.y] = true;
				int count = 1;
				// 섬인지 검사
				while (!move.isEmpty()) {
					Node d = move.poll();
					for (int k = 0; k < PATTERN.length; k++) {
						int nx = d.x + PATTERN[k][0];
						int ny = d.y + PATTERN[k][1];
						Node x = new Node(nx, ny);
						if (nx >= 0 && ny >= 0 && nx < K && ny < N && MAP[nx][ny] > 0 && isNotVisited(x)) {
							move.add(x);
							visited[x.x][x.y] = true;
							count++;
						}
					}
				}
//				System.out.println(count);
//				System.out.println(ice.size());
				if (count < ice.size()) {
					// 전부 방문하지않음.
					// 다른 섬이 있음.
					break;
				}

				// 녹이기
				int qsize = ice.size();
				for (int i = 0; i < qsize; i++) {
					Node n = ice.poll();
					int f = 0;
					for (int k = 0; k < PATTERN.length; k++) {
						int nx = n.x + PATTERN[k][0];
						int ny = n.y + PATTERN[k][1];
						if (nx >= 0 && ny >= 0 && nx < K && ny < N && MAP[nx][ny] <= 0) {
							f++;
						}
					}

					NMAP[n.x][n.y] = MAP[n.x][n.y] - f;

					if (NMAP[n.x][n.y] > 0) {
						ice.add(n);
					}

				}

				age++;

				if (ice.isEmpty()) {
					age = 0;
				}
				MAP = NMAP;
				NMAP = new int[K][N];
				visited = new boolean[K][N];
//				System.out.println(count);
//				System.out.println(node.size());

			}

			System.out.println(age);

		}
	}

	static boolean isNotVisited(Node x) {
		return !visited[x.x][x.y];
	}

	static class Node {

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		int x, y, k;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", k=" + k + "]";
		}

	}
}

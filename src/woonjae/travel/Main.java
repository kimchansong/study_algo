package woonjae.travel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int K;

	static int[][] map;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			N = sc.nextInt(); // N개의 도시
			M = sc.nextInt(); // M개 이하의 도시를 지나야함
			K = sc.nextInt(); // K 개의 항로가 있움

			map = new int[N + 1][N + 1];
			// ArrayList<Line> list = new ArrayList<>();

			// 최대치 이외에는 필요없을듯
			for (int i = 0; i < K; i++) {
				map[sc.nextInt()][sc.nextInt()] = sc.nextInt();
			}

			Queue<Node> q = new LinkedList<>();
			q.add(new Node(1, 0));
			while (!q.isEmpty()) {
				Node v = q.poll();
				for (int i = 0; i <= N; i++) {

				}

			}
		}

	}

	static class Node {

		Node() {
		}

		Node(int x, int value, int count) {
			this.x = x;
			this.value = value;
			this.count = count;
		}

		int x = 0;
		int value = 0;
		int count;

	}

}

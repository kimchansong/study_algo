package woonjae.mutal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static boolean[] visited = null;
	static int N = 0;
	static int SCV[];
	static int ZERO[];
	static int ATTACK[] = { 9, 3, 1 };

	static int FULL_ATTACK[][];
	static Queue<Node> q;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			N = sc.nextInt();
			SCV = new int[N];
			visited = new boolean[N];

			for (int i = 0; i < SCV.length; i++) {
				SCV[i] = sc.nextInt();
			}

			q = new LinkedList<>();
			q.add(new Node(SCV, 0));

			int value = 0;
			while (!q.isEmpty()) {
				Node v = q.poll();
				if (isZero(v)) {
					value = v.count;
					break;
				}
				int attackV = 0;
				dfs(v, attackV, N);
			}

			System.out.println(value);

		}
	}

	private static boolean isZero(Node v) {
		for (int i = 0; i < v.v.length; i++) {
			if (v.v[i] > 0)
				return false;
		}
		return true;
	}

	private static void dfs(Node v, int attackV, int remain) {
		if (remain == 0 || attackV > ATTACK.length) {
			q.add(new Node(v.v.clone(), v.count + 1));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int nv = attackV;
				boolean flag = v.v[i] > 0;
				if (flag) {
					v.v[i] -= ATTACK[attackV];
					nv += 1;
				}

				dfs(v, nv, remain - 1);

				if (flag)
					v.v[i] += ATTACK[attackV];

				visited[i] = false;
			}
		}
	}

	static class Node {
		int[] v;
		int count;

		public Node(int[] v, int count) {
			this.v = v;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [v=" + Arrays.toString(v) + ", count=" + count + "]";
		}

	}
}

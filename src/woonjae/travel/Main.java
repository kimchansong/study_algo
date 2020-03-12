package woonjae.travel;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int K;

	static int answer = Integer.MIN_VALUE;

	static int[][] map;

	static int[][] cache;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			N = sc.nextInt(); // N개의 도시
			M = sc.nextInt(); // M개 이하의 도시를 지나야함
			K = sc.nextInt(); // K 개의 항로가 있움

			map = new int[N + 1][N + 1];
			cache = new int[N + 1][N + 1];
			// ArrayList<Line> list = new ArrayList<>();

			// 최대치 이외에는 필요없을듯
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int value = sc.nextInt();

				map[x][y] = Math.max(value, map[x][y]);
			}

			// dfs(1, 1, 0);
			// System.out.println();

			for (int i = 0; i < cache.length; i++) {
				Arrays.fill(cache[i], -1);
			}

			int value = dfs(1, 1);
			System.out.println(value);
			// print();

		}

	}

	private static void print() {
		for (int i = 0; i < cache.length; i++) {
			System.out.print(cache[i] + " ");
		}
		System.out.println();
	}

	private static int dfs(int city, int count) {

		// 도달 못했으
		if (count >= M && city != N) {
			return Integer.MIN_VALUE;
		}

		// N에 도달하믄 뭐 다음으로 갈곳이 없으니
		// 기내식은 0
		if (city == N) {
			return 0;
		}

		// 온적없으면
		if (cache[city][count] == -1) {
			cache[city][count] = 0;

			for (int i = city + 1 /* 큰 쪽으로만 가면되니까 */ ; i <= N; i++) {
				if (map[city][i] > 0) {
					// 현재 에서 다음으로 가는 기내식이랑
					// 다음 값이랑
					cache[city][count] = Math.max(cache[city][count], dfs(i, count + 1) + map[city][i]);
				}
			}
		}
		return cache[city][count];
	}
}

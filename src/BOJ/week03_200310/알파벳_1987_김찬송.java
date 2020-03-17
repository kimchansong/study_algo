package BOJ.week03_200310;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ¾ËÆÄºª_1987_±èÂù¼Û {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int max = 0;

	static boolean newCheck[] = new boolean[26];
	static int N;
	static int M;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		char map[][] = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = sc.next();
			map[i] = input.toCharArray();
		}
		newCheck[map[0][0] - 'A'] = true;
		dfs(map, 0, 0, 1);
		System.out.println(max);
	}

	static void dfs(char map[][], int y, int x, int cnt) {
		max = Math.max(max, cnt);

		for (int i = 0; i < 4; i++) {
			int ty = y + dy[i];
			int tx = x + dx[i];
			if (ty > -1 && tx > -1 && ty < N && tx < M) {
				if (!newCheck[map[ty][tx] - 'A']) {
					newCheck[map[ty][tx] - 'A'] = true;
					dfs(map, ty, tx, cnt + 1);
					newCheck[map[ty][tx] - 'A'] = false;
				}
			}
		}
	}
}

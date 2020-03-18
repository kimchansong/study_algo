package woonjae.alphabet.move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;

	static int ANSWER = 0;

	static char[][] map;
	static boolean visited[][];
	static int pattern[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static Map<Character, Boolean> use = new HashMap<>();

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// k = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			visited = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			visited[0][0] = true;
			use.put(map[0][0], true);
			dfs(0, 0, 1);

			System.out.println(ANSWER);
		}
	}

	private static void dfs(int x, int y, int k) {
		if (x >= R)
			return;
		if (y >= C)
			return;

		boolean flag = true;
		for (int ii = 0; ii < pattern.length; ii++) {
			int nx = x + pattern[ii][0];
			int ny = y + pattern[ii][1];

			if (nx >= 0 && ny >= 0 && ny < C && nx < R && !visited[nx][ny] && !use.getOrDefault(map[nx][ny], false)) {
//				print();
//				System.out.println("==========================");
				visited[nx][ny] = true;
				use.put(map[nx][ny], true);
				dfs(nx, ny, k + 1);
				visited[nx][ny] = false;
				use.put(map[nx][ny], false);
				flag = false; // 찾은 케이스가 있음
			}
		}
		if (flag) // 찾은게 없다면 갈수없다고 보고
			ANSWER = Math.max(ANSWER, k);

	}

	private static void print() {
		for (Iterator<Character> iterator = use.keySet().iterator(); iterator.hasNext();) {
			Character type = iterator.next();
			System.out.println(" key " + type);
			System.out.println(" value " + use.get(type));
		}
	}
}

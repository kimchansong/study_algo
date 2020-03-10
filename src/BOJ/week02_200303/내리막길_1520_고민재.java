import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 내리막길_1520_고민재 {
	public static int ylength;
	public static int xlength;
	public static int[][] map;
	public static int answer;
	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[][] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		ylength = Integer.parseInt(s[0]);
		xlength = Integer.parseInt(s[1]);
		map = new int[ylength][xlength];
		count = new int[ylength][xlength];
		for (int i = 0; i < ylength; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < xlength; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				count[i][j] = -1;
			}
		}

		answer = dfs(0, 0);
		for (int[] ia : count) {
			System.out.println(Arrays.toString(ia));
		}
		System.out.println(answer);

	}

	private static int dfs(int y, int x) {
		if (y == ylength - 1 && x == xlength - 1) {
			return 1;
		}
		if(count[y][x]!=-1) {
			return count[y][x];
		}
		count[y][x] = 0;
		for (int d = 0; d < 4; d++) {
			int yy = y + dy[d];
			int xx = x + dx[d];
			if (yy>=0 && yy<ylength && xx>=0 && xx<xlength && map[yy][xx] < map[y][x]) {
				count[y][x] += dfs(yy, xx);
			}
		}
		return count[y][x];

	}
}
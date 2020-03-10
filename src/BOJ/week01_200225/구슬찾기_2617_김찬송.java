package BOJ.week01_200225;

import java.util.Arrays;
import java.util.Scanner;

public class 구슬찾기_2617_김찬송 {
	static int arr[][];
	static boolean check[];
	static int count[];
	static int N;
	static int M;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 숫자의 갯수
		int M = sc.nextInt(); // 주어지는 비교
		int mid = (N + 1) / 2;
		arr = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int h = sc.nextInt();
			int r = sc.nextInt();
			arr[h][r] = 1;
			arr[r][h] = -1;
		}
		check = new boolean[N + 1];
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(check, false);
			if (dfsUp(i) > mid) {
				ans++;
				continue;
			}
			Arrays.fill(check, false);
			if (dfsDown(i) > mid) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static int dfsUp(int idx) {
		check[idx] = true;
		int cnt = 1;
		for (int i = 1; i < check.length; i++) {
			if (arr[idx][i] == -1 && !check[i]) {
				cnt += dfsUp(i);
			}
		}
		return cnt;
	}

	static int dfsDown(int idx) {
		check[idx] = true;
		int cnt = 1;
		for (int i = 1; i < check.length; i++) {
			if (arr[idx][i] == 1 && !check[i]) {
				cnt += dfsDown(i);
			}
		}
		return cnt;
	}
}


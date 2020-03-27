package woonjae.easy2048;

import java.util.Scanner;

public class Main {

	private static int MAX = 0;
	static int K;
	static int MAP[][][];

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			K = sc.nextInt();
			MAP = new int[K][K][6]; // 다섯개..?
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					MAP[i][j][0] = sc.nextInt();
				}
			}

			figering(0);

			System.out.println(MAX);

			// print();

		}
	}

	private static void print() {
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < K; k++) {
				for (int j = 0; j < MAP.length; j++) {
					System.out.print(MAP[k][j][i] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

	}

	private static void figering(int count) {
		if (count >= 5) {
			MAX = Math.max(MAX, findMax());
			return;
		}

		left(count);
		clear(count);

		right(count);
		clear(count);

		up(count);
		clear(count);

		down(count);
		clear(count);
	}

	private static int findMax() {
		int max = 0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				max = Math.max(max, MAP[i][j][5]);
			}
		}
		return max;
	}

	private static void clear(int count) {
		for (int k = 0; k < K; k++) {
			for (int j = 0; j < K; j++) {
				MAP[k][j][count + 1] = 0;
			}
		}
	}

	private static void down(int count) {
		// TODO Auto-generated method stub

		// bottom-top 으로 진행하면서 stack에 넣고
		// 다음숫자를 만나면 꺼냄 (0아닌)
		// 두개가 같으면 숫자를 더해서 결과 리스트에 넣고
		// 같지 않으면 꺼냈던 숫자만 리스트에 넣음
		// 비교 숫자는 스택으로.
		// 스택이 아니어도 되겠군

		for (int i = 0; i < K; i++) {
			int value = 0;
			int k = K - 1;
			for (int j = K - 1; j >= 0; j--) {
				int v = MAP[j][i][count];
				if (v == 0)
					continue;
				if (value == 0) {
					value = v;
					continue;
				}

				if (value == v) {
					MAP[k][i][count + 1] = v * 2;
					value = 0;
					k--;
				} else {
					MAP[k][i][count + 1] = value;
					value = v;
					k--;
				}
			}
			if (value > 0) {
				MAP[k][i][count + 1] = value;
			}
		}

		figering(count + 1);
	}

	private static void up(int count) {

		for (int i = 0; i < K; i++) {
			int value = 0;
			int k = 0;
			for (int j = 0; j < K; j++) {
				int v = MAP[j][i][count];
				if (v == 0)
					continue;
				if (value == 0) {
					value = v;
					continue;
				}

				if (value == v) {
					MAP[k][i][count + 1] = v * 2;
					value = 0;
					k++;
				} else {
					MAP[k][i][count + 1] = value;
					value = v;
					k++;
				}
			}
			if (value > 0) {
				MAP[k][i][count + 1] = value;
			}
		}
		figering(count + 1);
	}

	private static void right(int count) {

		for (int i = 0; i < K; i++) {
			int value = 0;
			int k = K - 1;
			for (int j = K - 1; j >= 0; j--) {
				int v = MAP[i][j][count];
				if (v == 0)
					continue;
				if (value == 0) {
					value = v;
					continue;
				}

				if (value == v) {
					MAP[i][k][count + 1] = v * 2;
					value = 0;
					k--;
				} else {
					MAP[i][k][count + 1] = value;
					value = v;
					k--;
				}
			}
			if (value > 0) {
				MAP[i][k][count + 1] = value;
			}
		}
		figering(count + 1);
	}

	private static void left(int count) {

		System.out.println("left " + count);

		for (int i = 0; i < K; i++) {
			int value = 0;
			int k = 0;
			for (int j = 0; j < K; j++) {
				int v = MAP[i][j][count];
				if (v == 0)
					continue;
				if (value == 0) {
					value = v;
					continue;
				}

				System.out.println(count + 1);
				if (value == v) {
					MAP[i][k][count + 1] = v * 2;
					value = 0;
					k++;
				} else {
					MAP[i][k][count + 1] = value;
					value = v;
					k++;
				}
			}
			if (value > 0) {
				MAP[i][k][count + 1] = value;
			}
		}

		print();
		figering(count + 1);
	}
}

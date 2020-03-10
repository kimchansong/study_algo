package woonjae.scale;

import java.util.Scanner;

public class Main {

	static int k; // ?��?��
	static int K; // ?��?��
	static int C; // condition
	static int conditions[][];
	static int memory[][]; // ?��?��?��?��

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			k = sc.nextInt();
			C = sc.nextInt();
			K = k + 1;

			int min = (k - 1) / 2; // �?벼운 ?��?��
			int max = k - (k + 1) / 2; // 무거?��?��?��

			conditions = new int[C][2];
			memory = new int[K][K];

			int answer = 0;
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < 2; j++) {
					conditions[i][j] = sc.nextInt();
				}
			}

			//
			for (int i = 0; i < conditions.length; i++) {
				int nx = conditions[i][0];
				int ny = conditions[i][1];
				memory[nx][ny] = 1;
				memory[ny][nx] = -1;
			}

			// ?���? ?�� ?�� ?��?�� 모든 �?
			for (int m = 1; m < memory.length; m++) {
				for (int i = 1; i < memory.length; i++) {
					for (int j = 1; j < memory.length; j++) {
						if (memory[i][m] == -1 && memory[m][j] == -1) {
							// i -> m -> j ?��쪽이 ?���?벼�?
							memory[i][j] = -1;
							memory[j][i] = 1;
						}
					}
				}
			}
			
			

			for (int i = 1; i < K; i++) {
				int lcount = 0, hcount = 0; // light count, heavy count
				for (int j = 1; j < K; j++) {
					if (memory[i][j] == -1)
						lcount++;
					else if (memory[i][j] == 1)
						hcount++;
				}
				if (lcount > min || hcount > max) {
					answer++;
				}

			}

			System.out.println(answer);

		}
	}

}

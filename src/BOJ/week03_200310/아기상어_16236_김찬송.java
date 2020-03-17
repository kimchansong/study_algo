package BOJ.week03_200310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class �Ʊ���_16236_������ {
	static class fish {
		int fishY;
		int fishX;
		int size;

		public fish(int fishY, int fishX, int size) {
			// TODO Auto-generated constructor stub
			this.fishY = fishY;
			this.fishX = fishX;
			this.size = size;
		}
	}

	static int N;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int max = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int map[][] = new int[N][N];
		int sharkY = 0;
		int sharkX = 0;
		List<fish> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
				} else if (map[i][j] != 0) {
					list.add(new fish(i, j, map[i][j]));
				}
			}
		}
		turn(map, sharkY, sharkX, 2, list, 0, 0);
		System.out.println(max);
	}

	static void turn(int map[][], int sharkY, int sharkX, int size, List<fish> list, int cnt, int distance) {

		max = Math.max(max, distance);

		int c[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(c[i], 99999999);
		}
		c[sharkY][sharkX] = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { sharkY, sharkX, 0});
		while (!queue.isEmpty()) { // ���� �ִ� ���� üũ
			int temp[] = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ty = temp[0] + dy[i];
				int tx = temp[1] + dx[i];
				if (ty > -1 && tx > -1 && ty < N && tx < N) {
					if (map[ty][tx] <= size && c[ty][tx]==99999999) {
						c[ty][tx] = temp[2]+1;
						queue.add(new int[] { ty, tx ,c[ty][tx]});
					}
				}
			}
		}
		// ���� ������ ���� ����, ���� ���ʿ� �ִ� ����� ã��
		int eatY = 999;
		int eatX = 999;
		int eatdist = 99999999;
		int idx = -1;
		for (int i = 0; i < list.size(); i++) {
			// ����� ũ�Ⱑ �۰� �� �� �ִ��� Ȯ��
			if (list.get(i).size < size && c[list.get(i).fishY][list.get(i).fishX]!=99999999) {
				// �Ÿ��� ª���� �ٲٱ�
				if (c[list.get(i).fishY][list.get(i).fishX]<eatdist) {
					eatY = list.get(i).fishY;
					eatX = list.get(i).fishX;
					eatdist = c[list.get(i).fishY][list.get(i).fishX];
					idx = i;
					// ���� ��� Y�� �켱 ���, Y�� ������ X�� �켱 ���
				} else if (c[list.get(i).fishY][list.get(i).fishX]==eatdist) {
					if (list.get(i).fishY < eatY) {
						eatY = list.get(i).fishY;
						eatX = list.get(i).fishX;
						idx = i;
					} else if (list.get(i).fishY == eatY) {
						if (list.get(i).fishX < eatX) {
							eatX = list.get(i).fishX;
							idx = i;
						}
					}
				}
			}
		}
		// ���� ����� ����
		if (idx == -1) {
			return;
		}
		// distance ���� ��
		list.remove(idx);
		map[sharkY][sharkX] = 0;
		map[eatY][eatX] = 9;
		if (cnt + 1 == size) {
			turn(map, eatY, eatX, size + 1, list, 0, distance + eatdist);
		} else {
			turn(map, eatY, eatX, size, list, cnt + 1, distance + eatdist);
		}
	}
}

package BOJ.week03_200310;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ¹ÂÅ»¸®½ºÅ©_12869_±èÂù¼Û {
	static int damage[][] = { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 9, 3 }, { 1, 3, 9 } };
	static boolean visited[][][][] = new boolean[61][61][61][20];

	static class scv {
		int a, b, c, cnt;

		public scv(int a, int b, int c, int cnt) {
			// TODO Auto-generated constructor stub
			this.a = a;
			this.b = b;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int arr[] = {0,0,0};
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Queue<scv> q = new LinkedList<scv>();
		q.add(new scv(arr[0], arr[1], arr[2], 0));
		while (!q.isEmpty()) {
			scv s = q.poll();
			if(s.a==0&&s.b==0&&s.c==0) {
				System.out.println(s.cnt);
				break;
			}
			for (int i = 0; i < 6; i++) {
				int tmpa = s.a - damage[i][0];
				int tmpb = s.b - damage[i][1];
				int tmpc = s.c - damage[i][2];
				if (tmpa < 0)
					tmpa = 0;
				if (tmpb < 0)
					tmpb = 0;
				if (tmpc < 0)
					tmpc = 0;
				if(!visited[tmpa][tmpb][tmpc][s.cnt+1]) {
					visited[tmpa][tmpb][tmpc][s.cnt+1] = true;
					q.add(new scv(tmpa, tmpb, tmpc, s.cnt+1));
				}
			}
		}
	}
}

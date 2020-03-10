package BOJ.week01_200225;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ²É±æ_14620_±èÂù¼Û {
	static final int cant = 100000000;
	static int N;
	static int minValue=100000000;
	static boolean checkmap[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int map[][] = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		int count[][] = new int[N][N];
		flower get[] = new flower[3];
		checkmap = new boolean[N][N];
		counting(map, count);
		check(0, 0, get);
		System.out.println(minValue);
	}
	static int dx[] = {-1,1,0,0,0};
	static int dy[] = {0,0,-1,1,0};
	static List<flower> list = new ArrayList<flower>();
	static class flower{
		int x, y, count;
		public flower(int x, int y, int count) {
			// TODO Auto-generated constructor stub
			this.x = x; 
			this.y = y;
			this.count = count;
		}
	}
	static void counting(int map[][], int count[][]) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				boolean isOk = false;
				for(int k=0; k<4; k++) {
					int tx = i+dx[k];
					int ty = j+dy[k];
					if(tx<0||ty<0||tx>=N||ty>=N) {
						isOk= true;
						break;
					}
				}
				if(isOk) {
					count[i][j]=cant;
				}else {
					count[i][j]+=map[i][j];
					for(int k=0; k<4; k++) {
						int tx = i+dx[k];
						int ty = j+dy[k];
						count[i][j]+=map[tx][ty];
					}
					list.add(new flower(i, j, count[i][j]));
				}
			}
		}
	}
	
	static void check(int listIdx, int arrIdx, flower get[]) {
		if(arrIdx==3) {	
			int value =0;
			for(int i=0; i<get.length; i++) {
				value+=get[i].count;
			}
			minValue = Math.min(value, minValue);
			return;
		}
		if(listIdx==list.size()) {
			return;
		}
		flower f = list.get(listIdx);
		boolean isOk = false;
		for(int i=0; i<5;i++) {
			int tx = f.x+dx[i];
			int ty = f.y+dy[i];
			if(checkmap[tx][ty]) {
				isOk=true;
				break;
			}
		}
		if(isOk) {
			check(listIdx+1, arrIdx, get);			
		}else {
			for(int i=0; i<5;i++) {
				int tx = f.x+dx[i];
				int ty = f.y+dy[i];
				checkmap[tx][ty]=true;
			}
			get[arrIdx]=list.get(listIdx);
			check(listIdx+1, arrIdx+1, get);
			for(int i=0; i<5;i++) {
				int tx = f.x+dx[i];
				int ty = f.y+dy[i];
				checkmap[tx][ty]=false;
			}
			check(listIdx+1, arrIdx, get);	
		}
	}
	
}

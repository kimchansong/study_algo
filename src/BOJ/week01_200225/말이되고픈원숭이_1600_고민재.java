import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ¸»ÀÌµÇ°íÇÂ¿ø¼þÀÌ_1600_°í¹ÎÀç {
	public static int ylength;
	public static int xlength;
	public static int[][] map;
	public static int[][][] tmap;
	public static boolean[][][] check;
	public static Queue<int[]> q;
	public static int[] hy= {-1,-2,-2,-1,1,2,2,1};
	public static int[] hx= {-2,-1,1,2,2,1,-1,-2};
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	public static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		String[] s=br.readLine().split(" ");
		xlength=Integer.parseInt(s[0]);
		ylength=Integer.parseInt(s[1]);
		map=new int[ylength][xlength];
		tmap=new int[K+1][ylength][xlength];
		check=new boolean[K+1][ylength][xlength];
		for(int i=0;i<ylength;i++) {
			s=br.readLine().split(" ");
			for(int j=0;j<xlength;j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
		
		answer=Integer.MAX_VALUE;

		q=new LinkedList<>();
		q.add(new int[] {0,0,0,K});
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			if(tmp[0]==ylength-1 && tmp[1]==xlength-1) {
				if(answer>tmp[2]) {
					answer=tmp[2];
				}
				continue;
			}
			if(tmp[3]>0) {
				for(int h=0;h<8;h++) {
					int yy=tmp[0]+hy[h];
					int xx=tmp[1]+hx[h];
					if(yy<ylength && yy>=0 && xx<xlength && xx>=0 && map[yy][xx]==0 && (check[tmp[3]-1][yy][xx]==false || (tmap[tmp[3]][yy][xx]>0 &&tmap[tmp[3]][yy][xx]>tmp[2]+1))) {
						check[tmp[3]-1][yy][xx]=true;
						tmap[tmp[3]-1][yy][xx]=tmp[2]+1;
						q.add(new int[] {yy,xx,tmp[2]+1,tmp[3]-1});
					}
				}
			}
			for(int d=0;d<4;d++) {
				int yy=tmp[0]+dy[d];
				int xx=tmp[1]+dx[d];
				if(yy<ylength && yy>=0 && xx<xlength && xx>=0 && map[yy][xx]==0 && (check[tmp[3]][yy][xx]==false || (tmap[tmp[3]][yy][xx]>0 &&tmap[tmp[3]][yy][xx]>tmp[2]+1))) {
					check[tmp[3]][yy][xx]=true;
					tmap[tmp[3]][yy][xx]=tmp[2]+1;
					q.add(new int[] {yy,xx,tmp[2]+1,tmp[3]});
				}
			}
			
		}
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
		
	}
}

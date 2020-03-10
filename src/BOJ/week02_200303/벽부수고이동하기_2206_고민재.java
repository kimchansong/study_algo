import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 벽부수고이동하기_2206_고민재 {
	public static int ylength;
	public static int xlength;
	public static int[][] map;
	public static boolean[][][] check;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		ylength=Integer.parseInt(s[0]);
		xlength=Integer.parseInt(s[1]);
		
		map=new int[ylength][xlength];
		check=new boolean[ylength][xlength][2];
		for(int i=0;i<ylength;i++) {
			s=br.readLine().split("");
			for(int j=0;j<xlength;j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
//		for(int[] ia:map) {
//			System.out.println(Arrays.toString(ia));
//		}
		int answer=-1;
		check[0][0][0]=true;
		Queue<int[]> q=new LinkedList<>();
		q.add(new int[] {0,0,1,0});
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			if(tmp[0]==ylength-1 && tmp[1]==xlength-1) {
				answer=tmp[3]+1;
				break;
			}
			for(int d=0;d<4;d++) {
				int y=tmp[0]+dy[d];
				int x=tmp[1]+dx[d];
				if(y>=0 && y<ylength && x>=0 && x<xlength && !check[y][x][tmp[2]]) {
					if(map[y][x]==1) {
						if(tmp[2]>0) {
							check[y][x][tmp[2]-1]=true;
							q.add(new int[] {y,x,tmp[2]-1,tmp[3]+1});
						}
					}else {
						check[y][x][tmp[2]]=true;
						q.add(new int[] {y,x,tmp[2],tmp[3]+1});
					}
						
				}
			}
		}
		System.out.println(answer);
		
	}
}

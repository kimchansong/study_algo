import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 아기상어 {
	public static int n;
	public static int[][] map;
	public static Queue<int[]> q;
	public static boolean[][] check;
	public static int[] dy= {-1,0,0,1}; //상 좌 우하
	public static int[] dx= {0,-1,1,0};
	public static int answer;
	public static int next_y;
	public static int next_x;
	public static int next_time;
	public static int next_size;
	public static int next_eat_cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		q=new LinkedList<>();
		answer=0;
		check=new boolean[n][n];
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(s[j]);
				if(map[i][j]==9) {
					q.add(new int[] {i,j,0,2,0}); // y, x , 시간, 크기, 잡아먹은 물고기 수
					check[i][j]=true;
					map[i][j]=0;
				}
			}
		}
		
		start();
		System.out.println(answer);
	}
	private static void start() {
//		for(int[] ia:map) {
//			System.out.println(Arrays.toString(ia));
//			
//		}
//		System.out.println(answer);
//		System.out.println();
		int time=1;
		boolean find = false;
		while(!q.isEmpty()) {
			int qsize=q.size();
			for(int i=0;i<qsize;i++) {
				int[] tmp=q.poll();
				for(int d=0;d<4;d++) {
					int y=tmp[0]+dy[d];
					int x=tmp[1]+dx[d];
					if(y>=0 && y<n && x>=0 && x<n && !check[y][x] && map[y][x]<=tmp[3]) { //이동
						if(map[y][x]!=0 && map[y][x]<tmp[3]) { //물고기발견
							if(!find) {
								
								next_y=y;
								next_x=x;
								next_time=tmp[2]+time;
								answer=next_time;
								next_eat_cnt=tmp[4]+1;
								next_size=tmp[3];
								if(next_eat_cnt==next_size)
								{
									next_size++;
									next_eat_cnt=0;
								}
								find=true;
							}else {
								if(next_y>y) {
									next_y=y;
									next_x=x;
								}else if(next_y==y) {
									if(next_x>x) {
										next_x=x;
									}
								}
							}
							check[y][x]=true;
						}else {
							check[y][x]=true;
							q.add(new int[] {y,x,tmp[2],tmp[3],tmp[4]});
						}
					}
				}
			}
			if(find) {
				q.clear();
				check=new boolean[n][n];
				check[next_y][next_x]=true;
				map[next_y][next_x]=0;
				q.add(new int[] {next_y,next_x,next_time,next_size,next_eat_cnt});
				start();
				return;
			}
			time++;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ≤…±Ê_14620_∞ÌπŒ¿Á {
	public static int[][] map;
	public static boolean[][] check;
	public static int T;
	public static int N;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	public static int answer;
	public static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		answer=Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			String[] s=br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
		T=N*N;
		check=new boolean[N][N];
		count=0;
		solve(0,0,0);
		System.out.println(answer);
	}
	private static void solve(int begin,int cnt,int money) {
		if(answer<money) {
			return;
		}
		if(cnt==3) {
			answer=money;
			return;
		}
		
		loop:for(int i=begin+1;i<T;i++) {
			int y=i/N;
			int x=i%N;
			if(y==0 || y==N-1 || x==0 || x==N-1 || check[y][x] || map[y][x]>answer) continue;
			for(int d=0;d<4;d++) {
				if(check[y+dy[d]][x+dx[d]]) continue loop;
			}
			int nextmoney=money+map[y][x];
			check[y][x]=true;
			for(int d=0;d<4;d++) {
				int yy=y+dy[d];
				int xx=x+dx[d];
				check[yy][xx]=true;
				nextmoney+=map[yy][xx];
			}
			solve(i,cnt+1,nextmoney);
			check_return(y,x);
		}
	}
	private static void check_return(int y, int x) {
		check[y][x]=false;
		for(int d=0;d<4;d++) {
			check[y+dy[d]][x+dx[d]]=false;
		}

	}
}

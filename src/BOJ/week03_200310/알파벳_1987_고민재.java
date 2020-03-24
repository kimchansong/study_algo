import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class ¾ËÆÄºª {
	public static int R;
	public static int C;
	public static char[][] map;
	public static boolean[] charcheck;
	public static Stack<Character> st;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	public static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		R=Integer.parseInt(s[0]);
		C=Integer.parseInt(s[1]);
		map=new char[R][C];
		charcheck=new boolean[26];
		for(int i=0;i<R;i++) {
			map[i]=br.readLine().toCharArray();
		}
		answer=0;
		st=new Stack<Character>();
		charcheck[map[0][0]-'A']=true;
		dfs(0,0,1);
		System.out.println(answer);
		
	}
	private static void dfs(int y, int x, int cnt) {
		if(answer<cnt) {
			answer=cnt;
		}
		for(int d=0;d<4;d++) {
			int yy=y+dy[d];
			int xx=x+dx[d];
			if(yy>=0 && yy<R && xx>=0 && xx<C && !charcheck[map[yy][xx]-'A']) {
				charcheck[map[yy][xx]-'A']=true;
				dfs(yy,xx,cnt+1);
				charcheck[map[yy][xx]-'A']=false;
			}
		}
	}
}

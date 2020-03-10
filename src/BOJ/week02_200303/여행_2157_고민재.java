import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ø©«‡_2157_∞ÌπŒ¿Á {
	public static int[][] max;
	public static int[][] answer;
	public static int n;
	public static int m;
	public static int k;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		n=Integer.parseInt(s[0]);
		m=Integer.parseInt(s[1]);
		k=Integer.parseInt(s[2]);
		max=new int[n][n];
		answer=new int[n][m];
		
//		lists=new list[n];
//		for(int i=0;i<n;i++) {
//			lists[i]=new list();
//		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				answer[i][j]=-1;
			}
		}
		
		for(int i=0;i<k;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0])-1;
			int b=Integer.parseInt(s[1])-1;
			int c=Integer.parseInt(s[2]);
			if(a>b) continue;
			if(max[a][b]<c) {
				max[a][b]=c;
			}
		}
//		for(int[] ia:max) {
//			System.out.println(Arrays.toString(ia));
//		}
		answer[0][0]=0;
		for(int i=0;i<n;i++) {
			for(int j=1;j<n;j++) {
				if(max[i][j]==0) continue;
				for(int k=1;k<m;k++) {
					if(answer[i][k-1]==-1) continue;
					if(answer[j][k]<answer[i][k-1]+max[i][j]) {
						answer[j][k]=answer[i][k-1]+max[i][j];
					}
				}
			}
		}
//		for (int[] ia : answer) {
//			System.out.println(Arrays.toString(ia));
//		}
//		System.out.println();
		int ans=0;
		for(int i=0;i<m;i++) {
			if(ans<answer[n-1][i]) {
				ans=answer[n-1][i];
			}
		}
		System.out.println(ans);
			
	}
}

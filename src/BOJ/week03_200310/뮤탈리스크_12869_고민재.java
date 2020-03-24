import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ¹ÂÅ»¸®½ºÅ© {
	public static int[][][] dp;
	public static boolean[][][] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp=new int[61][61][61];
		check=new boolean[61][61][61];
		int n=Integer.parseInt(br.readLine());
		String[] s=br.readLine().split(" ");
		int[] scv=new int[3];
		for(int i=0;i<n;i++) {
			scv[i]=Integer.parseInt(s[i]);
		}
		int answer=solve(scv[0],scv[1],scv[2]);
		System.out.println(answer);
	}
	private static int solve(int i, int j, int k) {
		if(i<0) i=0;
		if(j<0) j=0;
		if(k<0) k=0;
		
		if(i==0 && j==0 && k==0) {
			return 0;
		}
		
		if(check[i][j][k]) return dp[i][j][k];
		dp[i][j][k]=Integer.MAX_VALUE;
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-9,j-3,k-1)+1);
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-9,j-1,k-3)+1);
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-3,j-9,k-1)+1);
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-3,j-1,k-9)+1);
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-1,j-9,k-3)+1);
		dp[i][j][k]=Math.min(dp[i][j][k], solve(i-1,j-3,k-9)+1);
		check[i][j][k]=true;
		return dp[i][j][k];
	}
	

	
}

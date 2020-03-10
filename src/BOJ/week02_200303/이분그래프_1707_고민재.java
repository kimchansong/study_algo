import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 이분그래프_1707_고민재 {
	public static int[] color;
	public static ArrayList<Integer>[] lists;
	public static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		for(int tc=0;tc<n;tc++) {
			String[] s=br.readLine().split(" ");
			
			int V=Integer.parseInt(s[0]);
			int E=Integer.parseInt(s[1]);
			
			lists=new ArrayList[V];
			
			for(int i=0;i<V;i++) {
				lists[i]=new ArrayList<>();
			}
			
			color=new int[V];
			
			for(int i=0;i<E;i++) {
				s=br.readLine().split(" ");
				int a=Integer.parseInt(s[0])-1;
				int b=Integer.parseInt(s[1])-1;
				lists[a].add(b);
				lists[b].add(a);
			}
			flag=true;
			for(int i=0;i<V;i++) {
				if(!flag) {
					break;
				}
				if(color[i]==0) {
					bfs(i);
				}
			}
			
			if(flag) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
	private static void bfs(int n) {
		Queue<Integer> q=new LinkedList<>();
		q.add(n);
		color[n]=1;
		while(!q.isEmpty()) {
			int qsize=q.size();
			for(int t=0;t<qsize;t++) {
				int tmp=q.poll();
				for(int i=0;i<lists[tmp].size();i++) {
					if(color[lists[tmp].get(i)]==color[tmp]) {
						flag=false;
						return;
					}else if(color[lists[tmp].get(i)]==0){
						if(color[tmp]==1) {
							color[lists[tmp].get(i)]=2;
							q.add(lists[tmp].get(i));
						}else if(color[tmp]==2){
							color[lists[tmp].get(i)]=1;
							q.add(lists[tmp].get(i));
						}
					}
				}
			}
		}
	}
}

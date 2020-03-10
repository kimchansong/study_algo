import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 구슬찾기_2617_고민재 {
	public static ArrayList<Integer>[] heavy;
	public static ArrayList<Integer>[] light;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		int N=Integer.parseInt(s[0]);
		int M=Integer.parseInt(s[1]);
		
		heavy=new ArrayList[N+1]; //자신보다 무거운 애들 번호
		light=new ArrayList[N+1]; //자신보다 가벼운 애들 번호
		
		for(int i=0;i<N+1;i++) {
			heavy[i]=new ArrayList();
			light[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			s=br.readLine().split(" ");
			int l=Integer.parseInt(s[0]);
			int r=Integer.parseInt(s[1]);
			heavy_input(r,l); //r보다 무거운애 l 추가
			light_input(l,r); //l보다 가벼운 r 추가
		}
		int mid=N/2;
		int answer=0;
		for(int i=1;i<N+1;i++) {
//			System.out.println(heavy[i].size());
//			System.out.println(light[i].size());
			if(heavy[i].size()>mid || light[i].size()>mid) {
				answer++;
			}
		}
		System.out.println(answer);
		
	}
	private static void light_input(int l, int r) {
		if(light[l].contains(r)) return;
		for(int i=0;i<heavy[l].size();i++) {
			int index=heavy[l].get(i);
			if(light[index].contains(r)) continue;
			light[index].add(r);
			heavy_input(r,index);
		}
		light[l].add(r);
	}
	private static void heavy_input(int r, int l) {
		if(heavy[r].contains(l)) return; 
		for(int i=0;i<light[r].size();i++) {
			int index=light[r].get(i);
			if(heavy[index].contains(l)) continue;
			heavy[index].add(l);
			light_input(l,index);
		}
		heavy[r].add(l);
		
	}
}

package BOJ.week02_200303;

import java.util.ArrayList;
import java.util.Scanner;

public class 이분그래프_1707_김찬송 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int testcase = 0; testcase < tc; testcase++) {
			int V = sc.nextInt(); // 정점의 갯수
			int E = sc.nextInt(); // 간선의 갯수
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			int visited[] = new int[V + 1];
			for (int i = 0; i <= V; i++) {
				list.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < E; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				list.get(start).add(end);
				list.get(end).add(start);
			}
			for (int i = 1; i < visited.length; i++) {
				if (visited[i] == 0) {
					dfs(list, visited, i, 1);
				}
			}
			if (result(list, visited)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	static void dfs(ArrayList<ArrayList<Integer>> list, int visited[], int idx, int color) {

		visited[idx] = color;
		for (int i = 0; i < list.get(idx).size(); i++) {
			if (visited[list.get(idx).get(i)] == 0) {
				dfs(list, visited, list.get(idx).get(i), 3 - color);
			}
		}
	}

	static boolean result(ArrayList<ArrayList<Integer>> list, int visited[]) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				if (visited[i] == visited[list.get(i).get(j)])
					return false;
			}
		}
		return true;
	}
}

package woonjae.sosu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int value = sc.nextInt();

			int[] arr = new int[value + 1];

			// 소수를 정해보즈아
			for (int i = 2; i < arr.length; i++) {
				if (arr[i] != 0) {
					continue;
				}
				for (int j = i * 2; j < arr.length; j += i) {
					arr[j] = 1;
				}
			}
			List<Integer> list = new ArrayList<>();

			for (int i = 2; i < arr.length; i++) {
				if (arr[i] == 0)
					list.add(i);
			}

			int count = 0;
			int size = list.size();

			// 연속 합이므로
			for (int start = 0; start < size; start++) {
				int dd = 0;
				for (int i = start; i < size; i++) {
					int point = list.get(i);
					dd += point;
					if (dd > value)
						break;
					if (dd == value) {
						count++;
						break;
					}
				}
			}
			System.out.println(count);
		}

	}
}

public class APISort {

	public static void main(String[] args) {
		int[] arr = { 1, 8, 6, 4, 5, 3, 7, 2, 9 };
		arr = partition(arr);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	static int[] partition(int[] arr) {

		int[] sorted = new int[arr.length];
		int index = 0;
		int n = arr.length / 3;
		int i = 0;
		int j = n;
		int k = arr.length - n;

		int[] A = fill(arr, i, j);
		int[] B = fill(arr, j, k);
		int[] C = fill(arr, k, arr.length);
		double[] AvgArr = new double[6];

		AvgArr[0] = sort(A);
		AvgArr[2] = sort(B);
		AvgArr[4] = sort(C);

		AvgArr[1] = n;
		AvgArr[3] = n;
		AvgArr[5] = n;

		double avg1 = (AvgArr[0] / AvgArr[1]);
		double avg2 = (AvgArr[2] / AvgArr[3]);
		double avg3 = (AvgArr[4] / AvgArr[5]);

		int Aindex = 0;
		int Bindex = 0;
		int Cindex = 0;

		while (Aindex < 3 && Bindex < 3 && Cindex < 3) {

			if (Aindex == 3) {
				AvgArr[0] = Integer.MAX_VALUE;
				AvgArr[1] = Integer.MIN_VALUE;
			}

			if (Bindex == 3) {
				AvgArr[2] = Integer.MAX_VALUE;
				AvgArr[3] = Integer.MIN_VALUE;
			}

			if (Cindex == 3) {
				AvgArr[4] = Integer.MAX_VALUE;
				AvgArr[5] = Integer.MIN_VALUE;
			}

			avg1 = (AvgArr[0] / AvgArr[1]);
			avg2 = (AvgArr[2] / AvgArr[3]);
			avg3 = (AvgArr[4] / AvgArr[5]);


			int x = smallest(avg1, avg2, avg3);
			int y = secsmall(avg1, avg2, avg3);
			System.out.println(x + " " + y);

			if ((x == 0 && y == 2) || (x == 2 && y == 0)) {
				int a = A[Aindex];
				int b = B[Bindex];

				if (a < b) {
					sorted[index] = a;
					AvgArr[0] = UpdateAVG(AvgArr[0], AvgArr[1], a);
					AvgArr[1]--;
					index++;
					Aindex++;

				} else {
					sorted[index] = b;
					AvgArr[2] = UpdateAVG(AvgArr[2], AvgArr[3], b);
					AvgArr[3]--;
					index++;
					Bindex++;
				}
			}
			if ((x == 0 && y == 4) || (x == 4 && y == 0)) {
				int a = A[Aindex];
				int c = C[Cindex];

				if (a < c) {
					sorted[index] = a;
					AvgArr[0] = UpdateAVG(AvgArr[0], AvgArr[1], a);
					AvgArr[1]--;
					index++;
					Aindex++;
				} else {
					sorted[index] = c;
					AvgArr[4] = UpdateAVG(AvgArr[4], AvgArr[5], c);
					AvgArr[5]--;
					index++;
					Cindex++;
				}
			}
			if ((x == 2 && y == 4) || (x == 4 && y == 2)) {
				int b = B[Bindex];
				int c = C[Cindex];

				if (b < c) {
					sorted[index] = b;
					AvgArr[2] = UpdateAVG(AvgArr[2], AvgArr[3], b);
					AvgArr[3]--;
					index++;
					Bindex++;
				} else {
					sorted[index] = c;
					AvgArr[4] = UpdateAVG(AvgArr[4], AvgArr[5], c);
					AvgArr[5]--;
					index++;
					Cindex++;
				}
			}
			System.out.println(sorted[index - 1]);
		}

		return sorted;
	}

	public static int smallest(double avg1, double avg2, double avg3) {
		double x = Math.min(Math.min(avg1, avg2), avg3);
		if (x == avg1) {
			x = 0;
		}

		if (x == avg2) {
			x = 2;
		}

		if (x == avg3) {
			x = 4;
		}
		return (int) x;
	}

	public static int secsmall(double avg1, double avg2, double avg3) {
		double x = Math.min(Math.min(avg1, avg2), avg3);
		double y = 0;
		if (x == avg1) {
			y = smallest(Integer.MAX_VALUE, avg2, avg3);
		}

		if (x == avg2) {
			y = smallest(avg1, Integer.MAX_VALUE, avg3);
		}

		if (x == avg3) {
			y = smallest(avg1, avg2, Integer.MAX_VALUE);
		}
		return (int) y;
	}

	static int[] fill(int arr[], int s, int f) {
		int[] temp = new int[f - s];

		int j = 0;
		for (int i = s; i < f; i++) {
			temp[j] = arr[i];
			j++;
		}

		return temp;
	}

	static double UpdateAVG(double avgArr, double avgArr2, int remove) {

		double OldAvg = avgArr / avgArr2;

		double NewAvg = ((avgArr - remove) / avgArr2 - 1) - ((avgArr - remove) / avgArr2) + OldAvg - remove / avgArr2;

		return NewAvg;
	}

	static double sort(int arr[]) {
		double sum = arr[0];
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			sum += arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
		return sum;
	}

}

import java.util.*;
class Main8{
	public static void main(String[] args){
		int a[]={5,3,8,6,7,2};
		int n = a.length;
		int count=0;
        for (int i = 0; i < n; i++) {
            int min_idx = i;
			for (int j = i; j < n; j++) {
                if (a[j] < a[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = a[i];
            a[i] = a[min_idx];
            a[min_idx] = temp;
			count++;
        }
		System.out.println(count);
		for(int i=0;i<n;i++){
			System.out.println(a[i]+" ");
		}
    }
}
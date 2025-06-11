import java.util.*;
class Main7{
	public static void main(String[] args){
		int a[]={5,3,8,6,7,2};
		int b=a.length;
		int i,j,temp=0;
		for(i=0;i<b;i++){
			for(j=1;j<(b-i);j++){
				if(a[j-1]>a[j]){
					temp=a[j-1];
					a[j-1]=a[j];
					a[j]=temp;
				}
			}
		}
		for(i=0;i<b;i++){
			System.out.print(a[i]+" ");
		}
	}
}
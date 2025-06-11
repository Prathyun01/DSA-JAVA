import java.util.*;
class Main6 {
    public static void main(String[] args) {      
		int b []={-11,4,6,9,33,45};
		int start,last,mid;
		start=0;
		last=b.length-1;
		int a=45;
		int pos=-1;
		while(start<=last){			
		mid=(start+last)/2;
		System.out.println(mid);
		if(b[mid]==a){
			pos=mid;
			//System.out.println(pos);
			break;
		}
		else if(b[mid]<a)
		{
			start=mid+1;
			System.out.println(start);
		}
		else if(b[mid]>a)
		{
			last=mid-1;
			//System.out.println(last);
		}
    }
	if(pos!=-1){
		System.out.println("found:"+(pos+1));
	}
	else{
		System.out.println("not found");
	}
}
}
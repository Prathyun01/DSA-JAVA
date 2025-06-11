import java.util.*;
class Phone{
	long num;
	String name;
	Phone(long num,String name){
		this.num=num;
		this.name=name;
	}
	void display() {
	System.out.println("num: " + num + ", Name: " + name);
	}
}
public class PhoneBook{
		public static void main(String [] args){
			Scanner input =new Scanner(System.in);
			Phone[] phones=new Phone[100];
			int count=0;
			while(true){
				System.out.println("\n1.Add number");
				System.out.println("2.Display numbers");
				System.out.println("3.Exit");
				System.out.println("choose any option");
				int choice=input.nextInt();
				input.nextLine();
				if(choice==1){
					System.out.print("Enter num: ");
					long num = input.nextLong();
					input.nextLine();
					System.out.print("Enter Name: ");
					String name = input.nextLine();
					phones[count++] = new Phone(num, name);
					System.out.println("Number added!");
				}
				else if(choice==2){
					System.out.println("\nList of numbers:");
					for (int i = 0; i < count; i++) {
						phones[i].display();
					}
				} else if(choice==3){
					System.out.println("THANKYOU");
					break;
				}
				else{
					System.out.println("Invalid choice");
				}
			}
			input.close();
	}
}
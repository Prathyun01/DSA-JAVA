import java.util.Scanner;
class Student {
    int id;
    String name;
    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    void display() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
public class StudentManagement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Student[] students = new Student[100];
        int count = 0;
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine(); 
            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                students[count++] = new Student(id, name);
                System.out.println("Student added!");
            } else if (choice == 2) {
                System.out.println("\nList of Students:");
                for (int i = 0; i < count; i++) {
                    students[i].display();
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        input.close();
    }
}

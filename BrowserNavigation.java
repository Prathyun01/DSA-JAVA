import java.util.Scanner;
import java.util.Stack;
public class BrowserNavigation {
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = "home";
    public void visit(String page) {
        if (currentPage != null) {
            backStack.push(currentPage);
        }
        currentPage = page;
        forwardStack.clear();
        System.out.println("Visited: " + currentPage);
    }
    public void back() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentPage);
            currentPage = backStack.pop();
            System.out.println("Back to: " + currentPage);
        } else {
            System.out.println("No page to go back to.");
        }
    }
    public void forward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentPage);
            currentPage = forwardStack.pop();
            System.out.println("Forward to: " + currentPage);
        } else {
            System.out.println("No page to go forward to.");
        }
    }
    public void display() {
        System.out.println("Current Page: " + currentPage);
        System.out.println("Back Stack: " + backStack);
        System.out.println("Forward Stack: " + forwardStack);
    }
    public static void main(String[] args) {
        BrowserNavigation browser = new BrowserNavigation();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Visit New Page");
            System.out.println("2. Back");
            System.out.println("3. Forward");
            System.out.println("4. Display Current Page");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter page URL: ");
                    String url = sc.nextLine();
                    browser.visit(url);
                    break;
                case 2:
                    browser.back();
                    break;
                case 3:
                    browser.forward();
                    break;
                case 4:
                    browser.display();
                    break;
                case 5:
                    System.out.println("Exiting browser.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
        sc.close();
    }
}
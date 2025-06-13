import java.util.*;
class Food {
    int id;
    String name;
    double price;

    Food(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
class CustomFoodRequest {
    String customerName;
    String foodName;
    boolean approved = false;
    double price;
    CustomFoodRequest(String customerName, String foodName) {
        this.customerName = customerName;
        this.foodName = foodName;
    }
}
class PaymentRecord {
    int orderId;
    String customerName;
    double amount;
    String method;

    PaymentRecord(int orderId, String customerName, double amount, String method) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.method = method;
    }
}

class Order {
    int orderId;
    String customerName;
    ArrayList<Food> items = new ArrayList<>();
    double total = 0;

    Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    void addItem(Food food) {
        items.add(food);
        total += food.price;
    }
}

class Booking {
    String name, contact;
    int tableNo, members;

    Booking(String name, String contact, int tableNo, int members) {
        this.name = name;
        this.contact = contact;
        this.tableNo = tableNo;
        this.members = members;
    }
}

public class RestaurantSystem {
    ArrayList<Food> menu = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Queue<Order> orderQueue = new LinkedList<>();
    Stack<Order> orderHistory = new Stack<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    ArrayList<PaymentRecord> paymentHistory = new ArrayList<>();
    ArrayList<CustomFoodRequest> foodRequests = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int orderCounter = 1;

    public RestaurantSystem() {
        menu.add(new Food(1, "Biriyani", 150.0));
        menu.add(new Food(2, "Chicken Lollipop", 300.0));
        menu.add(new Food(3, "Pasta", 100.0));
        menu.add(new Food(4, "Egg Rice", 120.0));
        menu.add(new Food(5, "Curd Rice", 60.0));
    }

    Food findFood(int id) {
        for (Food food : menu) {
            if (food.id == id) return food;
        }
        return null;
    }
	void requestCustomFood() {
        sc.nextLine();
        System.out.print("Enter your name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        CustomFoodRequest newRequest = new CustomFoodRequest(customerName, foodName);
        foodRequests.add(newRequest);
        System.out.println("Your food request for '" + foodName + "' has been submitted. Waiting for admin approval.");
    }
	void searchFood() {
        System.out.print("Enter food ID to search: ");
        int id = sc.nextInt();
        Food food = findFood(id);
        if (food != null) {
            System.out.println("Found: " + food.id + ". " + food.name + " " + food.price);
        } else {
            System.out.println("Food item not found!");
        }
    }
	void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet!");
            return;
        }
        for (Order order : orders) {
            System.out.printf("Order #%d - %s - Total: %.2f%n", order.orderId, order.customerName, order.total);
            for (Food food : order.items)
                System.out.printf("  - %d. %s - %.2f%n", food.id, food.name, food.price);
        }
    }
	void addMenuItem() {
        System.out.print("Enter food ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter food name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        menu.add(new Food(id, name, price));
        System.out.println("Menu item added successfully!");
    }

    void processOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process!");
            return;
        }
        Order order = orderQueue.poll();
        orderHistory.push(order);
        System.out.println("Processed: Order #" + order.orderId + " - " + order.customerName + " - Total: $" + order.total);
    }

    void Payment(Order order) {
        System.out.println("Order #" + order.orderId);
        for (Food item : order.items)
            System.out.println(" - " + item.name + ": Rs " + item.price);
        
        System.out.println("Total: Rs " + order.total);
        System.out.println("Payment Method:\n1. Cash\n2. Card\n3. UPI\n4. Cancel");       
        int choice = sc.nextInt();
        sc.nextLine(); 
        String method = "";
		switch (choice) {
			case 1 -> method = "Cash";
			case 2 -> method = "Card";
			case 3 -> method = "UPI";
			case 4 -> {
				System.out.println("Transaction canceled.");
				return;
			}
			default -> {
				System.out.println("Invalid choice!");
				return;
			}
		}
        paymentHistory.add(new PaymentRecord(order.orderId, order.customerName, order.total, method));
        System.out.println("Total Payment of Rs " + order.total + " done via " + method);
        System.out.print("Enter payment amount: ");
        double amountPaid = sc.nextDouble();
        if (amountPaid >= order.total) {
            System.out.println("Thank you! You may now exit.");
        } else {
            System.out.println("Insufficient payment! Please pay the full amount.");
        }
    }

    void viewPaymentHistory() {
        if (paymentHistory.isEmpty()) {
            System.out.println("No payments recorded!");
            return;
        }

        System.out.println("\n PAYMENT HISTORY ");
        for (PaymentRecord record : paymentHistory)
            System.out.printf("Order #%d - %s - Rs %.2f - %s%n",
                              record.orderId, record.customerName, record.amount, record.method);
    }

    void showMenu() {
        System.out.println("\n MENU ");
        for (Food food : menu)
            System.out.println(food.id + " " + food.name + " " + food.price);
    }

    void bookTable() {
        sc.nextLine();
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Table Number: ");
        int tableNo = sc.nextInt();
        System.out.print("Number of Members: ");
        int members = sc.nextInt();
        bookings.add(new Booking(name, contact, tableNo, members));
        System.out.println("Table booked successfully.");
    }

    void placeOrder() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        Order order = new Order(orderCounter++, name);
        while (true) {
            showMenu();
            System.out.print("Enter food ID (0 to finish): ");
            String foodId = sc.nextLine(); 
			if (foodId.toLowerCase().equals("finish") || foodId.equals("0")) break;
			int id = Integer.parseInt(foodId); 
			Food food = findFood(id); 
			if (food != null) { 
				order.addItem(food); 
				System.out.println("Added: " + food.name); 
			} else { 
				System.out.println("Food not found!"); 
			}
		} 
        if (!order.items.isEmpty()) {
            orders.add(order);
            orderQueue.add(order);
            System.out.println("Order placed successfully!");
            System.out.println("Order #" + order.orderId + " - " + order.customerName + " - Total: " + order.total);
        } else {
            System.out.println("No items added to order!");
        }
    }
	
    void customerPanel() {
        while (true) {
            System.out.println("\n CUSTOMER PANEL ");
            System.out.println("1. View Menu\n2. Book Table\n3. Place Order\n4. View Cart\n5. Customer Request\n6. Payment\n7. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            if (ch == 1) showMenu();
            else if (ch == 2) bookTable();
            else if (ch == 3) placeOrder();
            else if (ch == 4) viewAllOrders();
			else if (ch == 5) requestCustomFood();
            else if (ch == 6) {
                if (!orders.isEmpty()) {
                    Payment(orders.get(orders.size() - 1));
                } else {
                    System.out.println("No orders placed yet!");
                }
            } else if (ch == 7) break;
            else System.out.println("Invalid choice!");
        }
    }

    void adminapproval() {
		if (foodRequests.isEmpty()) {
            System.out.println("No custom food requests.");
            return;
        }
        System.out.println("\nPending Custom Food Requests:");
        for (CustomFoodRequest request : foodRequests) {
            if (!request.approved) {
                System.out.println("Request by " + request.customerName + ": " + request.foodName);
                System.out.print("Approve this item? (yes/no): ");
                String response = sc.next().toLowerCase();
                if (response.equals("yes")) {
                    System.out.print("Set price for " + request.foodName + ": ");
                    request.price = sc.nextDouble();
                    request.approved = true;
                    menu.add(new Food(menu.size() + 1, request.foodName, request.price));
                    System.out.println(request.foodName + " approved and added to the menu at Rs " + request.price);
                } else {
                    System.out.println("Request rejected.");
                }
            }
        }
	}
	void adminPanel(){
        while (true) {
            System.out.println("\n ADMIN PANEL ");
            System.out.println("1. View Orders\n2. Process Order\n3. Add Menu Item\n4. Food Items\n5. Search Food\n6. View Payment History\n7. Back");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1 -> viewAllOrders();
                case 2 -> processOrder();
                case 3 -> addMenuItem();
                case 4 -> showMenu();
                case 5 -> searchFood();
                case 6 -> viewPaymentHistory();
                case 7 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    public static void main(String[] args) {
        RestaurantSystem app = new RestaurantSystem();
        while (true) {
            System.out.println("\n RESTAURANT SYSTEM ");
            System.out.println("1. Customer\n2. Admin\n3. Adminapproval\n4. Exit");
            System.out.print("Choose: ");
            int ch = app.sc.nextInt();
            if (ch == 1) app.customerPanel();
            else if (ch == 2) { 
                System.out.println("Enter passkey:");
                int a = app.sc.nextInt();
                if (a == 10) app.adminPanel();
            } else if (ch == 3) {
				System.out.println("Enter passkey:");
                int b = app.sc.nextInt();
                if (b == 10) app.adminapproval();
			} else if (ch == 4) {
                System.out.println("Goodbye!");
                break;
            } else System.out.println("Invalid choice!");
        }
    }
}
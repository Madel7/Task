import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int showMainMenu() {
        System.out.println("\n----- MAIN MENU -----");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
        }
        return choice;
    }

    public void showProducts(List<Product> products) {
        System.out.println("\n----- PRODUCTS -----");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.getName() + " - " + p.getPrice() + " EGP");
        }
    }

    public String askProductId() {
        System.out.print("Enter product ID to add: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public int askQuantity() {
        System.out.print("Enter quantity: ");
        return scanner.nextInt();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showCart(List<Cart> cartItems, double total) {
        System.out.println("\n----- YOUR CART -----");
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            for (Cart c : cartItems) {
                System.out.println("  - " + c.getProduct().getName() + " x" + c.getQuantity() + " = " + c.getTotal() + " EGP");
            }
            System.out.println("---------------------");
            System.out.println("Subtotal: " + total + " EGP");
        }
    }

    public int askDiscount() {
        System.out.println("\n----- DISCOUNT -----");
        System.out.println("1. SAVE10 (10% off)");
        System.out.println("2. FLAT50 ($50 off)");
        System.out.println("3. No discount");
        System.out.print("Select discount: ");
        return scanner.nextInt();
    }

    public int askPaymentMethod() {
        System.out.println("\n----- PAYMENT -----");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.print("Select payment: ");
        return scanner.nextInt();
    }

    public String askCreditCard() {
        scanner.nextLine();
        System.out.print("Enter card number: ");
        String card = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        return card + " (CVV: " + cvv + ")";
    }

    public String askPaypalEmail() {
        scanner.nextLine();
        System.out.print("Enter PayPal email: ");
        return scanner.nextLine();
    }

    public void showOrderSummary(String orderId, List<Cart> cartItems, String discount, String paymentMethod, double total) {
        System.out.println("\n===== ORDER CONFIRMED =====");
        System.out.println("Order ID: " + orderId);
        System.out.println("Items:");
        for (Cart c : cartItems) {
            System.out.println("  - " + c.getProduct().getName() + " x" + c.getQuantity() + " = " + c.getTotal() + " EGP");
        }
        System.out.println("Discount: " + discount);
        System.out.println("Payment: " + paymentMethod);
        System.out.println("Total: " + total + " EGP");
        System.out.println("Status: Confirmed");
        System.out.println("===========================");
    }
}

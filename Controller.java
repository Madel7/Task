import java.util.List;
import java.util.UUID;

public class Controller {
    private Service service;
    private View view;

    public Controller(Service service, View view) {
        this.service = service;
        this.view = view;
    }

    public void start() {
        while (true) {
            int choice = view.showMainMenu();

            if (choice == 1) {
                view.showProducts(service.getAllProducts());
            } else if (choice == 2) {
                view.showProducts(service.getAllProducts());
                String productId = view.askProductId();
                int quantity = view.askQuantity();
                service.addToCart(productId, quantity);
                view.showMessage("Added " + quantity + " items to cart!");
            } else if (choice == 3) {
                List<Cart> cartItems = service.getCart();
                double total = 0;
                for (Cart c : cartItems) {
                    total += c.getTotal();
                }
                view.showCart(cartItems, total);
            } else if (choice == 4) {
                checkoutFlow();
            } else if (choice == 5) {
                view.showMessage("Thank you for shopping! Goodbye!");
                break;
            } else {
                view.showMessage("Invalid choice!");
            }
        }
    }

    private void checkoutFlow() {
        List<Cart> cartItems = service.getCart();
        if (cartItems.isEmpty()) {
            view.showMessage("Cart is empty!");
            return;
        }

        double subtotal = 0;
        for (Cart c : cartItems) {
            System.out.println("  - " + c.getProduct().getName() + " x" + c.getQuantity() + " = " + c.getTotal() + " EGP");
            subtotal += c.getTotal();
        }
        System.out.println("---------------------");
        System.out.println("Subtotal: " + subtotal + " EGP");

        int discountChoice = view.askDiscount();
        Discount discount;
        String discountApplied = "None";

        if (discountChoice == 1) {
            discount = new TenPercentDiscount();
            discountApplied = "SAVE10 (10% off)";
            System.out.println("Applied: 10% discount");
        } else if (discountChoice == 2) {
            discount = new FlatFiftyDiscount();
            discountApplied = "FLAT50 ($50 off)";
            System.out.println("Applied: $50 discount");
        } else {
            discount = new NoDiscount();
            System.out.println("No discount applied");
        }

        double finalTotal = discount.addDiscount(subtotal);
        System.out.println("Final Total: " + finalTotal + " EGP");

        int paymentChoice = view.askPaymentMethod();
        String paymentMethod = "";

        if (paymentChoice == 1) {
            paymentMethod = "Credit Card: " + view.askCreditCard();
        } else if (paymentChoice == 2) {
            paymentMethod = "PayPal: " + view.askPaypalEmail();
        } else {
            view.showMessage("Invalid payment method!");
            return;
        }

        String orderId = UUID.randomUUID().toString();
        view.showOrderSummary(orderId, cartItems, discountApplied, paymentMethod, finalTotal);

        service.clearCart();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
    }
}

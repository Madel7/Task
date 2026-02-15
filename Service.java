import java.util.ArrayList;
import java.util.List;

public class Service {

    private Repo repo;
    private List<Cart> cart = new ArrayList<>();

    public Service(Repo repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void addToCart(String productId, int quantity) {

        Product product = repo.findById(productId);

        if (product == null) {
            return;
        }
        for (Cart item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.increaseQuantity(quantity);
                return;
            }
        }

        cart.add(new Cart(product, quantity));
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}

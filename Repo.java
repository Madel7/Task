import java.util.ArrayList;
import java.util.List;

public class Repo{

    private List<Product> products= new ArrayList<>();

    public Repo(){
        products.add(new Product("1", "Laptop", 20000));
        products.add(new Product("2", "Phone", 15000));
        products.add(new Product("3", "Headphones", 400));
        products.add(new Product("4", "Book", 200));
    }

    public List<Product> findAll(){
        return products;
    }

    public Product findById(String id){
        for (Product p : products){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
}

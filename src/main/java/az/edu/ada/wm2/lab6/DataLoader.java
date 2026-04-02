package az.edu.ada.wm2.lab6;

import az.edu.ada.wm2.lab6.model.Category;
import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.repository.CategoryRepository;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(ProductRepository productRepository,
                      CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0 || productRepository.count() > 0) {
            return;
        }

        Category food = Category.builder()
                .name("Food")
                .products(new LinkedList<>())
                .build();

        Category drinks = Category.builder()
                .name("Drinks")
                .products(new LinkedList<>())
                .build();

        categoryRepository.save(food);
        categoryRepository.save(drinks);

        Product milk = Product.builder()
                .productName("Milk")
                .price(new BigDecimal("2.50"))
                .expirationDate(LocalDate.now().plusDays(7))
                .categories(new LinkedList<>())
                .build();

        Product bread = Product.builder()
                .productName("Bread")
                .price(new BigDecimal("1.20"))
                .expirationDate(LocalDate.now().plusDays(3))
                .categories(new LinkedList<>())
                .build();

        milk.getCategories().add(food);
        milk.getCategories().add(drinks);

        bread.getCategories().add(food);

        productRepository.save(milk);
        productRepository.save(bread);
    }
}
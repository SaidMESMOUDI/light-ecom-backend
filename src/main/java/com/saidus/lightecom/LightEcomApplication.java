package com.saidus.lightecom;

import com.saidus.lightecom.dao.CategoryRepository;
import com.saidus.lightecom.dao.ProductRepository;
import com.saidus.lightecom.entities.Category;
import com.saidus.lightecom.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class LightEcomApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(LightEcomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

        categoryRepository.save(new Category(null, "Computers", null, null, null));
        categoryRepository.save(new Category(null, "Printers", null, null, null));
        categoryRepository.save(new Category(null, "Smartphones", null, null, null));

        Random rnd = new Random();
        categoryRepository.findAll().forEach(c -> {
            for (int i = 0; i < 10; i++) {
                Product p = new Product();
                p.setName(RandomString.make(12));
                p.setDescription("Description of product n°" + (i + 1) + ". Cat: " + c.getName());
                p.setCurrentPrice(100 + rnd.nextInt(1000));
                p.setAvailable(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setPhotoName("unknown.png");
                p.setCategory(c);

                productRepository.save(p);
            }

        });
    }
}

import entity.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class MainHibernate {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductRepo productRepo = new ProductRepo(emFactory);

//        EntityManager em = emFactory.createEntityManager();
//        // INSERT
//        em.getTransaction().begin();
//
//                List.of(
//                new Product(null, "cookie", 200),
//                new Product(null, "tea", 150),
//                new Product(null, "apples", 76),
//                new Product(null, "tuna", 120),
//                new Product(null, "orange", 55),
//        ).forEach(em::persist);
//        em.getTransaction().commit();
//        em.close();

        productRepo.findAll().forEach(product -> productRepo.delete(product.getId()));

        List.of(
                new Product(null, "cookie", 200),
                new Product(null, "tea", 150),
                new Product(null, "apples", 76),
                new Product(null, "tuna", 120),
                new Product(null, "orange", 55)
        ).forEach(productRepo::insert);

       Optional<Product> product = productRepo.findById(1L);
       System.out.println(product);

       productRepo.findAll().forEach(System.out::println);

    }
}


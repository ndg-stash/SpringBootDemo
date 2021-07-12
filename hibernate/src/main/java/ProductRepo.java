import entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepo {
    private final EntityManagerFactory emFactory;

    public ProductRepo(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select p from Product p", Product.class).getResultList()
        );
    }

    public Optional<Product> findById(long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    public void insert(Product product) {
        executeInTransaction(
                em -> em.persist(product)
        );
    }

    public void update(Product product) {
        executeInTransaction(
                em -> em.merge(product)
        );
    }

    public void delete(long id) {
        executeInTransaction(
                em -> em.createQuery("delete from Product where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    public void save(Product product) {
        if (product.getId() == null) {
            insert(product);
        } else {
            update(product);
        }
    }


    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}

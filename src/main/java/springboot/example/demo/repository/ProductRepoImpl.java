package springboot.example.demo.repository;

import org.springframework.stereotype.Repository;
import springboot.example.demo.entity.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepoImpl implements ProductRepo{

    private final AtomicLong id = new AtomicLong(0);
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        this.add(new Product("sfsfsdf", BigDecimal.valueOf(10)));
        this.add(new Product("dfddfda", BigDecimal.valueOf(14)));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void add(Product product) {
        long productId = id.incrementAndGet();
        product.setId(productId);
        productMap.put(productId,product);
    }
}

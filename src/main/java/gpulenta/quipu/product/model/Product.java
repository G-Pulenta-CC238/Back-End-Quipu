package gpulenta.quipu.product.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_description", nullable = false, length = 500)
    private String productDescription;

    @Column(name = "product_price", nullable = false, length = 10)
    private Double productPrice;

    @Column(name = "product_image_url", nullable = false, length = 250)
    private String productImageUrl;

    @Column(name = "product_rating", nullable = false, length = 5)
    private int productRating;

    @Column(name = "product_stock", nullable = false, length = 1000)
    private int productStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}

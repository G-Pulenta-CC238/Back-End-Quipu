package gpulenta.quipu.product.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @Column(name = "category_image_url", nullable = false, length = 250)
    private String categoryImageUrl;
}

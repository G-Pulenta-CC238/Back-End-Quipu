package gpulenta.quipu.offer.model;

import gpulenta.quipu.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offer_status", length = 50)
    private String offerStatus;

    @Column(name = "offer_price", length = 50)
    private Double offerPrice;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long userId;

    @Transient
    private User user;

    @Column(name = "shoppingCart_id", nullable = false, length = 50)
    private Long shoppingCartId;

}


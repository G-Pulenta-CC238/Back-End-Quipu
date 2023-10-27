package gpulenta.quipu.payment.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import gpulenta.quipu.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_number", nullable = false, length = 16)
    private String paymentNumber;

    @Column(name = "payment_expiration", nullable = false, length = 6)
    private String paymentExpiration;

    @Column(name = "payment_cvv", nullable = false, length = 4)
    private String paymentCvv;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long userId;

    @Transient
    private User user;
}

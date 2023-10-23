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
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", length = 50)
    private String message;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long userId;

    @Transient
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_offer", nullable = false)
    private Offer offer;
}

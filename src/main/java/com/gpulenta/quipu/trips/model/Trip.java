package com.gpulenta.quipu.trips.model;

import com.gpulenta.quipu.users.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "origin", nullable = false, length = 50)
    private String origin;

    @Column(name = "destination", nullable = false, length = 50)
    private String destination;

    @Column(name = "date", nullable = false, length = 50)
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}

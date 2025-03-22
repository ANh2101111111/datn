package datn.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phone;

    private String avatar;

    private String address;

    private String fullName;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

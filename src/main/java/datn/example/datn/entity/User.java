package datn.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private boolean status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatbotHistory> chatbotHistories;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @Column()
    private LocalDateTime createdAt;  // Giữ LocalDateTime
    @Column()
    private LocalDateTime updatedAt;  // Giữ LocalDateTime
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // Gán giá trị LocalDateTime
        this.updatedAt = LocalDateTime.now();  // Gán giá trị LocalDateTime
    }
}

package datn.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;
@Data
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    @Column(nullable = false, unique = true)
    private String permissionName;
    private String description;
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}

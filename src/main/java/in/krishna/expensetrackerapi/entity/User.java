package in.krishna.expensetrackerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
//    private Long age;

    private BigDecimal budget;

    @Column(name = "created_at", nullable = false, updatable = true)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, updatable = true)
    @UpdateTimestamp
    private Timestamp updatedAt;
}

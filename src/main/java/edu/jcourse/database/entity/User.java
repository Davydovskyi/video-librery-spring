package edu.jcourse.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FieldNameConstants
@Data
@NoArgsConstructor
@ToString(exclude = {"reviews"})
@EqualsAndHashCode(exclude = {"reviews"}, callSuper = false)
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity<Long> {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_birth_date")
    private LocalDate birthDate;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "user_image")
    private String userImage;

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Review> reviews = new ArrayList<>();
}
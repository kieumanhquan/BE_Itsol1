package com.itsol.recruit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ROLE_ID")
    @SequenceGenerator(name = "GEN_ROLE_ID", sequenceName = "SEQ_ROLE", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "code", nullable = false)
    String code;

    @Column(name = "description")
    String description;

/*    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "permisstion",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users = new HashSet<>();*/

    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "permisstion",
//            joinColumns = @JoinColumn(name = "ROLE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "USER_ID")
//    )
//    private Set<User> users = new HashSet<>();
}

package com.bielevote.backend.user;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectViews;
import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.votes.Vote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Jacksonized
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"legalName", "phone"})
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQ")
    @SequenceGenerator(name = "USERS_ID_SEQ", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @NonNull
    @JsonView({UserViews.viewMe.class})
    @Column(unique = true)
    private String username;

    @NonNull
    private String password;

    @NonNull
    @JsonView({UserViews.viewMe.class, ProjectViews.GetProjectList.class})
    private String legalName;

    @NonNull
    @JsonView(UserViews.viewMe.class)
    private String phone;

    @NonNull
    @JsonView(UserViews.viewMe.class)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NonNull
    @JsonView(UserViews.viewMe.class)
    private Boolean anonymousOnLeaderboard;

    @JsonBackReference
    @OneToMany(mappedBy = "author")
    private Set<Project> projects;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Transaction> rewardPointTransactions;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Vote> votes;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

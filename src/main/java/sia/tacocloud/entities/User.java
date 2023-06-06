package sia.tacocloud.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
//        (access=AccessLevel.PRIVATE, force=true)
//@RequiredArgsConstructor
//implements UserDetails
public class User  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private  String street;
    private  String city;
    private  String state;
    private  String zip;
    private  String phoneNumber;


//    public User( String username, String password, String fullname, String street, String city, String state, String zip, String phoneNumber) {
//        this.username = username;
//        this.fullname = fullname;
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//        this.phoneNumber = phoneNumber;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//              return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

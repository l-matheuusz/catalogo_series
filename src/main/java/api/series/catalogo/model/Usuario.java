package api.series.catalogo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "TB_EP_USUARIO")
@Getter @Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ds_username", nullable = false, unique = true)
    private String username;

    @Column(name="ds_password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_EP_USUARIO_ROLE", joinColumns = @JoinColumn(name="id_usuario")
            ,inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    public Usuario(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

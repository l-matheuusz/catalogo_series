package api.series.catalogo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Entity @Table(name = "TB_EP_ROLE")
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nm_role", nullable = false, unique = true)
    private String name;

    @Column(name = "ds_label", nullable = false)
    private String label;

}

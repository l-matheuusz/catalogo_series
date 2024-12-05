package api.series.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_SERIE")
@EntityListeners(AuditingEntityListener.class)
public class Serie {

    @Id
    @GeneratedValue
    @Column(name = "ID_SERIE")
    private Long id;

    @Column(name = "NM_SERIE", nullable = false)
    private String nome;

    @Column(name = "GR_SERIE", nullable = false)
    private String genero;

    @Column(name = "ANO_SERIE", nullable = false)
    private LocalDate anoLancamento;

    @Column(name = "QT_TEMPORADAS", nullable = false)
    private Integer quantidadeTemporadas;

    @Column(name = "CLASS_INDICATIVA", nullable = false)
    private Integer classificacaoIndicativa;
}

package dino.junction.domain.template.model.entity;

import dino.junction.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@NoArgsConstructor()
@Getter
public class TemplateEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    public String title;

    @Builder
    public TemplateEntity(final Long id, final String title) {
        this.id = id;
        this.title = title;
    }
}

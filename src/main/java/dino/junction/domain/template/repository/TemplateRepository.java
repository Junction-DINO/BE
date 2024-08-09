package dino.junction.domain.template.repository;

import dino.junction.domain.template.model.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
}

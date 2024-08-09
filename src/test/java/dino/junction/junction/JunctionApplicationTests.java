package dino.junction.junction;

import dino.junction.common.logger.aop.LogAop;
import dino.junction.domain.template.repository.TemplateRepository;
import dino.junction.domain.template.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(LogAop.class)
class JunctionApplicationTests {
}

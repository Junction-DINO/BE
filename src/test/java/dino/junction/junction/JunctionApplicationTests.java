package dino.junction.junction;

import dino.junction.common.aop.LogAop;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(LogAop.class)
class JunctionApplicationTests {
}

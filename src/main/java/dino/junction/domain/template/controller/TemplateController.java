package dino.junction.domain.template.controller;

import dino.junction.common.model.CommonResponse;
import dino.junction.domain.template.controller.request.TemplateCreateRequest;
import dino.junction.domain.template.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.aop.support.AopUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@Slf4j
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping("")
    public ResponseEntity<Object> getTemplate() {

        log.info("isAopProxy, templateService={}", AopUtils.isAopProxy(templateService));
        return CommonResponse.ResponseEntitySuccess("test");
    }

    @GetMapping("404")
    public ResponseEntity<Object> getTemplate404Test() throws BadRequestException {
        Object object = templateService.templateServiceQueryTest();
        return CommonResponse.ResponseEntitySuccess(object);
    }

    @PostMapping("")
    public ResponseEntity<Object> postTemplate(@Valid @RequestBody TemplateCreateRequest req) throws Exception {
        return CommonResponse.ResponseEntitySuccess(templateService.templateServicePostTest(req));
    }
}

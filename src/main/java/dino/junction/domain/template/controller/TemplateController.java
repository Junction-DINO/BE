package dino.junction.domain.template.controller;

import dino.junction.common.model.CommonResponse;
import dino.junction.domain.template.controller.request.TemplateCreateRequest;
import dino.junction.domain.template.service.TemplateService;
import jakarta.mail.Multipart;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.aop.support.AopUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@Slf4j
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping("")
    public ResponseEntity<Object> getTemplate() {

        log.info("isAopProxy, templateService={}", AopUtils.isAopProxy(templateService));
        return CommonResponse.of("test");
    }

    @GetMapping("404")
    public ResponseEntity<Object> getTemplate404Test() throws BadRequestException {
        Object object = templateService.templateServiceTest();
        return CommonResponse.of(object);
    }

    @PostMapping("")
    public ResponseEntity<Object> postTemplate(@Valid @RequestBody TemplateCreateRequest req) throws Exception {
        return CommonResponse.of(templateService.templateServicePostTest(req));
    }

    @PostMapping("s3")
    public ResponseEntity<Object> postS3Template(@RequestParam("file")MultipartFile file) throws Exception {
        return CommonResponse.of(templateService.postS3TemplateUpload(file));
    }

    @GetMapping("s3")
    public ResponseEntity<Object> getS3Template() throws Exception {
        return CommonResponse.of(templateService.downloadFile("test.jpg"));
    }
}

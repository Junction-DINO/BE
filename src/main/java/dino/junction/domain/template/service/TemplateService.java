package dino.junction.domain.template.service;

import com.google.api.client.http.HttpStatusCodes;
import dino.junction.common.model.CustomException;
import dino.junction.common.model.ErrorCode;
import dino.junction.domain.template.controller.request.TemplateCreateRequest;
import dino.junction.domain.template.model.entity.TemplateEntity;
import dino.junction.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateService {
    private final TemplateRepository templateRepository;

    public Object templateServiceTest() {
        return "test";
    }

    public Object templateService404Test() throws BadRequestException{
        throw new BadRequestException("test");
    }

    public Object templateServicePostTest(TemplateCreateRequest req) throws BadRequestException{
        // save template
        templateRepository.save(TemplateEntity.builder().
                title(req.getTitle()).
                build());
        return "test";
    }

    public Object templateServiceQueryTest() throws BadRequestException{
        // save template
        TemplateEntity template = templateRepository.findById(1000L).orElseThrow(
                () -> new CustomException(HttpStatusCodes.STATUS_CODE_BAD_REQUEST, ErrorCode.TEMPLATE_NOT_FOUND, "template not found")
        );
        System.out.println(template.getTitle());
        return "test";
    }
}

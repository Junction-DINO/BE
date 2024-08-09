package dino.junction.domain.template.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dino.junction.config.s3.S3Config;
import org.springframework.beans.factory.annotation.Value;
import dino.junction.domain.template.controller.request.TemplateCreateRequest;
import dino.junction.domain.template.model.entity.TemplateEntity;
import dino.junction.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateService {
    private final AmazonS3Client amazonS3Client;
    private final TemplateRepository templateRepository;

    @Value("${cloud.aws.bucket}")
    private String bucket;

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

    public Object postS3TemplateUpload(MultipartFile file) throws IOException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, fileObj));
        fileObj.delete();
        return true;
    }

    public File downloadFile(String fileName) {

        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(amazonS3Client.getObject(bucket, fileName).getObjectContent().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("error", e);
            throw e;
        }
        return convFile;
    }
}

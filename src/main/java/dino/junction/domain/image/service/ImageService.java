package dino.junction.domain.image.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.bucket}")
    private String bucket;

    @Transactional
    public String saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    @Transactional
    public void deleteImage(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    @Transactional
    public String updateImage(String existingFileUrl, MultipartFile newFile) throws IOException {
        deleteImage(existingFileUrl);
        return saveImage(newFile);
    }
}
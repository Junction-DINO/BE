package dino.junction.domain.ocr.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OcrRequest {
    MultipartFile ocrImage;
}

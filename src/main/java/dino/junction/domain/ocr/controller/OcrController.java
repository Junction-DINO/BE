package dino.junction.domain.ocr.controller;

import dino.junction.domain.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OcrController {
    private final OcrService ocrService;

    @GetMapping("/test/extract-text") // test 용도
    public List<String> extractText(@RequestParam("imageUrl") String imageUrl) throws Exception {
        return ocrService.extractTextFromImageUrl(imageUrl);
    }
}
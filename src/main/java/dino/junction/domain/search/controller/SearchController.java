package dino.junction.domain.search.controller;

import dino.junction.common.model.CommonResponse;
import dino.junction.domain.ocr.dto.OcrRequest;
import dino.junction.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping(value = "/search/ocr", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<Object> searchFoodByNamesWithOcr(@ModelAttribute OcrRequest ocrRequest) {
        return CommonResponse.CommonResponseSuccess(searchService.searchFoodsWithOcr(ocrRequest, 0, 10));
    }
    @GetMapping("/search")
    public CommonResponse<Object> searchFoodByName(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return CommonResponse.CommonResponseSuccess(searchService.searchFoods(q, page, size));
    }
}

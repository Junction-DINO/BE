package dino.junction.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
@Schema(description = "Success Response")
public record CommonResponse<T>(
        @Schema(description = "status code")  int status,
        @Schema(description = "응답 코드")  String code,
        @Schema(description = "응답 메시지")  String message,
        @Schema(description = "응답 데이터")  T data)
    {
    public static CommonResponse<Object> CommonResponseSuccess(Object data) {
        return CommonResponse.builder().status(200).code("SUCCESS").message("OK").data(data).build();
    }

    public static CommonResponse<Object> CommonResponseSuccessWithMessage(Object data) {
        return CommonResponse.builder().status(200).code("SUCCESS").message("OK").data(data).build();
    }

    public static CommonResponse<Object> CommonResponseSuccessMessage() {
        return CommonResponse.builder().status(200).code("SUCCESS").message("OK").data("SUCCESS").build();
    }

    public static ResponseEntity<Object> of(Object data) {
        return ResponseEntity.status(200).body(CommonResponseSuccess(data));
    }
}

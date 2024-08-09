package dino.junction.common.model;

import dino.junction.common.logger.model.TraceId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class SuccessResponse<T>  {
    @NotNull
    @Schema(example = "200")
    private final int status;
    @NotBlank
    @Schema(example = "성공")
    private final String message;
    private final TraceId traceId;
    private final T data;
}

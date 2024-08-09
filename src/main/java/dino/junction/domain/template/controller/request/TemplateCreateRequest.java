package dino.junction.domain.template.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Request Template", type = "multipartForm")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TemplateCreateRequest {
    @Schema(description = "template title", example = "title")
    @Size(max = 100)
    //@NotNull(message = "title is required")
    @NotEmpty(message = "title is required")
    String title;
}

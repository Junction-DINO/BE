package dino.junction.common.dto.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}

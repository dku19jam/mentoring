package auth.refreshToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 3600 * 24 * 14) //60초 -> 실제 사용 시 2주정도로 변경
public class RefreshToken {

    @Id
    private String refreshToken;

    private String memberId;
}

package mk.ukim.finki.emt2025.model.dto;

import mk.ukim.finki.emt2025.model.domain.TokenLog;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayTokenInfoDto(String username,
                                  String token,
                                  Date issuedAt,
                                  Date expiresAt) {

    public static DisplayTokenInfoDto from (TokenLog tokenLog){
        return new DisplayTokenInfoDto(tokenLog.getUser().getUsername(), tokenLog.getToken(),
                tokenLog.getIssuedAt(), tokenLog.getExpiresAt());
    }

    public static List<DisplayTokenInfoDto> from(List<TokenLog> tokenLogs){
        return tokenLogs.stream().map(DisplayTokenInfoDto::from).collect(Collectors.toList());
    }

}

package dmu.dmuclub.dto.sign;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpResquest {

    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;


    @Builder
    public static SignUpResquest toDto(String email, String password, String username, String nickname, String phone){
        return SignUpResquest.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .email(email)
                .username(username)
                .phone(phone)
                .build();
    }
}

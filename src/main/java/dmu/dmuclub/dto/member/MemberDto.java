package dmu.dmuclub.dto.member;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private int id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private String mbti;
    private String role;

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

}

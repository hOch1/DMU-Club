package dmu.dmuclub.dto.member;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@NoArgsConstructor
public class MemberDto {

    private int id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private String role;

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }
}

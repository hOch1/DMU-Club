package dmu.dmuclub.dto.sign;


import dmu.dmuclub.dao.member.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SignUpResquest {

    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;

    @Builder
    public SignUpResquest(String email, String password, String username, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
    }

    public List<String> toList(SignUpResquest signUpRequest) {
        List<String> list = new ArrayList<>();
        list.add(signUpRequest.getEmail());
        list.add(signUpRequest.getPassword());
        list.add(signUpRequest.getUsername());
        list.add(signUpRequest.getNickname());
        list.add(signUpRequest.getPhone());
        list.add(Role.NORMAL.toString());
        return list;
    }
}

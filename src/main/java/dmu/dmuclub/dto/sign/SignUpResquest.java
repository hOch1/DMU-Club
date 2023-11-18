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

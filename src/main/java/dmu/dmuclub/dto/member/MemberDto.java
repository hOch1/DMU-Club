package dmu.dmuclub.dto.member;

import dmu.dmuclub.dao.member.Role;
import dmu.dmuclub.dto.board.BoardDto;

import java.util.List;

public class MemberDto {

    private int id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private String role;

    public MemberDto() {
    }


    public MemberDto(int id, String email, String password, String username, String nickname, String phone, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

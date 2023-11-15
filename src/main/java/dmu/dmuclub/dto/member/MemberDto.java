package dmu.dmuclub.dto.member;

public class MemberDto {

    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;

    public MemberDto() {
    }

    public MemberDto(String email, String password, String username, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
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
}

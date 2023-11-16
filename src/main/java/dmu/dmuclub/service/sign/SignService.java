package dmu.dmuclub.service.sign;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.dto.sign.SignResponse;
import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.exception.sign.EmailAlreadyExistsException;
import dmu.dmuclub.exception.sign.LoginFailureException;
import dmu.dmuclub.exception.sign.NicknameAlreadyExistsException;
import dmu.dmuclub.exception.sign.PhoneAlreadyExistsException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignService {

    private final MemberDao memberDao;

    public SignService() {
        this.memberDao = new MemberDao();
    }

    public SignResponse signUp(SignUpResquest signUpRequest) {
        try {
            emailExistsValidate(signUpRequest.getEmail());
            nicknameExistsValidate(signUpRequest.getNickname());
            phoneExistsValidate(signUpRequest.getPhone());


            memberDao.save(signUpRequest);
            System.out.println("member save success");
            return successResponse();

        } catch (EmailAlreadyExistsException | NicknameAlreadyExistsException | PhoneAlreadyExistsException e){
            return new SignResponse(e.getMessage(), "409");
        } catch (SQLException | ClassNotFoundException e) {
            return new SignResponse("회원 가입 중 오류가 발생하였습니다.", "500");
        }
    }

    public SignResponse signIn(SignInRequest signInRequest, HttpSession session) {
        try {
            MemberDto memberDto = memberDao.findByEmail(signInRequest.getEmail());
            if (memberDto == null)
                throw new MemberNotFoundException("가입한 회원정보가 없습니다");

            if (!memberDto.getPassword().equals(signInRequest.getPassword()) )
                throw new LoginFailureException("비밀번호를 확인해 주세요");

            session.setAttribute("member", memberDto);
            return successResponse();
        } catch (LoginFailureException | MemberNotFoundException e) {
            return new SignResponse(e.getMessage(), "400");
        }
    }

    private boolean emailExistsValidate(String email) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByEmail(email))
            throw new EmailAlreadyExistsException("이메일이 이미 사용중입니다");
        return false;
    }

    private boolean nicknameExistsValidate(String nickname) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByNickname(nickname))
            throw new NicknameAlreadyExistsException("닉네임이 이미 사용중입니다.");
        return false;
    }

    private boolean phoneExistsValidate(String phone) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByPhone(phone))
            throw new NicknameAlreadyExistsException("핸드폰 번호가 이미 사용중입니다.");
        return false;
    }

    private SignResponse successResponse() {
        return new SignResponse("success", "200");
    }
}

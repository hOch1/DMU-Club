package dmu.dmuclub.service.board;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.HasNotRoleException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class BoardService {

    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public BoardService() {
        this.memberDao = new MemberDao();
        this.boardDao = new BoardDao();
    }

    public Response createBoard(CreateBoardRequest boardDto, HttpSession session) throws SQLException, ClassNotFoundException {
        try {
            String email = isLoginValidate((MemberDto) session.getAttribute("member"));
            MemberDto memberDto = memberDao.findByEmail(email);

            boardDto.setMember_id(memberDto.getId());

            boardDao.save(boardDto);

            return Response.successResponse();
        }catch (HasNotRoleException e){
            return new Response(e.getMessage(), "400");
        }catch (Exception e){
            return new Response("게시물 생성 실패", "500");
        }
    }

    private String isLoginValidate(MemberDto memberDto){
        if (memberDto == null)
            throw new HasNotRoleException("권한이 없습니다");

        return memberDto.getEmail();
    }
}

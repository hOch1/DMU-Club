package dmu.dmuclub.service.board;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.board.BoardDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.HasNotRoleException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class BoardService {

    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public BoardService(MemberDao memberDao, BoardDao boardDao) {
        this.memberDao = memberDao;
        this.boardDao = boardDao;
    }

    public Response createBoard(BoardDto boardDto, HttpSession session) throws SQLException, ClassNotFoundException {
        String email = isLoginValidate((MemberDto) session.getAttribute("member"));


        boardDao.save(boardDto);

        return Response.successResponse();
    }

    private String isLoginValidate(MemberDto memberDto){
        if (memberDto == null)
            throw new HasNotRoleException("권한이 없습니다");

        return memberDto.getEmail();
    }
}

package dmu.dmuclub.service.board;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.exception.member.HasNotRoleException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class BoardService {

    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public BoardService() {
        this.memberDao = new MemberDaoImpl();
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

    public ViewBoardResponse viewBoard(String boardId) throws SQLException {
        try {
            ViewBoardRequest boardRequest = boardDao.findById(boardId);

            if (boardRequest == null)
                throw new BoardNotFoundException("게시물을 찾지못하였습니다");

            MemberDto memberDto = memberDao.findById(boardRequest.getMemberId());


            return ViewBoardResponse.toResponse(boardRequest, memberDto.getNickname());
        } catch (BoardNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    private String isLoginValidate(MemberDto memberDto){
        if (memberDto == null)
            throw new HasNotRoleException("권한이 없습니다");

        return memberDto.getEmail();
    }
}

package dmu.dmuclub.service.board;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dao.board.impl.BoardDaoImpl;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.board.BoardContentNullException;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.exception.board.BoardTitleNullException;
import dmu.dmuclub.exception.member.HasNotRoleException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class BoardService {

    private final MemberDao memberDao = new MemberDaoImpl();
    private final BoardDao boardDao = new BoardDaoImpl();

    public void createBoard(CreateBoardRequest boardRequest, HttpSession session) throws SQLException, ClassNotFoundException {
        try {
            createBoardValidate(boardRequest);
            String email = isLoginValidate((MemberDto) session.getAttribute("member"));
            MemberDto memberDto = memberDao.findByEmail(email);

            boardRequest.setMember_id(memberDto.getId());

            boardDao.save(boardRequest);
        }catch (HasNotRoleException | BoardTitleNullException | BoardContentNullException e){
            throw new RuntimeException(e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("게시물 생성 실패");
        }
    }

    public List<ViewBoardResponse> viewBoardAll(){
        try{
            List<ViewBoardResponse> boardResponses = boardDao.findAll();

            if(boardResponses.isEmpty())
                throw new BoardNotFoundException("게시물을 찾지못하였습니다");

            return boardResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ViewBoardResponse viewBoardDetail(String boardId) throws SQLException {
        try {
            ViewBoardResponse boardResponse = boardDao.findById(boardId);

            if (boardResponse == null)
                throw new BoardNotFoundException("게시물을 찾지못하였습니다");

            MemberDto memberDto = memberDao.findById(boardResponse.getAuthor());
            boardResponse.setAuthor(memberDto.getNickname());

            return boardResponse;
        } catch (BoardNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(String id) throws SQLException {
        boardDao.deleteById(id);
    }

    private String isLoginValidate(MemberDto memberDto){
        if (memberDto == null)
            throw new HasNotRoleException("권한이 없습니다");

        return memberDto.getEmail();
    }

    private void createBoardValidate(CreateBoardRequest boardRequest){
        if(boardRequest.getTitle() == null)
            throw new BoardTitleNullException("제목을 입력해주세요.");

        if (boardRequest.getContent() == null)
            throw new BoardContentNullException("내용을 입력해주세요.");

    }
}

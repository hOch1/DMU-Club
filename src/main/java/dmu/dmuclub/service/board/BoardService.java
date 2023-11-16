package dmu.dmuclub.service.board;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.board.BoardDto;

import java.sql.SQLException;

public class BoardService {

    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public BoardService(MemberDao memberDao, BoardDao boardDao) {
        this.memberDao = memberDao;
        this.boardDao = boardDao;
    }

    public Response createBoard(BoardDto boardDto) throws SQLException, ClassNotFoundException {
        boardDao.save(boardDto);

        return Response.successResponse();
    }

}

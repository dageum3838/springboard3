package cafe.ebsteam.springboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.ebsteam.springboard.vo.Board;
@Mapper
public interface BoardMapper {
	// 한 행 조회
	Board selectBoard(int boardNo);
	// 전체 회원 리스트 페이징 작업
	List<Board> selectBoardList(Map<String, Integer> map);
	// 총 행의 갯수 
	int selectboardCount();
	// 입력
	int insertBoard(Board board);
	// 삭제
	int deleteBoard(Board board);
	// 수정
	int updateBoard(Board board);
	

	
}

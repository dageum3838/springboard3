package cafe.ebsteam.springboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.ebsteam.springboard.mapper.BoardMapper;
import cafe.ebsteam.springboard.vo.Board;

@Service
@Transactional //예외가 발생하면 rollback한다
public class BoardService {
	@Autowired private BoardMapper boardMapper; 
	
	public Board getBoard(int boardNo) {
		return boardMapper.selectBoard(boardNo);
	}
	
	public int getBoardCount() {
		return boardMapper.selectboardCount();
	}
	
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	public int removeBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}
	
	public int updateBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	public Map<String, Object> selectBoardList(int currentPage) {
		/*
		 * 	6. 글 리스트 조회 selectBoardList()
		 * 	입력 매개변수 : int타입 currentPage
		 * 	리턴 타입 : Map<String, Object>
		 *  Mapper의 selectBoardCount(), selectBoardList()
		 */

			// 메서드 호출이 되면 출력문을 통한 확인
			/* System.out.println("Service의 selectBoardList 시작!"); */ 

			// 한페이지에 10개의 글을 보여주기 위해 상수로 선언
			final int ROW_PER_PAGE = 10;
			// 현재 페이지 -1 * 10개의 글의 수에 대한 계산값을 변수에 담는다.
			int calPage = (currentPage-1)*ROW_PER_PAGE;
			// Map을 사용하기 위해 생성자로 객체 생성과 참조변수 선언
			Map<String, Integer> map = new HashMap<String, Integer>();
			// map참조변수에 할당
			map.put("currentPage", currentPage);
			map.put("rowPerPage", ROW_PER_PAGE);
			map.put("calPage", calPage);

			// 글의 갯수 정보 가져오기(Mapper를 통한 쿼리문 실행)
			int boardCount = boardMapper.selectboardCount();
			/* System.out.println("Service의 selectBoardList / selectBoardCount() 완료!"); */

			// 마지막 페이지수의 정보를 lastPage에 담는다. (ceil은 올림 함수)
			int lastPage = (int)(Math.ceil(boardCount/ROW_PER_PAGE));
			/* System.out.println("Service의 selectBoardList / lastPage 계산 완료!"); */

			// 리스트에 관련된 값들을 리턴으로 넘기기 위해 Map을 사용(객체생성)
			Map<String, Object> returnMap = new HashMap<String, Object>();
			// 글의 정보와 리스트 갯수, 마지막 페이지수를 참조변수 returnMap에 할당
			returnMap.put("list", boardMapper.selectBoardList(map));
			/* System.out.println("Service의 selectBoardList / selectBoardList(map) 결과 세팅완료 !"); */

			returnMap.put("boardCount", boardCount);
			returnMap.put("lastPage", lastPage);
			// 모두 완료되었으면 출력문을 통한 확인
			/* System.out.println("Service의 selectBoardList 끝!"); */

			// 리턴 데이터는 한 페이지에 보여줄 'ROW_PER_PAGE'(10)개의 글의 정보
			return returnMap;
		}
	} 	
package cafe.ebsteam.springboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.ebsteam.springboard.service.BoardService;
import cafe.ebsteam.springboard.vo.Board;

@Controller
public class BoardController {
	@Autowired private BoardService boardService;
	private Board board;
	// 리스트을 화면
		@GetMapping(value="/boardList")
		public String boardList(Model model, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
			System.out.println("페이지 수 :"+currentPage);
			model.addAttribute("map", boardService.selectBoardList(currentPage));
			model.addAttribute("currentPage",currentPage);
			return "boardList";
		}
	// 입력 하기
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";

	}
	// 입력 액션 
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
			
		boardService.addBoard(board);
		return "redirect:/boardList";
	}
	// 수정 화면 
	@GetMapping("/modifyBoard")
	public String updateBoard(Model model, @RequestParam(value="boardNo")int boardNo) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		System.out.println("no:" + boardNo + "수정 화면");
		return "modifyBoard";
	}
	// 수정 처리
	@PostMapping("/modifyBoard")
	public String updateBoard(Board board) {
		
		boardService.updateBoard(board);
		System.out.println(board);
		
		return "redirect:/boardList";
	}
	
	// 삭제 화면
	@GetMapping("/removeBoard")
	public String removeBoard(Model model, @RequestParam(value="boardNo")int boardNo) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		System.out.println("No:" + board + "삭제 화면");
		return "removeBoard";
	}
	// 삭제 처리
	@PostMapping("/removeBoard")
	public String removeBoard(Board board) {
		boardService.removeBoard(board);
		System.out.println("No:" + board + "삭제 처리");
		
		return "redirect:/boardList";
	}
	// 상세보기
		@GetMapping(value="/boardDetail")
		public String boardDetail(Model model, @RequestParam(value="boardNo", required=false, defaultValue="1") int boardNo, @RequestParam(value="currentPage") int currentPage) {
			System.out.println("글 No :"+boardNo);
			model.addAttribute("list", boardService.getBoard(boardNo));
			model.addAttribute("currentPage",currentPage);
			return "boardDetail";
		}
	
}

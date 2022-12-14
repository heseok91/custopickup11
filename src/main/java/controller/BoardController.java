package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Board;
import service.BoardMybatisDao;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardMybatisDao bd;
	
	HttpServletRequest request;
	   Model m;
	   HttpSession session;

	   
	   @ModelAttribute
	   void init(HttpServletRequest request, Model m) {
	      this.request = request;
	      this.m = m;
	      this.session = request.getSession();
	   }
	
	@RequestMapping("index")
	public String index() throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("index", "board 입니다");
		return "/index";
	}	// end index
	
	@RequestMapping("boardForm")
	public String boardForm() throws Exception {
		
		return "board/boardForm";
	}	// end boardForm
	
	
	@RequestMapping("boardPro")
	public String boardPro(@RequestParam("uploadfile") MultipartFile multipartFile, Board board) throws Exception {
		
		// 저장 path확인
		// 이미지 업로드가 필요해 picturePro에서 소스 참고
		
		String path = 
				request.getServletContext().getRealPath("/")+"view/board/img/";
		
		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			board.setFile1(multipartFile.getOriginalFilename());
			
		} else {
			board.setFile1("");
		}
		
			String filename = null;
			String msg = "게시물 등록 실패";
			String url = "boardForm";
			
			HttpSession session = request.getSession();
			String boardid = (String) session.getAttribute("boardid");
			if (boardid == null) boardid = "1";
			
			board.setBoardid(boardid);
			int num = bd.insertBoard(board);
			
			
			if (num>0) {
				msg = "게시물 등록 성공";
				url = "/board/boardList";
			}
			
			System.out.println(board);
			
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
	}	// boardPro end
	
	@RequestMapping("boardList")
	public String boardList() throws Exception {
		
		// boardid 파라미터로 넘어 왔을때만 session에 저장한다.
		HttpSession session = request.getSession();
		if (request.getParameter("boardid") != null) {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", "1");
		}
		
		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null) boardid = "1";
		
		
		// pageNum이 파라미터로 넘어 왔을때만 session에 저장한다
		if (request.getParameter("pageNum") != null) {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) pageNum = "1";
		
		
		
		int pageInt = Integer.parseInt(pageNum);
		
		
		
		// 100개 order by 최근 num desc 
		int limit = 5; // 한 page당 게시물 갯수
		
		  /*
	       * 1 page : 1,2,3  startPage : (pageInt-1)*limit+1, 
	       *                 endPage   : startPage + limit - 1
	       * 2 page : 4,5,6      s: 4 , e: 6
	       * 3 page : 7,8,9      s: 7  ,e: 9
	       */

		int boardCount = bd.boardCount(boardid);  // DB로 연결해서 나중에 가져올 계획
		List<Board> list = bd.boardList(pageInt, limit, boardid);
		
		// page count
		int bottomLine = 3;
		int start = (pageInt-1)/bottomLine*bottomLine+1;
		int end = start + bottomLine - 1;
		int maxPage = (boardCount/limit) + (boardCount%limit==0 ? 0 : 1);
		if (end > maxPage) end = maxPage;
		
		/*
		 * 
		 */
		
		String boardName = "";
		switch (boardid) {
		case "1" : boardName = "공지사항"; break;
		case "2" : boardName = "자유게시판"; break;
		case "3" : boardName = "QnA"; break;
		}
		
		
		int boardNum = boardCount - (pageInt-1)*limit;
		
		
		request.setAttribute("list", list);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("boardName", boardName);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("bottomLine", bottomLine);
		
		
		return "board/boardList";
		
	}	// end boardList
	
	
	@RequestMapping("boardInfo")
	public String boardInfo(int num) throws Exception {
		

		Board board = bd.boardOne(num);
		bd.readCountUp(num);
		
		request.setAttribute("board", board);
		return "board/boardInfo";
	}	// end boardInfo
	
	
	@RequestMapping("replyForm")
	public String replyForm(int num) throws Exception {

		Board board = bd.boardOne(num);
		bd.readCountUp(num);
		
		HttpSession session = request.getSession();
		String boardid = (String) session.getAttribute("boardid");
		String boardName = "";
		switch (boardid) {
		case "1" : boardName = "공지사항"; break;
		case "2" : boardName = "자유게시판"; break;
		case "3" : boardName = "QnA"; break;
		}
		
		request.setAttribute("boardName", boardName);
		request.setAttribute("board", board);
		return "board/replyForm";
	}	// end replyForm
	
	
	@RequestMapping("replyPro")
	public String replyPro(Board board) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid==null) boardid = "1";
		

		board.setFile1("");
		board.setBoardid(boardid);
		
		
		String msg = "답변 등록시 오류 발생";
		String url = "/board/replyForm?num="+board.getNum();
		
		board.setRef(board.getRef());		// 원조 새글 ref
		board.setReflevel(board.getReflevel()+1);		// 기준글 reflevel+1
		board.setRefstep(board.getRefstep()+1);		// print 순서
		
		bd.refStepAdd(board.getRef(), board.getRefstep());
		if (bd.insertReply(board)>0) {
			msg = "답변 등록 완료";
			url = "/board/boardList";
		};
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}	// end replyPro
	
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(int num) throws Exception {

		Board board = bd.boardOne(num);
		
		
		request.setAttribute("board", board);
		return "board/boardUpdateForm";
		
	}	// end boardUpdateForm end
	
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(@RequestParam("uploadfile") MultipartFile multipartFile, Board board) throws Exception {
		
		// 저장 path확인
		// 이미지 업로드가 필요해 picturePro에서 소스 참고
		
		String path = 
				request.getServletContext().getRealPath("/")+"view/board/img/";
			String msg = "";
			String url = "";
			
			if (!multipartFile.isEmpty()) {
				File file = new File(path, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				board.setFile1(multipartFile.getOriginalFilename());
				
			} else {
				board.setFile1("");
			}
		
			
			
			msg = "비밀번호가 틀렸습니다";
			url = "/board/boardUpdateForm?num="+board.getNum();
			// 뭔가 문제가 생겼을시 다시 돌아가기위해서. boradUpdateForm에 보면 Num이 hidden으로 숨겨져 있고
			// url에서 boardUpdateForm?num=해당num 으로 구성되었기에 이렇게 세팅하는게 적절하다.
			

			
			Board dbboard = bd.boardOne(board.getNum());
			// 비밀번호 확인
			
			msg = "비밀번호가 틀렸습니다";
			url = "/board/boardUpdateForm?num="+board.getNum();
			
			if (board.getPass().equals(dbboard.getPass())) {
				// 수정 하기
				if (bd.boardUpdate(board)>0) {
					msg = "수정 완료";
					url = "/board/boardInfo?num="+board.getNum();
				} else {
					msg = "수정 실패";
				}
			}
			
			
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
	}	// end boardUpdatePro
	
	
	@RequestMapping("boardDeleteForm")
	public String boardDeleteForm(int num) throws Exception {
		
		request.setAttribute("num", num);
		
		return "board/boardDeleteForm";
		
	}	// end boardDeleteForm
	
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(String pass, int num) throws Exception {

		Board board = bd.boardOne(num);
		
		String msg = "비밀번호가 틀렸습니다";
		String url = "/board/boardDeleteForm?num="+num;
		
		// 비밀번호 확인
		if (pass.equals(board.getPass())) {
			if (bd.boardDelete(num)>0) {
				msg = "게시글이 삭제 되었습니다";
				url = "/board/boardList";
			} else {
				msg = "게시글 삭제를 실패 하였습니다";
			}
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}
	
}	// end class

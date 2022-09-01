package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import model.User;
import service.UserMybatisDao;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	UserMybatisDao ud;
	
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
			request.setAttribute("index", "user 입니다");
			return "index";
		}
	   
	   @RequestMapping("userJoinForm")
		public String userJoinForm() throws Exception {
			
			return "user/userJoinForm";
		}

		@RequestMapping("userJoinPro")
		public String userJoinPro(User user) throws Exception {
			

			int num = ud.insertUser(user);

			String msg = "";
			String url = "";

			if  (num>0) {
				msg=user.getUsername() + "님의 가입이 완료 되었습니다.";
				url="/user/userLoginForm";
			} else {
				msg="회원가입이 실패했습니다.";
				url="/user/userJoinForm";
			}


			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	   
		@RequestMapping("userLoginForm")
		public String userLoginForm() throws Exception {
			
			return "user/userLoginForm";
		}
		
		@RequestMapping("userLoginPro")
		public String userLoginPro(String userid, String userpass) throws Exception {

			User user = ud.selectOne(userid);

			String msg = "아이디를 확인하세요";
			String url = "/user/userLoginForm";

			if (user!=null) {
				
				if (userpass.equals(user.getUserpass())) {
					session.setAttribute("userid", userid);
					session.setAttribute("usertype", user.getUsertype());
					msg = user.getUsername() + "님이 로그인 하셨습니다";
					url="/user/index";
				
				} else {
					
					msg = "비밀번호를 확인하세요";
				}
				
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
		
		@RequestMapping("userLogout")
		public String userLogout() throws Exception {
			
			String login = (String)session.getAttribute("userid");
			session.invalidate();
			
			String msg = login + "님 로그아웃 되었습니다.";
			String url = "/user/userLoginForm";
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
		
		@RequestMapping("userInfo")
		public String userInfo() throws Exception {
			

			String userid =	(String) session.getAttribute("userid");
			if (userid!=null && !userid.equals("")) {

				User m = ud.selectOne(userid);
				request.setAttribute("m", m);
				return "user/userInfo";
			} else {
				String msg = "로그인이 필요합니다.";
				String url = "/user/userLoginForm";
				
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return "alert";
			}
			
		}
		

		@RequestMapping("userUpdateForm")
		public String userUpdateForm() throws Exception {
			

			String userid =	(String) session.getAttribute("userid");
			if (userid!=null && !userid.equals("")) {

				User m = ud.selectOne(userid);
				request.setAttribute("m", m);
				return "user/userUpdateForm";
			} else {
				String msg = "로그인이 필요합니다.";
				String url = "/user/userLoginForm";
				
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return "alert";
			}
			
		}	// memberUpdateForm end
		
		@RequestMapping("userUpdatePro")
		public String userUpdatePro(User user) throws Exception {
			
			HttpSession session = request.getSession();
			String userid =	(String) session.getAttribute("userid");
			
			String msg = "로그인이 필요합니다";
			String url = "/user/userLoginForm";

			if (userid!=null && !userid.equals("")) {

			User dbm = ud.selectOne(userid);
			
			if (dbm!=null) {
				if (dbm.getUserpass().equals(user.getUserpass())) {
					
					int num = ud.updateUser(user);
					
					if  (num>0) {
						msg=user.getUsername() + "님의 정보 수정이 되었습니다.";
						url="/user/userInfo";
					} else {
						msg="정보 수정이 실패했습니다.";
						url="/user/userUpdateForm";
					}
				} else {
					msg="비밀 번호가 틀렸습니다.";
					url="/user/userUpdateForm";
				}
				
			}
			
			
		}	// session end
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
		}	// memberUpdatePro end
		

		@RequestMapping("userDelete")
		public String userDelete() throws Exception {
			

			String userid =	(String) session.getAttribute("userid");
			String msg = "로그인이 필요합니다.";
			String url = "/user/userLoginForm";
			if (userid!=null && !userid.equals("")) {
				return "user/userDelete";
			}
			
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return "alert";
			
		
		}	// memberDelete end
		

		@RequestMapping("userDeletePro")
		public String userDeletePro(String userpass) throws Exception {
			
			String userid =	(String) session.getAttribute("userid");

			String msg = "로그인이 필요합니다";
			String url = "/user/userLoginForm";
			
			if (userid!=null && !userid.equals("")) {


				
				User dbm = ud.selectOne(userid);
				
				if (dbm!=null) {
					if (dbm.getUserpass().equals(userpass)) {
						
						int num = ud.deleteUser(userid);
						
						if  (num>0) {
							msg=userid + "님이 탈퇴 처리되었습니다.";
							session.invalidate();  // 세션을 죽이는 프로세스
							url="/user/userLoginForm";
						} else {
							msg="회원 탈퇴가 실패했습니다.";
							url="/user/userDelete";
						}
					} else {
						msg="비밀 번호가 틀렸습니다.";
						url="/user/userDelete";
					}
				
				}
				
			}	// session end
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
		}  // memberDeletePro end
		
		@RequestMapping("userPassUpdate")
		public String userPassUpdate() throws Exception {
			

			String userid =	(String) session.getAttribute("userid");
			String msg = "로그인이 필요합니다.";
			String url = "/user/userLoginForm";
			if (userid!=null && !userid.equals("")) {
				return "user/userPassUpdate";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
			
		}	// memberPassUpdate end
		

		@RequestMapping("userPassPro")
		public String userPassPro(String userpass, String chgpass1) throws Exception {
			

			String userid =	(String) session.getAttribute("userid");

			String msg = "로그인이 필요합니다";
			String url = "/user/userLoginForm";
			
			if (userid!=null && !userid.equals("")) {

				User dbm = ud.selectOne(userid);
				
				if (dbm!=null) {
					if (dbm.getUserpass().equals(userpass)) {
						
						int num = ud.changePass(userid, chgpass1);
						
						if  (num>0) {
							msg=userid + "님의 비밀번호가 수정 되었습니다.";
							url="/user/index";
						} else {
							msg="비밀번호 수정에 실패했습니다.";
							url="/user/userPassUpdate";
						}
					} else {
						msg="비밀 번호가 틀렸습니다.";
						url="/user/userPassUpdate";
					}
					
				}
				
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
		}	// memberPassPro end
		

		@RequestMapping("userList")
		public String userList() throws Exception {
			

			String userid =	(String) session.getAttribute("userid");
			
			String msg = "로그인이 필요합니다";
			String url = "/user/userLoginForm";
			
			if (userid!=null && userid.equals("admin")) {
				
				List<User> li = new ArrayList<User>();
				li = ud.userList();
				request.setAttribute("li", li);
				return "user/userList";
				
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
			
		}	// memberList end
		

		@RequestMapping("userPictureimgForm")
		public String userPictureimgForm() throws Exception {
			
			return "user/userPictureimgForm";
			
		}	// pictureimgForm end
		

		@RequestMapping("userPicturePro")
		public String userPicturePro(@RequestParam("userpicture") MultipartFile multipartFile) throws Exception {
			
			
			String path = 
				request.getServletContext().getRealPath("/")+"view/user/picture/";
			String filename = null;
			
			if (!multipartFile.isEmpty()) {
				File file = new File(path, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				filename = multipartFile.getOriginalFilename();
				
			}
			
			request.setAttribute("filename", filename);
			
			return "user/userPicturePro";
			
		}
		
		
}

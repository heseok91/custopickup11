package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Store;
import service.StoreMybatisDao;

@Controller
@RequestMapping("/store/")
public class StoreController {
	
	@Autowired
	StoreMybatisDao sd;
	
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
			request.setAttribute("index", "store 입니다");
			return "index";
		}
	   
	   @RequestMapping("storeJoinForm")
		public String storeJoinForm() throws Exception {
			
			return "store/storeJoinForm";
		}
	   
	   @RequestMapping("storeJoinPro")
		public String storeJoinPro(Store store) throws Exception {
			

			int num = sd.insertStore(store);

			String msg = "";
			String url = "";

			if  (num>0) {
				msg=store.getStorename() + "의 가게정보가 등록 되었습니다.";
				url="/store/index";
			} else {
				msg="가게정보 등록이 실패했습니다.";
				url="/store/storeJoinForm";
			}


			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	   

		@RequestMapping("storePictureimgForm")
		public String storePictureimgForm() throws Exception {
			
			return "store/storePictureimgForm";
			
		}	// pictureimgForm end
		

		@RequestMapping("storePicturePro")
		public String storePicturePro(@RequestParam("storepicture") MultipartFile multipartFile) throws Exception {
			
			
			String path = 
				request.getServletContext().getRealPath("/")+"view/store/picture/";
			String filename = null;
			
			if (!multipartFile.isEmpty()) {
				File file = new File(path, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				filename = multipartFile.getOriginalFilename();
				
			}
			
			request.setAttribute("filename", filename);
			
			return "store/storePicturePro";
			
		}
		
		
}

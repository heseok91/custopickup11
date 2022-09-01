package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserMybatisDao {
	
	@Autowired
	private SqlSessionTemplate session;

	private final static String ns = "user.";
	
	public int insertUser(User user) {

		int num = session.insert(ns+"insertUser", user);

		return num;
	}
	
	public User selectOne(String userid) { // loginPro.jsp에서 id로 검색 시, 해당 아이디가
		  // 존재하는지 확인하는 메서드
		  

			User user = session.selectOne(ns+"selectOne", userid);

			
			return user;
		}
	
	public int updateUser(User user) { 
		

		int num = session.update(ns+"updateUser", user);

		
		return num;
	}
	
	public int deleteUser(String userid) { 
		

		int num = session.delete(ns+"deleteUser", userid);

		
		return num;
	}
	
	
	public int changePass(String userid, String userpass) { 
		
		Map map = new HashMap();
		
		map.put("userid", userid);
		map.put("userpass", userpass);
		int num = session.update(ns+"changePass", map);

		
		return num;
	}
	
	public List<User> userList() { // loginPro.jsp에서 id로 검색 시, 해당 아이디가 존재하는지 확인하는 메서드
	  
		

		List<User> list = session.selectList(ns+"userList");

		
		return list;
	}
	
}

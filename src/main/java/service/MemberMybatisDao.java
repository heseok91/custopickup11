package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Member;

@Repository
public class MemberMybatisDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final static String ns = "member.";

	
	public int insertMember(Member mem) {

		int num = session.insert(ns+"insertMember", mem);

		
		return num;
	}
	
	
	
	public Member selectOne(String id) { // loginPro.jsp에서 id로 검색 시, 해당 아이디가
	  // 존재하는지 확인하는 메서드
	  

		Member mem = session.selectOne(ns+"selectOne", id);

		
		return mem;
	}
	
	  
	  
	
	public int updateMember(Member mem) { 
		

		int num = session.update(ns+"updateMember", mem);

		
		return num;
	}
	
	  
	
	public int deleteMember(String id) { 
		

		int num = session.delete(ns+"deleteMember", id);

		
		return num;
	}
	
	  
	
	public int changePass(String id, String pass) { 
		
		Map map = new HashMap();
		
		map.put("id", id);
		map.put("pass", pass);
		int num = session.update(ns+"changePass", map);

		
		return num;
	}
		
	
	  
	  
	
	public List<Member> memberList() { // loginPro.jsp에서 id로 검색 시, 해당 아이디가 존재하는지 확인하는 메서드
	  
		

		List<Member> list = session.selectList(ns+"memberList");

		
		return list;
	}
	 

} // end member

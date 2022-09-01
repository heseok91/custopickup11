package service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Store;

@Repository
public class StoreMybatisDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final static String ns = "store.";
	
	public int insertStore(Store store) {

		int num = session.insert(ns+"insertStore", store);

		return num;
	}
	
	public Store selectOne(String storeid) { // loginPro.jsp에서 id로 검색 시, 해당 아이디가
		  // 존재하는지 확인하는 메서드
		  

		Store store = session.selectOne(ns+"selectOne", storeid);

			
		return store;
	}
	
	public int updateStore(Store store) { 
		

		int num = session.update(ns+"updateStore", store);

		
		return num;
	}
	
	public int deleteStore(String storeid) { 
		

		int num = session.delete(ns+"deleteStore", storeid);

		
		return num;
	}
	
	
}

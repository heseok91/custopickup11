package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Board;



@Repository
public class BoardMybatisDao {
	
	@Autowired
	SqlSessionTemplate session;
	
	private final static String ns = "board.";
	private Map map = new HashMap();
	
	public List<Board> boardList(int pageInt, int limit, String boardid) {
		

		map.clear();
		map.put("boardid", boardid);
		map.put("start", (pageInt-1)*limit+1);
		map.put("end", (pageInt*limit));
		List<Board> list= session.selectList(ns+"boardList", map);

		
		return list;
	}
	
	public int boardCount(String boardid) {
		

		int num = session.selectOne(ns+"boardCount", boardid);

		
		return num;
	}
	
	public int insertBoard(Board board) {
		

		int num = session.insert(ns+"insertBoard", board);

		
		return num;
	}
	
	public Board boardOne(int num) {
		

		Board board = session.selectOne(ns+"boardOne", num);

		return board;
	}
	
	public void readCountUp(int num) {
		

		session.update(ns+"readCountUp", num);

		
	}
	
	public void refStepAdd(int ref, int refstep) {
		

		map.clear();
		map.put("ref", ref);
		map.put("refstep", refstep);
		session.update(ns+"refStepAdd", map);

		
	}
	
	public int insertReply(Board board) {
		

		int num = session.insert(ns+"insertReply", board);

		
		return num;
	}
	
	public int boardUpdate(Board board) {
		

		int num = session.update(ns+"boardUpdate", board);

		
		return num;
	}
	
	public int boardDelete(int num) {
		

		int m = session.delete(ns+"boardDelete", num);

		
		return m;
	}
	
	
	
} // end class

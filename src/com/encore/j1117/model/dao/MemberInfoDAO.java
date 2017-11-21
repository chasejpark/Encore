package com.encore.j1117.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.encore.j1117.model.vo.MemberInfoVO;

public class MemberInfoDAO {
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;

	Properties pro;

	public MemberInfoDAO() {// 생성자
		try {
			pro = new Properties();
			pro.load(new FileReader("conn/conn.properties"));

			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// MemberDAO

	public void connect() {// 연결
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
			System.out.println("DB connection succeess!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// connect

	public void disconnect() {// 연결해제

		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// disconnect

	public boolean insert(MemberInfoVO mvo) {// 추가
		connect();
		try {
			String sql = "insert into memberinfo (id,pass,name,ssn1,ssn2,tel,addr,job) values (?,?,?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);
			// stmt.setString(물음표 순서, 데이터);
			stmt.setString(1, mvo.getId());
			stmt.setString(2, mvo.getPass());
			stmt.setString(3, mvo.getName());
			stmt.setInt(4, mvo.getSsn1());
			stmt.setInt(5, mvo.getSsn2());
			stmt.setString(6, mvo.getTel());
			stmt.setString(7, mvo.getAddr());
			stmt.setString(8, mvo.getJob());
			int t = stmt.executeUpdate();

			System.out.println("insert SQL: " + t + "개 행이 추가 되었습니다");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// insert

	public boolean delete(String id) {// 삭제
		connect();
		try {
			String sql = "delete from memberInfo where id=?" + id;
			stmt = conn.prepareStatement(sql);
			int su = stmt.executeUpdate();
			int t = Integer.parseInt(sql);

			if (su == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}// delete

	public boolean update(MemberInfoVO mvo) {// 수정
		connect();
		try {
			String sql = "update memberInfo set pass=" + mvo.getPass() + ",tel=" + mvo.getTel() + ",addr='"
					+ mvo.getAddr() + "',job='" + mvo.getJob() + "' where id=" + mvo.getId();
			stmt = conn.prepareStatement(sql);
			int t = stmt.executeUpdate();
			if (t == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}// update

	public MemberInfoVO select(String str) {// 한행 조회
		connect();
		MemberInfoVO mvo = null;
		try {
			String sql = "select id,pass,name,ssn1,ssn2,tel,addr,job from memberInfo where id=?" + str;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int ssn1 = rs.getInt("ssn1");
				int ssn2 = rs.getInt("ssn2");
				String tel = rs.getString("tel");
				String addr = rs.getString("addr");
				String job = rs.getString("job");

				mvo = new MemberInfoVO(id, pass, name, ssn1, ssn2, tel, addr, job);
				return mvo;
			} // if
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return mvo;
	}// select

	public ArrayList<MemberInfoVO> selectAll() {// 전체 조회
		ArrayList<MemberInfoVO> list = new ArrayList<>();
		connect();
		try {
			String sql = "select id,pass,name,ssn1,ssn2,tel,addr,job from memberInfo order by name";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new MemberInfoVO(rs.getString("id"), rs.getString("pass"), rs.getString("name"),
						rs.getInt("ssn1"), rs.getInt("ssn2"), rs.getString("tel"), rs.getString("addr"),
						rs.getString("job")));
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}// selectAll

	public boolean selectLogin(String id, String pass) {
		connect();
		try {

			String sql = "select count(*) from memberInfo where id=? and pass=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();// 위에서 이미 준비해놨기 때문에 괄호 안에 sql을 넣을 필요가 없다.
			rs.next();
			int count = rs.getInt(1);
			if (count == 1) {
				System.out.println(id + " 로그인에 성공하였습니다.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// selectLogin

	public ArrayList<MemberInfoVO> chkId(String duplicatedId) {

		connect();
		ArrayList<MemberInfoVO> list = new ArrayList<>();

		try {
			String sql = "select id from memberinfo where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + duplicatedId + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new MemberInfoVO(rs.getString("id")));
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		disconnect();
		}
	}

	public void selectId() {

	}// selectId
}

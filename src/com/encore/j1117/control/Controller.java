package com.encore.j1117.control;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.encore.j1117.model.dao.MemberInfoDAO;
import com.encore.j1117.model.vo.MemberInfoVO;
import com.encore.j1117.view.JoinForm;
import com.encore.j1117.view.LoginForm;
import com.encore.j1117.view.ServiceForm;

public class Controller implements ActionListener {

	// 뷰 등록
	LoginForm loginForm;
	JoinForm joinForm;
	ServiceForm serviceForm;

	public Controller() {
		loginForm = new LoginForm();
		joinForm = new JoinForm();
		serviceForm = new ServiceForm();

		eventUp();
	}

	private void eventUp() {
		// 로그인 폼
		loginForm.bt_login.addActionListener(this);
		loginForm.bt_join.addActionListener(this);

		// 조인 폼
		joinForm.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				joinForm.setVisible(false);
				loginForm.setVisible(true);
			}

		});

		joinForm.bt_checkid.addActionListener(this);
		joinForm.bt_submit.addActionListener(this);
		joinForm.bt_reset.addActionListener(this);

		// 서비스 폼
		serviceForm.bt_up.addActionListener(this);
		serviceForm.bt_del.addActionListener(this);
		serviceForm.bt_sel_all.addActionListener(this);
		serviceForm.bt_sel_id.addActionListener(this);
		serviceForm.bt_exit.addActionListener(this);

		// 회원가입폼

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == loginForm.bt_login) {
			String id = loginForm.tf_id.getText();
			String pass = new String(loginForm.tf_pass.getPassword());

			MemberInfoDAO dao = new MemberInfoDAO();

			if (dao.selectLogin(id, pass)) {
				// 로그인 성공 시

				serviceForm.displayTable(dao.selectAll());
				loginForm.setVisible(false);
				serviceForm.setVisible(true);
			} else {
				System.out.println("login Failed.");
				joinForm.showMsg("아이디 또는 비밀번호가 일치하지않습니다");
			}

		} else if (ob == loginForm.bt_join) {
			loginForm.setVisible(false);
			joinForm.setVisible(true);

		} else if (ob == joinForm.bt_checkid) {
			MemberInfoDAO dao = new MemberInfoDAO();
			
			String id=null;
			String str = dao.ch(id);
			if(str == joinForm.tf_id.getText()) {
				joinForm.showMsg("이미 존재하는 아이디입니다");
				System.out.println("성공");
			}
			
				
			
			
		} else if (ob == joinForm.bt_reset) {

		} else if (ob == joinForm.bt_submit) {
			String id = joinForm.tf_id.getText();
			// joinForm.tf_pass.getPassword()===> new String(char[])
			String pass = new String(joinForm.tf_pass.getPassword());
			String pass2 = new String(joinForm.tf_pass2.getPassword());

			String name = joinForm.tf_name.getText();
			String ssn1 = joinForm.tf_ssn1.getText();
			String ssn2 = new String(joinForm.tf_ssn2.getPassword());
			String tel = joinForm.tf_tel1.getText() + "-" + joinForm.tf_tel2.getText() + "-"
					+ joinForm.tf_tel3.getText();
			// tel="010-1234-5678"
			String addr = joinForm.tf_addr.getText();
			String job = (String) joinForm.cb_job.getSelectedItem();

			MemberInfoVO vo = new MemberInfoVO(id, pass2, name, Integer.parseInt(ssn1), Integer.parseInt(ssn2), tel,
					addr, job);

			MemberInfoDAO dao = new MemberInfoDAO();
			if (dao.insert(vo)) {
				joinForm.showMsg("입력성공");
				joinForm.setVisible(false);
				loginForm.setVisible(true);

			} else {
				joinForm.showMsg("입력한 값을 확인하세요");
			}
		} else if (ob == serviceForm.bt_up) {

		} else if (ob == serviceForm.bt_del) {

		} else if (ob == serviceForm.bt_sel_id) {

		} else if (ob == serviceForm.bt_sel_all) {

		} else if (ob == serviceForm.bt_exit) {
			int t = joinForm.confirmMsg();
			if(t == 0) {
				System.out.println("프로그램이 종료되었습니다");
				System.exit(0);
			}
		}
		
	}
	public static void main(String[] args) {
		new Controller();
	}
}

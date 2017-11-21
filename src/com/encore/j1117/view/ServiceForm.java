package com.encore.j1117.view;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.encore.j1117.model.vo.MemberInfoVO;

public class ServiceForm extends JFrame {
	public JTable table;
	JLabel la;
	DefaultTableModel dtm;

	public JButton bt_up, bt_del, bt_sel_all, bt_sel_id, bt_exit;
	JPanel southp;

	public ServiceForm() {
		setTitle("Display Data");

		Object rowData[][] = new String[0][7];
		Object colNames[] = { "ID", "이름", "나이", "성별", "전화번호", "주소", "직업" };

		dtm = new DefaultTableModel(rowData, colNames);
		table = new JTable(dtm);

		JScrollPane scrol = new JScrollPane(table);

		bt_up = new JButton("수정");
		bt_del = new JButton("삭제");
		bt_sel_all = new JButton("전체보기");
		bt_sel_id = new JButton("아이디검색");
		bt_exit = new JButton("종료");

		southp = new JPanel();
		southp.add(bt_sel_all);
		southp.add(bt_up);
		southp.add(bt_del);
		southp.add(bt_sel_id);
		southp.add(bt_exit);

		add("Center", scrol);
		add("South", southp);

		setBounds(300, 200, 500, 300);
		// setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}// 생성자

	public void displayTable(ArrayList<MemberInfoVO> list) {
		
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		
		
		for (int i = 0; i < list.size(); i++) {
			MemberInfoVO vo = list.get(i);
			int birthYear = vo.getSsn1()/10000;
			String gender = (vo.getSsn2()/1000000) % 2 == 0 ? "여성":"남성";
			int age;
			// 나이==> 현재년도-(1900+출생년도)+1
			 age= year-(1900+birthYear)+1;
			// 나이==> 현재년도-(2000+출생년도)+1
			 
			Object[] rowData = { vo.getId(), vo.getName(), 13, "남자", vo.getTel(),vo.getAddr(),vo.getJob() };
			dtm.addRow(rowData);
		}

	}

}// ServiceForm class

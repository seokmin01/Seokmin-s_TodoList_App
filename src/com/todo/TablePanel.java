package com.todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.todo.service.DbConnect;

@SuppressWarnings("serial")
public class TablePanel extends JPanel {
	Connection conn;
	JScrollPane scroll;
	JTable table;
	String[][] data;
	String[] title = { "ID", "카테고리", "제목", "설명", "마감일자", "등록시간", "장소", "중요도", "완료여부" };
	DefaultTableModel model = new DefaultTableModel(title, 0);

	public TablePanel() {
		setBackground(new Color(41,134,204));
		setBorder(new EmptyBorder(50, 50, 50, 50));
		this.conn = DbConnect.getConnection();
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Todo List");
		titleLabel.setPreferredSize(new Dimension(900, 70));
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setOpaque(true);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(41,134,204));
		add(titleLabel);

		JButton refreshButton = new JButton("새로고침");
		add(refreshButton);

		data = getDB();
		table = new JTable(data, title);
		table.getColumn("ID").setPreferredWidth(20);
		table.getColumn("카테고리").setPreferredWidth(100);
		table.getColumn("제목").setPreferredWidth(100);
		table.getColumn("설명").setPreferredWidth(300);
		table.getColumn("마감일자").setPreferredWidth(150);
		table.getColumn("등록시간").setPreferredWidth(250);
		table.getColumn("장소").setPreferredWidth(100);
		table.getColumn("중요도").setPreferredWidth(100);
		table.getColumn("완료여부").setPreferredWidth(70);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(1000, 500));
		add(scroll);
		refreshButton.addActionListener(e -> {
			remove(scroll);
			data = getDB();
			table = new JTable(data, title);
			table.getColumn("ID").setPreferredWidth(20);
			table.getColumn("카테고리").setPreferredWidth(100);
			table.getColumn("제목").setPreferredWidth(100);
			table.getColumn("설명").setPreferredWidth(300);
			table.getColumn("마감일자").setPreferredWidth(150);
			table.getColumn("등록시간").setPreferredWidth(250);
			table.getColumn("장소").setPreferredWidth(100);
			table.getColumn("중요도").setPreferredWidth(100);
			table.getColumn("완료여부").setPreferredWidth(70);
			table.setFillsViewportHeight(true);
			scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(1000, 500));
			add(scroll);
			revalidate();
			repaint();
		});
	}

	public String[][] getDB() {
		String[][] list = new String[1000][10];
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String place = rs.getString("place");
				String importance = rs.getString("importance");
				String importance_str;
				if (importance.equals("3"))
					importance_str = "☆☆☆";
				else if (importance.equals("2"))
					importance_str = "☆☆";
				else
					importance_str = "☆";
				String is_completed_str;
				if (is_completed == 1)
					is_completed_str = "V";
				else
					is_completed_str = "";
				String[] itemInfo = { id, category, title, description, due_date, current_date, place, importance_str,
						is_completed_str };
				list[i] = itemInfo;
				i++;
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
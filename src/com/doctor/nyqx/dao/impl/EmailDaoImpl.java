package com.doctor.nyqx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.doctor.nyqx.commons.util.JdbcUtil;
import com.doctor.nyqx.dao.EmailDao;
import com.doctor.nyqx.entity.Email;

public class EmailDaoImpl implements EmailDao {
	private Connection conn = null;
	private PreparedStatement ps = null;

	@Override
	public List<Email> selectAllEmail() {
		List<Email> emails = new ArrayList<>();
		String sql = "SELECT uid, uemail FROM jee_user";

		conn = JdbcUtil.getConnection();
		try {
			if (null != conn) {
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int uid = rs.getInt("uid");
					String uemail = rs.getString("uemail");
					Email email = new Email(uid, uemail);
					emails.add(email);
				}
				return emails;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				JdbcUtil.close(conn);
			}
			if (null != ps) {
				JdbcUtil.close(ps);
			}
		}
		return emails;
	}

}

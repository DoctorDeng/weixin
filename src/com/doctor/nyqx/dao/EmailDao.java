package com.doctor.nyqx.dao;

import java.util.List;
import com.doctor.nyqx.entity.Email;

public interface EmailDao {
	public List<Email>	selectAllEmail();
}

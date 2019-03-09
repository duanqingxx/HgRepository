package com.duan.m.dao.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.duan.m.dao.ECommDao;
import com.duan.m.entity.EComment;
import com.duan.m.utils.Dbutils;

public class TestECommDaoImpl {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testECommDaoImple() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectCommCount() {
		ECommDao dao=new ECommDaoImpl(Dbutils.getConnection());
		dao.selectCommCount();
		System.out.println(dao.selectCommCount());
	}

	@Test
	public void testSelectCommList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMaxId() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertComm() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteComm() {
		fail("Not yet implemented");
	}

}

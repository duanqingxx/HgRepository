package com.duan.c.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.EUserBiz;
import com.duan.m.biz.impl.EUserBizImpl;
import com.duan.m.entity.UserInfo;
import com.duan.m.utils.Page;

/**
 * Servlet implementation class EUserServlet
 */
@WebServlet("/EUserServlet")
public class EUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		EUserBiz   biz=new EUserBizImpl();
		if("userList".equals(type)){
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<UserInfo> page=biz.findEUserPage(pageNo);
			request.setAttribute("userPage", page);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("manage/user.jsp");
			dispatcher.forward(request, response);
		}else if("updUser".equals(type)){
			String eu_id=request.getParameter("eu_id");
			String userid=request.getParameter("userName");
			String eu_name=request.getParameter("name");
			String eu_password=request.getParameter("passWord");
			String eu_sex=request.getParameter("sex");
			String eu_birthday=request.getParameter("birthday");
			Date date=null;
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try {
				date=format.parse(eu_birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String eu_mobile=request.getParameter("mobile");
			String eu_address=request.getParameter("address");
			UserInfo user=new UserInfo();
			user.setEu_user_id(userid);
			user.setEu_passWord(eu_password);
			user.setEu_user_name(eu_name);
			user.setEu_sex(eu_sex);
			user.setEu_birthday(new Timestamp(date.getTime()));
			user.setEu_mobile(eu_mobile);
			user.setEu_address(eu_address);
			biz.updEUser(user, eu_id);
			response.sendRedirect("manage/manage-result.jsp");
		}else if("delUser".equals(type)){
			String eu_id=request.getParameter("eu_id");
			biz.delEUser(eu_id);
			response.sendRedirect("manage/manage-result.jsp");
		}
		
	}

}

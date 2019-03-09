package com.duan.c.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.EProCateBiz;
import com.duan.m.biz.UserInfoBiz;
import com.duan.m.biz.impl.EProCateBizImpl;
import com.duan.m.biz.impl.UserInfoBizImpl;
import com.duan.m.entity.EProduct;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.entity.UserInfo;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
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
		UserInfoBiz biz=new UserInfoBizImpl();
		UserInfo   user=new UserInfo();
	
		String type=request.getParameter("type");
		String userId=request.getParameter("userId");
		String passWord=request.getParameter("password");
		
		user.setEu_user_id(userId);
		user.setEu_passWord(passWord);
		
		if("login".equals(type)){
			if(biz.findUser(user)){
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("cTime",new Date());
				List<EProduct>       buyList=new ArrayList<EProduct>();
				request.getSession().setAttribute("BuyCarList",buyList);
				
				Map<String,Integer> map=new HashMap<String,Integer>();
				request.getSession().setAttribute("BuyCarNum",map);
				response.sendRedirect("index.jsp");
			}else{
				PrintWriter out=response.getWriter();
				out.print("<script type='text/javascript'>");
				out.print("setTimeout(\"location.href='login.jsp'\", 3000);");
				out.print("</script>");
				out.println("<a href='login.jsp'>用户名或密码错误，点此返回登录页...</a>");
			}
		}
		else if("register".equals(type)){
			String userName=request.getParameter("userName");
			String userSex=request.getParameter("sex");
			String birthday=request.getParameter("birthday");
			Date date=null;
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try {
				date=format.parse(birthday);
			} catch (ParseException e) {
				System.out.println("time translate error");
			}
			String indentityCode=request.getParameter("identityCode");
			String email=request.getParameter("email");
			String mobile=request.getParameter("mobile");
			String address=request.getParameter("address");
			float login=0.0f;
			int    status=1;
			user.setEu_user_name(userName);
			user.setEu_sex(userSex);
			user.setEu_birthday(new Timestamp(date.getTime()));
			user.setEu_indentity_code(indentityCode);
			user.setEu_email(email);
			user.setEu_mobile(mobile);
			user.setEu_address(address);
			user.setEu_login(login);
			user.setEu_status(status);
			if(biz.addUser(user))
				response.sendRedirect("reg-result.jsp");
			
		}
		else if("checkName".equals(type)){
			boolean b=biz.findUserById(userId);
			response.getWriter().print(b);
		}
		else if("logout".equals(type)){
			request.getSession().removeAttribute("user");
			response.sendRedirect("index.jsp");
		}
		
	}

}

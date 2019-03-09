package com.duan.c.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.ECommBiz;
import com.duan.m.biz.impl.ECommBizImpl;
import com.duan.m.entity.EComment;
import com.duan.m.utils.Page;

/**
 * Servlet implementation class ECommServlet
 */
@WebServlet("/ECommServlet")
public class ECommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ECommServlet() {
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
		ECommBiz    commBiz=new ECommBizImpl();
		if("commList".equals(type)){
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EComment> page=commBiz.findECommPage(pageNo);
			request.setAttribute("commPage", page);
			RequestDispatcher dispatcher=request.getRequestDispatcher("guestbook.jsp");
			dispatcher.forward(request, response);
		}
		else if("commList2".equals(type)){
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EComment> page=commBiz.findECommPage(pageNo);
			request.setAttribute("commPage", page);
			RequestDispatcher dispatcher=request.getRequestDispatcher("manage/guestbook.jsp");
			dispatcher.forward(request, response);
		}else if("addComm".equals(type)){
			String guestContent=request.getParameter("guestContent");
			String    guestName=request.getParameter("guestName2");
			EComment comment=new EComment();
			comment.setEc_content(guestContent);
			comment.setEc_nick_name(guestName);
			commBiz.addEComment(comment);
			response.sendRedirect("ECommServlet?type=commList");
		}else if("delComm".equals(type)){
			int ec_id=Integer.parseInt(request.getParameter("ec_id"));
			commBiz.delEComment(ec_id);
			response.sendRedirect("manage/manage-result.jsp");
		}else if("updComm".equals(type)){
			int ec_id=Integer.parseInt(request.getParameter("ec_id"));
			String ec_reply=request.getParameter("replyContent");
			EComment comment=new EComment();
			comment.setEc_id(ec_id);
			comment.setEc_reply(ec_reply);
			commBiz.updEComment(comment);
			response.sendRedirect("manage/manage-result.jsp");
		}
	}

}

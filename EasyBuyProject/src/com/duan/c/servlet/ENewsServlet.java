package com.duan.c.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.ENewsBiz;
import com.duan.m.biz.impl.ENewsBizImpl;
import com.duan.m.entity.ENews;
import com.duan.m.utils.Page;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ENewsServlet
 */
@WebServlet("/ENewsServlet")
public class ENewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENewsServlet() {
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
		ENewsBiz biz=new ENewsBizImpl();
		if("hotNews".equals(type)){
			List<ENews> list=biz.findHotNews();
			JSONArray json=JSONArray.fromObject(list);
			String str=json.toString();
			response.getWriter().print(str);
		}
		else if("detailNews".equals(type)){
			String en_id=request.getParameter("newsId");
			ENews news=biz.findENewsDetail(Integer.parseInt(en_id));
			request.setAttribute("enews",news);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("news-view.jsp");
			dispatcher.forward(request, response);
		}
		else if("newsList".equals(type)){
			
			int currPageNo=1;
			String pageNo=request.getParameter("pageIndex");
			if(pageNo!=null)
				currPageNo=Integer.parseInt(pageNo);
			Page<ENews> page=biz.findENewsPage(currPageNo);
			request.setAttribute("newsPage", page);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("manage/news.jsp");
			dispatcher.forward(request, response);
		}else if("addNews".equals(type)){
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			ENews news=new ENews();
			news.setEn_title(title);
		    news.setEn_content(content);
		    biz.addENews(news);
		    response.sendRedirect("manage/manage-result.jsp");
		}else if("delNews".equals(type)){
			int en_id=Integer.parseInt(request.getParameter("en_id"));
			biz.delENews(en_id);
			response.sendRedirect("manage/manage-result.jsp");
		}else if("updNews".equals(type)){
			int en_id=Integer.parseInt(request.getParameter("id"));
			String en_title=request.getParameter("title");
			String en_content=request.getParameter("content");
			ENews news=new ENews();
			news.setEn_id(en_id);
			news.setEn_title(en_title);
			news.setEn_content(en_content);
			biz.updENews(news);
			response.sendRedirect("manage/manage-result.jsp");
		}
		
	}

}

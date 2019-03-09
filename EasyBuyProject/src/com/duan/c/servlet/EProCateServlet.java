package com.duan.c.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.EProCateBiz;
import com.duan.m.biz.impl.EProCateBizImpl;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.Page;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class EProCateServlet
 */
@WebServlet("/EProCateServlet")
public class EProCateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EProCateServlet() {
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
		EProCateBiz  cateBiz=new EProCateBizImpl();
		
		if("procateList".equals(type)){
			String currPageNo=request.getParameter("pageIndex");
			int pageNo=1;
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EProduct_Category> page=cateBiz.findProCatePage(pageNo);
			request.setAttribute("catePage", page);
			RequestDispatcher dispatcher=request.getRequestDispatcher("manage/productClass.jsp");
			dispatcher.forward(request, response);
		}else if("fatherCate".equals(type)){
			List<EProduct_Category> list=cateBiz.findProCateOne();
			JSONArray json=JSONArray.fromObject(list);
			String str=json.toString();
			response.getWriter().print(str);
		}else if("findeAllCate".equals(type)){
			List<EProduct_Category> list=cateBiz.findAllProCate();
			JSONArray json=JSONArray.fromObject(list);
			String str=json.toString();
			response.getWriter().print(str);
		}else if("updEPCate".equals(type)){
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			String epc_name=request.getParameter("className");
			String parentId=request.getParameter("parentId");
			int epc_id2=0;
			if(parentId!=null)
				epc_id2=Integer.parseInt(parentId);
			EProduct_Category category=new EProduct_Category();
			category.setEpc_id(epc_id);
			category.setEpc_name(epc_name);
			category.setEpc_id2(epc_id2);
			cateBiz.updProCate(category);
			response.sendRedirect("manage/manage-result.jsp");
		}else if("addEPCate".equals(type)){
			String epc_name=request.getParameter("className");
			int    epc_id2=Integer.parseInt(request.getParameter("parentId2"));
			EProduct_Category category=new EProduct_Category();
			category.setEpc_name(epc_name);
			category.setEpc_id2(epc_id2);
			System.out.println(cateBiz.addProCate(category));
			response.sendRedirect("manage/manage-result.jsp");
		}else if("delEPCate".equals(type)){
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			cateBiz.delProCate(epc_id);
			response.sendRedirect("manage/manage-result.jsp");
		}
		
	}

}

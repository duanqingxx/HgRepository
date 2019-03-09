package com.duan.c.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duan.m.biz.EOrderBiz;
import com.duan.m.biz.EOrderDetailBiz;
import com.duan.m.biz.EProductBiz;
import com.duan.m.biz.impl.EOrderBizImpl;
import com.duan.m.biz.impl.EOrderDetailBizImpl;
import com.duan.m.biz.impl.EProductBizImpl;
import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.Page;

/**
 * Servlet implementation class EOrderDetailServlet
 */
@WebServlet("/EOrderDetailServlet")
public class EOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EOrderDetailServlet() {
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
		EOrderBiz     ordBiz=new EOrderBizImpl();
		
		if("eodList".equals(type)){
			int pageNo=1;
			String pageIndex=request.getParameter("pageIndex");
			if(pageIndex!=null)
				pageNo=Integer.parseInt(pageIndex);			
			Page<EOrder> page=ordBiz.findEOrderPage(pageNo);
			request.setAttribute("eodPage", page);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("manage/order.jsp");
			dispatcher.forward(request, response);
		}
		else if("updEo".equals(type)){
			boolean b=false;
			int  eo_id=Integer.parseInt(request.getParameter("eo_id"));
			int eo_status=Integer.parseInt(request.getParameter("eo_status"));
			b=ordBiz.updEOrder(eo_id, eo_status);
			response.getWriter().print(b);
		}
		else if("findOrder".equals(type)){
			String tempId=request.getParameter("entityId");
			String userName=request.getParameter("userName");
			if("".equals(userName)&&"".equals(tempId)){
				response.sendRedirect("EOrderDetailServlet?type=eodList&pageIndex=1");
			}else{
			int entityId=Integer.parseInt(tempId);
			Page<EOrder> page=ordBiz.findEOrderPage2(entityId,userName);
			request.setAttribute("eodPage", page);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("manage/order.jsp");
			dispatcher.forward(request, response);	
			}
		}
		
	}

}

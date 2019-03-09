package com.duan.c.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.duan.m.entity.UserInfo;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class EProductBuyServlet
 */
@WebServlet("/EProductBuyCarServlet")
public class EProductBuyCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EProductBuyCarServlet() {
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
		EProductBiz proBiz=new EProductBizImpl();
		EOrderBiz     ordBiz=new EOrderBizImpl();
		EOrderDetailBiz eodBiz=new EOrderDetailBizImpl();
		
		if("buyProduct".equals(type)){
			String temp_ep_id=request.getParameter("ep_id");
			EProduct product=new EProduct();
			product.setEp_id(Integer.parseInt(temp_ep_id));
			proBiz.finEProduct(product);
			List<EProduct> buyList=buyList=new ArrayList<EProduct>();
			Map<String,Integer> map=new HashMap<String,Integer>();	
			buyList.add(product);
			map.put(temp_ep_id, 1);
			request.getSession().setAttribute("BuyCarList", buyList);
			request.getSession().setAttribute("BuyCarNum",map);
			response.sendRedirect("address.jsp");
		}
		else if("buyProductCar1".equals(type)){
			String temp_ep_id=request.getParameter("ep_id");
			EProduct product=new EProduct();
			product.setEp_id(Integer.parseInt(temp_ep_id));
			proBiz.finEProduct(product);
			List<EProduct> buyList=(List<EProduct>) request.getSession().getAttribute("BuyCarList");
			HashMap<String,Integer> map= (HashMap<String, Integer>) request.getSession().getAttribute("BuyCarNum");
			if(map.get(temp_ep_id)!=null){
				map.put(temp_ep_id, map.get(temp_ep_id)+1);
			}
			else{
			buyList.add(product);
			map.put(temp_ep_id, 1);
			}
			response.sendRedirect("shopping.jsp");
		}
		else if("buyProductCar2".equals(type)){
			String[]  productId=request.getParameterValues("productId");
			String[] prodcutNum=request.getParameterValues("number");
			List<EProduct> buyList=buyList=new ArrayList<EProduct>();
			Map<String,Integer> map=new HashMap<String,Integer>();	
			for (int i = 0; i < productId.length; i++) {
				EProduct product=new EProduct();
				product.setEp_id(Integer.parseInt(productId[i]));
				proBiz.finEProduct(product);
				buyList.add(product);
				map.put(productId[i],Integer.parseInt(prodcutNum[i]));
			}
			request.getSession().setAttribute("BuyCarList", buyList);
			request.getSession().setAttribute("BuyCarNum",map);
			response.sendRedirect("address.jsp");
		}
		else if("settleAccounts".equals(type)){
			String address=request.getParameter("address");
			UserInfo user=(UserInfo) request.getSession().getAttribute("user");
			List<EProduct> buyList=(List<EProduct>) request.getSession().getAttribute("BuyCarList");
			HashMap<String,Integer> map= (HashMap<String, Integer>) request.getSession().getAttribute("BuyCarNum");
			float countPrice=0.0f;
			for (int i = 0; i < buyList.size(); i++) {
				int num=map.get(String.valueOf(buyList.get(i).getEp_id()));
				countPrice+=buyList.get(i).getEp_price()*num;
			}
			countPrice=(float)(Math.round(countPrice*100)/100.0);
			EOrder order=new EOrder();
			order.setEo_user_id(user.getEu_user_id());
			order.setEo_user_name(user.getEu_user_name());
			order.setEo_user_address(address);
			order.setEo_cost(countPrice);
			order.setEo_status(1);
			ordBiz.addEOrder(order);
			for (int i = 0; i < buyList.size(); i++) {
				int num=map.get(String.valueOf(buyList.get(i).getEp_id()));
				float price=0.0f;
				price=num*buyList.get(i).getEp_price();
				EOrderDetail eod=new EOrderDetail();
				eod.setEo_id(order.getEo_id());
				eod.setEp_id(buyList.get(i).getEp_id());
				eod.setEod_quantity(num);
				eod.setEod_cost(price);
				eodBiz.addEOrderDetail(eod);
				proBiz.updEProduct2(eod.getEp_id(), num);
			}
			buyList=new ArrayList<EProduct>();
			request.getSession().setAttribute("BuyCarList", buyList);
			map=new HashMap<String,Integer>();
			request.getSession().setAttribute("BuyCarNum",map);
			response.sendRedirect("shopping-result.jsp");
		}
		else if("indexCar".equals(type)){
			List<EProduct> buyList=(List<EProduct>) request.getSession().getAttribute("BuyCarList");
			JSONArray  json=JSONArray.fromObject(buyList);
			String str=json.toString();
			response.getWriter().print(str);
		}
	}

}

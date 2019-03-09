package com.duan.c.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.duan.m.biz.EProCateBiz;
import com.duan.m.biz.EProductBiz;
import com.duan.m.biz.impl.EProCateBizImpl;
import com.duan.m.biz.impl.EProductBizImpl;
import com.duan.m.entity.EProduct;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.Page;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class EProductServlet
 */
@WebServlet("/EProductServlet")
public class EProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EProductServlet() {
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
		EProductBiz   proBiz=new EProductBizImpl();
		EProCateBiz  cateBiz=new EProCateBizImpl();
		if("proList".equals(type)){
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EProduct> page=proBiz.findEProductPage(pageNo);
			request.setAttribute("proPage", page);
			RequestDispatcher dispatcher=request.getRequestDispatcher("manage/product.jsp");
			dispatcher.forward(request, response);
			}
		else if("proList2".equals(type)){
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EProduct> page=proBiz.findEProductPage2(pageNo,epc_id);
			EProduct_Category cate2=cateBiz.findProCate(epc_id);
			EProduct_Category cate1=cateBiz.findProCate(cate2.getEpc_id2());
			request.setAttribute("proPage2", page);
			request.setAttribute("proCate1", cate1);
			request.setAttribute("proCate2", cate2);
			RequestDispatcher dispatcher=request.getRequestDispatcher("product-list.jsp");
			dispatcher.forward(request, response);
			}
		else if("proList3".equals(type)){
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			int pageNo=1;
			String currPageNo=request.getParameter("pageIndex");
			if(currPageNo!=null)
				pageNo=Integer.parseInt(currPageNo);
			Page<EProduct> page=proBiz.findEProductPage3(pageNo,epc_id);
			EProduct_Category cate2=cateBiz.findProCate(epc_id);
//			EProduct_Category cate1=cateBiz.findProCate(cate2.getEpc_id2());
			EProduct_Category cate1=new EProduct_Category();
			request.setAttribute("proPage2", page);
			request.setAttribute("proCate1", cate2);
			request.setAttribute("proCate2", cate1);
			RequestDispatcher dispatcher=request.getRequestDispatcher("product-list.jsp");
			dispatcher.forward(request, response);
			}
		else if("detailPro".equals(type)){
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			EProduct product=new EProduct();
			product.setEp_id(ep_id);
			proBiz.finEProduct(product);
			request.setAttribute("product", product);
			EProduct_Category cate2=cateBiz.findProCate(epc_id);
			EProduct_Category cate1=cateBiz.findProCate(cate2.getEpc_id2());
			request.setAttribute("proCate1", cate1);
			request.setAttribute("proCate2", cate2);
			
			Cookie []cookies = request.getCookies();
	        List<Cookie> productCookies = new ArrayList<Cookie>();
	        Cookie tempCookie = null;
            for(Cookie c :cookies){
                String cookieName = c.getName();
                if(cookieName.startsWith("CHANCE_PRODUCT")){
                	productCookies.add(c);
                }
                if(c.getValue().equals(ep_id))
                	tempCookie=c;
                }
	        if(productCookies.size() >= 5 && tempCookie == null){
	            tempCookie = productCookies.get(0);
	            }
	        if(tempCookie != null){
	            tempCookie.setMaxAge(0);
	            response.addCookie(tempCookie);
	            }
	        String ep_name=URLEncoder.encode(product.getEp_name(),"utf-8");
	        Cookie cookie = 
	        		new Cookie("CHANCE_PRODUCT"+ep_id,String.valueOf(ep_id)
	        				+","+ep_name+"|"+String.valueOf(epc_id));
	        response.addCookie(cookie);
		    
			RequestDispatcher dispatcher=request.getRequestDispatcher("product-view.jsp");
			dispatcher.forward(request, response);
			}
		else if("hotPro".equals(type)){
			List<EProduct> list=proBiz.findHotProduct();
			JSONArray json=JSONArray.fromObject(list);
			String str=json.toString();
			response.getWriter().print(str);
		}
	    else if("addPro".equals(type)){
				String        ep_name = null;
				String ep_description = null;
				int            epc_id = 0;
				int           epc_id2 = 0;
				float        ep_price = 0;
				int          ep_stock = 0;
				String   ep_file_name = null;
			
			String uploadFileName = "";	//上传的文件名
			String fieldName = "";	//表单字段元素的name属性值
			//请求信息中的内容是否是multipart类型
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			//上传文件的存储路径（服务器文件系统上的绝对文件路径）
			String strUploadFilePath = request.getSession().getServletContext().getRealPath("images/product");
			File uploadFilePath = new File(strUploadFilePath);
			if(!uploadFilePath.exists()){
				uploadFilePath.mkdirs();
			}
			if(isMultipart){
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("utf-8");
				try {
					//解析form表单中所有文件
					List<FileItem> items = upload.parseRequest(request);
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) {   //依次处理每个文件
						FileItem item = iter.next();
						if (item.isFormField()){  //普通表单字段
							fieldName = item.getFieldName();   //表单字段的name属性值
							if (fieldName.equals("productName")){
								ep_name=item.getString("utf-8");
							}
							if (fieldName.equals("productDetail")){
								ep_description=item.getString("utf-8");
							}
							if (fieldName.equals("parentId")){
								epc_id=Integer.parseInt(item.getString("utf-8"));
							}
							if (fieldName.equals("productPrice")){
								ep_price=Float.parseFloat(item.getString("utf-8"));
							}
							if (fieldName.equals("productNumber")){
								ep_stock=Integer.parseInt(item.getString("utf-8"));
							}
						}else{  //文件表单字段
							fieldName = item.getFieldName();
							if("photo".equals(fieldName)){
							String fileName = item.getName();
							if (fileName != null && !fileName.equals("")) {
								File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
								item.write(saveFile);
								System.out.println("服务器端文件名：" + request.getContextPath() + "/images/product/" + saveFile.getName());
								String url=request.getContextPath() + "/images/product/" + saveFile.getName();
								String pic=saveFile.getName();
								ep_file_name=pic.substring(0,pic.indexOf("."));
							}}
							if("photo2".equals(fieldName)){
								String fileName = item.getName();
								if (fileName != null && !fileName.equals("")) {
									File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
									item.write(saveFile);
								}}
							if("photo3".equals(fieldName)){
								String fileName = item.getName();
								if (fileName != null && !fileName.equals("")) {	
									File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
									item.write(saveFile);
								}}
							
						}
						}
					} catch (Exception e) {e.printStackTrace();}			
				}
					EProduct product=new EProduct();
					product.setEp_name(ep_name);
					product.setEp_description(ep_description);
					EProduct_Category cate=cateBiz.findProCate(epc_id);
					product.setEpc_id(epc_id);
					product.setEpc_id2(cate.getEpc_id2());
					product.setEp_price(ep_price);
					product.setEp_stock(ep_stock);
					product.setEp_file_name(ep_file_name);
					proBiz.addEProduct(product);
					response.sendRedirect("manage/manage-result.jsp");
					}
			
		else if("delPro".equals(type)){
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));
			proBiz.delEProduct(ep_id);
			response.sendRedirect("manage/manage-result.jsp");
		}
		else if("updPro".equals(type)){
			int             ep_id = Integer.parseInt(request.getParameter("ep_id"));
			String        ep_name = null;
			String ep_description = null;
			int            epc_id = 0;
			int           epc_id2 = 0;
			float        ep_price = 0;
			int          ep_stock = 0;
			String   ep_file_name = null;
		
		String uploadFileName = "";	//上传的文件名
		String fieldName = "";	//表单字段元素的name属性值
		//请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		//上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String strUploadFilePath = request.getSession().getServletContext().getRealPath("images/product");
		File uploadFilePath = new File(strUploadFilePath);
		if(!uploadFilePath.exists()){
			uploadFilePath.mkdirs();
		}
		if(isMultipart){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			try {
				//解析form表单中所有文件
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {   //依次处理每个文件
					FileItem item = iter.next();
					if (item.isFormField()){  //普通表单字段
						fieldName = item.getFieldName();   //表单字段的name属性值
						if (fieldName.equals("productName")){
							ep_name=item.getString("utf-8");
						}
						if (fieldName.equals("productDetail")){
							ep_description=item.getString("utf-8");
						}
						if (fieldName.equals("parentId2")){
							epc_id=Integer.parseInt(item.getString("utf-8"));
						}
						if (fieldName.equals("productPrice")){
							ep_price=Float.parseFloat(item.getString("utf-8"));
						}
						if (fieldName.equals("productNumber")){
							ep_stock=Integer.parseInt(item.getString("utf-8"));
						}
					}else{  //文件表单字段
						fieldName = item.getFieldName();
						if("photo".equals(fieldName)){
						String fileName = item.getName();
						if (fileName != null && !fileName.equals("")) {
							File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
							item.write(saveFile);
							System.out.println("服务器端文件名：" + request.getContextPath() + "/images/product/" + saveFile.getName());
							String url=request.getContextPath() + "/images/product/" + saveFile.getName();
							String pic=saveFile.getName();
							ep_file_name=pic.substring(0,pic.indexOf("."));
						}}
						if("photo2".equals(fieldName)){
							String fileName = item.getName();
							if (fileName != null && !fileName.equals("")) {
								File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
								item.write(saveFile);
							}}
						if("photo3".equals(fieldName)){
							String fileName = item.getName();
							if (fileName != null && !fileName.equals("")) {	
								File saveFile = new File(uploadFilePath, fileName.substring(fileName.lastIndexOf('\\') + 1));
								item.write(saveFile);
							}}
						
					}
					}
				} catch (Exception e) {e.printStackTrace();}			
			}
				EProduct product=new EProduct();
				product.setEp_id(ep_id);
				product.setEp_name(ep_name);
				product.setEp_description(ep_description);
				product.setEpc_id(epc_id);
				EProduct_Category cate=cateBiz.findProCate(epc_id);
				product.setEpc_id2(cate.getEpc_id2());
				product.setEp_price(ep_price);
				product.setEp_stock(ep_stock);
				product.setEp_file_name(ep_file_name);
				proBiz.updEProduct(product);
				response.sendRedirect("manage/manage-result.jsp");
		}
		
	
	}
}

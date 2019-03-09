import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duan.m.biz.ECommBiz;
import com.duan.m.biz.ENewsBiz;
import com.duan.m.biz.EOrderBiz;
import com.duan.m.biz.EOrderDetailBiz;
import com.duan.m.biz.EProCateBiz;
import com.duan.m.biz.EProductBiz;
import com.duan.m.biz.EUserBiz;
import com.duan.m.biz.UserInfoBiz;
import com.duan.m.biz.impl.ECommBizImpl;
import com.duan.m.biz.impl.ENewsBizImpl;
import com.duan.m.biz.impl.EOrderBizImpl;
import com.duan.m.biz.impl.EOrderDetailBizImpl;
import com.duan.m.biz.impl.EProCateBizImpl;
import com.duan.m.biz.impl.EProductBizImpl;
import com.duan.m.biz.impl.EUserBizImpl;
import com.duan.m.biz.impl.UserInfoBizImpl;
import com.duan.m.dao.ECommDao;
import com.duan.m.dao.ENewsDao;
import com.duan.m.dao.EOrderDao;
import com.duan.m.dao.EOrderDetailDao;
import com.duan.m.dao.EProCateDao;
import com.duan.m.dao.EProductDao;
import com.duan.m.dao.EUserDao;
import com.duan.m.dao.UserInfoDao;
import com.duan.m.dao.impl.ECommDaoImpl;
import com.duan.m.dao.impl.ENewsDaoImpl;
import com.duan.m.dao.impl.EOrderDaoImpl;
import com.duan.m.dao.impl.EOrderDetailDaoImpl;
import com.duan.m.dao.impl.EProCateDaoImpl;
import com.duan.m.dao.impl.EProductDaoImpl;
import com.duan.m.dao.impl.EUserDaoImpl;
import com.duan.m.dao.impl.UserInfoDaoImpl;
import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.Dbutils;

public class test {
public static void main(String[] args) {
	Connection conn=Dbutils.getConnection();
	UserInfoDao userDao=new UserInfoDaoImpl(conn);
	UserInfoBiz userBiz=new UserInfoBizImpl();
	ENewsDao    newsDao=new ENewsDaoImpl(conn);
	ENewsBiz    newsBiz=new ENewsBizImpl();
	EUserDao   euserDao=new EUserDaoImpl(conn);
	EUserBiz   euserBiz=new EUserBizImpl();
	ECommDao    commDao=new ECommDaoImpl(conn);
	ECommBiz    commBiz=new ECommBizImpl();
	EProCateDao  cateDao=new EProCateDaoImpl(conn); 
	EProCateBiz  cateBiz=new EProCateBizImpl();
	EProductDao   proDao=new EProductDaoImpl(conn);
	EProductBiz   proBiz=new EProductBizImpl();
	EOrderDao     ordDao=new EOrderDaoImpl(conn);
	EOrderDetailDao eodDao=new EOrderDetailDaoImpl(conn);
	EOrderBiz     ordBiz=new EOrderBizImpl();
	EOrderDetailBiz eodBiz=new EOrderDetailBizImpl();
//	System.out.println(conn);
//	UserInfo user=new UserInfo();
//	user.setEu_user_id("duan");
//	user.setEu_passWord("123456");
//	System.out.println(userDao.selectUser(user));
//	System.out.println(user.getEu_status());
//	UserInfo user=new UserInfo();
//	user.setEu_user_id("xiaobai");
//	user.setEu_user_name("小白白");
//	user.setEu_passWord("123456");
//	user.setEu_sex("F");
//	Date date=null;
//	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//	try {
//		date=format.parse("1996-02-01");
//	} catch (ParseException e) {
//		System.out.println("time translate error");
//	}
//	user.setEu_birthday(new Timestamp(date.getTime()));
//	user.setEu_indentity_code("123123123123");
//	user.setEu_mobile("123123123");
//	user.setEu_address("23wqe");
  /*user.setEu_sex("M");
	Date date=null;
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	try {
		date=format.parse("1996-06-04");
	} catch (ParseException e) {
		System.out.println("time translate error");
	}
//	System.out.println(date.toString());
	user.setEu_birthday(new Timestamp(date.getTime()));
	user.setEu_indentity_code("123123123123");
	user.setEu_email("12@qq.com");
	user.setEu_mobile("123123123");
	user.setEu_address("23wqe");
	user.setEu_login(1.2f);
	user.setEu_status(2);*/

//	System.out.println(userDao.insertUser(user));
//	System.out.println(userBiz.addUser(user));
//	System.out.println(userBiz.findUser(user));
//	System.out.println(user.getEu_birthday());
//	System.out.println(userDao.selectUserId("zhangsan"));
//	System.out.println(newsBiz.findHotNews().size());
//	System.out.println(newsDao.selectENewsDetail(3).getEn_create_time());
//	System.out.println(newsBiz.findENewsDetail(311).getEn_create_time());
	
//	System.out.println(newsDao.selectNews(2, 5).get(3).getEn_content());
//	System.out.println(newsDao.selectENewsCount());
//	System.out.println(newsBiz.findENewsPage(2).getNewsList().get(4).getEn_id());
//	System.out.println(newsDao.selectMaxId());
//	System.out.println(euserDao.selectEUser(2, 3).get(1).getEu_indentity_code());
//	System.out.println(euserDao.selectEUserCount());
//	System.out.println(euserBiz.findEUserPage(1).getNewsList().get(1).getEu_indentity_code());
//	System.out.println(euserBiz.updEUser(user,"xiaobai1"));
//	System.out.println(commDao.selectCommCount());
//	System.out.println(commDao.selectCommList(2, 4).get(1).getEc_content());
//	System.out.println(commBiz.findECommPage(1).getNewsList().get(2).getEc_content());
//	System.out.println(commDao.selectMaxId());
//	System.out.println(commDao.deleteComm(5));
//	EComment comment=new EComment();
//	comment.setEc_id(5);
//	comment.setEc_content("留言测试5");
//	comment.setEc_create_time(new Timestamp(new Date().getTime()));
//	comment.setEc_nick_name("zhangsan");
//	System.out.println(commDao.insertComm(comment));
//	System.out.println(cateDao.selectEProCateList(1, 2).get(1).getEpc_id2());
//	System.out.println(cateDao.selectEProCateCount());
//	System.out.println(cateBiz.findProCatePage(1).getNewsList().get(0).getEpc_name());
//	System.out.println(cateBiz.findProCateOne().size());
	EProduct_Category category=new EProduct_Category();
	category.setEpc_id(cateDao.selectMaxId()+1);
	category.setEpc_name("显卡");
	category.setEpc_id2(3);
//	System.out.println(cateDao.updateEProCate(category));
//	System.out.println(cateDao.selectMaxId());
//	System.out.println(cateDao.insertEProCate(category));
	float num=12.2234f;
//	System.out.println(num);
//	System.out.println(prodDao.selectMaxId());
//	System.out.println(prodDao.selectEProductList(5, 10).get(1).getEp_description());
//	System.out.println(prodDao.selectEProductCount());
//	System.out.println(prodDao.deleteEProduct(31));
	EProduct product=new EProduct();
	product.setEp_id(10);
//	product.setEp_name("ep_name12222");
//	product.setEp_description("ep_description");
//	product.setEp_price(31.6f);
//	product.setEp_stock(231);
//	product.setEpc_id(1);
//	product.setEpc_id2(1);
//	product.setEp_file_name("ep_file_name");
//	System.out.println(prodDao.insertEProduct(product));
//	System.out.println(prodDao.updateEProduct(product));
//	System.out.println(prodDao.deleteEProduct(31));
//	System.out.println(proBiz.findEProductPage(2).getNewsList().get(1).getEp_description());
//	System.out.println(proBiz.addEProduct(product));
//	System.out.println(proBiz.delEProduct(31));
//	System.out.println(proBiz.updEProduct(product));
//	System.out.println(cateDao.selectAllEProCate().get(0).getEpc_name());
//	System.out.println(cateBiz.findAllProCate().get(0).getEpc_id2());
	String pic="1,9.jpg|2";
//	System.out.println(pic);
//	String pic1=pic.substring(0, pic.indexOf(","));
//	String pic2=pic.substring(pic.indexOf(",")+1,pic.indexOf("|"));
//	String pic3=pic.substring(pic.indexOf("|")+1);
//	System.out.println(pic1);
//	System.out.println(pic2);
//	System.out.println(pic3);
//	System.out.println(cateBiz.findProCate(7).getEpc_name());
//	System.out.println(proDao.selectEProduct(product));
//	System.out.println(product.getEp_description());
//	System.out.println(proBiz.findHotProduct().get(0).getEp_description());
//	System.out.println(proBiz.findHotProduct().get(0).getEpc_id());
//	System.out.println(proBiz.findEProductPage2(1, 6).getNewsList().get(0).getEp_description());
//	System.out.println(proDao.selectEProductCount3(1));
//	System.out.println(proBiz.findEProductPage3(1, 1).getTotalPageCount());
//	System.out.println(ordDao.selectMaxId());
	EOrder order=new EOrder();
//	order.setEo_id(ordDao.selectMaxId()+1);
	order.setEo_user_id("zhangsan");
	order.setEo_user_name("张三");
	order.setEo_user_address("吉林");
//	order.setEo_create_time(new Timestamp(new Date().getTime()));
	order.setEo_cost(45.6f);
	order.setEo_status(1);
	EOrderDetail eod=new EOrderDetail();
//	eod.setEod_id(eodDao.selectMaxId()+1);
	eod.setEo_id(ordDao.selectMaxId());
	eod.setEp_id(1);
	eod.setEod_quantity(2);
	eod.setEod_cost(85.8f);
//	System.out.println(ordDao.insertOrder(order));
//	System.out.println(eodDao.insertOrderDetail(eod));
//	System.out.println(ordBiz.addEOrder(order));
//	System.out.println(eodBiz.addEOrderDetail(eod));
//	System.out.println(ordDao.selectMaxId());
//	Map<String, Integer> map=new HashMap<String,Integer>();
//	map.put("23", 111);
//	System.out.println(map.get("232"));
//	System.out.println(eodDao.selectEODCount());
//	System.out.println(eodBiz.findEODPage(1).getNewsList().get(2).getEo_id());
//	EOrder order1=new EOrder();
//	order1.setEo_id(2);
//	System.out.println(ordBiz.finEOrder(order1));
//	System.out.println(order1.getEo_user_name());
//	System.out.println(ordBiz.updEOrder(1, 1));
//	ArrayList<EOrder> orderList=new ArrayList<EOrder>();
//	HashMap<String,EProduct> productMap=new HashMap<String,EProduct>();
//	eodBiz.findEODPage(1, orderList, productMap);
//	System.out.println(productMap.size());
	List<EOrder> orderList=ordDao.selectEOrderList(10, 12);
//	System.out.println(orderList.get(0).getEodList().get(0).getProduct().getEp_name());
//	System.out.println(ordDao.selectEOrderCount());
//	System.out.println(ordBiz.findEOrderPage(4).
//			getNewsList().get(0).getEodList().get(0).getProduct().getEp_name());
//	System.out.println(ordDao.selectEOrderList2(10, "张三").get(0).getEo_cost());
	System.out.println(proBiz.updEProduct2(1, 3));
}
}

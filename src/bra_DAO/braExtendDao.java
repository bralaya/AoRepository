package bra_DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import bra_DAO.braBasicDao;
import bra_Data.braArticleData;

public class braExtendDao {
	//输出测试
	public static void main(String[] args) {
		//int i = insertArticle("3da2ssss4b03-37df-49c2-a6bc-8e623c5927a8","教你如何每晚战斗到通宵","教你如何使用Java读写文件教你如何使用Java读写文件教你如何使用Java读写文件教你如何使用Java","2018年12月21日");
		//System.out.println("返回结果："+i);
	}
	
	//修改某篇文章
	public int updateArticle(String articleid,String head,String content,String mtime){
		int i=0;
		String sql="update bralaya_article set bra_head='"+head+"',bra_mtime='"+mtime+"',bra_content='"+content+"' where bra_id='" +articleid+ "';";
		i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	//查询某篇文章
	public ArrayList<braArticleData> selectArticle(String articleid) throws Exception{
		//List<braArticleData>list = new ArrayList<braArticleData>();
		//这句话都没用到，之前是用来干什么的？
		
		String sql ="select * from bralaya_article where bra_id='" + articleid +  " ' ";
		ResultSet rs = braBasicDao.executeQuery(sql);//执行
		braArticleData articledata = null;
		ArrayList<braArticleData> articleList = new ArrayList<braArticleData>();
		try {
			while(rs.next()){
				//新建一个article数据层的类
				articledata = new braArticleData();
				articledata.setId(rs.getString("bra_id"));
				articledata.setHead(rs.getString("bra_head"));
				articledata.setContent(rs.getString("bra_content"));
				articleList.add(articledata);
			}
			rs.close();
			return articleList;
		}catch(Exception e){
			e.printStackTrace();
		}
		braBasicDao.close();
		return null;
	}
	
	//删除某篇文章
	public static int delArticle(String articleid){
		int i=0;
		String sql = "delete from bralaya_article where bra_id='" + articleid + "'";
		i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	
	//发表文章执行插入文字内容
	/*
	 * 获得返回结果并返回
	 * 
	 */
	public static int insertArticle(String id,String head,String content,String stime){
		String sql = "insert into bralaya_article(bra_id,bra_head,bra_content,bra_createtime,bra_mtime)values"
				+"('" + id + "','" + head + "','"+content+"','"+stime+"','"+stime+"')";
		//这边我觉得不能随便空格吧'ssss'和'ssss '是有区别的?
		int i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	//查询返回文章列表
	/*
	 * 返回内容：文章id、文章标题、创建时间、修改时间、
	 */
	public ArrayList<braArticleData> queryAllArticle() throws Exception {
			
			ArrayList<braArticleData> articleArrayList = new ArrayList<braArticleData>();
			
			String sql = "select * from bralaya_article order by bra_mtime desc";
			
			ResultSet rs = braBasicDao.executeQuery(sql);
			
			braArticleData articleMessage = null;
				//
				try {
					while(rs.next()){
						//new 一个VO层临时存放从数据库查询到的数据
						articleMessage = new braArticleData();
						articleMessage.setId(rs.getString("bra_id"));
						articleMessage.setHead(rs.getString("bra_head"));
						articleMessage.setContent(rs.getString("bra_content"));
						articleMessage.setCreatetime(rs.getString("bra_createtime"));
						articleMessage.setMtime(rs.getString("bra_mtime"));
						//放入ArrayList数组
						articleArrayList.add(articleMessage);
					}
					rs.close();
					return articleArrayList;
		
				}catch (Exception e) {
					e.printStackTrace();
					
				}finally {
					
				}
				
				return null;
	}
	
		
	
	
	
	//判断账户是否存在
	
		
	//登入校验
	/*
	 * 因为是可以手机/邮箱登入的，所以到时那边传递来时要夹一个参数让这里知道传递来的是手机还是邮箱
	 * select xxx from xxxx where xx= xx=;
	 * 然后将结果返回，没查到返回一个标识表示手机或密码错误
	 */
//	public ArrayList<braArticleData> LoginCheck(String account,String password,boolean isPhone) throws Exception {
//		String sql=null;
//		if(isPhone==true) {
//			sql = "select * from moocuser where mPhone='" + account + "' and mPasswd ='" + password +"'";
//			
//		}else {
//			sql = "select * from moocuser where mEmail='" + account + "' and mPasswd ='" + password +"'";
//			
//		}
//		//上面这段还可以再修改
//		ResultSet rs = braBasicDao.executeQuery(sql);
//		ArrayList<braArticleData> userList = new ArrayList<braArticleData>();
//		braArticleData userData = null;
//		try {
//			while(rs.next()){
//				userData = new braArticleData();
//				userData.setmUsername(rs.getString("mUserName"));
//				userData.setmUserid(rs.getString("mUserid"));
//				//添加到arraylist里
//				userList.add(userData);
//			}
//			rs.close();
//			return userList;
//
//		}catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//		}
//		System.out.println("输出null");//???
//		return null;
//	}
}

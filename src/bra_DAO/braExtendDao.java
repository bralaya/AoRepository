package bra_DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import bra_DAO.braBasicDao;
import bra_Data.braArticleData;

public class braExtendDao {
	//�������
	public static void main(String[] args) {
		//int i = insertArticle("3da2ssss4b03-37df-49c2-a6bc-8e623c5927a8","�������ÿ��ս����ͨ��","�������ʹ��Java��д�ļ��������ʹ��Java��д�ļ��������ʹ��Java��д�ļ��������ʹ��Java","2018��12��21��");
		//System.out.println("���ؽ����"+i);
	}
	
	//�޸�ĳƪ����
	public int updateArticle(String articleid,String head,String content,String mtime){
		int i=0;
		String sql="update bralaya_article set bra_head='"+head+"',bra_mtime='"+mtime+"',bra_content='"+content+"' where bra_id='" +articleid+ "';";
		i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	//��ѯĳƪ����
	public ArrayList<braArticleData> selectArticle(String articleid) throws Exception{
		//List<braArticleData>list = new ArrayList<braArticleData>();
		//��仰��û�õ���֮ǰ��������ʲô�ģ�
		
		String sql ="select * from bralaya_article where bra_id='" + articleid +  " ' ";
		ResultSet rs = braBasicDao.executeQuery(sql);//ִ��
		braArticleData articledata = null;
		ArrayList<braArticleData> articleList = new ArrayList<braArticleData>();
		try {
			while(rs.next()){
				//�½�һ��article���ݲ����
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
	
	//ɾ��ĳƪ����
	public static int delArticle(String articleid){
		int i=0;
		String sql = "delete from bralaya_article where bra_id='" + articleid + "'";
		i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	
	//��������ִ�в�����������
	/*
	 * ��÷��ؽ��������
	 * 
	 */
	public static int insertArticle(String id,String head,String content,String stime){
		String sql = "insert into bralaya_article(bra_id,bra_head,bra_content,bra_createtime,bra_mtime)values"
				+"('" + id + "','" + head + "','"+content+"','"+stime+"','"+stime+"')";
		//����Ҿ��ò������ո��'ssss'��'ssss '���������?
		int i = braBasicDao.executeUpdate(sql);
		braBasicDao.close();
		return i;
	}
	
	//��ѯ���������б�
	/*
	 * �������ݣ�����id�����±��⡢����ʱ�䡢�޸�ʱ�䡢
	 */
	public ArrayList<braArticleData> queryAllArticle() throws Exception {
			
			ArrayList<braArticleData> articleArrayList = new ArrayList<braArticleData>();
			
			String sql = "select * from bralaya_article order by bra_mtime desc";
			
			ResultSet rs = braBasicDao.executeQuery(sql);
			
			braArticleData articleMessage = null;
				//
				try {
					while(rs.next()){
						//new һ��VO����ʱ��Ŵ����ݿ��ѯ��������
						articleMessage = new braArticleData();
						articleMessage.setId(rs.getString("bra_id"));
						articleMessage.setHead(rs.getString("bra_head"));
						articleMessage.setContent(rs.getString("bra_content"));
						articleMessage.setCreatetime(rs.getString("bra_createtime"));
						articleMessage.setMtime(rs.getString("bra_mtime"));
						//����ArrayList����
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
	
		
	
	
	
	//�ж��˻��Ƿ����
	
		
	//����У��
	/*
	 * ��Ϊ�ǿ����ֻ�/�������ģ����Ե�ʱ�Ǳߴ�����ʱҪ��һ������������֪�������������ֻ���������
	 * select xxx from xxxx where xx= xx=;
	 * Ȼ�󽫽�����أ�û�鵽����һ����ʶ��ʾ�ֻ����������
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
//		//������λ��������޸�
//		ResultSet rs = braBasicDao.executeQuery(sql);
//		ArrayList<braArticleData> userList = new ArrayList<braArticleData>();
//		braArticleData userData = null;
//		try {
//			while(rs.next()){
//				userData = new braArticleData();
//				userData.setmUsername(rs.getString("mUserName"));
//				userData.setmUserid(rs.getString("mUserid"));
//				//��ӵ�arraylist��
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
//		System.out.println("���null");//???
//		return null;
//	}
}

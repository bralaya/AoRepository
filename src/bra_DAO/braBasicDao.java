package bra_DAO;

//java��sql������
import java.sql.*;

public class braBasicDao {
	//�������
	public static void main(String[] args) {
			
	}
	//����������
	String driver = "com.mysql.cj.jdbc.Driver";
		
	//URLָ��Ҫ���ʵ����ݿ���,�������Ҫ��̬�޸��ˡ���Ϊ������������Ҳ��Ҫ������
	String url = "jdbc:mysql://localhost:3306/ghostyblog?serverTimezone=UTC&useSSL=false";
	String dbUser = "root";
	String dbPasswd = "fuckMysql007";
	//�������ݿ����Ӷ���
	private static Connection conn = null;	
	
	//�������ӵķ���
	private braBasicDao(){
		try{
			if(conn == null ){
				//Ϊ�ռ�����������
				Class.forName(driver).newInstance();
				//�������ݿ�
				conn = DriverManager.getConnection(url,dbUser,dbPasswd);
			}else
				return ;
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	//��ѯ����---���ز�ѯ�����...
	static ResultSet executeQuery(String sql){
		try{
			if(conn ==null)
				//�������ӷ���
				new braBasicDao();
			return conn.createStatement().executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//��̬���·���---��ִ���޸Ĳ���
	public static int executeUpdate(String sql){
		try{
			if(conn == null)
				new braBasicDao();
			Statement stmt = conn.createStatement();
			System.out.println("���³ɹ�");
			return stmt.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("����ʧ��");
			return -1;
		}
	}
	
	//�ر����ݿ����ӵķ���
	public static void close(){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				conn = null;
			}
	}
	
}

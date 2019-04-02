package bra_DAO;

//java的sql操作包
import java.sql.*;

public class braBasicDao {
	//输出测试
	public static void main(String[] args) {
			
	}
	//驱动程序名
	String driver = "com.mysql.cj.jdbc.Driver";
		
	//URL指向要访问的数据库名,这里就需要动态修改了。因为加了密码这里也需要密码了
	String url = "jdbc:mysql://localhost:3306/ghostyblog?serverTimezone=UTC&useSSL=false";
	String dbUser = "root";
	String dbPasswd = "fuckMysql007";
	//定义数据库连接对象
	private static Connection conn = null;	
	
	//建立连接的方法
	private braBasicDao(){
		try{
			if(conn == null ){
				//为空加载驱动程序
				Class.forName(driver).newInstance();
				//连接数据库
				conn = DriverManager.getConnection(url,dbUser,dbPasswd);
			}else
				return ;
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	//查询方法---返回查询结果集...
	static ResultSet executeQuery(String sql){
		try{
			if(conn ==null)
				//调用连接方法
				new braBasicDao();
			return conn.createStatement().executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//静态更新方法---是执行修改操作
	public static int executeUpdate(String sql){
		try{
			if(conn == null)
				new braBasicDao();
			Statement stmt = conn.createStatement();
			System.out.println("更新成功");
			return stmt.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("更新失败");
			return -1;
		}
	}
	
	//关闭数据库连接的方法
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

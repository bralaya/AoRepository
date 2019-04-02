package bra_Servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


import bra_DAO.braExtendDao;
import bra_Data.braArticleData;
import bra_Generate.braGenerateHTML;

import java.util.UUID;

@WebServlet("/braServlet")
public class braServlet extends HttpServlet {
	
	public static void main(String[] args) throws Exception {
//		File newFile = new File(".");
//		newFile.createNewFile();
//		//输出文件所在绝对路径
//		System.out.println(newFile.getAbsolutePath());
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset  = UTF-8");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset  = UTF-8");//这里不写会如何？
		//获取传递过来的方法选择参数
		String functionChoise = request.getParameter("functionChoise");
		//执行登入校验方法
		if (functionChoise.equals("EnterCheck")) {
			//there (try..catch) i remember copy from teacher,didn't know why
			try {
				
				EnterCheck(request,response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行查询文章列表(后台）
		if (functionChoise.equals("selectArticleMessage")) {
			try {
				
				selectArticleMessage(request,response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行发布文章（后台)
		if(functionChoise.equals("publicArticle")){
			try {
				
				publicArticle(request,response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行删除文章（后台）
		if(functionChoise.equals("delArticle")) {
			try {
				delArticle(request,response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行获取某篇文章内容（后台）
		if(functionChoise.equals("selectSingleArticle")) {
			try {
				selectSingleArticle(request,response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行文章修改操作（后台）
		if(functionChoise.equals("alterArticle")) {
			try {
				alterArticle(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//执行退出操作
		if(functionChoise.equals("loginOut")) {
			try {
				loginOut(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//测试项
//		if(functionChoise.equals("testGetPath")) {
//			try {
//				testGetPath(request,response);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	//执行测试项
//	public void testGetPath(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		File newFile = new File("/linuxtestfile.kkk");
//		newFile.createNewFile();
//		//输出文件所在绝对路径
//		PrintWriter out = response.getWriter();
//		out.println("测试项："+newFile.getAbsolutePath());
//		
//	}
	
	//执行修改文章内容操作
	public void alterArticle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//这里得先得到id啊
		String articleId = request.getParameter("articleId");
		//获取文章标题
		String articleHead = request.getParameter("articleHead");
		//获取文章内容
		String articleContent = request.getParameter("articleContent");
		//生成修改时间
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String stime = sdf.format(new Date());
		//调用扩展DAO执行修改文章内容方法并获得返回内容
		braExtendDao braextendDao  = new braExtendDao();
		int i=braextendDao.updateArticle(articleId, articleHead, articleContent, stime);
		//返回json数据给请求页
		PrintWriter out = response.getWriter();
		if(i==1) {
			//执行修改成功
			out.println("{\"success\":1}");
			braGenerateHTML bragenerateHTML = new braGenerateHTML();
			//执行重新读取首页内容方法
			bragenerateHTML.generateHomePage();
			//执行刷新文章页文件
			bragenerateHTML.generateArticlePage(articleId, articleHead,articleContent);
		}else {
			out.println("{\"success\":0}");
		}
	}
	
	//查询某篇文章内容并返回
	/*
	 * json格式{\"success\":1,\"head\":\"sssss\",\"content\":\"sss\"}
	 */
	public void selectSingleArticle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取文章id
		String articleid = request.getParameter("articleid");
		//执行扩展DAO进行查询并返回结果
		braExtendDao braextendDao  = new braExtendDao();
		ArrayList articleMessage = braextendDao.selectArticle(articleid);
		System.out.println("获取结果："+articleMessage.size());
		//获取返回结果，并构造成json数据传送给请求页
		//若不为空，有获取到就打印xxx，否则打印xxx
		PrintWriter out = response.getWriter();
		if(articleMessage.size()==1) {
			out.print("{\"success\":1,");
			braArticleData articleData = null;
			for(int i=0;i<articleMessage.size();i++) {
				articleData = (braArticleData)articleMessage.get(i);
				out.print("\"head\":\"");
				out.print(articleData.getHead()+"\",\"content\":\"");
				out.println(articleData.getContent()+"\"}");
			}
			
		}else {
			out.println("{\"success\":0}");
		}
	}
	
	//删除文章
	public void delArticle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取文章id
		String articleid = request.getParameter("articleid");
		//执行删除DAO
		int i=braExtendDao.delArticle(articleid);
		//得到返回结果并输出到网络给请求页
		PrintWriter out = response.getWriter();
		if(i==1) {
			//删除成功
			out.println("{\"success\":1}");
			braGenerateHTML bragenerateHTML = new braGenerateHTML();
			//执行重新读取首页内容方法
			bragenerateHTML.generateHomePage();
			//执行删除文章页文件
			bragenerateHTML.delArticlePage(articleid);
		}else {
			out.println("{\"success\":0}");
		}
	}
		
		
		//发表文章（后台）
		/*
		 * 传递进来的参数 文章标题，文章内容
		 * 这里在生成随机id，创建时间(修改时间共用)
		 * //获取文章标题
			//获取文章内容
			//生成随机id
			//生成时间
			//调用扩展DAO的插入数据方法获得返回结果
			//返回json数据给请求页
		//发表文章成功，更新到首页
		// 读取首页模板上半部分内容，然后输出到首页文件
		// 重新读取数据库里的文章标题列表输出到首页文件
		// 读取首页模板下部分内容，输出到首页文件
		//生成文章页
		// 读取文章页模板上半部分，并输出到文章页文件
		// 输出文章标题，内容到文章页文件
		// 读取文章页模板下部分，输出到文章页文件
		// 这个可能可以独立成一个方法。
		 * 
	     * 
		 * 
		 */
		public void publicArticle(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			//获取文章标题
			String articleHead = request.getParameter("articleHead");
			//获取文章内容
			String articleContent = request.getParameter("articleContent");
			System.out.println(articleContent);
			//生成随机id
			String articheId = UUID.randomUUID().toString();
			//生成时间
			SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
			String stime = sdf.format(new Date());
			
			//调用扩展DAO的插入数据方法获得返回结果
			int i=braExtendDao.insertArticle(articheId, articleHead, articleContent, stime);
			
			//返回json数据给请求页
			PrintWriter out = response.getWriter();
			if(i==1) {
				out.println("{\"success\":1,}");
				
//				File newFile = new File("linuxtestfile.rio");
//				newFile.createNewFile();
//				//输出文件所在绝对路径
//				out.println("{\"path\":\""+newFile.getAbsolutePath()+"\"}");
				
				//*发表文章成功，执行更新到首页方法
				braGenerateHTML braGenerateHTML = new braGenerateHTML();
				braGenerateHTML.generateHomePage();
				//*执行生成文章页
				braGenerateHTML.generateArticlePage(articheId,articleHead,articleContent);
			}else {
				out.println("{\"success\":0}");
			}
		}
		
	
	//function need to write out there ，and don't know why should write (HttpServletRequest request, HttpServletResponse response)
	
	//查询文章列表获得文章id，文章标题，创建时间，修改时间
	/*
	 * 获取到数据后循环读取出来，直接输出html格式的数据到网络传递给ajax请求页
	 * 改列表输出了
	 *  
	 * 	文章id 文章标题 创建时间 修改时间  修改按钮 删除按钮
	 */
	public void selectArticleMessage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//new 一个ExtendDao
		braExtendDao braextendDao  = new braExtendDao();
		//执行查询方法，并获取返回结果
		ArrayList articleMessage = braextendDao.queryAllArticle();
		//然后按照html样式输出数据到网络等待ajax获取
		//System.out.println(articleMessage.size());
		braArticleData articleData = null;
		//下面是输出到网络
		PrintWriter out = response.getWriter();
		//先输出标题行
		out.println("<tr><th>文章ID</th><th>文章标题</th><th>创建时间</th><th>修改时间</th><th>修改</th><th>删除</th>");
		for(int i=0;i<articleMessage.size();i++) {
			articleData = (braArticleData)articleMessage.get(i);
			//但是感觉这种写法有点蠢，还不如直接传递json数据给xxx
			out.println("<tr>");
			out.println("<td id="+"\""+articleData.getId().substring(0, 8)+"\">"+articleData.getId()+"</td>");
			//System.out.println("<td id=\"dwadwa\">"+articleData.getId()+"</td>");
			out.println("<td><a href=\"bralaya_article/"+articleData.getId()+".html\">"+articleData.getHead()+"</a></td>");
			out.println("<td>"+articleData.getCreatetime()+"</td>");
			out.println("<td>"+articleData.getMtime()+"</td>");
			//开始动刀子( •̀ ω •́ )y
			out.println("<td><button onclick=\"AjaxFather('"+articleData.getId().substring(0, 8)+"',2)\">修改</button></td>");
			out.println("<td><button onclick=\"AjaxFather('"+articleData.getId().substring(0, 8)+"',1)\">删除</button></td>");
			out.println("</tr>");
		}
		
	}
	
	
	
	
	//登入校验方法
	/*
	 * 这里直接写死密码在这里不查询数据库了
	 */
	public void EnterCheck(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//session声明
		HttpSession session = request.getSession();
		
		//获取传入的参数:账号,密码,是否为手机号
		String passwdParameter = request.getParameter("passwd");
		
		PrintWriter out = response.getWriter();
		if(passwdParameter.equals("babynomoney")) {
			//返回成功标识并添加session
			session.setAttribute("success","true");
			out.println("{\"success\":1}");
		}else {
			//返回错误标识
			out.println("{\"success\":0}");
		}
	}
	
	//登出
	public void loginOut(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//清空session
		HttpSession session = request.getSession();
		session.invalidate();
		//返回json数据
		PrintWriter out = response.getWriter();
		out.println("{\"success\":1}");
	}
}

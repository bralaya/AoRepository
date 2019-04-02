package bra_Generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import bra_DAO.braExtendDao;
import bra_Data.braArticleData;

public class braGenerateHTML {
	
	
	//������ҳ�ļ�
	/*
	 * ����	/	ɾ��	/	�޸�	���¶�Ҫ���¸��¶�ȡ�������б���ҳ
	 * 
	 * ��ȡ��ҳģ���ϰ벿�����ݣ�Ȼ���������ҳ�ļ�
	   ���¶�ȡ���ݿ�������±����б��������ҳ�ļ�
	   ��ȡ��ҳģ���²������ݣ��������ҳ�ļ�
	   ����ûѧ�������ݵ�...�Ȱ�����д�������ַ������
	 */
	public  void generateHomePage () throws Exception{
		//��ҳģ���ϰ벿��
		String homepageTop ="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<!--bootstrap Enviroment-->\r\n" + 
				"		<meta charset=\"utf-8\">\r\n" + 
				"	    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"	    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"	    <!-- ����3��meta��ǩ*����*������ǰ�棬�κ��������ݶ�*����*������� -->\r\n" + 
				"	    <!-- Bootstrap -->\r\n" + 
				"	    <link href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"	\r\n" + 
				"	    <!-- HTML5 shim �� Respond.js ��Ϊ���� IE8 ֧�� HTML5 Ԫ�غ�ý���ѯ��media queries������ -->\r\n" + 
				"	    <!-- ���棺ͨ�� file:// Э�飨����ֱ�ӽ� html ҳ����ק��������У�����ҳ��ʱ Respond.js �������� -->\r\n" + 
				"	    <!--[if lt IE 9]>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\r\n" + 
				"	    <![endif]-->\r\n" + 
				"	    <!-- jQuery (Bootstrap ������ JavaScript ��������� jQuery�����Ա������ǰ��) -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js\"></script>\r\n" + 
				"	    <!-- ���� Bootstrap ������ JavaScript �������Ҳ���Ը�����Ҫֻ���ص�������� -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				"		\r\n" + 
				"		<title>bralaya'Home</title>\r\n" + 
				"		<!--my own js,css-->\r\n" + 
				"		<script src=\"/ghostyBlog/js/ghostyBlogAutoRobot.js\"></script>\r\n" + 
				"		<link rel=\"stylesheet\" type=\"text/css\" href=\"/ghostyBlog/css/ghostyBlogAutoRobot.css\"/>\r\n" + 
				"	</head>\r\n" + 
				"	<body style=\"background-color: #DCDCDC;\">\r\n" + 
				"		\r\n" + 
				"		<!--������������	����1-->\r\n" + 
				"		<div id=\"outestContainer\" class=\"row\">\r\n" + 
				"			<!--��߿հ�����	����2-->\r\n" + 
				"			<div id=\"leftBlankContainer\" class=\"col-md-1  hidden-sm hidden-xs\">\r\n" + 
				"				\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--�м���������	����2-->\r\n" + 
				"			<div id=\"middleContentContainer\" class=\"col-md-10 col-sm-12 col-xs-12\">\r\n" + 
				"				<!--�м����������������	������	����3-->\r\n" + 
				"				<div id=\"mCoutestNavigationBar\" >\r\n" + 
				"					<!--bootstrap ������ nav����-->\r\n" + 
				"					<nav id=\"xxxxx\" class=\"navbar navbar-default\" role=\"navigation\" >\r\n" + 
				"						<!--bootstrap ������ ������������-->\r\n" + 
				"						<div class=\"container-fluid\"> \r\n" + 
				"							\r\n" + 
				"							<!--bootstrap ������ ���ݲ�	pc���ʱ��ʾ��div-->\r\n" + 
				"							<div id=\"navRealContentContainer\" class=\"navbar-header\" >\r\n" + 
				"								<!--pc��ʱ��ʾ�ı���͸���ǩ��-->\r\n" + 
				"								<div id=\"pcNavBarEdition\">\r\n" + 
				"									<br>\r\n" + 
				"									<h1 class=\"container-fluid\" >ȼ�ĸ���վ��</h1>\r\n" + 
				"									<div class=\"container-fluid\">\r\n" + 
				"										<h4 class=\"\">���ܵ��ģ�����������棬����磬�ս��û����ꡣ</h4>\r\n" + 
				"									</div>\r\n" + 
				"								</div>\r\n" + 
				"								\r\n" + 
				"								<br>\r\n" + 
				"								<!--�ֻ�������ʾ�ı���-->\r\n" + 
				"								<div id=\"phoneNavBarEdition\">\r\n" + 
				"									\r\n" + 
				"									<a id=\"head2\" class=\"navbar-brand\" >ƽ��֮��</a>\r\n" + 
				"									<span style=\"\">\r\n" + 
				"										<!--������ť-->\r\n" + 
				"										<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\r\n" + 
				"												data-target=\"#example-navbar-collapse\">\r\n" + 
				"											<span class=\"sr-only\">�л�����</span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"										</button>\r\n" + 
				"									</span>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"							<!--bootstrap ������ ���ݲ�	������Ԫ�ز�-->\r\n" + 
				"							<div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\r\n" + 
				"								\r\n" + 
				"								<ul id=\"navbarElement\" class=\"nav navbar-nav\">\r\n" + 
				"									<li id=\"navbarElementValue1\"><a href=\"/ghostyBlog/home.html\">��������</a></li>\r\n" + 
				"									<li id=\"navbarElementValue2\"><a href=\"/ghostyBlog/ContactMe.html\">��ϵ��</a></li>\r\n" + 
				"								</ul>\r\n" + 
				"							</div>\r\n" + 
				"						</div>\r\n" + 
				"						\r\n" + 
				"					</nav>\r\n" + 
				"					\r\n" + 
				"					\r\n" + 
				"					\r\n" + 
				"				</div>\r\n" + 
				"				\r\n" + 
				"				<!--�м���������	��	�����	����ҳ	����	����3	-->\r\n" + 
				"				<div id=\"mCoutestContentContainer\" class=\"container-fluid\">\r\n" + 
				"					<!--�������ҳ����\r\n" + 
				"						�������ĺô��ǣ���ʱ��������ݻ����Ժ�����������м��\r\n" + 
				"					-->\r\n" + 
				"					<div id=\"leftContentContainer\" class=\"col-md-9 col-sm-9 col-xs-12\">\r\n" + 
				"						<!--������ݴ�Ŵ�-->\r\n" + 
				"						<div id=\"leftContent\" >\r\n" + 
				"							<br>\r\n" + 
				"							<!-- ҳ���ж��ϲ��� -->";
		//��ҳģ���°벿��
		String homePageBottom ="<!-- ҳ���ж��²��� -->\r\n" + 
				"						\r\n" + 
				"						</div>\r\n" + 
				"						<!--���濪ʼ�Ǳ������-->\r\n" + 
				"							<hr style=\"border:2px solid whitesmoke;\">\r\n" + 
				"							\r\n" + 
				"							\r\n" + 
				"					</div>\r\n" + 
				"					<!--�ұ����򵼺�������-->\r\n" + 
				"					<div id=\"rightNavigationBarContainer\" class=\"col-md-3 col-sm-3 hidden-xs\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--�ұ߿հ�����	����2-->\r\n" + 
				"			<div id=\"rightBlankContainer\" class=\"col-md-1 hidden-sm hidden-xs \">\r\n" + 
				"				\r\n" + 
				"			</div><!--�ұ߿հ�����	����2-->\r\n" + 
				"			\r\n" + 
				"		</div><!--������������	����1-->\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				"";
		//��ҳ��windows�ϵľ���·��
		String WindowshomePagePath="C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\home.html";
		//��ҳ��Linux�ϵľ���·��
		String LinuxhomePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/home.html";
		//������ҳ��·��
		String nowPagePath = LinuxhomePagePath;
		
		//����·��
		//String testPath="/homex.html";
		
//		File newFile = new File(".");
//		newFile.createNewFile();
//		//����ļ����ھ���·��
//		System.out.println(newFile.getAbsolutePath());
//		
		//����ϲ������ݵ���ҳ�ļ�
		try(
				//һ���Դ���PrintStream��������粻д�����ĸ�ʽ...
				PrintStream pStream = new PrintStream(new FileOutputStream(nowPagePath));
		){
				//����׼����ض���pStream�����
				System.setOut(pStream);
				System.out.println(homepageTop);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//��ȡ��չDAO��������б��������ҳ�ļ�����ӵ�β��ģʽ
		//new һ��ExtendDao
		braExtendDao braextendDao  = new braExtendDao();
		//ִ�в�ѯ����������ȡ���ؽ��
		ArrayList articleMessage = braextendDao.queryAllArticle();
		//Ȼ����html��ʽ������ݵ�����ȴ�ajax��ȡ
		//System.out.println(articleMessage.size());
		braArticleData articleData = null;
		try(
				//�Զ�д��ʽ��һ��RandomAccessFile����
				RandomAccessFile randomAccessFile = new RandomAccessFile(nowPagePath, "rw");
		){
				//����¼ָ���Ƶ�����β��...C++�߼�����ֱ����׷�Ӳ���
				randomAccessFile.seek(randomAccessFile.length());
				randomAccessFile.write("<ul id=\"leftContentUl\">".getBytes());
				//ѭ����ȡ���ݲ������li��ǩ
				for(int finger=0;finger<articleMessage.size();finger++) {
					articleData = (braArticleData)articleMessage.get(finger);
					randomAccessFile.write("<li><a href=\"".getBytes());
					//��ת·�������ﻹ����д�������������������תӦ�ò�����IO��д�����������ˡ�
					randomAccessFile.write("bralaya_article/".getBytes());
					randomAccessFile.write(articleData.getId().getBytes());
					randomAccessFile.write(".html\">".getBytes());
					//���±���
					randomAccessFile.write(articleData.getHead().getBytes());
					randomAccessFile.write("</a></li>".getBytes());
				}
				randomAccessFile.write("</ul>".getBytes());
				//�����ҳ�²������ݵ��ļ�
				randomAccessFile.write(homePageBottom.getBytes());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	//��������ҳ�ļ�
	/*
	 * �������¾�Ҫ����һ������ҳ�ļ����޸�����ֱ�����¶�ȡ���ݵ��������ҳ�ļ�
	 * 
	 * ���﷢�����º��޸����¾��ǹ������������
	 * 
	 */
	public void generateArticlePage(String articleId,String articleHead,String articleContent) {
		// ��ȡ����ҳģ���ϰ벿�֣������������ҳ�ļ����ļ�ֱ��Ϊid����
		String articlePageTop="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<!--bootstrap Enviroment-->\r\n" + 
				"		<meta charset=\"utf-8\">\r\n" + 
				"	    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"	    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"	    <!-- ����3��meta��ǩ*����*������ǰ�棬�κ��������ݶ�*����*������� -->\r\n" + 
				"	    <!-- Bootstrap -->\r\n" + 
				"	    <link href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"	\r\n" + 
				"	    <!-- HTML5 shim �� Respond.js ��Ϊ���� IE8 ֧�� HTML5 Ԫ�غ�ý���ѯ��media queries������ -->\r\n" + 
				"	    <!-- ���棺ͨ�� file:// Э�飨����ֱ�ӽ� html ҳ����ק��������У�����ҳ��ʱ Respond.js �������� -->\r\n" + 
				"	    <!--[if lt IE 9]>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\r\n" + 
				"	    <![endif]-->\r\n" + 
				"	    <!-- jQuery (Bootstrap ������ JavaScript ��������� jQuery�����Ա������ǰ��) -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js\"></script>\r\n" + 
				"	    <!-- ���� Bootstrap ������ JavaScript �������Ҳ���Ը�����Ҫֻ���ص�������� -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				"		\r\n" + 
				"		<title>bralaya'Article</title>\r\n" + 
				"		<!--my own js,css-->\r\n" + 
				"		<script src=\"/ghostyBlog/js/ghostyBlogAutoRobot.js\"></script>\r\n" + 
				"		<link rel=\"stylesheet\" type=\"text/css\" href=\"/ghostyBlog/css/ghostyBlogAutoRobot.css\"/>\r\n" + 
				"	</head>\r\n" + 
				"	<body style=\"background-color: whitesmoke\">\r\n" + 
				"		<!--#DCDCDC,khaki,white,whitesmoke-->\r\n" + 
				"		\r\n" + 
				"		<!--������������	����1-->\r\n" + 
				"		<div id=\"outestContainer\" class=\"row\">\r\n" + 
				"			<!--��߿հ�����	����2-->\r\n" + 
				"			<div id=\"leftBlankContainer\" class=\"col-md-1  hidden-sm hidden-xs\">\r\n" + 
				"				\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--�м���������	����2-->\r\n" + 
				"			<div id=\"middleContentContainer\" class=\"col-md-10 col-sm-12 col-xs-12\">\r\n" + 
				"				<!--�м����������������	������	����3-->\r\n" + 
				"				<div id=\"mCoutestNavigationBar\" >\r\n" + 
				"					<!--bootstrap ������ nav����-->\r\n" + 
				"					<nav id=\"xxxxx\" class=\"navbar navbar-default\" role=\"navigation\" >\r\n" + 
				"						<!--bootstrap ������ ������������-->\r\n" + 
				"						<div class=\"container-fluid\"> \r\n" + 
				"							\r\n" + 
				"							<!--bootstrap ������ ���ݲ�	pc���ʱ��ʾ��div-->\r\n" + 
				"							<div id=\"navRealContentContainer\" class=\"navbar-header\" >\r\n" + 
				"								<!--pc��ʱ��ʾ�ı���͸���ǩ��-->\r\n" + 
				"								<div id=\"pcNavBarEdition\">\r\n" + 
				"									<br>\r\n" + 
				"									<h1 class=\"container-fluid\" >ȼ�ĸ���վ��</h1>\r\n" + 
				"									<div class=\"container-fluid\">\r\n" + 
				"										<h4 class=\"\">���ܵ��ģ�����������棬����磬�ս��û����ꡣ</h4>\r\n" + 
				"									</div>\r\n" + 
				"								</div>\r\n" + 
				"								\r\n" + 
				"								<br>\r\n" + 
				"								<!--�ֻ�������ʾ�ı���-->\r\n" + 
				"								<div id=\"phoneNavBarEdition\">\r\n" + 
				"									\r\n" + 
				"									<a id=\"head2\" class=\"navbar-brand\" >ƽ��֮��</a>\r\n" + 
				"									<span style=\"\">\r\n" + 
				"										<!--������ť-->\r\n" + 
				"										<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\r\n" + 
				"												data-target=\"#example-navbar-collapse\">\r\n" + 
				"											<span class=\"sr-only\">�л�����</span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"										</button>\r\n" + 
				"									</span>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"							<!--bootstrap ������ ���ݲ�	������Ԫ�ز�-->\r\n" + 
				"							<div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\r\n" + 
				"								\r\n" + 
				"								<ul id=\"navbarElement\" class=\"nav navbar-nav\">\r\n" + 
				"									<li id=\"navbarElementValue1\"><a href=\"/ghostyBlog/home.html\" >��������</a></li>\r\n" + 
				"									<li id=\"navbarElementValue2\"><a href=\"/ghostyBlog/ContactMe.html\" >��ϵ��</a></li>\r\n" + 
				"								</ul>\r\n" + 
				"							</div>\r\n" + 
				"						</div>\r\n" + 
				"						\r\n" + 
				"					</nav>\r\n" + 
				"					\r\n" + 
				"					\r\n" + 
				"					\r\n" + 
				"				</div>\r\n" + 
				"				\r\n" + 
				"				<!--�м���������	��	�����	����ҳ	����	����3	-->\r\n" + 
				"				<div id=\"mCoutestContentContainer\" class=\"container-fluid\">\r\n" + 
				"					<!--�������ҳ����\r\n" + 
				"						�������ĺô��ǣ���ʱ��������ݻ����Ժ�����������м��\r\n" + 
				"					-->\r\n" + 
				"					<div id=\"leftContentContainer\" class=\"col-md-9 col-sm-9 col-xs-12\">\r\n" + 
				"						<!--������ݴ�Ŵ�-->\r\n" + 
				"						<div id=\"leftContent\"  style=\"margin: 12px 0px 0px 0px;\">\r\n" + 
				"							<div id=\"articleStyle\">\r\n" + 
				"								<!--���濪ʼ�����ݿ������������-->\r\n" + 
				"								<!-- ҳ���ж��ϲ��� -->";
		//����ҳ��windows�ϵľ���·��
		String WindowsArticlePagePath = "C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\bralaya_article\\"+articleId+".html";
		//����ҳ��Linux�ϵľ���·��
		String LinuxArticlePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/bralaya_article/"+articleId+".html";
		//����ҳ��ǰ�ľ���·��
		String nowArticlePagePath = LinuxArticlePagePath;
		//�����²�������
		String articlePageBottom ="<!-- ҳ���ж��²��� -->\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"						</div>\r\n" + 
				"						<!--���濪ʼ�Ǳ������-->\r\n" + 
				"						<hr style=\"border:2px solid whitesmoke;\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"					<!--�ұ����򵼺�������-->\r\n" + 
				"					<div id=\"rightNavigationBarContainer\" class=\"col-md-3 col-sm-3 hidden-xs\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--�ұ߿հ�����	����2-->\r\n" + 
				"			<div id=\"rightBlankContainer\" class=\"col-md-1 hidden-sm hidden-xs \">\r\n" + 
				"				\r\n" + 
				"			</div><!--�ұ߿հ�����	����2-->\r\n" + 
				"			\r\n" + 
				"		</div><!--������������	����1-->\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try(
				//һ���Դ���PrintStream��������粻д�����ĸ�ʽ...
				PrintStream pStream = new PrintStream( new FileOutputStream( nowArticlePagePath) );
		){
				//����׼����ض���pStream�����
				System.setOut(pStream);
				System.out.println(articlePageTop);
		}catch (Exception e) {
			// TODO: handle exception
		}
		// ������������,����ҳģ���²��ֶ����������ҳ�ļ�
		//������ʵ���д��ͬһ��������Ϳ����ڿ�ͷ�򿪣���β���������ˣ�����Ҫ���ؿ���
		
		//������⻹������д�ð�
		String articlehead = "<h3>"+articleHead+"</h3>";
		try(
				//�Զ�д��ʽ��һ��RandomAccessFile����
				RandomAccessFile randomAccessFile = new RandomAccessFile(nowArticlePagePath, "rw");
		){
				//����¼ָ���Ƶ�����β��...C++�߼�����ֱ����׷�Ӳ���
				randomAccessFile.seek(randomAccessFile.length());
				randomAccessFile.write(articlehead.getBytes());
				randomAccessFile.write(articleContent.getBytes());
				randomAccessFile.write(articlePageBottom.getBytes());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//ɾ������ҳ�ļ�
	/*
	 * ��������ҵ�Ŀ¼��Ҫɾ�����ļ�Ȼ��ɾ��������
	 */
	public void delArticlePage(String articleId){
		//ֱ�ӵ�bralaya_articleĿ¼��ɾ����Ӧ�ļ�
		//ɾ���ļ�
		//�����ظ�������Ҫ�������������Ľ��డ
		//����ҳ��windows�ϵľ���·��
		String WindowsArticlePagePath = "C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\bralaya_article\\"+articleId+".html";
		//����ҳ��Linux�ϵľ���·��
		String LinuxArticlePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/bralaya_article/"+articleId+".html";
		//����ҳ��ǰ�ľ���·��
		String nowArticlePagePath = LinuxArticlePagePath;
		
		File delFile = new File(nowArticlePagePath);
		delFile.delete();
	}
}

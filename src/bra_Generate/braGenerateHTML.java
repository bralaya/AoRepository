package bra_Generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import bra_DAO.braExtendDao;
import bra_Data.braArticleData;

public class braGenerateHTML {
	
	
	//生成首页文件
	/*
	 * 发表	/	删除	/	修改	文章都要重新更新读取下文章列表到首页
	 * 
	 * 读取首页模板上半部分内容，然后输出到首页文件
	   重新读取数据库里的文章标题列表输出到首页文件
	   读取首页模板下部分内容，输出到首页文件
	   好像还没学读入数据的...先把内容写死放在字符串里吧
	 */
	public  void generateHomePage () throws Exception{
		//首页模板上半部分
		String homepageTop ="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<!--bootstrap Enviroment-->\r\n" + 
				"		<meta charset=\"utf-8\">\r\n" + 
				"	    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"	    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\r\n" + 
				"	    <!-- Bootstrap -->\r\n" + 
				"	    <link href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"	\r\n" + 
				"	    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->\r\n" + 
				"	    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->\r\n" + 
				"	    <!--[if lt IE 9]>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\r\n" + 
				"	    <![endif]-->\r\n" + 
				"	    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js\"></script>\r\n" + 
				"	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				"		\r\n" + 
				"		<title>bralaya'Home</title>\r\n" + 
				"		<!--my own js,css-->\r\n" + 
				"		<script src=\"/ghostyBlog/js/ghostyBlogAutoRobot.js\"></script>\r\n" + 
				"		<link rel=\"stylesheet\" type=\"text/css\" href=\"/ghostyBlog/css/ghostyBlogAutoRobot.css\"/>\r\n" + 
				"	</head>\r\n" + 
				"	<body style=\"background-color: #DCDCDC;\">\r\n" + 
				"		\r\n" + 
				"		<!--最外层包裹容器	级别1-->\r\n" + 
				"		<div id=\"outestContainer\" class=\"row\">\r\n" + 
				"			<!--左边空白容器	级别2-->\r\n" + 
				"			<div id=\"leftBlankContainer\" class=\"col-md-1  hidden-sm hidden-xs\">\r\n" + 
				"				\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--中间内容容器	级别2-->\r\n" + 
				"			<div id=\"middleContentContainer\" class=\"col-md-10 col-sm-12 col-xs-12\">\r\n" + 
				"				<!--中间内容容器的最外层	导航栏	级别3-->\r\n" + 
				"				<div id=\"mCoutestNavigationBar\" >\r\n" + 
				"					<!--bootstrap 导航栏 nav容器-->\r\n" + 
				"					<nav id=\"xxxxx\" class=\"navbar navbar-default\" role=\"navigation\" >\r\n" + 
				"						<!--bootstrap 导航栏 二级包裹容器-->\r\n" + 
				"						<div class=\"container-fluid\"> \r\n" + 
				"							\r\n" + 
				"							<!--bootstrap 导航栏 内容层	pc宽度时显示的div-->\r\n" + 
				"							<div id=\"navRealContentContainer\" class=\"navbar-header\" >\r\n" + 
				"								<!--pc宽时显示的标题和个性签名-->\r\n" + 
				"								<div id=\"pcNavBarEdition\">\r\n" + 
				"									<br>\r\n" + 
				"									<h1 class=\"container-fluid\" >燃的个人站点</h1>\r\n" + 
				"									<div class=\"container-fluid\">\r\n" + 
				"										<h4 class=\"\">不管到哪，冷风总是相随，而这风，终将幻化成雨。</h4>\r\n" + 
				"									</div>\r\n" + 
				"								</div>\r\n" + 
				"								\r\n" + 
				"								<br>\r\n" + 
				"								<!--手机屏宽显示的标题-->\r\n" + 
				"								<div id=\"phoneNavBarEdition\">\r\n" + 
				"									\r\n" + 
				"									<a id=\"head2\" class=\"navbar-brand\" >平衡之剑</a>\r\n" + 
				"									<span style=\"\">\r\n" + 
				"										<!--汉堡按钮-->\r\n" + 
				"										<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\r\n" + 
				"												data-target=\"#example-navbar-collapse\">\r\n" + 
				"											<span class=\"sr-only\">切换导航</span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"										</button>\r\n" + 
				"									</span>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"							<!--bootstrap 导航栏 内容层	导航栏元素层-->\r\n" + 
				"							<div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\r\n" + 
				"								\r\n" + 
				"								<ul id=\"navbarElement\" class=\"nav navbar-nav\">\r\n" + 
				"									<li id=\"navbarElementValue1\"><a href=\"/ghostyBlog/home.html\">所有文章</a></li>\r\n" + 
				"									<li id=\"navbarElementValue2\"><a href=\"/ghostyBlog/ContactMe.html\">联系我</a></li>\r\n" + 
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
				"				<!--中间内容容器	的	最外层	内容页	容器	级别3	-->\r\n" + 
				"				<div id=\"mCoutestContentContainer\" class=\"container-fluid\">\r\n" + 
				"					<!--左边内容页容器\r\n" + 
				"						：这样的好处是，到时里面的内容还可以和最外层容器有间距\r\n" + 
				"					-->\r\n" + 
				"					<div id=\"leftContentContainer\" class=\"col-md-9 col-sm-9 col-xs-12\">\r\n" + 
				"						<!--左边内容存放处-->\r\n" + 
				"						<div id=\"leftContent\" >\r\n" + 
				"							<br>\r\n" + 
				"							<!-- 页面切断上部分 -->";
		//首页模板下半部分
		String homePageBottom ="<!-- 页面切断下部分 -->\r\n" + 
				"						\r\n" + 
				"						</div>\r\n" + 
				"						<!--下面开始是别的内容-->\r\n" + 
				"							<hr style=\"border:2px solid whitesmoke;\">\r\n" + 
				"							\r\n" + 
				"							\r\n" + 
				"					</div>\r\n" + 
				"					<!--右边竖向导航条容器-->\r\n" + 
				"					<div id=\"rightNavigationBarContainer\" class=\"col-md-3 col-sm-3 hidden-xs\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--右边空白容器	级别2-->\r\n" + 
				"			<div id=\"rightBlankContainer\" class=\"col-md-1 hidden-sm hidden-xs \">\r\n" + 
				"				\r\n" + 
				"			</div><!--右边空白容器	级别2-->\r\n" + 
				"			\r\n" + 
				"		</div><!--最外层包裹容器	级别1-->\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				"";
		//首页在windows上的绝对路径
		String WindowshomePagePath="C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\home.html";
		//首页在Linux上的绝对路径
		String LinuxhomePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/home.html";
		//现在首页的路径
		String nowPagePath = LinuxhomePagePath;
		
		//测试路径
		//String testPath="/homex.html";
		
//		File newFile = new File(".");
//		newFile.createNewFile();
//		//输出文件所在绝对路径
//		System.out.println(newFile.getAbsolutePath());
//		
		//输出上部分内容到首页文件
		try(
				//一次性创建PrintStream输出流，早不写这样的格式...
				PrintStream pStream = new PrintStream(new FileOutputStream(nowPagePath));
		){
				//将标准输出重定向到pStream输出流
				System.setOut(pStream);
				System.out.println(homepageTop);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//读取扩展DAO获得文章列表并输出到首页文件，添加到尾巴模式
		//new 一个ExtendDao
		braExtendDao braextendDao  = new braExtendDao();
		//执行查询方法，并获取返回结果
		ArrayList articleMessage = braextendDao.queryAllArticle();
		//然后按照html样式输出数据到网络等待ajax获取
		//System.out.println(articleMessage.size());
		braArticleData articleData = null;
		try(
				//以读写方式打开一个RandomAccessFile对象
				RandomAccessFile randomAccessFile = new RandomAccessFile(nowPagePath, "rw");
		){
				//将记录指针移到内容尾巴...C++高级多了直接有追加参数
				randomAccessFile.seek(randomAccessFile.length());
				randomAccessFile.write("<ul id=\"leftContentUl\">".getBytes());
				//循环读取内容并构造进li标签
				for(int finger=0;finger<articleMessage.size();finger++) {
					articleData = (braArticleData)articleMessage.get(finger);
					randomAccessFile.write("<li><a href=\"".getBytes());
					//跳转路径，这里还不好写啊。不过这里的链接跳转应该不会像IO读写那样的问题了。
					randomAccessFile.write("bralaya_article/".getBytes());
					randomAccessFile.write(articleData.getId().getBytes());
					randomAccessFile.write(".html\">".getBytes());
					//文章标题
					randomAccessFile.write(articleData.getHead().getBytes());
					randomAccessFile.write("</a></li>".getBytes());
				}
				randomAccessFile.write("</ul>".getBytes());
				//输出首页下部分内容到文件
				randomAccessFile.write(homePageBottom.getBytes());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	//生成文章页文件
	/*
	 * 发表文章就要生成一个文章页文件，修改文章直接重新读取内容到这个文章页文件
	 * 
	 * 这里发表文章和修改文章就是共用这个方法的
	 * 
	 */
	public void generateArticlePage(String articleId,String articleHead,String articleContent) {
		// 读取文章页模板上半部分，并输出到文章页文件，文件直接为id命名
		String articlePageTop="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<!--bootstrap Enviroment-->\r\n" + 
				"		<meta charset=\"utf-8\">\r\n" + 
				"	    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"	    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\r\n" + 
				"	    <!-- Bootstrap -->\r\n" + 
				"	    <link href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"	\r\n" + 
				"	    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->\r\n" + 
				"	    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->\r\n" + 
				"	    <!--[if lt IE 9]>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n" + 
				"	      <script src=\"https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\r\n" + 
				"	    <![endif]-->\r\n" + 
				"	    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->\r\n" + 
				"	    <script src=\"https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js\"></script>\r\n" + 
				"	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->\r\n" + 
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
				"		<!--最外层包裹容器	级别1-->\r\n" + 
				"		<div id=\"outestContainer\" class=\"row\">\r\n" + 
				"			<!--左边空白容器	级别2-->\r\n" + 
				"			<div id=\"leftBlankContainer\" class=\"col-md-1  hidden-sm hidden-xs\">\r\n" + 
				"				\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--中间内容容器	级别2-->\r\n" + 
				"			<div id=\"middleContentContainer\" class=\"col-md-10 col-sm-12 col-xs-12\">\r\n" + 
				"				<!--中间内容容器的最外层	导航栏	级别3-->\r\n" + 
				"				<div id=\"mCoutestNavigationBar\" >\r\n" + 
				"					<!--bootstrap 导航栏 nav容器-->\r\n" + 
				"					<nav id=\"xxxxx\" class=\"navbar navbar-default\" role=\"navigation\" >\r\n" + 
				"						<!--bootstrap 导航栏 二级包裹容器-->\r\n" + 
				"						<div class=\"container-fluid\"> \r\n" + 
				"							\r\n" + 
				"							<!--bootstrap 导航栏 内容层	pc宽度时显示的div-->\r\n" + 
				"							<div id=\"navRealContentContainer\" class=\"navbar-header\" >\r\n" + 
				"								<!--pc宽时显示的标题和个性签名-->\r\n" + 
				"								<div id=\"pcNavBarEdition\">\r\n" + 
				"									<br>\r\n" + 
				"									<h1 class=\"container-fluid\" >燃的个人站点</h1>\r\n" + 
				"									<div class=\"container-fluid\">\r\n" + 
				"										<h4 class=\"\">不管到哪，冷风总是相随，而这风，终将幻化成雨。</h4>\r\n" + 
				"									</div>\r\n" + 
				"								</div>\r\n" + 
				"								\r\n" + 
				"								<br>\r\n" + 
				"								<!--手机屏宽显示的标题-->\r\n" + 
				"								<div id=\"phoneNavBarEdition\">\r\n" + 
				"									\r\n" + 
				"									<a id=\"head2\" class=\"navbar-brand\" >平衡之剑</a>\r\n" + 
				"									<span style=\"\">\r\n" + 
				"										<!--汉堡按钮-->\r\n" + 
				"										<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\r\n" + 
				"												data-target=\"#example-navbar-collapse\">\r\n" + 
				"											<span class=\"sr-only\">切换导航</span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"											<span class=\"icon-bar\"></span>\r\n" + 
				"										</button>\r\n" + 
				"									</span>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"							<!--bootstrap 导航栏 内容层	导航栏元素层-->\r\n" + 
				"							<div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\r\n" + 
				"								\r\n" + 
				"								<ul id=\"navbarElement\" class=\"nav navbar-nav\">\r\n" + 
				"									<li id=\"navbarElementValue1\"><a href=\"/ghostyBlog/home.html\" >所有文章</a></li>\r\n" + 
				"									<li id=\"navbarElementValue2\"><a href=\"/ghostyBlog/ContactMe.html\" >联系我</a></li>\r\n" + 
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
				"				<!--中间内容容器	的	最外层	内容页	容器	级别3	-->\r\n" + 
				"				<div id=\"mCoutestContentContainer\" class=\"container-fluid\">\r\n" + 
				"					<!--左边内容页容器\r\n" + 
				"						：这样的好处是，到时里面的内容还可以和最外层容器有间距\r\n" + 
				"					-->\r\n" + 
				"					<div id=\"leftContentContainer\" class=\"col-md-9 col-sm-9 col-xs-12\">\r\n" + 
				"						<!--左边内容存放处-->\r\n" + 
				"						<div id=\"leftContent\"  style=\"margin: 12px 0px 0px 0px;\">\r\n" + 
				"							<div id=\"articleStyle\">\r\n" + 
				"								<!--下面开始是数据库里的文章内容-->\r\n" + 
				"								<!-- 页面切断上部分 -->";
		//文章页在windows上的绝对路径
		String WindowsArticlePagePath = "C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\bralaya_article\\"+articleId+".html";
		//文章页在Linux上的绝对路径
		String LinuxArticlePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/bralaya_article/"+articleId+".html";
		//文章页当前的绝对路径
		String nowArticlePagePath = LinuxArticlePagePath;
		//文章下部分内容
		String articlePageBottom ="<!-- 页面切断下部分 -->\r\n" + 
				"							</div>\r\n" + 
				"							\r\n" + 
				"						</div>\r\n" + 
				"						<!--下面开始是别的内容-->\r\n" + 
				"						<hr style=\"border:2px solid whitesmoke;\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"					<!--右边竖向导航条容器-->\r\n" + 
				"					<div id=\"rightNavigationBarContainer\" class=\"col-md-3 col-sm-3 hidden-xs\">\r\n" + 
				"						\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			\r\n" + 
				"			<!--右边空白容器	级别2-->\r\n" + 
				"			<div id=\"rightBlankContainer\" class=\"col-md-1 hidden-sm hidden-xs \">\r\n" + 
				"				\r\n" + 
				"			</div><!--右边空白容器	级别2-->\r\n" + 
				"			\r\n" + 
				"		</div><!--最外层包裹容器	级别1-->\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try(
				//一次性创建PrintStream输出流，早不写这样的格式...
				PrintStream pStream = new PrintStream( new FileOutputStream( nowArticlePagePath) );
		){
				//将标准输出重定向到pStream输出流
				System.setOut(pStream);
				System.out.println(articlePageTop);
		}catch (Exception e) {
			// TODO: handle exception
		}
		// 输出文章整体块,文章页模板下部分都输出到文章页文件
		//这里其实如果写在同一个方法里就可以在开头打开，结尾结束就行了，不需要开关开关
		
		//这里标题还得这里写好啊
		String articlehead = "<h3>"+articleHead+"</h3>";
		try(
				//以读写方式打开一个RandomAccessFile对象
				RandomAccessFile randomAccessFile = new RandomAccessFile(nowArticlePagePath, "rw");
		){
				//将记录指针移到内容尾巴...C++高级多了直接有追加参数
				randomAccessFile.seek(randomAccessFile.length());
				randomAccessFile.write(articlehead.getBytes());
				randomAccessFile.write(articleContent.getBytes());
				randomAccessFile.write(articlePageBottom.getBytes());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//删除文章页文件
	/*
	 * 这个就是找到目录下要删除的文件然后删掉就是了
	 */
	public void delArticlePage(String articleId){
		//直接到bralaya_article目录下删除对应文件
		//删除文件
		//这里重复了这是要单独做个方法的节奏啊
		//文章页在windows上的绝对路径
		String WindowsArticlePagePath = "C:\\Users\\bralaya_h\\Documents\\CodeProject\\eclipse-workspace\\ghostyBlog\\WebContent\\bralaya_article\\"+articleId+".html";
		//文章页在Linux上的绝对路径
		String LinuxArticlePagePath = "/usr/local/tomcat/apache-tomcat-9.0.12/webapps/ghostyBlog/bralaya_article/"+articleId+".html";
		//文章页当前的绝对路径
		String nowArticlePagePath = LinuxArticlePagePath;
		
		File delFile = new File(nowArticlePagePath);
		delFile.delete();
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>古代后台管理器</title>
<!-- 暂时引用wang富文本编辑器CDN -->
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"> </script>
<script src="js/ghosty_robotAdmin.js"></script>
<link rel="stylesheet" type="text/css" href="css/ghosty_robotAdmin.css">
</head>
<body>
	<%
		//页面加载时判断session存在否
		//存在让字段P变为Login Success,否则Login Wait
		String LoginState="Login Wait";
		String getSession = (String)session.getAttribute("success");
		if(getSession!=null)
			LoginState = "Login Success";
		
	%>
	<!-- 遮罩层登入 -->
	<div id="enterPage">
		<input  id="passwdid" type="password">
		<button id="loginButton" onclick="AjaxSend(0)">Login</button>
	</div>
	
	<!-- 导航栏 -->
	<div id="NavBar">
		
		<div style="border:1px solid black;">
			<a id="state"><%=LoginState %></a>
			<button style="float:right;" onclick="AjaxSend(6)">Login Out</button>
		</div>
		
		<div style="border:1px solid black;overflow:hidden;height:100%;">
			
				<button onclick="tabPbArticleManageArticle(1);"><h1>发布文章</h1></button>
				<button  onclick="AjaxSend(1);tabPbArticleManageArticle(2);"><h1>文章管理</h1></button>
				<button id="refreshButton" onclick=""><h1>一键更新</h1></button>
			
		</div>
	</div>
	
	<hr>
	
	<!-- 内容主体块靠左 -->
	<div id="contentContainer">
		<!-- 文章管理(默认显示)
			 显示所有数据就是了，然后右侧有个管理，点击可以批量删除，还有修改按钮，单独删除按钮
		 -->
		<div id="articleManageContainer">
			<!-- 来个当前文章总数,可以扩展为各种信息综合查询吧 -->
			<h1>当前文章总数：<span id="articleNumPresent"></span></h1>
			<table border="1" id="articleList">
							
			</table>
		</div>
		<!-- 发表文章 -->
		<div id="editorContainer">
				标题：<input id='articleHead' type="text"  style="width:80%"/>
				<!--wangEditor编辑器固定代码 begin-->
				<div id="div1">
				   
				</div>
				<script type="text/javascript" src="/wangEditor.min.js"></script>
				<script type="text/javascript">
				    var E = window.wangEditor
				    var editor = new E('#div1')
				    editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
				    editor.create()
				</script>
				<!--wangEditor编辑器固定代码 end-->
				<button id="publicArticleButton" onclick="AjaxSend(2);" >发表文章</button>
				<button id="alterArticleButton" onclick="AjaxSend(5);">修改文章</button>
				
		</div><!-- 发表文章 -->
		
	
	</div>
</body>
</html>
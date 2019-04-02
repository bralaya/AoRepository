/*
	这里获取到了ajax返回内容后，就查看成功与否，成功刷新页面，否则清空密码重新输入
	然后刷新页面后，会先判断某个位置的内容是否为Login Success，来决定是否弹出遮罩登入层
*/




//************Ajax请求 开始************

//试试全局变量
var articleid = null;

//初始化Ajax
function initAjax(){
	var xmlHttp = false;
	//Mozilla等现代浏览器
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();	
		return xmlHttp;
	}
	//IE浏览器
	else if(window.ActiveXObject){
		try{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			return xmlHttp;
		}catch(e){
			try{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				return xmlHttp;
			}catch(e){
				window.alert("浏览器不支持Ajax");
				return null;
			}
		}
    }
}

//Ajax固定发起请求步骤
function AjaxSend(n){
	 var xmlHttp = initAjax();
	 var url  = null; 
	 switch(n){
	 	case 0:url = enteInitrUrl();//发起登入请求的url
	 		break;
	 	case 1:url = articleManageRequest();//发起文章管理请求
	 		break;
	 	case 2:url = publicArticle();//发起发表文章请求
	 		break;
	 	case 3:url = delArticleRequest();//删除文章请求
	 		break;
	 	case 4:url = alterArticleRequest();//修改文章请求(获取单篇文章内容）
	 		break;
	 	case 5:url = alterArticleRealRequest();//修改文章执行请求
	 		break;
	 	case 6:url = loginOutRequest();	//登出请求
	 		break;
	 }
	// alert("AjaxSend:"+url);
	 //开启ajax异步
	 xmlHttp.open("post",url,true);
	 //开始执行
	 xmlHttp.onreadystatechange = function (){
		 	if(xmlHttp.readyState == 4){
		 		//alert("测试得到返回的数据："+xmlHttp.responseText);
		 		var u = xmlHttp.responseText;
		 		switch(n){
				 	case 0:enterResult(u);
				 		break;	
				 	case 1:articleManageResponse(u);
				 		break;
				 	case 2:publicArticleResponse(u);
				 		break;
				 	case 3:delArticleResponse(u);
				 		break;
				 	case 4:alterArticleResponse(u);
				 		break;
				 	case 5:alterArticleRealResponse(u);
				 		break;
				 	case 6:loginOutResponse(u);
				 		break;
		 		}	
		 	}
		 }
	 xmlHttp.send();
	
}


//登入请求url构造方法
/*	获取字段内容
	构造请求URL并返回 
*/
function enteInitrUrl(){
	var passwdGet = document.getElementById("passwdid").value;
	var url = "braServlet?functionChoise=EnterCheck&&passwd="+passwdGet;
	return url;
}

//登入返回结果方法
/*
* 返回json结果，成功登入success：1，失败success:0
* 
*/
function enterResult(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
		//刷新页面
		location.reload();
	}else{
		//清空输入框内容,算了不做处理
		document.getElementById('passwdid').value=null;
	}
}

//登出请求发起函数
function loginOutRequest(){
	var url = "braServlet?functionChoise=loginOut";
	return url;
}

//登出请求返回结果函数
function loginOutResponse(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
		alert("已经安全退出！");
		//刷新页面
		location.reload();
	}else{
		alert("退出失败！");
	}
}


//登入检查函数
function enterCheckSession(){
	if(document.getElementById('state').innerHTML=='Login Success')
		document.getElementById('enterPage').style.display='none';
	else
		document.getElementById('enterPage').style.display='block';
}

//***********

//发起查询文章列表的请求（后台）
function articleManageRequest(){
	var url = "braServlet?functionChoise=selectArticleMessage";
	return url;
	
}

//返回文章列表管理结果并直接显示到页面上
function articleManageResponse(u){
	//alert(u);
	document.getElementById('articleList').innerHTML=u;
	
}

//***********

//发起发表文章的请求
/*
 * 先判断是否为空再
 * 获取文章标题
 * 获取文章内容
 * 构造url
 * 
 */
function publicArticle(){
	//获取判断,不正常弹窗显示
	if(document.getElementById('articleHead').value==''){
		alert("标题："+articleHead);
		alert("标题不能为空啊！！！");
	}else{
		//内容为空的时候获取到的是<p><br></p>..所以我这样判断了...
		if(editor.txt.html()=='<p><br></p>'){
			alert("内容："+articleContent);
			alert("内容不能为空啊！！！");
		}else{
			//都正常执行发起请求
			var articleHead = document.getElementById('articleHead').value;
			var articleContent = editor.txt.html();
			//alert("标题："+articleHead);
			//alert(typeof articleContent);
			var url =  "braServlet?functionChoise=publicArticle&&articleHead="+articleHead+"&&articleContent="+articleContent;
			//alert(encodeURI(url));
			return encodeURI(url);
		}
	}
	
}

// 返回发表成功与否结果并显示到页面
/* 返回json结果和登入的一样，success:1 or 0
 * 成功，弹窗显示发表成功，然后刷新页面，并清空发表文章的那些内容
 * 失败，弹窗显示失败，然后返回发表文章页面，但是不返回点为什么错误的信息么
 */
function publicArticleResponse(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
		alert("发表成功！！！！");
		//alert(jsonResponse.path);
		//刷新页面
		location.reload();
		//清空发表文章输入框的内容
		
		
	}else{
		alert("发表失败！！！！");
		//清空输入框内容
		//document.getElementById('passwdid').value=null;
	}
}

//获取文章ID的函数
/* 这里出现了比Ajax更高层级的函数，获取文章id的函数,为了纪念这个时刻命名函数为AjaxFather
*  在servlet里就已经打印好了	
*  1.获取id
*  2.判断第二个参数为1还是2，1的话删除，2的话修改
*  3.第二个参数为1，执行传递id给删除函数，删除函数返回url给AjaxSend函数
*  4.AjaxSend函数发起请求删除
*  //这里问题没那么简单，主要由于我ajax设定的问题，不然我这里就是重新写个独立请求的就完事了。
*  或者就是把获取到的id设置为全局变量也可以解决，可以。
*  第二个参数为2执行修改
*  先跳转到发表文章界面，然后读取内容到对应框框里，那这里有两步啊
*  1是先获取单篇文章内容，2是修改完内容重新更新到文章里
*  另一办法是一开始读取所有文章的时候就直接读取显示出来了，最简单的了，还省得再重新获取，也可以构造，不想在首页看到
*  也可以直接隐藏掉
*  
*/
function AjaxFather(son,daughter){
	//赋值给全局变量articleid
	articleid = document.getElementById(son).innerHTML;
	//alert("daughter:"+daughter);
	//alert("id:"+articleid);
	//判断是执行删除还是修改
	if(daughter==1){
		AjaxSend(3);
	}else{
		//执行修改
		AjaxSend(4);
	}
	
}

//删除文章请求
/*
 * 这里就是从全局变量获取来id，然后构造url发送出去
 * 
 */
function delArticleRequest(){
	//alert("全局变量："+articleid);
	var url = "braServlet?functionChoise=delArticle&&articleid="+articleid;
	//alert(url);
	return url;
}

//删除文章返回结果
/*也是json对象结果success：1或0，1弹窗显示并刷新页面，0也是
 * 
 */
function delArticleResponse(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
		alert("删除成功！！！！");
		//刷新页面
		location.reload();
	}else{
		alert("删除失败！！！！");
	}
}

//修改文章请求（实际是获取单篇文章内容）
/*修改文章分两部分，1是先获取文字内容，2 才是下面的修改完成后再发起修改请求
 * 一样构建url
 */
function alterArticleRequest(){
	var url  = "braServlet?functionChoise=selectSingleArticle&&articleid="+articleid;
	return url;
}

//修改文章(获取单篇文章返回内容)返回结果
/*
 * 这里获取内容用json传递，传递查询结果成功，再加上标题，内容并放到对应位置
 * 并跳转到发表文章界面，修改发表文章按钮为修改文章按钮
 */
function alterArticleResponse(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
//		alert("获取成功！！！！");
//		alert(jsonResponse.head);
//		alert(jsonResponse.content);
		//跳转到发表文章div,直接调用切换函数
		tabPbArticleManageArticle(1);
		//显示修改文章按钮，隐藏发表文章按钮
		document.getElementById('alterArticleButton').style.display='block';
		document.getElementById('publicArticleButton').style.display='none';
		//将获取的json数据传入对应的位置
		document.getElementById('articleHead').value=jsonResponse.head;
		editor.txt.html(jsonResponse.content);
	}else{
		alert("获取失败！！！！");
	}
}

//真的发起修改文章请求
/*
 * 这里其实就是和发表文章一样的构造，只是执行函数不一样，到时要是合并的话..
 * 而且这里其实应该多一个判断内容和原来一样，那也没必要修改啊****没做
 */
function alterArticleRealRequest(){
	//获取判断,不正常弹窗显示
	if(document.getElementById('articleHead').value==''){
		alert("标题："+articleHead);
		alert("标题不能为空啊！！！");
	}else{
		//内容为空的时候获取到的是<p><br></p>..所以我这样判断了...
		if(editor.txt.html()=='<p><br></p>'){
			alert("内容："+articleContent);
			alert("内容不能为空啊！！！");
		}else{
			//都正常执行发起请求
			var articleId = articleid;
			var articleHead = document.getElementById('articleHead').value;
			var articleContent = editor.txt.html();
			//alert("标题："+articleHead);
			//alert(typeof articleContent);
			var url =  "braServlet?functionChoise=alterArticle&&articleHead="+articleHead+"&&articleContent="+articleContent+"&&articleId="+articleId;
			//alert(encodeURI(url));
			return encodeURI(url);
		}
	}
}

//真的发起文章修改获得的结果
function alterArticleRealResponse(u){
	//将内容转为json对象
	var jsonResponse = eval("("+u+")");
	if(jsonResponse.success==1){
		alert("修改成功！！！！");
		//刷新页面
		location.reload();
	}else{
		alert("修改失败！！！！");
	}	
}

//************Ajax请求 结束************


//切换发表文章和管理文章的div
/*
 * 传入	参数1切换到发表文章
 * 		参数2切换到管理文章（默认显示）
 */
function tabPbArticleManageArticle(n){
	if(n==1){
		//隐藏管理文章div
		document.getElementById('articleManageContainer').style.display='none';
		//显示发表文章div
		document.getElementById('editorContainer').style.display='block';
		//显示发表文章按钮，隐藏修改文章按钮
		document.getElementById('publicArticleButton').style.display='block';
		document.getElementById('alterArticleButton').style.display='none';
	}else{
		//显示管理文章div
		document.getElementById('articleManageContainer').style.display='block';
		//隐藏发表文章div
		document.getElementById('editorContainer').style.display='none';
	}
}


//获取wang富文本内容测试
//function getWangEditor(){
//	 document.getElementById('testSaveContent').innerHTML=editor.txt.html();
//}



//测试项
//function testGetPath(){
//
//	var xmlHttp = initAjax();
//	 var url  = "braServlet?functionChoise=testGetPath";
//	 //开启ajax异步
//	 xmlHttp.open("post",url,true);
//	 //开始执行
//	 xmlHttp.onreadystatechange = function (){
//		 	if(xmlHttp.readyState == 4){
//		 		alert("返回的测试数据:"+xmlHttp.responseText);
//		 		var u = xmlHttp.responseText;
//		 	}
//		 }
//	 xmlHttp.send();
//}



///**********页面加载时执行的操作

//页面加载时判断字段为Login Success则不弹出登入框
onload = function(){
	//执行查询文章函数
	AjaxSend(1);
	//执行检查session是否存在函数
	enterCheckSession();
	
}

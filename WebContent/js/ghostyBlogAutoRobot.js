//主页内容距离顶部/导航栏变化自适应函数
/*
 * 当屏宽大于平板宽度768时
 * 		距离顶部xx
 * 		显示pc版导航栏标题
 * 
 *  	隐藏手机版导航栏标题
 * 
 * 		显示x-large字体大小
 * 小于
 * 		距离顶部为0
 *  	隐藏pc版导航栏标题
 * 
 *  	显示手机版导航栏标题
 * 		显示large字体大小
 * 
 * 微调后为1020
 * 
 * 单独的导航元素当宽度<400多的时候就距离左边xxpx,暂时好像不需要解决了
 */
function autoMarginTop(){
	if(window.innerWidth>1020){
		//距离顶部
		document.getElementById('outestContainer').style.marginTop='80px';
		//导航栏变化
		document.getElementById('phoneNavBarEdition').style.display='none';
		document.getElementById('pcNavBarEdition').style.display='block';
		//内容里的字体为
		document.getElementById('leftContent').style.fontSize='large';
		//导航栏元素为large
		document.getElementById('navbarElementValue1').style.fontSize='large';
		document.getElementById('navbarElementValue2').style.fontSize='large';
		document.getElementById('navbarElement').style.margin='10px 0px 6px 0px';
	}else{
		//距离顶部
		document.getElementById('outestContainer').style.marginTop='0px';
		//导航栏变化
		document.getElementById('pcNavBarEdition').style.display='none';
		document.getElementById('phoneNavBarEdition').style.display='block';
		//内容里的字体为
		document.getElementById('leftContent').style.fontSize='medium';
		//导航栏元素 不为large,距离左边xxx和文章列表对齐
		document.getElementById('navbarElementValue1').style.fontSize='medium';
		document.getElementById('navbarElementValue2').style.fontSize='medium';
		document.getElementById('navbarElement').style.margin='10px 0px 6px 10px';
		
	}
//	if(window.innerWidth<400){
//		
//	}
}



//每隔1秒执行一遍自适应函数
setInterval(
	function(){
		autoMarginTop();
	}, 1000
);

//页面加载后运行
window.onload = function(){
	
	autoMarginTop();
	setInterval();
	
}

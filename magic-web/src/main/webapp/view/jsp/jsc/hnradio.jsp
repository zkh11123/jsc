<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="Keywords" content="${keyword}" />
<meta name="Description" content="${keyword}_${infoWord }" />
<title>${keyword}/${keyword}_ 百度 _ 知道</title>
<link href="http://news.hnradio.com/news.css" rel="stylesheet"
	type="text/css" />
<link href="http://www.hnradio.com/main.css" rel="stylesheet"
	type="text/css" />
<script>
	(function() {
		var bp = document.createElement('script');
		var curProtocol = window.location.protocol.split(':')[0];
		if (curProtocol === 'https') {
			bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
		} else {
			bp.src = 'http://push.zhanzhang.baidu.com/push.js';
		}
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(bp, s);
	})();
</script>
</head>
<body>
	<div id="top1">
		<div id="top1_a">
			<a href="http://www.hnradio.com/broadcast" target="_blank"
				class="nnaa">打开FM播放器</a>
		</div>
	</div>
	<div id="top2">
		<div id="top2_l">
			<img src="http://www.hnradio.com/image2014/new_logo.gif" width="77"
				height="94">
		</div>
		<!-- new2_l -->
		<div id="top2_r">
			<div id="top2_r_1">
				<div id="top2_r_1_a">
					<a href="http://fm918.cn/" target="_blank">湖南交通频道</a> | <a
						href="http://fm893.hnradio.com/" target="_blank">893汽车音乐</a> | <a
						href="http://www.news938.cn/" target="_blank">News938潇湘之声</a> | <a
						href="http://fm1028.hnradio.com/" target="_blank">新闻综合频道</a> <br>
					<a href="http://fm975.hnradio.com/" target="_blank">青春975</a> | <a
						href="http://fm901.hnradio.com/" target="_blank">湖南经广</a> | <a
						href="http://fm1069.hnradio.com/" target="_blank">乐田1069</a> | <a
						href="http://www.ting955.com/" target="_blank">金鹰955</a>
				</div>
				<div id="top2_r_1_b">
					<form id="form2" name="form2" method="post" action=""
						style="margin: 0px; padding: 0px; width: 240px;">
						<input name="textfield" type="text" id="textfield" value="搜索"
							class="sousuo"><input type="submit" name="search_B"
							id="search_B"
							style="border: none; width: 31px; height: 28px; background-image: url(http://www.hnradio.com/image2014/new_5.gif); border: 0px;"
							value=" ">
					</form>
				</div>
			</div>
			<!-- new2_r_1 -->
			<div id="top2_r_2"></div>
			<!-- new2_r_2 -->
			<div id="top2_r_3">
				<div class="top2_r_3_b" id="dh1" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/program/" target="_blank">节目</a>
				</div>
				<div id="top2_r_3_">
					<img src="http://www.hnradio.com/image2014/new_2.gif" width="1"
						height="36">
				</div>
				<div class="top2_r_3_b" id="dh2" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/hot/" target="_blank">热点</a>
				</div>
				<div id="top2_r_3_">
					<img src="http://www.hnradio.com/image2014/new_2.gif" width="1"
						height="36">
				</div>
				<div class="top2_r_3_a" id="dh3" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/activity/" target="_blank">活动</a>
				</div>
				<div id="top2_r_3_">
					<img src="http://www.hnradio.com/image2014/new_2.gif" width="1"
						height="36">
				</div>
				<div class="top2_r_3_b" id="dh4" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/dj/" target="_blank">主持人</a>
				</div>
				<div id="top2_r_3_">
					<img src="http://www.hnradio.com/image2014/new_2.gif" width="1"
						height="36">
				</div>
				<div class="top2_r_3_b" id="dh5" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/broadcast/" target="_blank">直播</a>
				</div>
				<div id="top2_r_3_">
					<img src="http://www.hnradio.com/image2014/new_2.gif" width="1"
						height="36">
				</div>
				<div class="top2_r_3_b" id="dh6" onmousemove="pua(this)">
					<a href="http://www.hnradio.com/about" target="_blank">关于电台</a>
				</div>
			</div>
			<!-- new2_r_3 -->
		</div>
		<!-- new2_r -->
	</div>
	<div id="jj_0"></div>
	<!-- jj_0 -->
	<div id="jj">
		<div id="jj_l">
			<div id="jj_l_1">
				<a href="http://www.hnradio.com" target="_blank">芒果广播网</a>&nbsp;&nbsp;<a
					href="http://www.hnradio.com" target="_blank">新闻</a>&gt;&gt;<a href="" target="_blank">${head }</a>>>正文
			</div>
			<!-- jj_l_1 -->
			<!-- jj_l_2 -->
			<div id="jj_l_3"></div>
			<!-- jj_l_3 -->
			<div id="jj_l_4" class="tt2">${head}</div>
			<!-- jj_l_4 -->
			<div id="jj_l_6">
				<div id="src">发稿时间:${time}</div>
				<div>
					<p>
						<span class="zi12 STYLE1"> <b
							style="color: black; background-color: #99ff99">${keyword}${infoWord} <a name="baidusnap1"></a> <b
								style="color: black; background-color: #A0FFFF">全国均可办理</b>,质量保证。████████████
								<a name="baidusnap2"></a>.
						</b>
						</span>
					</p>
				</div>
				${article} <br />
				<div></div>
				<div id="jj_l_8">来源:百度新闻源 责编:周斌&nbsp;</div>
				<!-- jj_l_8 -->
			</div>
			<!-- jj_l_6 -->
			<div id="jj_l_7"></div>
			<!-- jj_l_7 -->
			<div id="jj_l_9">
				<img src="http://www.hnradio.com/image2014/new_arr.gif" width="26"
					height="25" align="middle" /><strong>&nbsp;&nbsp;关键字：${keyword}</strong>
				&nbsp;
			</div>
			<!-- jj_l_9 -->
			<div id="jj_l_10" style="height:300px;">
				<div style="padding-left: 20px;">
					<ul>
						<c:forEach items="${slink }" var="linkmap">
							<li>.<a href="${linkmap.link }">${linkmap.kw }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- jj_l_10 -->
		</div>
	</div>
	<!-- jj -->
	<div id="footer">
		<div id="top4"></div>
		<!-- new4 -->
		<div id="top5">
			<div id="top5_2">
				<div id="top5_2_a">
					<img src="http://www.hnradio.com/image2014/new_7.gif" width="70"
						height="17"><br>
					<br>
					<a href="http://fm918.cn/" target="_blank">湖南交通频道</a><br>
					<a href="http://fm893.hnradio.com/" target="_blank">893汽车音乐</a><br>
					<a href="http://www.news938.cn/" target="_blank">News938潇湘之声</a><br>
					<a href="http://fm1028.hnradio.com/" target="_blank">新闻综合频道</a> <br>
					<a href="http://fm975.hnradio.com/" target="_blank">青春975</a><br>
					<a href="http://fm901.hnradio.com/" target="_blank">湖南经广</a><br>
					<a href="http://fm1069.hnradio.com/" target="_blank">乐田1069</a><br>
					<a href="http://www.ting955.com/" target="_blank">金鹰955</a><br>
				</div>
				<div id="top5_2_line"></div>
				<div id="top5_2_b">
					<img src="http://www.hnradio.com/image2014/new_8.gif" width="69"
						height="17"><br>
					<br>地址：湖南省长沙市开福区洪山桥街道金鹰影视文化城 广播传媒中心大楼<br>邮箱：mgradio@sina.com<br>官方微博：<a
						href="http://weibo.com/xhzgj2" target="_blank">湖南电台</a>
				</div>
				<div id="top5_2_line"></div>
				<div id="top5_2_c">
					<img src="http://www.hnradio.com/image2014/new_9.gif" width="70"
						height="17"><br>
					<br>芒果Radio手机APP <br>
					<a
						href="https://itunes.apple.com/cn/app/mang-guoradio/id627880122?mt=8"
						target="_blank" class="foot2">IOS版</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;<a
						href="http://zhushou.360.cn/detail/index/soft_id/700324?recrefer=SE_D_%E8%8A%92%E6%9E%9Cradio"
						target="_blank" class="foot2">安卓版</a><br>
					<img src="http://www.hnradio.com/image2014/aPPLE.PNG" width="100"
						height="100"> &nbsp; <img
						src="http://www.hnradio.com/image2014/ANDRIOD.PNG" width="100"
						height="100">
				</div>
				<div id="top5_2_line"></div>
				<div id="top5_2_d">
					<img src="http://www.hnradio.com/image2014/new_10.gif" width="70"
						height="17"><br>
					<br>
					<a href="http://www.wmwz.tv/" class="foot" target="_blank">为民网</a><br>
					<a href="http://hunan.voc.com.cn/" class="foot" target="_blank">湖南在线</a><br>
					<a href="http://www.rednet.cn/" class="foot" target="_blank">红网</a><br>
					<a href="http://www.ynradio.com/" class="foot" target="_blank">云南人民广播电台</a><br>
					<a href="http://www.ccradio.cn/" class="foot" target="_blank">长春广播网</a>
				</div>
			</div>
			<!-- new5_2 -->
		</div>
		<!-- new5 -->
		<div id="top6">
			<div id="top6_1">
				《中国互联网视听节目服务自律公约》 | 信息网络传播视听节目许可证号：1808280 | <a
					href="http://www.miibeian.gov.cn/" target="_blank" class="foot">湘ICP备12004976号-3</a>
				| 版权所有：<a href="http://www.hnradio.com/" class="foot">芒果广播网 </a> |
			</div>
			<div style="DISPLAY: none;">
				<script
					src="http://s95.cnzz.com/stat.php?id=840924&amp;web_id=840924"
					language="JavaScript"></script>
				<script src="http://c.cnzz.com/core.php?web_id=840924&amp;t=z"
					charset="utf-8" type="text/javascript"></script>
				<a href="http://www.cnzz.com/stat/website.php?web_id=840924"
					target="_blank" title="站长统计">站长统计</a>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发布博文</title>
    </head>
    <script src="../static/js/jquery-1.12.1.js"></script>
	<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/addbootstrap.css" />
	<script src="../static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<link href="../static/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="../static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="../static/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="../static/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="../static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <body>
    	<div class="container">
    	<div class="col-md-12">   		
				<img class="img-responsive" src="../img/head.png"> 
		</div>
		<div class="col-md-12">
			<nav class="navbar navbar-inverse">
			  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			    	<a class="navbar-brand" href="${pageContext.request.contextPath}/index/homepage.jsp"><font color="#87FA38" size="5">鹊谷社区</font></a>
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">鹊谷写博</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			    </div>
			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
				        <li><a
							href="${pageContext.request.contextPath}/topicServlet?method=lists">&nbsp;&nbsp;论坛
								<span class="sr-only">(current)</span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/articleServlet?method=lists">&nbsp;&nbsp;博文</a></li>
				      </ul>
				      <div class="collapse navbar-collapse" align="right" style="margin-top:15px">
						<p><font color="white">欢迎你，</font><a href="${pageContext.request.contextPath}/userServlet?method=userself&&username=${userBean.username}&&password=${userBean.password}"><font color="#87FA38">${userBean.nickname }</font></a></p>
					</div>
			     <!--  <form class="navbar-form navbar-right">
			        <div class="form-group">
			          <input type="text" class="form-control" placeholder="Search">
			        </div>
			        <button type="submit" class="btn btn-default">google</button>
			      </form> -->
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
		</div>
		<div class="col-md-12">
		<form  role="form"  id="checkForm" class="form-horizontal" action="${pageContext.request.contextPath }/articleServlet?method=add&&userid=${userBean.id}" method="post">	
	    	<div class="form-group col-md-12">
	    		<div class="col-md-12">
	    		<label> 标题：</label>
	    		</div>
	    		<div class="col-md-12">
	    		<input type="text" class="form-control" name="title">
	    		</div>
	    	</div>
	    	<div class="form-group col-md-12" >
	    		<div class="col-md-12">
	    		<label> 内容发布：</label>
	    		</div>
				<div class="col-md-12" style="margin: 20px auto 40px;" align="center">
					<textarea name="intro" id="myEditor"></textarea>
				</div>
			</div>
			<div class="form-group col-md-12" >
				<div class="col-md-12">
	    		<label> 内容摘要：</label>
	    		</div>
				<div class="col-md-12"  >
					<textarea  name="information" id="information" style="width: 100%;height: 100px;" onclick="document.getElementById('information').value=UE.getEditor('myEditor').getPlainTxt().substring(0, 100)+'...'">
					</textarea>
				</div>
			</div>
			<div class="form-group col-md-12">
				<div class="col-md-3 col-md-offset-0">
					<input class="form-control btn btn-black" type="submit" value="发布">
				</div>
			</div>
		</form>
		</div>
		<hr>
    	<script type="text/javascript">
			var ue = UE.getEditor('myEditor', {
		        initialFrameHeight: 500,
		        initialFrameWeight: 100,
		        maximumWords: 3000 
   			});
		</script>
		 <c:if test="${param.status.equals('0')}">
		    <script type="text/javascript"> //JavaScript脚本标注
				alert("添加成功");//在页面上弹出
			</script>     			
		</c:if>
		<c:if test="${param.status.equals('1')}">
		    <script type="text/javascript"> //JavaScript脚本标注
				alert("添加失败");//在页面上弹出
			</script>     			
		</c:if>
		</div>
 	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>鹊谷博文</title>
        <script type="text/javascript" src="../static/bootstrap-3.3.7-dist/js/bootstrap.js" ></script>
		<link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/bootstrap.css" />
		<link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/addbootstrap.css" />
		<script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>
    	<style type="text/css">
    		.back{
    			background-color: #87FA38;
    		}
    	</style>
    </head>
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
				        <span class="sr-only">Toggle navigation</span>
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
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>
			</div>
			<div class="col-md-9">
				<div class="panel panel-black" >
				    <div class="panel-heading" >
					      <div class="" style="margin-top: -10px;margin-bottom: 10px;">
							  <h3>${articalBean.title }</h3>
						 </div>
						
				    </div>
				    <div class="panel-body">
				       <small><font size="4">${articalBean.content } </font></small> 
				    </div>
				     
				</div>
				<hr>
				<div class="col-md-12" style="margin-top: 30px;margin-bottom: 20px;">
				<font size="3">最新评论：</font>
				</div>
				
				<ul class="list-group col-md-12">
					<c:forEach items="${contains}" var="item" varStatus="status">
				    <li class="list-group-item">
					    <div class="panel-body" style="margin-bottom: -20px;">
					      	<div class="page-header" style="margin-top: -10px;">
							    <h4>${item.userBean.nickname }
							    </h4>
							</div>
							<p>${item.content }</p>
					    </div>
					</li>
					</c:forEach>
					<!-- <li class="list-group-item">
					     <div class="panel-body" style="margin-bottom: -20px;">
					      	<div class="page-header" style="margin-top: -10px;">
							    <h4>随风的叶子：
							    </h4>
							</div>
							<p>这是一个评论，这是一个评论，这是一个评论，这是一个示例文本。</p>
					    </div>
					</li>
					<li class="list-group-item">
					     <div class="panel-body" style="margin-bottom: -20px;">
					      	<div class="page-header" style="margin-top: -10px;">
							    <h4>随风的叶子：
							    </h4>
							</div>
							<p>这是一个评论，这是一个评论，这是一个评论，这是一个示例文本。</p>
					    </div>
					</li> -->
				</ul>
				<form action="${pageContext.request.contextPath}/articleServlet?method=addcontain&&articleid=${articalBean.id}&&userid=${userBean.id}" method="post">
					<div class="col-md-12">		
						  <div class="form-group">
						    <label for="name">发表评论</label>
						    <textarea name="content" class="form-control" rows="5"></textarea>
						  </div>
					</div>
					<div class="col-md-12" style="margin-bottom: 20px;">
						<div class="col-md-10"></div>
						<div class="col-md-2">
						<button type="button" class="btn  btn-small btn-block btn-black" onclick="this.form.submit()">
						    <span class="glyphicon glyphicon-edit">
						    <font color="#87FA38">发表</font>
						    </span>
						</button>
						</div>
					</div>
				</form>
				<div class="col-md-12" style="margin-top: 50px;margin-bottom: 20px;">
				<hr>
				</div>
			</div >
			<div class="col-md-3">
				<div class="panel panel-default">
				    <div class="panel-body">
				       <table  class="table table-bordered" >
				       <div class="col-md-12" align="center">
				       		<img src="../img/head.png" class="img-circle" width="70px" height="70px">
				       </div>
				       <div class="col-md-12" style="margin-top: 20px;">
					       <div class="col-md-12" align="center">
					          ${articalBean.userBean.nickname }
					       </div>
					       <div class="col-md-12" style="margin-top: 20px;">
					       	   <button type="button" class="btn  btn-lg btn-block btn-black">
							        <span class="glyphicon glyphicon-plus"><font color="#87FA38">&nbsp;&nbsp;关注&nbsp;&nbsp;</font></span>
							    </button>
				      	   </div>
				       </div>
				       
				       </table>
				       <hr>
				       <table  class="table table-bordered" >
				       <tr>
					       <td>原创</td>
					       <td>关注</td>
					       <td>被关注</td>
				       </tr>
				       <tr>
					       <td>5</td>
					       <td>10</td>
					       <td>25</td>
				       </tr>
				       </table>
				    </div>
				</div>
				<div class="panel panel-default">
					<a href="#" class="list-group-item active1">
					    <font color="#87FA38">他的最新文章
					</a>
					<c:forEach items="${newArticleBeans}" var="item" varStatus="status">
					<a href="${pageContext.request.contextPath}/articleServlet?method=details&id=${item.id }" class="list-group-item">${item.title }</a>
					<!-- 
					<a href="#" class="list-group-item">免费 Window 空间托管</a>
					<a href="#" class="list-group-item">图像的数量</a>
					<a href="#" class="list-group-item">每年更新成本</a> -->
					</c:forEach>
				</div>
			</div>
		</div>
		<c:if test="${param.status.equals('1')}">
			<script language="javascript"> //JavaScript脚本标注
			alert("已评论");//在页面上弹出
			</script>
		</c:if>
 	</body>
</html>
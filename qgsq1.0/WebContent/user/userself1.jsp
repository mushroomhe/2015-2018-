<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人中心</title>
         <script type="text/javascript" src="../static/bootstrap-3.3.7-dist/js/bootstrap.js" ></script>
		<link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/bootstrap.css" />
		<link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/addbootstrap.css" />
		<script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
				    	<a class="navbar-brand" href="#"><font color="#87FA38" size="5">雀谷个人中心</font></a>
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
				        <li><a  href="${pageContext.request.contextPath}/index/homepage.jsp">&nbsp;&nbsp;返回首页<span class="sr-only">(current)</span></a></li> 
				        <li><a href="${pageContext.request.contextPath}/topicServlet?method=lists">&nbsp;&nbsp;论坛
								<span class="sr-only">(current)</span>
						</a></li>
						<li><a href="${pageContext.request.contextPath}/articleServlet?method=lists">&nbsp;&nbsp;博文</a></li>
				      </ul>
				       <div class="collapse navbar-collapse" align="right" style="margin-top:15px">
						<p><a href="${pageContext.request.contextPath}/userServlet?method=end"><font color="#87FA38">退出登录</font></a></p>
					</div>
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>
			</div>
			<!--//个人信息页-->
			<div class="col-md-9">
				<div class="panel panel-default">
				    <div class="panel-body">
				    	<div class="col-md-6">
					       <table  class="table table-bordered"  >
					       <div class="col-md-6" align="center">
						       <div class="col-md-12">
						       		<img src="../img/head.png" class="img-circle" width="70px" height="70px">
						       </div>    
						       <div class="col-md-12" style="margin-top: 20px;">
								    <div class="col-md-12" align="center">
							                        随风的叶子
							       	</div>
						       	   
					      	   </div>
					       </div>
					       </table>
				       </div>
				       <div class="col-md-6" style="margin-top: 20px;">
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
					    <div class="col-md-offset-10">
					    	 <span class="glyphicon glyphicon-pencil"></span>
					    	修改个人信息
					    </div>
				    </div>
				</div>
			</div>
			<div class="col-md-3" style="margin-top: 10px;">
				<div class="panel panel-default ">
				    <div class="panel-body ">
				        <button type="button" class="btn  btn-lg btn-block btn-black">
					        <span class="glyphicon glyphicon-edit"><font color="#87FA38">&nbsp;&nbsp;发布话题&nbsp;&nbsp;</font></span>
					    </button>
				    </div>
				    <div class="panel-body ">
				        <button type="button" class="btn btn-lg btn-block btn-black">
					        <span class="glyphicon glyphicon-book"><font color="#87FA38">&nbsp;&nbsp;发布博文&nbsp;&nbsp;</font></span>
					    </button>
				    </div>
				</div>
			</div>
			<hr>
			<!--tab显示界面-->
			<div class="col-md-12">
				
				<ul id="myTab" class="nav nav-tabs">
			    <li class="active">
			    	<a href="#home" data-toggle="tab"><font size="4">发贴管理</font></a>
			    </li>
			    <li>
			    	<a href="#ios" data-toggle="tab"><font size="4">博文管理</font></a>
			    </li>
			    
				</ul>
				<div id="myTabContent" class="tab-content">
				    <div class="tab-pane fade in active" id="home" style="margin-top: 20px;">
				        <table class="table">
						  <thead>
						    <tr>
						      <th>帖子标题</th>
						      <th>发帖日期</th>
						      <th>修改</th>
						      <th>删除</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr class="active">
						      <td>如何将java的环境进行配置？</td>
						      <td>23/11/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						    <tr class="success">
						      <td>如何了解c#领域的行业知识？</td>
						      <td>10/11/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						    <tr class="warning">
						      <td>为什么我们不是程序员大神？</td>
						      <td>20/10/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						   
						  </tbody>
						</table>
				    </div>
				    
				    <div class="tab-pane fade" id="ios" style="margin-top: 20px;">
				        <table class="table">
						  <thead>
						    <tr>
						      <th>博文标题</th>
						      <th>发布日期</th>
						      <th>修改</th>
						      <th>删除</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr class="active">
						      <td>如何将java的环境进行配置？</td>
						      <td>23/11/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						    <tr class="success">
						      <td>如何了解c#领域的行业知识？</td>
						      <td>10/11/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						    <tr class="warning">
						      <td>为什么我们不是程序员大神？</td>
						      <td>20/10/2013</td>
						      <th><span class="glyphicon glyphicon-pencil"></span></th>
						      <th><span class="glyphicon glyphicon-trash"></th>
						    </tr>
						   
						  </tbody>
						</table>
				    </div>
				    
				</div>
				<script>
					$(function () {
						$('#myTab li:eq(1) a').tab('show');
					});
				</script>
			</div>
			<hr>
    	</div>
		
 	</body>
</html>
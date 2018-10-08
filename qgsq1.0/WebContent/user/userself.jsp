<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080/QueBird/util" prefix="util"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<script type="text/javascript"
	src="../static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<link rel="stylesheet"
	href="../static/bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="../static/bootstrap-3.3.7-dist/css/addbootstrap.css" />
<script type="text/javascript" src="../static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("input[type='radio'][name=sex][value='${userBean.sex}']")
						.attr("checked", true);
			});
</script>
<!-- 
<script type="text/javascript">
//触发模态框的同时调用此方法  
function editInfo(obj) {  
    var id = $(obj).attr("id");  
    //获取表格中的一行数据  
    var sex = document.getElementById("table").rows[id].cells[3].innerText;  
    //向模态框中传值  
    $('#stuno').val(stuno);  
    $('#pass').val(pass);  
    $('#stuname').val(name);  
    if (sex == '女') {  
        document.getElementById('women').checked = true;  
    } else {  
        document.getElementById('man').checked = true;  
    }  
    $('#update').modal('show');  
}  
//提交更改  
function update() {  
    //获取模态框数据  
    var stuno = $('#stuno').val();  
    var pass = $('#pass').val();  
    var name = $('#stuname').val();  
    var sex = $('input:radio[name="sex"]:checked').val();  
    $.ajax({  
        type: "post",  
        url: "update.do",  
        data: "stuno=" + stuno + "&pass=" + pass + "&name=" + name + "&sex=" + sex,  
        dataType: 'html',  
        contentType: "application/x-www-form-urlencoded; charset=utf-8",  
        success: function(result) {  
            //location.reload();  
        }  
    });  
}  
</script>
 -->
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
						<a class="navbar-brand" href="#"><font color="#87FA38"
							size="5">鹊谷个人中心</font></a>
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a
								href="${pageContext.request.contextPath}/index/homepage.jsp">&nbsp;&nbsp;返回首页<span
									class="sr-only">(current)</span></a></li>


						</ul>
						 <div class="collapse navbar-collapse" align="right" style="margin-top:15px">
						<p><a href="${pageContext.request.contextPath}/userServlet?method=end&&status=1"><font color="#87FA38">退出登录</font></a></p>
						</div>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
		<!--//个人信息页-->
		<div class="col-md-9">
			<div class="panel panel-default">
				<c:if test="${userBean!=null }">
					<div class="panel-body">
						<div class="col-md-7">
							<table class="table table-bordered">
								<div class="col-md-6" align="center">
									<div class="col-md-12">
										<div class="col-md-12">
										<img src="${userBean.picture }" alt="头像" class="img-circle"
											width="70px" height="70px">
										</div>
										<div class="col-md-12">
										<form enctype="multipart/form-data" role="form"
											action="${pageContext.request.contextPath}/userServlet?method=add&id=${userBean.id}"
											method="post" id="checkForm">
											<input type="file" class="form-control" name="picture"
												id="picture" placeholder="picture" />
												 <input type="submit" class="btn  btn-md btn-block btn-black" value="更换头像"></input>
										</form> 
										</div>
									</div>
									<div class="col-md-12" style="margin-top: 20px;">
										<div class="col-md-12" align="center">
											${userBean.nickname}</div>

									</div>
								</div>
							</table>
						</div>
						<div class="col-md-5" style="margin-top: 20px;">
							<table class="table table-bordered">
								<tr>
									<td>原创</td>
									<td>关注</td>
									<td>被关注</td>
								</tr>
								<tr>
									<td>${articlenumber}</td>
									<td>10</td>
									<td>25</td>
								</tr>
							</table>
						</div>
						<div class="col-md-offset-10">
							<span class="glyphicon glyphicon-pencil" data-toggle="modal"
								data-target="#myModal"></span> 修改个人信息
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="col-md-3" style="margin-top: 10px;">
			<div class="panel panel-default ">
				<div class="panel-body ">
					<a href="${pageContext.request.contextPath}/user/addtie.jsp">
						<button type="button" class="btn  btn-lg btn-block btn-black">
							<span class="glyphicon glyphicon-edit"><font
								color="#87FA38">&nbsp;&nbsp;发布话题&nbsp;&nbsp;</font></span>
						</button>
					</a>
				</div>
				<div class="panel-body ">
					<a href="${pageContext.request.contextPath}/user/addbowen.jsp">
						<button type="button" class="btn btn-lg btn-block btn-black">
							<span class="glyphicon glyphicon-book"><font
								color="#87FA38">&nbsp;&nbsp;发布博文&nbsp;&nbsp;</font></span>
						</button>
					</a>
				</div>
			</div>
		</div>
		<hr>
		<!--tab显示界面-->
		<div class="col-md-12">

			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a href="#home" data-toggle="tab"><font
						size="4">发贴管理</font></a></li>
				<li><a href="#ios" data-toggle="tab"><font size="4">博文管理</font></a>
				</li>

			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="home"
					style="margin-top: 20px;">
					<table class="table">
						<thead>
							<tr>
								<th>帖子标题</th>
								<th>发帖日期</th>
								<th>删除</th>
							</tr>
						</thead>
						<c:forEach items="${toList}" var="item" varStatus="status">
							<tbody>
								<tr class="active">
									<td>${item.title}</td>
									<td>${item.datetime}</td>
									<th><span class="glyphicon glyphicon-trash"></th>
								</tr>
								<!-- <tr class="active">
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
						    -->
							</tbody>
						</c:forEach>
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
						<c:forEach items="${boList}" var="item" varStatus="status">
							<tbody>
								<tr class="active">
									<td>${item.title}</td>
									<td>${item.datetime}</td>
									<th><a href="${pageContext.request.contextPath}/articleServlet?method=beforchange&&articleid=${item.id}&&username=${userBean.username}&&password=${userBean.password}"><span class="glyphicon glyphicon-pencil"></span></a></th>
									<th><span class="glyphicon glyphicon-trash"></th>
								</tr>
								<!-- <tr class="active">
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
						     -->
							</tbody>
						</c:forEach>
					</table>
				</div>

			</div>
			<script>
				$(function() {
					$('#myTab li:eq(1) a').tab('show');
				});
			</script>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
					</div>
					<form
						action="${pageContext.request.contextPath}/userServlet?method=update&id=${userBean.id }"
						method="post" id="checkForm">
						<div class="modal-body">
							<div class="form-group">
								<label for="username">用户名:${userBean.username}</label>
							</div>
							<div class="form-group">
								<label for="nickname">昵称:</label> <input type="text"
									class="form-control" name="nickname" id="nickname"
									placeholder="nickname" value="${userBean.nickname}">
							</div>
							<div class="form-group">
								<label for="password">密码: </label> <input type="password"
									class="form-control" name="password" id="password"
									placeholder="password" value="${userBean.password}">
							</div>
							<div class="form-group">
								<label for="password2">重新输入密码:</label> <input type="password"
									class="form-control" name="password2" id="password2"
									placeholder="password" value="${userBean.password}">
							</div>
							<div class="form-group">
								<label for="sex">性别:</label> <label><input type="radio"
									name="sex" class="sex" id="sex" value="男">
									男&nbsp;&nbsp;&nbsp;</label><label><input type="radio"
									name="sex" class="sex" id="sex" value="女"> 女</label>
							</div>
							<div class="form-group">
								<label for="age">年龄:</label> <input type="text"
									class="form-control" name="age" id="age" placeholder="age"
									value="${userBean.age }">
							</div>
							<div class="form-group">
								<label for="edugrade">学历:</label> <input type="text"
									class="form-control" name="edugrade" id="edugrade"
									placeholder="edugrade" value="${userBean.edugrade }">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">提交更改</button>
						</div>
					</form>
				</div>

				<!-- /.modal-content -->

			</div>
			<!-- /.modal -->

		</div>
		<hr>
	</div>
	 <c:if test="${param.status.equals('1')}">
		    <script type="text/javascript"> //JavaScript脚本标注
				alert("修改成功");//在页面上弹出
			</script>     			
		</c:if>
</body>
</html>
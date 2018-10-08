<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鹊谷登录</title>
</head>
<style>
   p {font-family:微软雅黑;color:#FFFFFF}
   a {font-family:微软雅黑;color:#FFFFFF}
</style>
<script type="text/javascript">
    $(document).ready(function(){
 
        var strName = localStorage.getItem('keyName');
        var strPass = localStorage.getItem('keyPass');
        if(strName){
            $('#username').val(strName);
        }if(strPass){
            $('#password').val(strPass);
        }
 
    });
 
    function loginBtn_click(){
            var strName = $('#username').val();
            var strPass = $('#password').val();
            localStorage.setItem('keyName',strName);
            if($('#remember').is(':checked')){
                localStorage.setItem('keyPass',strPass);
            }else{
                localStorage.removeItem('keyPass');
                localstorage.clear();
            }
        }
</script>
<body >
<table width="1200" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="650" height="270" background="../images/login_2_1.png">&nbsp;</td>
    <td width="650" rowspan="3"><img src="../images/login_1.png" width="650" height="740"></td>
  </tr>
  <tr>
    <td width="650" height="200" background="../images/login_2_2.png">
	<form name="form1" method="post" action="${pageContext.request.contextPath}/userServlet?method=login" onsubmit="return loginBtn_click();">
	<table width="650" height="120" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td><div align="center">
            <p>账户：<input type="text" autofocus="autofocus" name="username" value=${userBean.username }></p>
          </div>
         </td>
         <td></td>
      </tr>
      <tr>
        <td><div align="center">
          <p>密码：<input type="password" name="password"  value=${userBean.password }> </p>
          
        </div>
        </td>
        <td>
        <p><input type="checkbox" id="remember" checked>记住密码？</p>
        </td>
      </tr>
      <tr>
        <td><div align="center">
            <input type="submit" name="login" value="登录">
            <a href="register.jsp">注册</a>
        </div></td>
      </tr>
    </table>
	</form>
	</td>
  </tr>
  <tr>
    <td background="../images/login_2_3.png">&nbsp;</td>
  </tr>
</table>

<c:if test="${param.status.equals('0')}">
	<script language="javascript"> //JavaScript脚本标注
	alert("账号不存在");//在页面上弹出
	</script>
</c:if>

<c:if test="${param.status.equals('1')}">
	<script language="javascript"> //JavaScript脚本标注
	alert("密码错误");//在页面上弹出
	</script>
</c:if>

<c:if test="${param.status.equals('2')}">
	<script language="javascript"> //JavaScript脚本标注
	alert("账号被冻结");//在页面上弹出
	</script>
</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成为鹊民</title>
</head>
<style type="text/css">
a:link {
	color: #87FA38;
	text-decoration: none;
}

a:visited {
	color: #87FA38;
	text-decoration: none;
}

a:hover {
	color: #87FA38;
	text-decoration: none;
}

a:active {
	color: #87FA38;
	text-decoration: none;
}
</style>
<style>
   p {font-family:微软雅黑;color:#FFFFFF}
</style>
<script>
	function test() 
	{
		var reg =/^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/; 
		//var re=/^(13[0-9]{9})|(15[89][0-9]{8})$/;  
		if (document.form1.username.value.length == 0) { 
			alert("请输入您账号!");
			document.form1.username.focus();
			return false;
		}
		else if(document.form1.nickname.value.length>50)
		{
			alert("不能超过10个字符！");
			document.checkform.nickname.focus();
			return false;
		}
		else if(!reg.test(document.form1.username.value)){      
			alert('请输入正确的手机号码。'); 
			document.form1.username.focus();
			return false;   
		}
		else if (document.form1.password.value.length<6)
		{ 
			alert('密码长度小于6。'); 
			document.form1.password.focus();
			return false; 
		}
		else if(document.form1.password.value!=document.form1.password2.value)
	   {
	    	alert("两次密码不一致!");
	    	document.form1.password2.focus();
	    	return false;
		    /* document.getElementById("see").style.display="block";
		    setTimeout("javascript:document.getElementById('see').style.display='none';",2000);
		    input1.value="";
		    input2.value="";
		    document.getElementById("input1").focus(); */
	   }else
	   {
		   document.form1.submit();
	   }
	}
</script>
<body>
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/userServlet?method=regist" onsubmit="return test()" >
<table width="1200" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td background="../images/login_2.png"><table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="325" height="50"><div align="center">
          <p align="right">账号（使用手机号）：</p>
        </div></td>
        <td>
            <div align="center">
              <input type="text"	 id="username" name="username" maxlength="11">
              </div></td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">昵称：</p>
        </div></td>
        <td>
          <div align="center">
            <input type="text" id="nickname" name="nickname">
            </div></td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">密码：</p>
        </div></td>
        <td>
          <div align="center">
            <input type="password" id="password" name="password">
            </div></td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">确认密码：</p>
        </div></td>
        <td>
          <div align="center">
            <input type="password" id="password2" name="password2">
            </div></td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">性别：</p>
        </div></td>
        <td>
            <p align="center"><input type="radio" name="sex" value="男" checked="checked">
            男<input type="radio" name="sex" value="女">
          女</p>
        </td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">年龄：</p>
        </div></td>
        <td>
          <div align="center">
            <input type="text" id="age" name="age">
            </div></td>
      </tr>
      <tr>
        <td height="50"><div align="center">
          <p align="right">学历：</p>
        </div></td>
        <td>
          <div align="center">
            <input type="text" name="edugrade">
            </div></td>
      </tr>
      <tr>
        <td height="50" >
        <div align="right">
              <input type="submit" name="Submit" value="注册" >
          </div>
       </td>
        <td height="50" >
        <div align="center">
              <p>已有账号？返回<a href="login.jsp">登陆</a></p>
          </div>
       </td>
       
        </tr>
    </table></td>
    <td width="650"><img src="../images/login_1.png" width="650" height="740"></td>
  </tr>
</table>
</form>
<c:if test="${param.status.equals('0')}">
	<script language="javascript"> //JavaScript脚本标注
	alert("账号已存在！");//在页面上弹出
	</script>
</c:if>
<c:if test="${param.status.equals('1')}">
	<script language="javascript"> 
	question = alert("注册成功！") 
	if (question != "0"){ 
	window.open("login.jsp") 
	} 
	</script> 
</c:if>
</body>
</html>
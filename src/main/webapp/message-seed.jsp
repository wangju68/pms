<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发信息</title>
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function(){
        $.ajax({
            type:"get",
            url:"ema/sendEma",
            success:function(msg){
                if((msg.map.statusCode)==("请先登录")){
                    window.location.href="loginUse.jsp";
                    return false;
                }
				if((msg.map.statusCode)=="200"){
					$(msg.map.emails).each(function (index,item) {
						var option = "<option value='"+item.ename+"'>"+item.ename+"</option>";
						$("#enameOpt").append(option);
                    })
				}
            },
            error:function(){
            	alert("失败");
            }
        });
    });

</script>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:信息箱>>发信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" enctype="multipart/form-data" action="ema/saveEma" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;发信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">收件人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="enameOpt" name="ename">
			<option>---请选择---</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="title"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><textarea rows=15 cols=130 name="content"></textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >附件：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<input type="file" name="file"/>
	</td>
</tr>
<tr bgcolor="#FAFAF1">
	<td height="28" colspan=4 align=center>
		&nbsp;
		<input type="submit" value="保存" class="coolbg">
</td>
</tr>
</table>

</form>
  

</body>
</html>
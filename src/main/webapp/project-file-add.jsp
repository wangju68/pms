<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加附件</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function () {
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/pro/selectPname",
			success:function (msg) {
				$(msg).each(function (index,item) {
                	var option = "<option value='"+item.pid+"'>"+item.pname+"</option>";
                	$("#proFk").append(option);
                })
            }
		})
    })
	//文件的上传
	function saveInfo() {
	    var proFk = $("#proFk").val();
	    var attname = $("#attname").val();
	    var attdis = $("#attdis").val();
	    var file = $("#path")[0].files[0];
        var remark = $("#remark").val();

		var formdata = new FormData();
		formdata.append("proFk",proFk);
		formdata.append("attname",attname);
		formdata.append("attdis",attdis);
		formdata.append("file",file);
		formdata.append("remark",remark);
		$.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/att/saveInfo",
            data:formdata,
            cache:false,//不要在浏览器端缓存
            processData:false, //告诉浏览器，不要进行数据转换
            contentType:false, //告诉浏览器，不要设置编码
            success:function () {
                window.location.href="${pageContext.request.contextPath}/project-file.jsp";
            },
            error:function () {
                alert("出现不可预估的错误");
                window.location.href="${pageContext.request.contextPath}/project-file-add.jsp";

            }
        })
    }
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>添加附件
 </td>
 </tr>
</table>
</td>

</tr>
</table>

<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加附件&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proFk" name="proFk">
			<option>---请选择---</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="attname" name="attname" size="26"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="attdis" name="attdis" size="52"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="path" name="path" type="file"/>
	</td>
</tr>


<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea id="remark" name="remark" rows=10 cols=130></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:saveInfo()" class="coolbg">保存</a>
	<a href="project-file.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>
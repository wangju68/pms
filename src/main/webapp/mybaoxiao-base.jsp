<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
        function jump() {
            var jumpNum = $("#jumpNum").val();
            var reg = /^[1-9]\d*$/;
            if(!reg.test(jumpNum)){
                alert("请输入正整数");
                return false;
			}
			window.location.href="bx/myList?pageNum="+jumpNum;
            return false;
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
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<c:import url="searchHead.jsp" />
<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	<c:forEach items="${page.list}" var="bs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>${bs.bxid}</td>
	<td>${bs.totalmoney}</td>
	<td align="center"><a href=''><u> <fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
	<td>${bs.bxremark}</td>

	<c:if test="${bs.bxstatus == 0}">
		<td>未审批</td>
		<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}" style="pointer-events:none;color: grey">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 1}">
		<td>审批通过</td>
		<td><a href="javascript:void(0)">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 2}">
		<td>驳回</td>
		<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 3}">
		<td>审批并付款</td>
		<td><a href="javascript:void(0)" >编辑</a> </td>
	</c:if>

</tr>
	</c:forEach>


<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center">
		<a href="bx/myList?pageNum=1">首页</a>
		<a href="bx/myList?pageNum=${page.pageNum-1}">上一页</a>
		<c:forEach items="${page.navigatepageNums}" var="pageNum">
			<c:if test="${pageNum==page.pageNum}">
				<a style="color: red" href="bx/myList?pageNum=${pageNum}">${pageNum}</a>
			</c:if>
			<c:if test="${pageNum!=page.pageNum}">
				<a href="bx/myList?pageNum=${pageNum}">${pageNum}</a>
			</c:if>
		</c:forEach>
		<a href="bx/myList?pageNum=${page.pageNum+1}">下一页</a>
		<a href="bx/myList?pageNum=${page.pages}">尾页</a>
		&nbsp;&nbsp;<input id="jumpNum" type="text" placeholder="跳转到...页">
		<a onclick="jump()" style="color: blue">跳转</a>
	</td>
</tr>
</table>

</form>
  

</body>
</html>
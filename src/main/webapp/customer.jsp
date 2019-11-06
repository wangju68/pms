<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户信息管理</title>
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
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/customer-add.jsp';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id="form3" name='form3' action='${pageContext.request.contextPath}/cus/select' method='get'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
            <option value='0'>选择类型...</option>
          	<option value='1'>客户所在公司名称</option>
          	<option value='2'>联系人姓名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>时间正序</option>
            <option value='2'>时间倒序</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input id="imgSelect" name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form id="form2" name="form2" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>

    <c:forEach items="${customers}" var="cus" varStatus="index">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input type="checkbox" id="cheId${index.count}" value="${cus.id}" class="np"></td>
            <td>${index.count}</td>
            <td>${cus.comname}</td>
            <td align="center">${cus.companyperson}</td>
            <td>
                <fmt:formatDate value="${cus.addtime}" pattern="yyyy-MM-dd"/>
            </td>
            <td>${cus.comphone}</td>
            <td>
                <a href="${pageContext.request.contextPath}/cus/edit/${cus.id}" >编辑</a> |
                <a href="${pageContext.request.contextPath}/cus/look/${cus.id}">查看详情</a>
            </td>
        </tr>
    </c:forEach>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:checkAll()" class="coolbg">全选</a>
	<a href="javascript:backAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:outCus()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
        <input id="file" name="file" type="file">
        <input onclick="upload()" type="button" value="上传">
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    <!--全选 -->
    function checkAll() {
        $("input[id^='cheId']").attr("checked","checked");

    }
    <!--反选 -->
    function backAll() {
        $("input[id^=cheId]").each(function () {
            var boolean = $(this).prop("checked");
            $(this).prop("checked",!boolean);
        })
    }
    <!--删除 -->
    function del() {
        var ids="";
        $("#form2").find("input:checked").each(function (index,item) {
            var id = $(this).val();
            ids = ids + id+",";
        });
        ids = ids.substring(0,ids.length-1);
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/cus/delete",
            data:{"ids":ids,"_method":"delete"},
            success:function () {
                window.location.href="${pageContext.request.contextPath}/cus/list";
            },
            error:function () {
                alert("出现不可预估的错误");
                window.location.href="${pageContext.request.contextPath}/cus/list";
            }
        })
    }

    <!--条件 -->
    $("#imgSelect").click(function () {
        $("#form3").submit();
        return false;
    })
    /*导出*/
    function outCus() {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/cus/outCus",
            success:function () {
                alert("导出成功");
            },
            error:function () {
                alert("出现不可预估的错误");
                window.location.href="${pageContext.request.contextPath}/cus/list";
            }
        })
    }
    /*xlsx文件上传*/
    var myform = document.getElementById("myform");
    var myFormData = new FormData(myform);
    myFormData.append("file",imgObj);
    function upload() {
        var formdate = new FormData();
        var file = $("#file")[0].files[0];
        formdate.append("file",file);
        alert("123");
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/cus/upload",
            data:formdate,
            cache:false,//不要在浏览器端缓存
            processData:false, //告诉浏览器，不要进行数据转换
            contentType:false, //告诉浏览器，不要设置编码
            success:function (msg) {
                window.location.href="${pageContext.request.contextPath}/cus/aaa/"+msg;
            }
        })

        return false;
    }
</script>
</html>
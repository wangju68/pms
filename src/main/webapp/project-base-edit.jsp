<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑项目信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/WdatePicker.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>编辑项目基本信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form2" name="form2" method="post" action="${pageContext.request.contextPath}/pro/update">
<input type="hidden" name="_method" value="put">
<input type="hidden" name="pid" value="${pro.pid}">
<input type="hidden" id="opt" value="${pro.comname}">
<input type="hidden" id="opt1" value="${pro.empFk}">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;编辑项目信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">项目名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="pname" value="${pro.pname}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="comname" id="cuscompany"  onchange="change(value)">
            <option >---请选择---</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input id="companyperson" type="text" name="comper" value="${pro.comper}"/>
    </td>
	<td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <select id="leadPerson" name="empFk">
            <option>---请选择---</option>
        </select>
    </td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开发人数：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input name="empcount" value="${pro.empcount}">人</td>
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input value="<fmt:formatDate value='${pro.starttime}' pattern='yyyy-MM-dd'/>" id="startTime" type="text" name="starttime" />
        <img onclick="WdatePicker({el:'startTime','skin':'YcloudRed','minDate':'%y%-%M%-%d%'})" src="${pageContext.request.contextPath}/static/js/skin/datePicker.gif" width="16" height="22" align="absmiddle">
    </td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input value="<fmt:formatDate value='${pro.buildtime}' pattern='yyyy-MM-dd'/>" id="buildTime" type="text" name="buildtime"/>
        <img onclick="WdatePicker({el:'buildTime','skin':'YcloudRed'})" src="${pageContext.request.contextPath}/static/js/skin/datePicker.gif" width="16" height="22" align="absmiddle">
    <td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input name="cost" value="${pro.cost}"/>万</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <select name="level">
            <option>---请选择---</option>
            <option <c:if test="${pro.level=='紧急'}">selected</c:if> value="紧急">紧急</option>
            <option <c:if test="${pro.level=='一般'}">selected</c:if> value="一般">一般</option>
            <option <c:if test="${pro.level=='暂缓'}">selected</c:if> value="暂缓">暂缓</option>
        </select>
    </td>
	<td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input value="<fmt:formatDate value='${pro.endtime}' pattern='yyyy-MM-dd'/>"  id="endTime" type="text" name="endtime"/>
        <img onclick="WdatePicker({el:'endTime','skin':'YcloudRed'})" src="${pageContext.request.contextPath}/static/js/skin/datePicker.gif" width="16" height="22" align="absmiddle">
    </td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="remark" rows=15 cols=130>${pro.remark}</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a id="save" href="javascript:void(0)" onclick="save()" class="coolbg">保存</a>
	<a href="${pageContext.request.contextPath}/pro/list" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
</body>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function () {
        //显示所有的客户公司名称
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/cus/selectCo",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var option = "<option  value='"+item.id+"'>"+item.comname+"</option>";
                    $("#cuscompany").append(option);
                })
                $("#cuscompany option").each(function () {
                    if ($("#opt").val()==$(this).val()){
                        $(this).attr("selected","selected");
                    }
                })
            }
        })
        //显示所有的项目经理
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/emp/selectLeadEmp",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var option = "<option value='"+item.eid+"'>"+item.ename+"</option>";
                    $("#leadPerson").append(option);
                })
                $("#leadPerson option").each(function () {
                    if ($("#opt1").val()==$(this).val()){
                        $(this).attr("selected","selected");
                    }
                })
            }
        })
    })

    //根据选择的客户公司名称，显示对于的客户负责人
    function change(id){
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/cus/getNameCus/"+id,
            success:function (msg) {
                $("#companyperson").val(msg.companyperson);
            }
        })
    }
    //保存修改后的信息
    function save() {
        $("#save").click(function () {
            $("#form2").submit();
            return false;
        })
    }

</script>
</html>
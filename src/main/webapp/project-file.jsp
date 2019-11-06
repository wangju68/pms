<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    //生成附件列表
    $(function () {
        var ajaxTest = $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/att/list",
            success:function (msg) {
                $(msg).each(function(index,item){
/*                    alert(item.starttime);
                    alert(item.attname);*/
                    //还有一个id属性
                    var tr = "<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                        "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value='"+item.id+"' class=\"np\"></td>\n" +
                        "\t<td>"+(index+1)+"</td>\n" +
                        "\t<td>"+item.attname+"</td>\n" +
                        "\t<td align=\"center\"><a href=''><u>"+item.pname+"</u></a></td>\n" +
                        "\t<td>"+item.count+"</td>\n" +
                        "\t<td>"+item.buildtime+"</td>\n" +
                        "\t<td>"+item.starttime+"</td>\n" +
                        "\t<td><a href=\"#\">下载</a> | " +
                        "<a href=\"#\">删除</a> | " +
                        "<a href=\"project-file-edit.jsp\">编辑</a> | " +
                        "<a href='att/lookById/"+item.id+"'>查看详情</a></td>\n" +
                        "</tr>";
                    $("#tr").before(tr);
                })
            }
        })
    })
    //全选
    function chooseAll() {
        $("#form2 input").attr("checked","checked");
        return false;
    }
    //反选
    function noChoose(){
        $("#form2 input").each(function () {
            var boolean = $(this).attr("checked");
            $(this).attr("checked",!boolean);
        })
        return false;
    }
    //批量删除
    function delChoose() {
        var ids = "";
        $("#form2").find("input:checked").each(function () {
            var id = $(this).val();
            ids = ids + id + ",";
        })
        ids = ids.substring(0,ids.length-1);
        $.ajax({
            type:"post",
            url:"pro/delFile/"+ids,
            data:{"_method":"delete"},
            success:function () {
                window.location.href="project-file.jsp";
            },
            error:function () {
                alert("出现不可预估的错误");
                window.location.href="project-file.jsp";
            }
        })

        return false;
    }
    //导出XLSX
    function outXlsx() {
        var ids = "";
        $("#form2").find("input:checked").each(function () {
            var id = $(this).val();
            ids = ids + id + ",";
        })
        ids = ids.substring(0,ids.length-1);
        if(ids==null || ids==""){
            alert("请先选择要导出的附件");
            window.location.href="project-file.jsp";
            return false;
        }
        $.ajax({
            type:"get",
            url:"att/outXlsx/"+ids,
            success:function () {
                alert("导出成功");
                window.location.href="project-file.jsp";
                return false;
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
    当前位置:项目管理>>附件管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-file-add.jsp';" value='添加新附件' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='1' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>附件名称</option>
          	<option value='1'>项目名称</option>
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
            <option value='id'>排序...</option>
            <option value='pubdate'>添加时间</option>
            <option value='pubdate'>修改时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2" id="form2">

<table id="table" width="98%" border="1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">附件名称</td>
	<td width="10%">所属项目</td>
	<td width="10%">附件个数</td>
	<td width="8%">上传时间</td>
	<td width="8%">修改时间</td>
	<td width="13%">操作</td>
</tr>


<tr id="tr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:void(0)" onclick="chooseAll()" class="coolbg">全选</a>
	<a href="javascript:void(0)" onclick="noChoose()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" onclick="delChoose()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:void(0)" onclick="outXlsx()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<%--<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>--%>
</table>

</form>
  

</body>
</html>
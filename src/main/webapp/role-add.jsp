<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加角色</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/js/script/docs.min.js"></script>
	<script src="static/js/layer/layer.js"></script>
	<script src="static/js/ztree/jquery.ztree.all-3.5.min.js"></script>
	<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<link rel="stylesheet" href="static/js/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/js/css/font-awesome.min.css">
	<link rel="stylesheet" href="static/js/ztree/zTreeStyle.css">
	<script type="text/javascript">
        $(function(){
            var setting = {
                check: {
                    enable: true
                },
                async: {
                    enable: true,
                    type:"get",
                    url:"auth/showAuth",
                    autoParam:["id", "name=n", "level=lv"]
                },
            };
            $.fn.zTree.init($("#permissionTree"), setting);

        });
        function saveInfo() {
            var rolename = $("#rolename").val();
            var status = $("#status").val();
            var roledis = $("#roledis").val();

            var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
            var nodes = treeObj.getCheckedNodes(true);
            var ids = "";
            for(var i=0;i<nodes.length;i++){
                ids = ids+nodes[i].id+",";
			}
			ids = ids.substring(0,ids.length-1);
            $.ajax({
				type:"post",
				url:"role/saveInfo",
				data:{"rolename":rolename,"status":status,"roledis":roledis,"ids":ids},
				success:function (msg) {
					window.location.href="role.jsp";
					return false;
                },
				error:function () {
					if (confirm("添加失败，是否离开本页面")){
                        window.location.href="role.jsp";
					}
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
    当前位置:权限管理>>添加角色信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加角色&nbsp;</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">角色名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="rolename" id="rolename"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">状态：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="status" name="status">
			<option>---请选择---</option>
			<option value=0>禁用</option>
			<option value=1>启用</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">赋菜单资源：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<div class="panel-body">
			<ul id="permissionTree" class="ztree"></ul>
		</div>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea id="roledis" name="roledis" rows=10 cols=130></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:void(0)" onclick="saveInfo()" class="coolbg">保存</a>
	<a href="role.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function selectReq() {
            $("#form5").submit();
        }
    </script>
</head>
<body>
<!--  搜索表单  -->
<form id="form5" name='form5' action='bx/selectReq' method='get'>
    <table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select name='bxstatus' style='width:150px'>
                                <option value=''>---请选择---</option>
                                <option value='0'>未审批</option>
                                <option value='1'>审批通过</option>
                                <option value='2'>驳回</option>
                                <option value='3'>审批已付款</option>
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
                                <option value='1'>总金额</option>
                                <option value='2'>使用时间</option>
                            </select>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;
                            <input onclick="selectReq()" name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
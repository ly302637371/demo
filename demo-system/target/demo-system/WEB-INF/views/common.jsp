<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel=”icon” href="img/favicon.ico" mce_href="img/favicon.ico" type="image/x-icon">
<%--<link rel="shortcut icon" href="images/favicon.ico" mce_href="images/favicon.ico" type="image/x-icon">--%>
<%--<link rel="stylesheet" type="text/css" href="easyui/themes/bootstrap/easyui.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="easyui/themes/bootstrap/tabs.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="css/theme-datagrid.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="css/theme-dialog.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="css/score/star-rating.css"/>--%>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<%--<script type="text/javascript" src="js/jquery-form.js"></script>--%>
<%--<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>--%>
<%--<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>--%>
<%--<script type="text/javascript" src="js/format_date.js"></script>--%>
<%--<script type="text/javascript" src="js/custom-easyui-validate.js"></script>--%>
<%--<script type="text/javascript" src="js/sys_msg.js"></script>--%>
<%--<script type="text/javascript" src="js/sys-dictionaries.js"></script>--%>
<%--<script type="text/javascript" src="js/utils.js"></script>--%>
<%--<script type="text/javascript" src="js/common.js"></script>--%>
<%--<script type="text/javascript" src="js/calendar/calendarY.js"></script>--%>
<%--<script type="text/javascript" src="js/qrcode/jquery.qrcode.min.js"></script>--%>
<%--<script type="text/javascript" src="js/score/star-rating.js"></script>--%>


<%--<link href="js/webuploader-0.1.5/webuploader.css" rel="stylesheet" />--%>
<%--<script src="js/webuploader-0.1.5/webuploader.js"></script>--%>

<script>
	$.ajaxSetup({
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		complete:function(XMLHttpRequest,textStatus){
			// 通过XMLHttpRequest取得响应头，sessionstatus，
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
			if(sessionstatus=="sessionOut"){
				$.messager.confirm("确认","页面已过期,请重新登陆!",function(flag){
					if(flag){
						window.location.replace("login/toLogin");
					}
				});
			}
		}
	});
</script>
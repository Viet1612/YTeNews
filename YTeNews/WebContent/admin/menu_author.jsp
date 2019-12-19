<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/admin/content/dist/img/admin.jpg" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${fullname}</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li><a
				href="${pageContext.request.contextPath}/listnewsauthor.at"><i
					class="fa fa-wechat"></i> <span> Management News</span></a></li>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
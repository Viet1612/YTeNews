<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin</title>
<!-- Tell the browser to be responsive to screen width -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="images/icon.ico" type="image/png" />
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link
	href="${pageContext.request.contextPath}/admin/content/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
	<link
	href="${pageContext.request.contextPath}/admin/content/bootstrap/css/style.css"
	rel="stylesheet" type="text/css" />
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link
	href="${pageContext.request.contextPath}/admin/content/dist/css/AdminLTE.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/admin/content/bootstrap/css/style.css"
	rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->

<link
	href="${pageContext.request.contextPath}/admin/content/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">

	<!-- Site wrapper -->
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>

		<!-- =============================================== -->
		<!-- Left side column. contains the sidebar -->
		<jsp:include page="menu.jsp"></jsp:include>

		<!-- =============================================== -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>List News</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index.do"><i class="fa fa-dashboard"></i>
							Home</a></li>
					<li class="active">List News</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">List</h3>
						<div>
							<h3 style="color: red;">${approvesuccess}${deletesuccess}</h3>
						</div>
						<div class="box-tools pull-right">
							<button class="btn btn-box-tool" data-widget="collapse"
								data-toggle="tooltip" title="Collapse">
								<i class="fa fa-minus"></i>
							</button>
							<button class="btn btn-box-tool" data-widget="remove"
								data-toggle="tooltip" title="Remove">
								<i class="fa fa-times"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<div class="row">

							<form method="get"
								action="${pageContext.request.contextPath}/listnews.do">
								<input type="hidden" name="action" value="search" />
								<input type="hidden" name="newsid" value="${news.newsId}" />
								<div class="col-md-6" style="width: 90%;">
									<div class="input-group">

										<input type="text" class="form-control" style="width: 40%;"
											name="keyname" value="${keyname}" placeholder="keyword">
										<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<select style="margin-top: 9px; width: 22%;" name="categoryid" >
											<option value="0">Please Choose Category</option>
											<c:forEach items="${listcategory}" var="category">
												<option value="${category.categoryId}"
													<c:if test="${categoryid == category.categoryId}"> selected </c:if>><c:out
														value="${category.categoryName}" /></option>
											</c:forEach>

										</select> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><select
											style="margin-top: 9px; width: 15%;" name="approve">
											<option value="-1"
												<c:if test="${approve == -1}"> selected </c:if>>Please
												Choose stt...</option>
											<option value="0"
												<c:if test="${approve == 0}"> selected </c:if>>Chưa
												duyệt</option>
											<option value="1"
												<c:if test="${approve == 1}"> selected </c:if>>Đã
												duyệt</option>
										</select><span class="input-group-btn">
											<button class="btn btn-default" type="submit">
												Search</button>
										</span>
									</div>
								</div>
							</form>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table table-bordered " style="table-layout: fixed;">
									<tr>
										<th style="width: 3%">ID</th>
										<th style="width: 12%">Image</th>
										<th style="width: 8%">Category</th>
										<th style="width: 12%">Title</th>
										<th style="width: 3%">Content</th>
										<th style="width: 10%">Author</th>
										<th style="width: 6%">Date Post</th>
										<th style="width: 6%">Action</th>
									</tr>
									<c:forEach items="${listNews}" var="news">
										<tr>
											<td><a
												href="${pageContext.request.contextPath}/detailnews.do?newsid=${news.newsId}"><c:out
														value="${news.newsId}" /></a></td>
											<td><img src="${pageContext.request.contextPath}/images_news/${news.image}"
												class="img-responsive" alt="${news.categoryName}"></td>
											<td class="namewrap"><c:out value="${news.categoryName}" /></td>
											<td class="namewrap"><c:out value="${news.newsName}" /></td>
											<td class="namewrap"><c:out value="${news.content}" /></td>
											<td class="namewrap"><c:out value="${news.userName}" /></td>
											<td class="namewrap"><c:out value="${news.datePost}" /></td>

											<td><c:if test="${news.approve == 0}">
													<a
														href="${pageContext.request.contextPath}/detailnews.do?newsid=${news.newsId}"><button
															class="btn btn-sm btn-primary">
															<i class="fa fa-plus-circle"></i>
														</button></a>
												</c:if>
												<button class="btn  btn-sm btn-danger"
													onclick="comfirmDeleteAdmin(${news.newsId});">
													<i class="fa fa-trash"></i>
												</button></td>
										</tr>
									</c:forEach>

									<tfoot>
										<tr>
											<td colspan="8"><span class="pull-right">Page:
													${currentpage} - Total of recorders: ${totalnews}</span></td>
										</tr>
									</tfoot>
								</table>
							</div>

						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">

						<!-- Begin vung paging -->
						<table>
							<tr>
								<c:url value="listnews.do" var="url_paging">
									<c:param name="action" value="paging" />
									<c:param name="keyname" value="${keyname}" />
									<c:param name="categoryid" value="${categoryid}" />
									<c:param name="approve" value="${approve}" />

								</c:url>
								<td class="lbl_paging"><a
									href="${url_paging}&currentpage=${previous}">${previouschar}</a>
									<c:forEach items="${listPaging}" var="Paging">
										<a <c:if test = "${currentpage == Paging}">style="color: red;"</c:if> href="${url_paging}&currentpage=${Paging}">${Paging}</a>&nbsp;&nbsp;
									</c:forEach> <a href="${url_paging}&currentpage=${next}">${nextchar}</a></td>

							</tr>
						</table>
						<!-- End vung paging -->
					</div>
					<!-- /.box-footer-->
				</div>
				<!-- /.box -->
			</section>
			<!-- /.content -->
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- Control Sidebar -->

	</div>

	<!-- ./wrapper -->
	<!-- jQuery 2.2.3 -->
	<script
		src="${pageContext.request.contextPath}/admin/content/plugins/jQuery/jQuery-2.1.4.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/admin/content/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/admin/content/bootstrap/js/news.js"
		type="text/javascript"></script>
	<!-- Bootstrap 3.3.6 -->
	<!-- SlimScroll -->
	<script
		src="${pageContext.request.contextPath}/admin/content/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/admin/content/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/admin/content/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/admin/content/dist/js/demo.js"></script>


</body>

</html>
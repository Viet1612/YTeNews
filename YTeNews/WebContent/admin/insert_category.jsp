<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin</title>
<link rel="icon" href="/images/icon.ico" type="image/png" />
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link
	href="${pageContext.request.contextPath}/admin/content/bootstrap/css/bootstrap.min.css"
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
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Category</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp"><i class="fa fa-dashboard"></i>Home</a></li>
					<li><a
						href="${pageContext.request.contextPath}/listcategory.do">Category</a></li>
					<li class="active">Add Category</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">Add Category</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form class="form-horizontal"
						<c:choose>
		<c:when test="${action eq 'edit'}"> action="${pageContext.request.contextPath}/updatecategory.do}" </c:when>
		<c:otherwise> action="${pageContext.request.contextPath}/insertcategory.do"  </c:otherwise>
	</c:choose>
	
						method="post">
						<input type="hidden" name="categoryid" value="${category.categoryId}">
						<div class="box-body">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Category
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="categoryname"
										value="<c:out value="${category.categoryName}" />">
								</div>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<a href='${pageContext.request.contextPath}/listcategory.do'
								class="btn btn-default">Back</a> <input type="submit"
								class="btn btn-success pull-right" value='Add' />
						</div>
						<!-- /.box-footer -->
					</form>
				</div>
				<!-- /.box -->
			</section>
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
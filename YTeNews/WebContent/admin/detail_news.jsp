<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Detail News</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="icon"
	href="${pageContext.request.contextPath}/images/icon.ico"
	type="image/png" />
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
		<c:if test="${rule == 2}">
		<jsp:include page="menu_author.jsp"></jsp:include>
		</c:if>
		<c:if test="${rule == 0}">
		<jsp:include page="menu.jsp"></jsp:include>
		</c:if>
		<!-- =============================================== -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>News</h1>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">News Information</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form action="" enctype="" class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2 control-label">Title</label>
								<div class="col-sm-8">
									<textarea id="des" class="form-control" name="title"
										placeholder="Please enter title!" rows="5" cols="80" readonly><c:out value="${news.newsName}" /></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Description</label>
								<div class="col-sm-8">
									<textarea id="des" class="form-control" name="description"
										placeholder="Please enter description!" rows="5" cols="80"
										readonly><c:out value="${news.description}" /></textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">Image</label>
								<div class="col-sm-8">
									<img class="text-center" width="250" height="230" alt="Image"
										style="border: 1px solid black;"
										src="${pageContext.request.contextPath}/images_news/${news.image}">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">Content</label>
								<div class="col-sm-8">
									<textarea id="des" class="form-control" name="content"
										placeholder="Please enter content!" rows="15" cols="80"
										readonly><c:out value="${news.content}" /></textarea>

								</div>
							</div>
						</div>

						<div class="box-footer">
						<c:if test="${rule == 0}">
							<a
								href='${pageContext.request.contextPath}/listnews.do?action=back'
								class="btn btn-default">Back</a>
								</c:if>
									<c:if test="${rule == 2}">
							<a
								href='${pageContext.request.contextPath}/listnewsauthor.at?action=back'
								class="btn btn-default">Back</a>
								</c:if>
							<c:if test="${news.approve == 0 && rule == 0}">
								<input id="btnAdd" type="button"
									class="btn btn-success pull-right" value='Approve'
									data-toggle="modal" data-target="#myModal" />
							</c:if>
						</div>

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
	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">

			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Modal Heading</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<form action="${pageContext.request.contextPath}/approvenews.do"
					method="post">
					<div class="modal-body" style="text-align: center;">
						<input type="hidden" name="newsid" value="${news.newsId}" />
						 <select style="margin-top: 9px; width: 50%;" name="categoryid">
							<option value="0">Please Choose Category</option>
							<c:forEach items="${listcategory}" var="category">
								<option value="${category.categoryId}"><c:out
										value="${category.categoryName}" /></option>
							</c:forEach>

						</select>

					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-danger"
							style="background: Blue" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- ./wrapper -->
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
	<script
		src="${pageContext.request.contextPath}/admin/content/dist/js/autoNumeric.js"></script>

	<script language="javascript">
		$(document).ready(function() {
			$('#price').bind('blur focusout keypress keyup', function() {
				//$('#price').autoNumeric('init');
			})
		});
	</script>
<!-- modal -->
<script src="${pageContext.request.contextPath}/admin/content/bootstrap/modal/jquery-3.2.1.slim.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/content/bootstrap/modal/popper.min.js"></script>

</body>
</html>
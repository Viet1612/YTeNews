<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Yte24hNews</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/images/icon.png"/>

  <link href="https://fonts.googleapis.com/css?family=B612+Mono|Cabin:400,700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/icomoon/style.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fancybox.min.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon/font/flaticon.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aos.css">
  <link href="${pageContext.request.contextPath}/css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet" type="text/css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">



</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v5.0&appId=501216240502165&autoLogAppEvents=1"></script>
  <div class="site-wrap">

    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>



<div class="header-top">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-12 col-lg-6 d-flex">
				<a href="${pageContext.request.contextPath}/index.html" class="site-logo"> YTe24hNews </a> <a href="#"
					class="ml-auto d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black"><span
					class="icon-menu h3"></span></a>

			</div>
			<div class="col-12 col-lg-6 ml-auto d-flex">
				<div class="ml-md-auto top-social d-none d-lg-inline-block">
					<a href="https://www.facebook.com/vietTr97" class="d-inline-block p-3"><span
						class="icon-facebook"></span></a> <a href="https://twitter.com/Impossible_suzy"
						class="d-inline-block p-3"><span class="icon-twitter"></span></a>
					<a href="https://www.instagram.com/viettranthanh/" class="d-inline-block p-3"><span
						class="icon-instagram"></span></a>
				</div>
				<form action="${pageContext.request.contextPath}/categories.html" class="search-form d-inline-block">
					<input type="hidden" name="action" value="search" />
					<div class="d-flex">
						<input type="text" class="form-control" placeholder="Search..." value="${keyname}" name="keyname">
						<button type="submit" class="btn btn-secondary">
							<span class="icon-search"></span>
						</button>
					</div>
				</form>


			</div>
			<div class="col-6 d-block d-lg-none text-right"></div>
		</div>
	</div>




	<div
		class="site-navbar py-2 js-sticky-header site-navbar-target d-none pl-0 d-lg-block"
		role="banner">

		<div class="container">
			<div class="d-flex align-items-center">

				<div class="mr-auto">
					<nav class="site-navigation position-relative text-right"
						role="navigation">
						<ul
							class="site-menu main-menu js-clone-nav mr-auto d-none pl-0 d-lg-block">
							<li class="active"><a
								href="${pageContext.request.contextPath}/index.html"
								class="nav-link text-left">Home</a></li>
							<c:forEach items="${listcategory}" var="category">
								<li><a href="${pageContext.request.contextPath}/categories.html?categoryid=${category.categoryId}" class="nav-link text-left"><c:out
											value="${category.categoryName}" /></a></li>
							</c:forEach>
							<li><a href="contact.html" class="nav-link text-left">Contact</a></li>
						</ul>
					</nav>

				</div>

			</div>
		</div>

	</div>

</div>
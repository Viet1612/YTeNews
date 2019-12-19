<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp"></jsp:include>

    <div class="site-section py-0">
      <div class="owl-carousel hero-slide owl-style">
<c:forEach items="${listnewsheader}" var="news">
        <div class="site-section">
          <div class="container">
            <div class="half-post-entry d-block d-lg-flex bg-light">
              <div class="img-bg" style="background-image: url('${pageContext.request.contextPath}/images_news/${news.image}')"></div>
              <div class="contents">
                <span class="caption"><c:out value="${news.categoryName}" /></span>
                <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <p class="mb-3" style="word-wrap: break-word;"><c:out value="${news.description}" /></p>
                <div class="post-meta">
                  <span class="d-block"><a href="#"><c:out value="${news.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>

              </div>
            </div>
          </div>
        </div>
</c:forEach>

      </div>
    </div>
  
      <!-- START section -->
   <div class="site-section">
      <div class="container">
        <div class="row">
        <c:if test = "${category0.size() > 0}">
          <!-- category 1 -->
          <div class="col-lg-8">
           
            <div class="row">
              <div class="col-12">
                <div class="section-title">
                  <h2><a style="color: black;" href="${pageContext.request.contextPath}/categories.html?categoryid=${category.categoryId}"><c:out value="${listcategory.get(0).categoryName}" /></a></h2>
                </div>
              </div>
            </div>
           
            <div class="row">
       
            <!-- news 1 -->
           
              <div class="col-md-6">
               <c:set var = "news0"  value = "${category0.get(0)}"/>
               
                <div class="post-entry-1">
                  <a href="${pageContext.request.contextPath}/news.html?newsid=${news0.newsId}"><img src="${pageContext.request.contextPath}/images_news/${news0.image}" alt="Image" class="img-fluid"></a>
                  <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news0.newsId}"><c:out value="${news0.newsName}" /></a></h2>
                  <p style="word-wrap: break-word;"><c:out value="${news0.description}" /></p>
                  <div class="post-meta">
                    <span class="d-block"><a href="#"><c:out value="${news0.userName}" /></a></span>
                    <span class="date-read"><c:out value="${news0.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news0.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                  </div>
                </div>
              </div>
              
               <!-- end news 1 -->
            
              <div class="col-md-6">
                    <c:set var = "i"  value = "${0}"/>
                <c:forEach items="${category0}" var="news">
               <c:if test = "${i > 0}">
                <div class="post-entry-2 d-flex bg-light">
                  <div class="thumbnail"><img src="${pageContext.request.contextPath}/images_news/${news.image}" alt="Image" class="img-fluid"></div>
                  <div class="contents">
                    <h2  style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                    <div class="post-meta">
                      <span class="d-block"><a href="#"><c:out value="${news.userName}" /></a></span>
                      <span class="date-read"><c:out value="${news.datePost}" /> &nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                    </div>
                  </div>
                </div>
                 </c:if>
                <c:set var = "i"  value = "${i + 1}"/>
                </c:forEach>
              </div>
            </div>
            
          </div>
         </c:if>
           <!-- end category 1 -->
          <div class="col-lg-4">
            <div class="section-title">
              <h2>Trending</h2>
            </div>
             <c:set var = "i"  value = "${1}"/>
			<c:forEach items="${listnewstrending}" var="news">
            <div class="trend-entry d-flex">
              <div class="number align-self-start">0<c:out value="${i}" /></div>
              <div class="trend-contents">
                <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <div class="post-meta">
                  <span class="d-block"><a href="#"><c:out value="${news.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>
              </div>
            </div>
			<c:set var = "i"  value = "${i + 1}"/>
        </c:forEach>
            
            <p>
              <a href="${pageContext.request.contextPath}/categories.html?action=trending" class="more">See All Trends <span class="icon-keyboard_arrow_right"></span></a>
            </p>

          </div>
        </div>
      </div>
    </div>
    <!-- END section -->

	<c:set var = "newstop"  value = "${listnewstrending.get(0)}"/>
    <div class="py-0">
      <div class="container">
        <div class="half-post-entry d-block d-lg-flex bg-light">
          <div class="img-bg" style="background-image: url('${pageContext.request.contextPath}/images_news/${newstop.image}')"></div>
          <div class="contents">
            <span class="caption">${listnewstrending.get(0).categoryName}</span>
            <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${newstop.newsId}"><c:out value="${newstop.newsName}" /></a></h2>
            <p class="mb-3" style="word-wrap: break-word;"><c:out value="${newstop.description}" /></p>
            
            <div class="post-meta">
              <span class="d-block"><a href="#"><c:out value="${newstop.userName}" /></a></span>
              <span class="date-read"><c:out value="${newstop.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${newstop.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
            </div>

          </div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
        <div class="row">
          <!-- category 2 -->
           <c:if test = "${category1.size() > 0}">
          <div class="col-lg-6">
            <div class="section-title">
              <h2><a style="color: black;" href="${pageContext.request.contextPath}/categories.html?categoryid=${category.categoryId}"><c:out value="${listcategory.get(1).categoryName}" /></a></h2>
            </div>
             <c:forEach items="${category1}" var="news">
            <div class="post-entry-2 d-flex">
              <div class="thumbnail"><img src="${pageContext.request.contextPath}/images_news/${news.image}" alt="Image" class="img-fluid"></div>
              <div class="contents">
                <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <p style="word-wrap: break-word;" class="mb-3"><c:out value="${news.description}" /></p>
                <div class="post-meta">
                  <span class="d-block"><a href=""><c:out value="${newstop.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>
              </div>
            </div>
            </c:forEach>
          </div>
          </c:if>
            <!-- end category 2 -->
              <!-- category 3 -->
                <c:if test = "${category2.size() > 0}">
          <div class="col-lg-6">
            <div class="section-title">
              <h2><a style="color: black;" href="${pageContext.request.contextPath}/categories.html?categoryid=${category.categoryId}"><c:out value="${listcategory.get(2).categoryName}" /></a></h2>
            </div>
             <c:forEach items="${category2}" var="news">
            <div class="post-entry-2 d-flex">
              <div class="thumbnail"><img src="${pageContext.request.contextPath}/images_news/${news.image}" alt="Image" class="img-fluid"></div>
              <div class="contents">
                <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <p style="word-wrap: break-word;" class="mb-3"><c:out value="${news.description}" /></p>
                <div class="post-meta">
                  <span class="d-block"><a href=""><c:out value="${newstop.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>
              </div>
            </div>
            </c:forEach>
          </div>
          </c:if>
            <!--end category 3 -->
        </div>
      </div>
    </div>



    <div class="site-section">
      <div class="container">
        <div class="row">
        <!-- category 4 -->
        <c:if test = "${category3.size() > 0}">
          <div class="col-lg-9">
            <div class="section-title">
              <h2><a style="color: black;" href="${pageContext.request.contextPath}/categories.html?categoryid=${category.categoryId}"><c:out value="${listcategory.get(3).categoryName}" /></a></h2>
            </div>
              <c:forEach items="${category3}" var="news">
            <div class="post-entry-2 d-flex">
              <div class="thumbnail order-md-2" ><img src="${pageContext.request.contextPath}/images_news/${news.image}" alt="Image" class="img-fluid"></div>
              <div class="contents order-md-1 pl-0">
                <h2 style="word-wrap: break-word;"><a href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <p  style="word-wrap: break-word;" class="mb-3"><c:out value="${news.description}" /></p>
                <div class="post-meta">
                  <span class="d-block"><a href=""><c:out value="${newstop.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" />&nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>
              </div>
            </div>
            </c:forEach>
          </div>
          </c:if>
          <!--end category 4 -->
          <!--recent -->
          <div class="col-lg-3">
            <div class="section-title">
              <h2>Recent News</h2>
            </div>
            <c:set var = "i"  value = "${1}"/>
			<c:forEach items="${listnewsheader}" var="news">
            <div class="trend-entry d-flex">
              <div class="number align-self-start">0<c:out value="${i}" /></div>
              <div class="trend-contents">
                <h2 ><a style="word-wrap: break-word;" href="${pageContext.request.contextPath}/news.html?newsid=${news.newsId}"><c:out value="${news.newsName}" /></a></h2>
                <div class="post-meta">
                  <span class="d-block"><a href="#"><c:out value="${news.userName}" /></a></span>
                  <span class="date-read"><c:out value="${news.datePost}" /> &nbsp<span class="icon-star2">&nbsp</span>${news.categoryName}<span>&nbsp</span><span class="icon-star2"></span></span>
                </div>
              </div>
            </div>
             <c:set var = "i"  value = "${i + 1}"/>
            </c:forEach>

   
            
            <p>
              <a href="${pageContext.request.contextPath}/categories.html?action=recent" class="more">See All Popular <span class="icon-keyboard_arrow_right"></span></a>
            </p>
          </div>
          <!--end recent -->
        </div>

<%--
        <div class="row">
          <div class="col-lg-6">
            <ul class="custom-pagination list-unstyled">
              <li><a href="#">1</a></li>
              <li class="active">2</li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
            </ul>
          </div>
        </div>
        --%>
      </div>
    </div>

   

<jsp:include page="footer.jsp"></jsp:include>
  
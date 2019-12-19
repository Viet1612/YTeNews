<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<jsp:include page="header.jsp"></jsp:include>


    

    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 single-content">
            
            <p class="mb-5">
              <img src="${pageContext.request.contextPath}/images_news/${news.image}" alt="Image" class="img-fluid">
            </p>  
            <h1 class="mb-4" style="word-wrap: break-word;">
              <c:out value="${news.newsName}" />
            </h1>
            <div class="post-meta d-flex mb-5">
              <div class="vcard">
                <span class="d-block" style="font-weight: bold;"><c:out value="${news.userName}" /></span>
                <span class="date-read"><c:out value="${news.datePost}" /><span class="mx-1">&bullet;</span><span class="icon-star2"></span></span>
              </div>
            </div>
			<c:forEach items="${listcontent}" var="content">						
            <p style="word-wrap: break-word;"><c:out value="${content}" /></p>
			</c:forEach>

            <div class="pt-5">
                    <p>Categories:  <a href="${pageContext.request.contextPath}/categories.html?categoryid=${news.categoryId}"><c:out value="${news.categoryName}" /></a></p>
                  </div>
       <%--
                 <div class="pt-5">
                    <div class="section-title">
                      <h2 class="mb-5">6 Comments</h2>
                    </div>
                    <ul class="comment-list">
                      <li class="comment">
                        <div class="vcard bio">
                          <img src="images/person_1.jpg" alt="Image placeholder">
                        </div>
                        <div class="comment-body">
                          <h3>Jean Doe</h3>
                          <div class="meta">January 9, 2018 at 2:21pm</div>
                          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                          <p><a href="#" class="reply">Reply</a></p>
                        </div>
                      </li>
      
                      <li class="comment">
                        <div class="vcard bio">
                          <img src="images/person_1.jpg" alt="Image placeholder">
                        </div>
                        <div class="comment-body">
                          <h3>Jean Doe</h3>
                          <div class="meta">January 9, 2018 at 2:21pm</div>
                          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                          <p><a href="#" class="reply">Reply</a></p>
                        </div>
      
                        <ul class="children">
                          <li class="comment">
                            <div class="vcard bio">
                              <img src="images/person_1.jpg" alt="Image placeholder">
                            </div>
                            <div class="comment-body">
                              <h3>Jean Doe</h3>
                              <div class="meta">January 9, 2018 at 2:21pm</div>
                              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                              <p><a href="#" class="reply">Reply</a></p>
                            </div>
      
      
                            <ul class="children">
                              <li class="comment">
                                <div class="vcard bio">
                                  <img src="images/person_1.jpg" alt="Image placeholder">
                                </div>
                                <div class="comment-body">
                                  <h3>Jean Doe</h3>
                                  <div class="meta">January 9, 2018 at 2:21pm</div>
                                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                                  <p><a href="#" class="reply">Reply</a></p>
                                </div>
      
                                  <ul class="children">
                                    <li class="comment">
                                      <div class="vcard bio">
                                        <img src="images/person_1.jpg" alt="Image placeholder">
                                      </div>
                                      <div class="comment-body">
                                        <h3>Jean Doe</h3>
                                        <div class="meta">January 9, 2018 at 2:21pm</div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                                        <p><a href="#" class="reply">Reply</a></p>
                                      </div>
                                    </li>
                                  </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
      
                      <li class="comment">
                        <div class="vcard bio">
                          <img src="images/person_1.jpg" alt="Image placeholder">
                        </div>
                        <div class="comment-body">
                          <h3>Jean Doe</h3>
                          <div class="meta">January 9, 2018 at 2:21pm</div>
                          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                          <p><a href="#" class="reply">Reply</a></p>
                        </div>
                      </li>
                    </ul>
                    <!-- END comment-list -->
                    
                    <div class="comment-form-wrap pt-5">
                      <div class="section-title">
                        <h2 class="mb-5">Leave a comment</h2>
                      </div>
                      <form action="#" class="p-5 bg-light">
                        <div class="form-group">
                          <label for="name">Name *</label>
                          <input type="text" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                          <label for="email">Email *</label>
                          <input type="email" class="form-control" id="email">
                        </div>
                        <div class="form-group">
                          <label for="website">Website</label>
                          <input type="url" class="form-control" id="website">
                        </div>
      
                        <div class="form-group">
                          <label for="message">Message</label>
                          <textarea name="" id="message" cols="30" rows="10" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                          <input type="submit" value="Post Comment" class="btn btn-primary py-3">
                        </div>
      
                      </form>
                    </div>
                  </div> --%>
          </div>


          <div class="col-lg-3 ml-auto">
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
              <a href="${pageContext.request.contextPath}/categories.html?action=trending" class="more">See All Popular <span class="icon-keyboard_arrow_right"></span></a>
            </p>
          </div>


        </div>
        
      </div>
    </div>




  <jsp:include page="footer.jsp"></jsp:include>
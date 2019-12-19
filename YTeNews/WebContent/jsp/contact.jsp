<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<jsp:include page="header.jsp"></jsp:include>

    

    <div class="site-section bg-light">
      <div class="container">
        <div class="row">

          <div class="col-lg-12">
            <div class="section-title mb-5">
              <h2>Contact Us</h2>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/contact.html">
                  <div class="row">
                      <div class="col-md-6 form-group" >
                      <font color = "red">${msg006}</font>
                      </div>
                  </div>
            	<c:forEach items="${listerr}" var="err">
              	<div class="row">
                      <div class="col-md-6 form-group" >
                      <font color = "red">${msg006}${err}</font>
                      </div>
                  </div>
                  </c:forEach>
                  <div class="row">
                      <div class="col-md-6 form-group">
                          <label for="fname">Name</label>
                          <input type="text" name="name" id="name" class="form-control form-control-lg" value="<c:out value="${contact.userName}" />">
                      </div>
                  </div>
                  <div class="row">
                  
                      <div class="col-md-6 form-group">
                          <label for="eaddress">Email Address</label>
                          <input type="text" name="email" id="email" class="form-control form-control-lg" value="<c:out value="${contact.email}" />">
                      </div>

                  </div>
                    <div class="row">
                      <div class="col-md-6 form-group">
                          <label for="tel">Tel. Number</label>
                          <input type="text" name="tel" id="tel" class="form-control form-control-lg" value="<c:out value="${contact.tel}" />">
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-md-12 form-group">
                          <label for="message">Message</label>
                          <textarea name="message" id="message" cols="30" rows="10" class="form-control" ><c:out value="${contact.message}" /></textarea>
                      </div>
                  </div>

                  <div class="row">
                      <div class="col-12">
                          <input type="submit" value="Send Message" class="btn btn-primary py-3 px-5">
                      </div>
                  </div>
              
            </form>
          </div>
          
        </div>

        
      </div>
    </div>

    
  
 <jsp:include page="footer.jsp"></jsp:include>
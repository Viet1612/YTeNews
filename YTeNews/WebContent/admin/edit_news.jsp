<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" href="${pageContext.request.contextPath}/images/icon.ico" type="image/png" />
        <title>Update News</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="${pageContext.request.contextPath}/admin/content/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link href="${pageContext.request.contextPath}/admin/content/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/admin/content/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">

 
        <!-- Site wrapper -->
        <div class="wrapper">
            <jsp:include page="header.jsp"></jsp:include>
                <!-- =============================================== -->
                <!-- Left side column. contains the sidebar -->
           <jsp:include page="menu_author.jsp"></jsp:include>
                <!-- =============================================== -->
                <!-- Content Wrapper. Contains page content -->
                <div class="content-wrapper">
                    <!-- Content Header (Page header) -->
                    <!-- Content Header (Page header) -->
                    <section class="content-header">
                        <h1>
                            Update News
                        </h1>
                        <ol class="breadcrumb">
                            <li><a href="index.jsp"><i class="fa fa-dashboard"></i>Home</a></li>
                            <li><a href="manager_news">News</a></li>
                            <li class="active">Update</li>
                        </ol>
                    </section>
                    <!-- Main content -->
                    <section class="content">
                        <!-- Horizontal Form -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">News Information</h3>
                            </div><!-- /.box-header -->
                            <!-- form start -->
                            <form action="${pageContext.request.contextPath}/updatenews.at" method="post" class="form-horizontal" enctype="multipart/form-data">
                                <div class="box-body">
                                <div>${err}</div>
		                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Title</label>
                                       <div class="col-sm-8">
                                             <textarea id="des" class="form-control" name="title" placeholder="Please enter title!" rows="5" cols="80">${news.newsName}</textarea>	                      	
                                        </div>	                      
		                    </div>  
		                         <div class="form-group">
                                        <label class="col-sm-2 control-label">Description</label>
                                     <div class="col-sm-8">
                                         <textarea id="des" class="form-control" name="description" placeholder="Please enter description!" rows="5" cols="80">${news.description}</textarea>	                      	
                                        </div>		                      
		                    </div>  
		                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Image</label>
                                        <div class="col-sm-8">
                                            <input onchange="readURL(this);" value="" type="file" id="file" name="files[]"  class="btn btn-white btn-warning btn-bold">	                                            
                                            <img width="160" height="230" alt="Hình ảnh" src="${pageContext.request.contextPath}/images_news/${news.image}"  style="border:1px solid black;" id="hideAvatar" > 
                                           <img class="text-center" width="160" height="230" alt="Image" hidden="" style="border:1px solid black;"  id="showAvatar" >
                                        </div>		                      
		                    </div>       
                                            
		                    	                   
		                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Content</label>
                                           <div class="col-sm-8"> 
                                            <textarea id="des" class="form-control" name="content" placeholder="Please enter content!" rows="15" cols="80">${news.content}</textarea>	                      	
                                            
                                        </div>		                      
		                    </div>       
                                </div>
		                 <input type="hidden" name="newsid" value="${news.newsId}" />
		                 <div class="box-footer">
                                    <a href='${pageContext.request.contextPath}/listnewsauthor.at?action=back' class="btn btn-default">Back</a> 
                                    <input id="btnUpdate" type="submit" class="btn btn-success pull-right" value='Update'/>
                                </div>
                            </form>                        
                        </div><!-- /.box -->
                    </section><!-- /.content -->
                </div>
                <!-- /.content-wrapper -->
            <jsp:include page="footer.jsp"></jsp:include>
            <!-- Control Sidebar -->

        </div>
        <!-- ./wrapper -->
        <!-- jQuery 2.2.3 -->
       <script src="${pageContext.request.contextPath}/admin/content/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/admin/content/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Bootstrap 3.3.6 -->
        <!-- SlimScroll -->
        <script src="${pageContext.request.contextPath}/admin/content/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="${pageContext.request.contextPath}/admin/content/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/admin/content/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${pageContext.request.contextPath}/admin/content/dist/js/demo.js"></script>
        <script src="${pageContext.request.contextPath}/admin/content/dist/js/autoNumeric.js"></script>
        
         <script language="javascript">  
                function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function(e) {
                            $('#showAvatar').prop('hidden', false);
                            $('#showAvatar').attr('src', e.target.result);
                            $('#hideAvatar').attr('src', "");
                            $('#hideAvatar').prop('hidden', true);
                        };
                        reader.readAsDataURL(input.files[0]);
                    }
                }
        </script>
        
         <script language="javascript">  
            $(document).ready(function () {
                $('#price').bind('blur focusout keypress keyup', function () {
                    
                })
            });
            
        </script>
        
    
        
    </body>
</html>

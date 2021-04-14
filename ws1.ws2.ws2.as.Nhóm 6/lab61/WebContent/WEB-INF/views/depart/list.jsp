<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Lumino - Dashboard</title>
	<link href="../css/bootstrap.min.css" type="text/css"  rel="stylesheet">
	<link href="../css/font-awesome.min.css" type="text/css"  rel="stylesheet">
	<link href="../css/datepicker3.css" type="text/css"  rel="stylesheet">
	<link href="../css/styles.css" type="text/css"  rel="stylesheet">
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<c:if test="${user1.username.length()>0}">
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">${user1.username }</div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li class="active"><a href="${pageContext.request.contextPath}/user/index.htm"><em class="fa fa-dashboard">&nbsp;</em> trang chủ</a></li>
			<li><a href="${pageContext.request.contextPath}/staff/list.htm"><em class="fa fa-calendar">&nbsp;</em> quản lý  nhân viên</a></li>
			<li><a href="${pageContext.request.contextPath}/depart/list.htm"><em class="fa fa-bar-chart">&nbsp;</em>Quản lý phòng ban</a></li>
			<li><a href="${pageContext.request.contextPath}/record/list.htm"><em class="fa fa-toggle-off">&nbsp;</em> Ghi nhận thành tích và kỷ luật</a></li>
			<li><a href="${pageContext.request.contextPath}/user/list.htm"><em class="fa fa-clone">&nbsp;</em> quản lý  tài khoản</a></li>
			<li><a href="${pageContext.request.contextPath}/user/thongke.htm"><em class="fa fa-clone">&nbsp;</em> thông kê</a></li>
			<li><a href="${pageContext.request.contextPath}/user/login.htm"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">quản lý  phòng ban </li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">quản lý   phòng ban</h1>
			</div>
		</div><!--/.row-->
	<table class="table">
  <caption>List of users</caption>
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">name</th>
      
    
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  
<c:forEach var="u" items="${depart}">
<tr>
<td>${u.id}</td>
<td>${u.name}</td>

<td><a href="depart/update/${u.id}.htm">update</a></td>
<td><a href="depart/delete/${u.id}.htm">Delete</a></td>
</tr>
</c:forEach>
  </tbody>
</table>
<li><a href="${pageContext.request.contextPath}/depart/insert.htm"> thêm  phòng bang</a></li>
			<div class="col-sm-12">
				<p class="back-link">Lumino Theme by <a href="https://www.medialoot.com">Medialoot</a></p>
			</div>
		</div><!--/.row-->
	</div>	<!--/.main-->
	</c:if>
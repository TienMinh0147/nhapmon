<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form" %>
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
	<!-- <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css"> -->
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
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
			<li class="active"><a href="index.htm"><em class="fa fa-dashboard">&nbsp;</em> trang ch???</a></li>
			<li><a href="${pageContext.request.contextPath}/staff/list.htm"><em class="fa fa-calendar">&nbsp;</em> qu???n l??  nh??n vi??n</a></li>
			<li><a href="${pageContext.request.contextPath}/depart/list.htm"><em class="fa fa-bar-chart">&nbsp;</em>Qu???n l?? ph??ng ban</a></li>
			<li><a href="${pageContext.request.contextPath}/record/list.htm"><em class="fa fa-toggle-off">&nbsp;</em> Ghi nh???n th??nh t??ch v?? k??? lu???t</a></li>
			<li><a href="${pageContext.request.contextPath}/user/list.htm"><em class="fa fa-clone">&nbsp;</em> qu???n l??  t??i kho???n</a></li>
			<li><a href="${pageContext.request.contextPath}/user/thongke.htm"><em class="fa fa-clone">&nbsp;</em> th??ng k??</a></li>
			<li><a href="${pageContext.request.contextPath}/user/login.htm"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">c???p nh??p th??nh t??ch v?? k??? lu???t  </li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="text-align: center; ">c???p nh??p th??nh t??ch v?? k??? lu???t</h1>
			</div>
		</div><!--/.row-->
		<form:form style="margin-left: 400px; " action="record/update.htm" modelAttribute="record" >

<div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username">Nh??n vi??n</label>
      <div class="controls">
    <form:input path="staff.id" value="${name }"/>
        
       
      </div>
    </div>
     <label class="control-label" for="password">Lo???i</label>
      <div class="controls">
      <c:if test="${type == true}">
 <form:radiobutton path="type" value="true" label="Th??nh t??ch" checked="checked"/>
<form:radiobutton path="type" value="false" label="K??? lu???t"/>
</c:if>
     <c:if test="${type == false}">
 <form:radiobutton path="type" value="true" label="Th??nh t??ch" />
<form:radiobutton path="type" value="false" label="K??? lu???t" checked="checked"/>
</c:if>
       
      </div>
  
	
	<div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">L?? do</label>
      <div class="controls">
      <form:textarea path="reason" rows="3"/>
      </div>
    </div>


<div>
	 <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">update</button>
      </div>
    </div>
	</div>

</form:form>
		
			<div class="col-sm-12">
				<p class="back-link">Lumino Theme by <a href="https://www.medialoot.com">Medialoot</a></p>
			</div>
		</div><!--/.row-->
	<!--/.main-->
	</c:if>
	
		
</body>
</html>
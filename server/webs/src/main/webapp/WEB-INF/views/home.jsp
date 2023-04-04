<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">


/* 		position: relative;
		position: absolute; */
	.smdd{
		/* width: 800px; */
		container: text-center;
		text-align: center; margin: 0 auto;
	}


	.mainimg {
		width: 250px;
		height: 300px;
		object-fit: contain;
	}


	.homeimg{
		width: 800px;
		text-align: center; margin: 0 auto;
	}
	
	
</style>





<div class="mainsm">


<div class='smdd'>
<div class='btnSet'>
	<a href='new.cu' class='btn-fill'>인기산</a>
</div>
<table class='w-px600 tb-list'>

<tbody>
<div class="homeimg">
<c:forEach items='${list}' var='vo'>
	<a href='info.go?id=${vo.id}'></a>
		<span style="display:inline-block; height:300px; width:250px;">
	
		<div class="card"><img class="mainimg object-fit-cover border rounded" src="${vo.filepath}"
    		 alt="사진파일">
		</div>
		<div>${vo.filename}</div>
	
		</span>
</c:forEach>
</div>
</tbody>
</table>


<div class='btnSet'>
	<a href='new.go' class='btn-fill'>게시판</a>
</div>


<table class='w-px600 tb-list'>

<tbody>
<div class="homeimg">
<c:forEach items='${list}' var='vo'>
	<a href='info.go?id=${vo.id}'></a>
		<span style="display:inline-block; height:300px; width:250px;">
	
		<div class="card"><img class="mainimg object-fit-cover border rounded" src="${vo.filepath}"
    		 alt="사진파일">
		</div>
		<div>${vo.filename}</div>
	
		</span>

</c:forEach>
</div>


</tbody>
</table>


</div>

</div>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
table tr:hover { cursor: pointer;  background-color: #f6f6f6 }



	.mainsm{
		text-align: center;
		margin-top: 30px;
	}

	.smdd{
		width: auto;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	

	
</style>
<body>

<div class="mainsm">


<div class='smdd'>

<tbody>

<h3>고객목록</h3>
<div class='btnSet'>
	<a href='new.cu' class='btn-fill'>신규고객등록</a>
</div>
<table class='w-px600 tb-list'>
<thead>
	<tr><th class='w-px140'>고객명</th>
		<th>이메일</th>
		<th class='w-px160'>전화번호</th>
	</tr>
</thead>
<tbody>
<c:forEach items='${list}' var='vo'>
	<tr><td><a href='info.cu?id=${vo.id}'>${vo.name }</a></td>
		<td>${vo.email }</td>
		<td>${vo.phone }</td>
	</tr>
</c:forEach>
</table>
	
</tbody>

</div>



</body>
</html>
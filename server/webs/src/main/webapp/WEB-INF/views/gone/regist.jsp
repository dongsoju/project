<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>탐방 글쓰기</h3>
<form method='post' enctype='multipart/form-data' action='insert.go'>
<!-- <input type='hidden' name='course_id' class="co2" >  --><!-- 코스선택 -->
<!-- <input type='hidden' name='location_id' class="loc2" >  --><!-- 산선택 -->
<input type='hidden' name='member_id' value='${loginInfo.id}'>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='title' title='제목' class='chk full'></td>
</tr>
	<tr><th>가본산/찜한산</th>
		<td>
			<label><input type='radio' name='type' value='1'>가본산</label>
			<label><input type='radio' checked name='type' value='2'>찜한산</label>
		</td>
	</tr>
<tr><th>산 선택</th>
	<td><select name='location_id' class='w-px300 loc'>
		<c:forEach items='${location}' var='l'>		
		<option value='${l.id}'>${l.locname }</option>
		
		
		</c:forEach>		
		</select>
	</td>
</tr>


<tr><th>코스 선택</th>
	<td><select name='course_id' class='w-px300 cou'>
		<c:forEach items='${course}' var='c'>		
		<option value='${c.id}'>${c.couname }</option>
		
		
		</c:forEach>		
		</select>
	</td>
</tr>
<tr><th>내용</th>
	<td><textarea name='content' title='내용' class='chk full'></textarea> </td>
</tr>
<tr><th>첨부파일</th>
	<td class='txt-left'>
		<div class='align'>
		<label>
			<input type='file' name='file' class='attach-file'>
			<a><i class="font-img-b fa-solid fa-file-circle-plus"></i></a>
		</label>
		<span class='file-name'></span>
		<span class='preview'></span>
		<a class='delete-file'><i class="font-img-r fa-regular fa-trash-can"></i></a>
		</div>
	</td>
</tr>
</table>
</form>
<div class='btnSet'>
<a class='btn-fill btn-save'>저장</a>
<a class='btn-empty' href='list.go'>취소</a>
</div>
<script>
$('.btn-save').on('click', function(){
	if( emptyCheck() ) {
// 		$(".loc2").val( $( ".loc option:selected" ).val() );
// 		$(".co2").val(  $( ".cou option:selected" ).val() );
// // 		console.log('loc2:', $(".loc2").val(), ' co2:', $(".co2").val() );
		$('form').submit();
	}
});
</script>
</body>

<script>
$(function(){
	/*  
	*/
	$( ".loc" ).change(function() {
		var v =$(this).val();
		$(".loc2").val(v);
		});
	$( ".cou" ).change(function() {
		var v =$(this).val();
		$(".co2").val(v);
		});
})
</script>

</html>
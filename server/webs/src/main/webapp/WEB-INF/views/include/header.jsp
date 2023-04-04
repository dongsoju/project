<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<header>
	<div class="head">
	<div class="mainhead">
		<a href='<c:url value="/"/>'><img src='imgs/main.png' width="100%" height="30px" ></a></li>
	</div>
	<div class="header">
	<div>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<ul>
		<!-- 로그인하지 않은 경우 -->
		<c:if test='${empty loginInfo}'>
			
		</c:if>
		<!-- 로그인된 경우 -->
		<c:if test='${not empty loginInfo}'>
			<li><a ${category eq 'cu' ? "class='active'" : ''} href='<c:url value="/"/>list.cu'>고객관리</a></li>
		</c:if>	
		<c:if test='${not empty loginInfo}'>
           <li><a ${loginInfo.admin eq 'Y' ? "class='active'" : ''} href='<c:url value="/"/>list.no'>공지사항</a></li>
		</c:if>
		<c:if test='${not empty loginInfo}'>
           <li><a ${loginInfo.admin eq 'N' ? "class=''" : 'active'} href='<c:url value="/"/>list.no'>???</a></li>
		</c:if>
        
		<%-- <c:if test="${not empty loginInfo.manager == 'N'}"> --%>
		
		<%-- <li><a ${category eq 'hr' ? "class='active'" : ''} href='<c:url value="/"/>list.hr'>사원관리</a></li> --%>
		<%-- 	  <li><a type="button" value="회원전체목록(관리자용)" class="btn" onclick="location.href='/test/member/memberList'"></a></li>
			<li><a href='<c:url value="/"/>list.hr' sec:authorize = "hasRole('Y')">사원관리</a></li> --%>
			<%-- <li><a ${category eq 'no' ? "class='active'" : ''} href='<c:url value="/"/>list.no'>공지사항</a></li> --%>
			<li><a ${category eq 'bo' ? "class='active'" : ''} href='<c:url value="/"/>list.bo'>방명록</a></li>
			<li><a ${category eq 'lo' ? "class='active'" : ''} href='<c:url value="/"/>list.lo'>전국산정보</a></li>
			<li><a ${category eq 'co' ? "class='active'" : ''} href='<c:url value="/"/>list.co'>코스정보</a></li>
			<li><a ${category eq 'go' ? "class='active'" : ''} href='<c:url value="/"/>list.go'>탐방정보</a></li>
			<li><a ${category eq 'da' ? "class='active'" : ''} href='<c:url value="/"/>list.da'>공공데이터</a></li>
			<li><a ${category eq 'vi' ? "class='active'" : ''} href='<c:url value="/"/>visual/list'>시각화</a></li>
			<li><a ${category eq 'na' ? "class='active'" : ''} href='<c:url value="/"/>list.na'>내정보</a></li>
		</ul>
	</nav>
	</div>
	<div class="login" style="float: right;">
		<ul>
		<!-- 로그인하지 않은 경우 -->
		<c:if test='${empty loginInfo}'>
			<li><a class=' btn btn-primary' href='login'>로그인</a></li>
			<li><a class=' btn btn-primary' href='member'>회원가입</a></li>
		</c:if>
		<!-- 로그인된 경우 -->
		<c:if test='${not empty loginInfo}'>
			<c:choose>
				<c:when test='${empty loginInfo.profile}'>
					<i class="font-profile fa-regular fa-circle-user"></i>
				</c:when>
				<c:otherwise>
					<img class='profile' src='${loginInfo.profile}'>
				</c:otherwise>
			</c:choose>
		
			<li><strong>${loginInfo.name}</strong> 님</li>
			<li><a class='btn-empty' href='changepw'>비밀번호변경</a></li>
			<li><a class='btn-fill' href='logout'>로그아웃</a></li>
		</c:if>
		</ul>
	</div>
	</div>
	<div>
	<!-- <img src='imgs/mainm.png' style='width:100%'> -->
	<!-- <img class="mainimg object-fit-cover border rounded" src='imgs/mainm.png' style='width:100%'> -->
	</div>
	</div>
</header>
<style>



.font-profile { font-size: 50px; }
.profile { width:50px; height: 50px;  border-radius: 50%; }
.header { 
	border-bottom: 1px solid #aaa;
	align-items: center; justify-content: space-between;
	width:auto; justify-content: center; padding: 0 100px; 
	
	display: flex;
	position: relative;
}
.login{
	position: absolute;
	left: 10ox;
	bottom: 0px;
	right: 10px;
}



header nav ul { font-size: 18px; font-weight: bold;}
header ul { display: flex; }
header nav ul li:not(:first-child) { margin-left: 50px }
header nav a:hover, header nav a.active { color:#0730fa  }

</style>

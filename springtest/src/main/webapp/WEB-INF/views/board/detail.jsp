<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	  <h2>게시글 상세</h2>
	  <c:if test="${board != null}">
	  <form action="">
	    <div class="form-group">
	      <label>번호</label>
	      <input type="text" class="form-control" name="num" value="${board.num}" readonly>
	    </div>
	    <div class="form-group">
	      <label>제목</label>
	      <input type="text" class="form-control" name="title" value="${board.title}" readonly>
	    </div>
	    <div class="form-group">
	      <label>작성자</label>
	      <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
	    </div>
	    <div class="form-group">
	      <label>작성일</label>
	      <input type="text" class="form-control" name="registerDate" value="${board.registerDate}" readonly>
	    </div>
	    <div class="form-group">
	      <label>조회수</label>
	      <input type="text" class="form-control" name="views" value="${board.views}" readonly>
	    </div>
	    <div class="form-group">
	      <label>내용</label>
	      <textarea rows="10" class="form-control" readonly>${board.content}</textarea>
	    </div>
	    <c:if test="${fList.size() != 0}">
		    <div class="form-group">
		    	<label>첨부파일</label>
		    	<c:forEach items="${fList}" var="file">	
		    	<div><a href="<%=request.getContextPath()%>/board/download?filename=${file.filename}">${file.oriFilename}</a></div>
		    	</c:forEach>
		    </div>
	    </c:if>
	  </form>
	  </c:if>
	  <c:if test="${board == null}">
	  <h1>존재하지 않은 게시글입니다.</h1>
	  </c:if>
	</div>
	<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&type=${cri.type}&search=${cri.search}">
		<button type="button" class="btn btn-outline-dark">목록</button>
	</a>
	<a href="<%=request.getContextPath()%>/board/register">
		<button type="button" class="btn btn-outline-dark">글쓰기</button>
	</a>
	<c:if test="${user != null && user.id == board.writer}">
	<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}">
		<button type="button" class="btn btn-outline-dark">수정</button>
	</a>
	<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}">
		<button type="button" class="btn btn-outline-dark">삭제</button>
	</a>
	</c:if>
</body>
</html>
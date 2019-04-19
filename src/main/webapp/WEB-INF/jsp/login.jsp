<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf" %>
<%@ include file="/common/navigation.jspf" %> 

<c:choose>
<c:when test = "${mode==MODE_LOGIN}">
<form method="POST" action="login-user">
					
					<div >
						<label>Email</label>
						<div>
							<input type="text" name="userEmail"
								value="${user.userEmail }" />
								<c:if test="${mode==INVALID_USEREMAIL}">
    								<p>${INVALID_USEREMAIL}</p>
								</c:if>
						</div>
					</div>
					
					<div >
						<label>Password</label>
						<div>
							<input type="password" name="userPassword"
								value="${user.userPassword }" />
								
								<c:if test="${mode==INVALID_PASSWORD}">
    								<p>${INVALID_PASSWORD}</p>
								</c:if>
						</div>
					</div>
					
					<div>
					<c:if test="${mode==INVALID_CREDENTIALS}">
    								<p>${INVALID_CREDENTIALS}</p>
					</c:if>	
					
					<c:if test="${mode==NOT_EXISTING_USER}">
    								<p>${NOT_EXISTING_USER}</p>
					</c:if>					
					
					
					
					</div>
					
					<div class="form-group ">
						<input type="submit" value="Login" />
						<button onclick="location.href='/register'" type="button"> Register </button>
					</div>
				</form>
</c:when>
</c:choose>
<%@include file="/common/footer.jspf" %>
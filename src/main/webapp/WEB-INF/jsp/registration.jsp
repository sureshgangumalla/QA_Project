<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf" %>
<%@ include file="/common/navigation.jspf" %> 

<c:choose>
<c:when test = "${mode==MODE_REGISTER}">
<form method="POST" action="Register">
					
					<div >
						<label>Full Name</label>
						<div>
							<input type="text" name="username"
								value="${username}" />
								
								<c:if test="${empty username}">
    								<p>${INVALID_USERNAME}</p>
								</c:if>
								
								
						</div>
					</div>
					
					<div >
						<label>Email</label>
						<div>
							<input type="text" name="userEmail"
								value="${userEmail}" />
								
								<c:if test="${mode==INVALID_USEREMAIL}">
    								<p>${INVALID_USEREMAIL}</p>
								</c:if>
								
						</div>
					</div>
					
					<div >
						<label>Phone Number</label>
						<div>
							<input type="text" name="userPhoneNumber"
								value="${userPhoneNumber}" />
								
								<c:if test="${mode==INVALID_PHONENUMBER}">
    								<p>${INVALID_PHONENUMBER}</p>
								</c:if>
						</div>
					</div>
					
					<div >
						<label>Password</label>
						<div>
							<input type="password" name="userPassword" />
								
								<c:if test="${mode==INVALID_PASSWORD}">
    								<p>${INVALID_PASSWORD}</p>
								</c:if>
								
								<c:if test="${mode==MODE_PASSWORD_DOES_NOT_MATCH}">
    								<p>${MODE_PASSWORD_DOES_NOT_MATCH}</p>
								</c:if>
						</div>
					</div>
					
					<div >
						<label>Confirm Password</label>
						<div>
							<input type="password" name="userConfirmPassword"/>
						</div>
					</div>
					
					<div class="form-group ">
						<input type="submit" value="Register" />
						<button onclick="location.href='/login'" type="button"> Login </button>
					</div>
				</form>
</c:when>
</c:choose>
<%@include file="/common/footer.jspf" %>
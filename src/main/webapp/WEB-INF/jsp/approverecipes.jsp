<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<h3>Recipes To Be Approved page</h3>
	<form method="POST" action="approve-recipe">
		<c:forEach items="${recipes}" var="recipe">
			<button type="submit" name="recipeId" class="btn btn-info"
				value="${recipe.getRecipeId()}">${recipe.getRecipeName()}</button>
		</c:forEach>
	</form>
	<form method="POST" action="cancelHome">
		<button type="submit" name="cancel" value="cancel">CANCEL</button>
	</form>
</div>
<%@include file="/common/footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">


	<form method="POST" action="submitAddRecipe">
		<br>
		<button type="submit" name="action">add New Recipe</button>
	</form>
	<form method="POST" action="submitRecipeID">
		<h3>My Rejected recipes</h3>

		<c:forEach items="${rejectedRecipes}" var="recipe">
			<button type="submit" name="recipeId" class="btn btn-info"
				value="${recipe.getRecipeId()}">${recipe.getRecipeName()}</button>

		</c:forEach>

		<h3>My pending recipes</h3>
		<c:forEach items="${pendingRecipes}" var="recipe">
			<button type="submit" name="recipeId" class="btn btn-info"
				value="${recipe.getRecipeId()}">${recipe.getRecipeName()}</button>

		</c:forEach>

	</form>
	<form method="POST" action="/recipeDesc">

		<h3>My Approved recipes</h3>
		<c:forEach items="${approvedRecipes}" var="recipe">
			<button type="submit" name="recipeId" class="btn btn-info"
				value="${recipe.getRecipeId()}">${recipe.getRecipeName()}</button>
		</c:forEach>
	</form>

</div>
<%@include file="/common/footer.jspf"%>
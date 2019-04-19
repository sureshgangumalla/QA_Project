<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">


	<div class="card">
		<h5 class="card-header info-color white-text text-center py-4">
			<strong>Recipe Details</strong>
		</h5>
	</div>

	<c:choose>
		<c:when test="${not empty rating}">
			<form method="POST" action="recipedescription">
				<h4>Your Rating:</h4>
				<input type="radio" name="rating" value="1" class="star"> <input
					type="radio" name="rating" value="2" class="star"> <input
					type="radio" name="rating" value="3" class="star"> <input
					type="radio" name="rating" value="4" class="star"> <input
					type="radio" name="rating" value="5" class="star"> <input
					type='text' name="recipeId" value='${recipe.getRecipeId()}' hidden>
				<tr>
					<td><input type="submit" value="Save" /></td>
				</tr>
			</form>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test='${follow == "NOT_FOLLOWING"}'>
			<div>
				<label>Following Status :</label>
				<div>
					<form method="POST" action="followUser">
						<input type='text' name="recipeId" value='${recipe.getRecipeId()}'
							hidden> <input type="submit" value="Follow" />
					</form>
				</div>
			</div>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test='${follow == "FOLLOWING"}'>
			<div>
				<label>Following Status :</label>
				<div>
					<input type="submit" value="Following" disabled />
				</div>
			</div>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${not empty OwnRecipe}">
			<div>
				<div>
					<form method="POST" action="/submit">
						<button type="submit" name="action" class="btn btn-info"
							value="${recipe.getRecipeId()}">${OwnRecipe}</button>
					</form>
				</div>
			</div>
		</c:when>
	</c:choose>

	<div>
		<label>Recipe Name :</label>
		<div>
			<input type='text' value='${recipe.getRecipeName()}' disabled>
		</div>
	</div>

	<div class="row">
		<label>Images :</label>
		<c:forEach items="${images}" var="image">
			<div class="col-sm-4 margin-5px">
				<div class="card">
					<!-- Card image -->
					<img class="card-img-top" src="${image.getImageLocation()}"
						alt="Recipe Image"> <input type='text'
						value='${image.getImageLocation()}' hidden>
				</div>
			</div>
		</c:forEach>
	</div>

	<div>
		<label>Recipe Categories :</label>
		<c:forEach items="${recipe.getCategories()}" var="category">
			<div>
				<input type='text' value='${category}' disabled>
			</div>
		</c:forEach>
	</div>

	<div>
		<label>Recipe Cuisine :</label>
		<div>
			<input type='text' value='${recipe.getCuisine()}' disabled>
		</div>
	</div>
	<div>
		<label>Recipe Description :</label>
		<div>
			<input type='text' value='${recipe.getDescription()}' disabled>
		</div>
	</div>
	<div>
		<label>Recipe Steps :</label>
		<div>
			<input type='text' value='${recipe.getSteps()}' disabled>
		</div>
	</div>
	<div>
		<label>Recipe Average Rating :</label>
		<div>
			<input type='text' value='${recipe.getAverageRating()}' disabled>
		</div>

	</div>
	<div>
		<label>Uploaded User Name :</label>
		<div>
			<input type='text' value='${recipe.getUserId()}' hidden>
			<input type='text' value='${recipe.getUploaderName()}' disabled>
		</div>
	</div>
	<div>
		<label>Recipe Ingredients :</label>
		<div>
			<input type='text' value='${recipe.getIngredients()}' disabled>
		</div>
	</div>
	<div>
		<form method="GET" action="/home">
			<input type="submit" value="Cancel" />
		</form>
	</div>

</div>
<%@include file="/common/footer.jspf"%>
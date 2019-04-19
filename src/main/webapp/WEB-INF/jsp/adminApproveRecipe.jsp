<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">



	<h3>Admin - Approve Recipe :</h3>
	<h3>Recipe Details</h3>
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
			<input type='text' value='${recipe.getUserId()}' hidden> <input
				type='text' value='${recipe.getUploaderName()}' disabled>

		</div>
	</div>
	<div>
		<label>Recipe Ingredients :</label>
		<div>
			<input type='text' value='${recipe.getIngredients()}' disabled>
		</div>
	</div>
	<div>
		<label>Recipe Categories :</label>
		<c:forEach items="${recipe.getCategories()}" var="category">
			<div>
				<input type='text' value='${category}' disabled>
			</div>
		</c:forEach>
	</div>

	<div class="row">

		<form method="POST" action="/recipe-response">

			<label>Admin Response :</label>
			<div class="col-sm-4 margin-5px">

				<div class="form-group ">
					<input type='text' name="recipeId" value='${recipe.getRecipeId()}'
						hidden> <input type="submit" name="submit"
						 value="approved"/> 
				</div>
			</div>
			<div class="col-sm-4 margin-5px">
				<div>
					<input type="text" name="response" value="${response}" />
				</div>

				<div class="form-group ">
					<input type='text' name="recipeId" value='${recipe.getRecipeId()}'
						hidden> <input type="submit" name="submit"
						value="rejected"/>
				</div>
			</div>
		</form>
	</div>

	<div>
		<form method="GET" action="/approverecipes">
			<input type="submit" value="Cancel" />
		</form>
	</div>

</div>


<%@include file="/common/footer.jspf"%>


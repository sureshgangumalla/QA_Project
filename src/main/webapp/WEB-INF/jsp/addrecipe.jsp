
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>

<html>
<head>
</head>
<body>
	<h3>Add / Edit Recipe page</h3>
	<form:form method="POST" action="/addrecipe" modelAttribute="recipe"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><form:input path="recipeId" type="hidden" /></td>
			</tr>
			<tr>
				<td><form:label path="recipeName">recipe name</form:label></td>
				<td><form:input path="recipeName" /></td>
				<td>${recipeErrors.recipeName}</td>
			</tr>
			<tr>
				<td><form:label path="cuisine">Cuisine</form:label></td>
				<td><form:input path="cuisine" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /></td>
			</tr>

			<tr>
				<td>Select Categories</td>
				<td><form:checkboxes items="${recipe.defaultcategories}"
						path="categories" /></td>

				<td>${recipeErrors.categories}</td>
			</tr>
			<tr>

				<td><input type="text" name="newCategory" /></td>
				<td><input type="submit" name="action" value="addNewCatergory" /></td>

				<td>${categoryErrors.categoryname }</td>
			</tr>

			<tr>
				<td><form:label path="ingredients">ingredients</form:label></td>
				<td><form:input path="ingredients" /></td>
				<td>${recipeErrors.ingredients}</td>
			</tr>
			<tr>
				<td><form:label path="steps">Steps</form:label></td>
				<td><form:input path="steps" /></td>
				<td>${recipeErrors.steps}</td>
			</tr>
			<tr>
				<td><input type="submit" name="action" value="upload" /></td>
				<td><input type="file" name="selectedFile" /></td>
				<td>${imageErrors.imageFile}</td>
			</tr>
			<tr>
				<td><c:forEach var="img" items="${recipe.images}">
						<img src="${img.getImageLocation()}" width="100" height="100" />
						<button type="submit" name="action" value="${img.getImageName() }">
							remove</button>
					</c:forEach></td>

			</tr>

			<tr>
				<td><input type="submit" name="action" value="Save" /></td>
				<td><input type="submit" name="action" value="Cancel" /></td>
			</tr>

		</table>
	</form:form>
</body>
</html>
<%@include file="/common/footer.jspf"%>
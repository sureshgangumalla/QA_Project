<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="POST" action="cancelHome">
	<br>
	<button type="submit" name="cancel" value="cancel">Go Back To
		Home</button>
</form>
<form method="POST" action="/recipeDesc">
	<h3 class="margin-top-15">Recipes Related to selected Category</h3>
	<c:forEach items="${categories}" var="cusineResults">

		<div class="col-sm-4 margin-5px">

			<!-- Card -->

			<div class="card">
				<!-- Card image -->
				<img class="card-img-top" src="/assets/images/defaultImage.jpg"
					alt="Card image cap">
				<!-- Card content -->
				<div class="card-body">
					<!-- Title -->
					s
					<button type="submit" name="recipeId" class="btn btn-info"
						value="${cusineResults.getRecipeId()}">
						<h4 class="card-title">
							<a>${cusineResults.getRecipeName()}</a>
						</h4>
					</button>
					<!-- Text -->

				</div>
			</div>

			<!-- Card -->

		</div>

	</c:forEach>
</form>


<%@include file="/common/footer.jspf"%>
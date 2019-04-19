<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<form method="POST" action="/userhome">
		<c:forEach items="${tools}" var="rights">
			<button type="submit" name="action" class="btn btn-info"
				value="${rights}">${rights}</button>
		</c:forEach>
	</form>

	<form method="POST" action="/recipeDesc">
		<h3 class="margin-top-15">New Arrival</h3>

		<div class="row">

			<c:forEach items="${newArrivals}" var="newArrivals">

				<div class="col-sm-4 margin-5px">

					<!-- Card -->

					<div class="card">
						<!-- Card image -->
						<img class="card-img-top" src='${newArrivals.getImageLocation()}'
							alt="Card image cap">
						<!-- Card content -->
						<div class="card-body">
							<!-- Title -->
							<button type="submit" name="recipeId" class="btn btn-info"
								value="${newArrivals.getRecipeId()}">
								<h4 class="card-title">
									<a>${newArrivals.getRecipeName()}</a>
								</h4>
							</button>
							<!-- Text -->

						</div>
					</div>

					<!-- Card -->

				</div>

			</c:forEach>

		</div>


		<h3 class="margin-top-15">Most Popular</h3>

		<div class="row">

			<c:forEach items="${ratingValues}" var="ratingValues">
				<div class="col-sm-4 margin-5px">

					<!-- Card -->


					<div class="card">
						<!-- Card image -->
						<img class="card-img-top" src="/assets/images/defaultImage.jpg"
							alt="Card image cap">
						<!-- Card content -->
						<div class="card-body">
							<!-- Title -->
							<button type="submit" name="recipeId" class="btn btn-info"
								value="${ratingValues.getRecipeId()}">
								<h4 class="card-title">
									<a>${ratingValues.getRecipeName()}</a>
								</h4>
							</button>
							<!-- Text -->
							<p class="card-text">${ratingValues.getRatings()}</p>
						</div>
					</div>

					<!-- Card -->

				</div>

			</c:forEach>
		</div>

	</form>


	<form method="POST" action="/categoryresults">
		<h3>Categories</h3>
		<c:forEach items="${categories}" var="category">
			<button type="submit" name="categoryID"
				value="${category.getCategoryId()}" class="btn btn-info">${category.getCategoryName()}</button>
		</c:forEach>
	</form>
</div>

<%@include file="/common/footer.jspf"%>
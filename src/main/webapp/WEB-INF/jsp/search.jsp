<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
	<form method="POST" action="/recipeDesc">
		<h3 class="margin-top-15">Search Results</h3>

		<div class="row margin-5px">
		
		<c:if test="${totalCount < 1}">
			<div><h4>No results found</h4></div>
		</c:if>

			<c:forEach items="${searchField}" var="searchResults">

				<div class="col-sm-4 margin-5px">
					<!-- Card -->
					<div class="card">
						<!-- Card image -->
						<button type="submit" name="recipeId" class="btn btn-info"
							value="${searchResults.getBuildRecipeId()}">
							<img class="card-img-top" src="/assets/images/defaultImage.jpg"
								alt="Card image cap">
						</button>
						<!-- Card content -->
						<div class="card-body">
							<!-- Title -->
							<h4 class="card-title">
								<a>${searchResults.getBuildRecipeName()}</a>
							</h4>
							<!-- Text -->
							<p class="card-text">${searchResults.getBuildRecipeId()}</p>
						</div>
					</div>
					<!-- Card -->
				</div>
			</c:forEach>

		</div>
	</form>

	<nav aria-label="Page navigation example">
		<ul class="pagination pg-blue justify-content-center">
			<c:forEach begin="0" end="${numberOfPages}" var="numberOfPages">
				<c:if test="${numberOfPages > 0}">
					<li class="page-item"><a class="page-link"
						href="/searchResults?search=&page=${numberOfPages}">${numberOfPages}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</nav>
</div>

<%@include file="/common/footer.jspf"%>
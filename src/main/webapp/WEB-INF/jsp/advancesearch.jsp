<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf" %>
<%@ include file="/common/navigation.jspf" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h3 class="margin-top-15">Advance Results</h3>
	<!-- Medium input -->
	<div class="md-form">
	  <input type="text" id="searchTerm" class="form-control" value="${searchField}">
	  <label for="inputMDEx">Search Term</label>
	</div>
	
	<h4>Filter Cuisine</h4>
	
	  <select id="mySelect" class="browser-default custom-select">
	  	<c:forEach items="${cuisines}" var="cuisines">
	  		<option value="${cuisines}">${cuisines}</option>
	    </c:forEach>
	  </select>
	  
	<div class="margin-botton-2"></div>
	
	<h4>Categories</h4>
	<!-- Default unchecked -->
	<div class="custom-control custom-checkbox">
	    <input type="checkbox" class="custom-control-input" id="All">
	    <label class="custom-control-label" for="All">All</label>	    
	</div>
	
	<c:forEach items="${categories}" var="categories">
	<div class="custom-control custom-checkbox">
	    <input type="checkbox" class="custom-control-input" id="${categories.getCategoryName()}">
	    <label class="custom-control-label" for="${categories.getCategoryName()}">${categories.getCategoryName()}</label>	    
	</div>
	</c:forEach>
	
	
	
	<div class="margin-botton-2"></div>
	
	<div  class="btn btn-default searchButton">Search</div>
	<div  class="btn btn-danger">Cancel</div>
	
</div>


<%@include file="/common/footer.jspf" %>
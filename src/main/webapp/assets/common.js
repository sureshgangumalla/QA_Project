$(document).ready(function(){
	$('.login-link').hide();
	$('.logout-link').hide();
	$.ajax({
		  url: "/checkSession",
		  context: document.body
		}).done(function(response) {
			if(response){
				$('.login-link').hide();
				$('.register-link').hide();
				$('.logout-link').show();
			}else{
				$('.login-link').show();
				$('.register-link').show();
				$('.logout-link').hide();
			}
		});

});


$('.search-button-navigation').click(function(){
	var input = $('.serch-input-navigation').val();	
	window.location.replace("/searchResults?search="+input+"&page=1");
});

$('.advance-search-button-navigation').click(function(){
	var input = $('.serch-input-navigation').val();	
	window.location.replace("/advanceSearchResults?search="+input);
});

$('#All').click(function(){
	 if ($("#All").is(':checked')) {
         $("input[type=checkbox]").each(function () {
             $(this).prop("checked", true);
         });
	 } else {
         $("input[type=checkbox]").each(function () {
             $(this).prop("checked", false);
         });
     }
});

$('#Chicken').click(function(){
	if($('#Vegatarian').is(':checked')){
		$('#Vegatarian').prop("checked", false);
	}
	if($('#Vegan').is(':checked')){
		$('#Vegan').prop("checked", false);
	}
});

$('#Beef').click(function(){
	if($('#Vegatarian').is(':checked')){
		$('#Vegatarian').prop("checked", false);
	}
	if($('#Vegan').is(':checked')){
		$('#Vegan').prop("checked", false);
	}
});

$('#Vegatarian').click(function(){
	if($('#Chicken').is(':checked')){
		$('#Chicken').prop("checked", false);
	}
	if($('#Beef').is(':checked')){
		$('#Beef').prop("checked", false);
	}
});

$('#Vegan').click(function(){
	if($('#Chicken').is(':checked')){
		$('#Chicken').prop("checked", false);
	}
	if($('#Beef').is(':checked')){
		$('#Beef').prop("checked", false);
	}
});

$('#search').on('input',function(){
	var inputString = $('#search').val();
	var urlString = "/searchSuggestions?searchSuggestion="+inputString;
	$.get(urlString,function(data){
		console.log(data);
		var availableTags  =  data;
		$('#search').autocomplete({
			source: availableTags
		});
	})
	
});

$('.searchButton').click(function(){
	
	var searchTerm = $('#searchTerm').val();
	var selected = [];
	var selectedCategories = "";
	$('.custom-checkbox input:checked').each(function() {
	    selected.push($(this).attr('id'));
	    if(selectedCategories){
	    	selectedCategories = selectedCategories+","+$(this).attr('id');
	    }else{
	    	selectedCategories = $(this).attr('id');
	    }
	    
	});
	
	
	var selectedCuisine = $("#mySelect").val();
	var url = "/advanceResults";
	var data = {search: searchTerm, cuisine: selectedCuisine , category: selectedCategories};
	$.post(url,data,function(data, status, jqXHR){
		$("body").html(data);
	}).done(function(){
//		alert("process completed")
	})
});






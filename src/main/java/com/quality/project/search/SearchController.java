package com.quality.project.search;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.quality.project.recipeCategory.CategoriesBusiness;

@RestController
public class SearchController {
	@RequestMapping("/searchSample")
	public String sample() {
		SearchBusiness searchObj = new SearchBusiness();
		return String.valueOf(searchObj.getCurrentUserId());
	}	
	@RequestMapping("/searchSuggestions")
	public List<String> searchSuggestion(@RequestParam("searchSuggestion") String searchSuggestion) {
		SearchBusiness searchObj = new SearchBusiness();
		List<String> searchSuggestions= new ArrayList<String>();
		List<ISearch> searchList = searchObj.getSearchSuggestions(searchSuggestion);
		for (int i = 0; i < searchList.size(); i++) {
			searchSuggestions.add(searchList.get(i).getSearchSuggestion());
		}
		return searchSuggestions;
	}
	@RequestMapping("/searchResults")
	public @ResponseBody ModelAndView getSearchResults(@RequestParam("search") String search, @RequestParam("page") int pageNumber) throws Exception{
		SearchBusiness searchObj = new SearchBusiness();
		ISearchSuggestion searchSuggestions = new SearchBusiness();
		searchSuggestions.insertSearchSuggestion(search);		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("search");
		mav.addObject("numberOfPages",searchObj.getTotalNumberOfPageCount(search));
		mav.addObject("searchField",searchObj.searchResults(search, pageNumber));
		mav.addObject("totalCount",searchObj.getTotalResultCount());
		return mav;
	}	
	@RequestMapping("/advanceSearchResults")
	public @ResponseBody ModelAndView getAdvanceSerchResults(@RequestParam("search") String search) throws Exception{		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("advancesearch");		
		SearchBusiness searchObj = new SearchBusiness();
		mav.addObject("cuisines",searchObj.getAllCusines());		
		CategoriesBusiness categoryBusinessObj = new CategoriesBusiness();
		mav.addObject("categories",categoryBusinessObj.getAllCategories());		
		mav.addObject("searchField",search);
		return mav;
	}	
	@RequestMapping("/advanceResults")
	public @ResponseBody ModelAndView getResultsForAdvanceSearch(@RequestParam("search") String search, @RequestParam("cuisine") String cuisine, @RequestParam("category") String category ){
		ModelAndView mav = new ModelAndView();		
		SearchBusiness searchObj = new SearchBusiness();		
		mav.setViewName("search");
		searchObj.getTotalNumberOfPageCount(search);
		mav.addObject("searchField",searchObj.getAdvanceSearchResults(search, cuisine, category));
		mav.addObject("totalCount",searchObj.getTotalResultCount());
		return mav;
	}	

	
}

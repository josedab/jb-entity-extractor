package com.technobitia.entities.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Strings;
import com.technobitia.entities.exceptions.EntityException;
import com.technobitia.entities.model.Entity;
import com.technobitia.entities.request.EntityRequest;
import com.technobitia.search.client.SearchResultClient;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.model.SearchData;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;
import com.technobitia.wikipedia.client.WikipediaClient;
import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.model.WikipediaInformation;
import com.technobitia.wikipedia.request.WikipediaRequest;

public class EntityClient {
    private WikipediaClient wikipediaClient;
    private SearchResultClient searchResultClient;
    
    public EntityClient() {
        wikipediaClient = new WikipediaClient();
        searchResultClient = new SearchResultClient();
    }
    
    public Entity extractEntityInformation(EntityRequest request) throws EntityException {
        checkNotNull(request);
        
        SearchRequest searchRequest = new SearchRequest.Builder(request.getTerm())
                                                       .build();
        SearchData searchData = null;
        try {
            searchData = searchResultClient.extractSearchData(searchRequest);
        } catch (SearchResultException e) {
            throw new EntityException("Issues retrieving results for the term");
        }

        List<SearchResult> searchResults = searchData.getSearchResults();
        SocialProfile socialProfile = searchData.getSocialProfile();
        WikipediaInformation wikipediaInfo = null;
        
        if(!Strings.isNullOrEmpty(socialProfile.getWikipedia())){
            WikipediaRequest wikipediaRequest = new WikipediaRequest.Builder(socialProfile.getWikipedia())
                                                                    .build();
            try {
                wikipediaInfo = wikipediaClient.extractSidebarInformation(wikipediaRequest);
            } catch (WikipediaException e) {
                wikipediaInfo = null;
            }
        }
        
        Entity entity = new Entity.Builder(request.getTerm())
                                  .withLinks(searchResults)
                                  .withSocialProfile(socialProfile)
                                  .withWikipediaInformation(wikipediaInfo)
                                  .build();
        
        
        return entity;
    }
}

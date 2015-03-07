package com.technobitia.entities.model;

import java.util.List;

import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.wikipedia.model.WikipediaInformation;

public class Entity {
    private final String name;
    private WikipediaInformation wikipedia;
    private SocialProfile social;
    private List<SearchResult> links;

    private Entity(Builder builder) {
        this.name = builder.name;
        this.wikipedia = builder.wikipedia;
        this.social = builder.social;
        this.links = builder.links;
    }

    public String getName() {
        return name;
    }

    public WikipediaInformation getWikipedia() {
        return wikipedia;
    }

    public SocialProfile getSocial() {
        return social;
    }

    public List<SearchResult> getLinks() {
        return links;
    }

    public static class Builder {
        private final String name;
        private WikipediaInformation wikipedia;
        private SocialProfile social;
        private List<SearchResult> links;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withWikipediaInformation(WikipediaInformation wikipediaInfo) {
            this.wikipedia = wikipediaInfo;
            return this;
        }

        public Builder withLinks(List<SearchResult> links) {
            this.links = links;
            return this;
        }

        public Builder withSocialProfile(SocialProfile socialProfile) {
            this.social = socialProfile;
            return this;
        }

        public Entity build() {
            return new Entity(this);
        }
    }

}

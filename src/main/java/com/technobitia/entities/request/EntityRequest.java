package com.technobitia.entities.request;

public class EntityRequest {
    
    private final String term;

    private EntityRequest(Builder requestBuilder) {
        this.term = requestBuilder.term;
    }

    public String getTerm() {
        return term;
    }

    public static class Builder {
        private final String term;

        public Builder(String term) {
            this.term = term;
        }

        public EntityRequest build() {
            return new EntityRequest(this);
        }
    }
}

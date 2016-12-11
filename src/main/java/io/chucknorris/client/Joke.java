/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.chucknorris.client;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a joke.
 *
 * @author Marcel Overdijk
 * @since 1.0.0
 */
public class Joke {

    private String id;
    private String value;
    private String sourceUrl;
    private String iconUrl;
    private List<String> categories = new ArrayList<>();

    /**
     * Returns the unique id of the joke.
     *
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique id of the joke.
     *
     * @param id the unique id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the actual joke.
     *
     * @return the joke
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the actual joke.
     *
     * @param value the joke value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the source url of the joke.
     *
     * @return the source url
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * Sets the source url of the joke.
     *
     * @param sourceUrl the source url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * Returns the icon url of the joke.
     *
     * @return the icon url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Sets the icon url of the joke.
     *
     * @param iconUrl the source url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * Returns the categories associated with the joke.
     *
     * @return the categories, not null
     */
    public List<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    /**
     * Sets the categories associated with the joke.
     *
     * @param categories the categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Adds a category to the list of categories associated with the joke.
     *
     * @param category the category
     */
    public void addCategory(String category) {
        getCategories().add(category);
    }

    /**
     * Adds the categories to the list of categories associated with the joke.
     *
     * @param categories the categories
     */
    public void addCategories(List<String> categories) {
        getCategories().addAll(categories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Joke joke = (Joke) o;
        if (id != null ? !id.equals(joke.id) : joke.id != null) {
            return false;
        }
        if (value != null ? !value.equals(joke.value) : joke.value != null) {
            return false;
        }
        if (sourceUrl != null ? !sourceUrl.equals(joke.sourceUrl) : joke.sourceUrl != null) {
            return false;
        }
        if (iconUrl != null ? !iconUrl.equals(joke.iconUrl) : joke.iconUrl != null) {
            return false;
        }
        return categories != null ? categories.equals(joke.categories) : joke.categories == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (sourceUrl != null ? sourceUrl.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", categories=" + categories +
                '}';
    }
}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        getCategories().add(category);
    }

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

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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * The official {@code https://api.chucknorris.io} Java client.
 *
 * @author Marcel Overdijk
 * @since 1.0.0
 */
public class ChuckNorrisClient {

    public static final String BASE_URL = "https://api.chucknorris.io/jokes";

    /**
     * Returns a list of available categories.
     * The list is sorted on the count of the categories.
     *
     * @return the list of available categories
     * @throws ChuckNorrisException in case an error occurs while retrieving the categories
     */
    public List<String> getCategories() {
        try {
            HttpURLConnection conn = createConnection(BASE_URL + "/categories");
            int respCode = conn.getResponseCode();
            if (respCode == HttpURLConnection.HTTP_OK) {
                List<String> categories = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(new JSONTokener(conn.getInputStream()));
                for (int i = 0; i < jsonArray.length(); i++) {
                    categories.add(jsonArray.getString(i));
                }
                return categories;
            } else {
                String errorMessage = getErrorMessage(conn);
                throw new ChuckNorrisException(
                        "Error retrieving categories: (#" + respCode + ") " + errorMessage);
            }
        } catch (IOException e) {
            throw new ChuckNorrisException("Error retrieving categories", e);
        }
    }

    /**
     * Returns a random Chuck Norris joke.
     *
     * @return the random joke
     * @throws ChuckNorrisException in case an error occurs while retrieving the categories
     */
    public Joke getRandomJoke() {
        return getRandomJoke(null);
    }

    /**
     * Returns a random Chuck Norris joke for the given category.
     *
     * @param category the category
     * @return the random joke
     * @throws ChuckNorrisException in case an error occurs while retrieving the categories
     */
    public Joke getRandomJoke(String category) {
        try {
            StringBuilder url = new StringBuilder(BASE_URL + "/random");
            if (category != null && category.length() > 0) {
                url.append("?category=").append(category);
            }
            HttpURLConnection conn = createConnection(url.toString());
            int respCode = conn.getResponseCode();
            if (respCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonObject = new JSONObject(new JSONTokener(conn.getInputStream()));
                return parseJoke(jsonObject);
            } else {
                String errorMessage = getErrorMessage(conn);
                throw new ChuckNorrisException(
                        "Error retrieving random joke: (#" + respCode + ") " + errorMessage);
            }
        } catch (IOException e) {
            throw new ChuckNorrisException("Error retrieving random joke", e);
        }
    }

    /**
     * Searches Chuck Norris jokes for the given free-text query.
     *
     * @param query the free-text query
     * @return the list of jokes
     * @throws ChuckNorrisException in case an error occurs while retrieving the categories
     */
    public List<Joke> searchJokes(String query) {
        try {
            StringBuilder url = new StringBuilder(BASE_URL + "/search");
            url.append("?query=").append(query);
            HttpURLConnection conn = createConnection(url.toString());
            int respCode = conn.getResponseCode();
            if (respCode == HttpURLConnection.HTTP_OK) {
                List<Joke> jokes = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(new JSONTokener(conn.getInputStream()));
                if (jsonObject.getInt("total") > 0) {
                    JSONArray jsonResult = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonResult.length(); i++) {
                        JSONObject jsonJoke = jsonResult.getJSONObject(i);
                        jokes.add(parseJoke(jsonJoke));
                    }
                }
                return jokes;
            } else {
                String errorMessage = getErrorMessage(conn);
                throw new ChuckNorrisException(
                        "Error searching jokes: (#" + respCode + ") " + errorMessage);
            }
        } catch (IOException e) {
            throw new ChuckNorrisException("Error searching jokes", e);
        }
    }

    /**
     * Creates a new {@link HttpURLConnection} for the given url. Also sets the user agent.
     */
    private HttpURLConnection createConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty("User-Agent",
                "chucknorris-io/client-java-" + getVersion());
        return conn;
    }

    /**
     * Returns the version string or {@code null} if it cannot be determined.
     * 
     * @see Package#getImplementationVersion()
     */
    private String getVersion() {
        Package pkg = ChuckNorrisClient.class.getPackage();
        return (pkg != null ? pkg.getImplementationVersion() : null);
    }

    /**
     * Parses the {@code JSONObject} and converts it to a {@link Joke} object.
     */
    private Joke parseJoke(JSONObject jsonObject) {
        Joke joke = new Joke();
        joke.setId(jsonObject.optString("id"));
        joke.setValue(jsonObject.optString("value"));
        joke.setSourceUrl(jsonObject.optString("url"));
        joke.setIconUrl(jsonObject.optString("icon_url"));
        JSONArray jsonCategories = jsonObject.optJSONArray("category");
        if (jsonCategories != null) {
            for (int i = 0; i < jsonCategories.length(); i++) {
                joke.addCategory(jsonCategories.getString(i));
            }
        }
        return joke;
    }

    private String getErrorMessage(HttpURLConnection conn) throws IOException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(conn.getErrorStream()));
        return jsonObject.optString("message");
    }
}

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

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Tests for {@link ChuckNorrisClient}.
 *
 * @author Marcel Overdijk
 */
public class ChuckNorrisClientTests {

    private ChuckNorrisClient client;

    @Before
    public void setUp() {
        this.client = new ChuckNorrisClient();
    }

    @Test
    public void testGetCategories() {
        List<String> categories = client.getCategories();
        assertThat(categories, is(CoreMatchers.notNullValue()));
        assertThat(categories, hasSize(greaterThan(0)));
        assertThat(categories, hasItems(equalTo("explicit"), equalTo("dev")));
    }

    @Test
    public void testGetRandomJoke() {
        Joke joke = client.getRandomJoke();
        assertThat(joke.getId(), is(notNullValue()));
        assertThat(joke.getValue(), is(notNullValue()));
        assertThat(joke.getSourceUrl(), is(notNullValue()));
        assertThat(joke.getIconUrl(), is(notNullValue()));
        assertThat(joke.getCategories(), is(notNullValue()));
    }

    @Test
    public void testGetRandomJokeByCategory() {
        Joke joke = client.getRandomJoke("dev");
        assertThat(joke.getId(), is(notNullValue()));
        assertThat(joke.getValue(), is(notNullValue()));
        assertThat(joke.getSourceUrl(), is(notNullValue()));
        assertThat(joke.getIconUrl(), is(notNullValue()));
        assertThat(joke.getCategories(), is(notNullValue()));
    }

    @Test
    public void testSearchJokes() {
        List<Joke> jokes = client.searchJokes("developer");
        assertThat(jokes, hasSize(greaterThan(0)));
        Joke joke = jokes.get(0);
        assertThat(joke.getId(), is(notNullValue()));
        assertThat(joke.getValue(), is(notNullValue()));
        assertThat(joke.getSourceUrl(), is(notNullValue()));
        assertThat(joke.getIconUrl(), is(notNullValue()));
        assertThat(joke.getCategories(), is(notNullValue()));
    }
}

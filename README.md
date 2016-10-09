# Official chucknorris.io api client for Java

[chucknorris.io][] is a free JSON API for hand curated Chuck Norris facts.

Chuck Norris facts are satirical factoids about martial artist and actor Chuck Norris that have become an Internet
phenomenon and as a result have become widespread in popular culture. The 'facts' are normally absurd hyperbolic claims
about Norris' toughness, attitude, virility, sophistication, and masculinity.

Chuck Norris facts have spread around the world, leading not only to translated versions, but also spawning localized
versions mentioning country-specific advertisements and other Internet phenomena. Allusions are also sometimes made to
his use of roundhouse kicks to perform seemingly any task, his large amount of body hair with specific regard to his
beard, and his role in the action television series Walker, Texas Ranger.

## Installation

Add the dependency to your project:

_Maven_:

```xml
<dependency>
  <groupId>io.chucknorris</groupId>
  <artifactId>client-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

_Gradle_:

```groovy
dependencies {
    compile "io.chucknorris:client-java:1.0.0"
}
```

## Usage

```java
// create the chuck norris client
ChuckNorrisClient client = new ChuckNorrisClient();

// get a random joke and print it
Joke joke = client.getRandomJoke();
System.out.println(joke.getValue());

// get a random joke with a specifc category
Joke joke = client.getRandomJoke("dev");

// search jokes with free-text search
List<Joke> jokes = client.searchJokes("developer");

// get a list of available categories
List<String> categories = client.getCategories();
```

## License

This software is released under version 2.0 of the [Apache License][].


[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
[chucknorris.io]: https://api.chucknorris.io

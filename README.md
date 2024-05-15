### Repo Spotlight

The Repo Spotlight allows a user to query the github API for the most popular repositories and view
some relevant information about said repositories.

I made a conscious effort to try to replicate how I would normally work on an Android application,
so this included reviewing the specific requirements, thinking a bit about general architecture,
and reviewing Github's [api documentation](https://docs.github.com/en/rest/using-the-rest-api/using-pagination-in-the-rest-api?apiVersion=2022-11-28)

Note the API is pretty prescriptive about how to reference nested URL's so I've avoided manually concatenating 
URL's like ```https://api.github.com/repos/{owner}/{repo}/contributors``` and opted in favor of referencing the
`contributors_url` field provided in the initial response.

This is a single-activity application that leverages the android navigation library, and the List/Detail ui
paradigm.

## Architecture

The application follows a MVVM architectural pattern and also leverages a local TestRepository that was
particularly useful for me while still in development. 

Along with the specific asks of the prompt, I added some custom logic like displaying stargazers count,
relevant topics, and highlighting any repositories that reference "Android" in the topics.

## Libraries

Libraries used include Retrofit, Paging, Hilt, Mockito, and others.

## Extras

There were some other nice to have's that could have been included in this to display familiarity with specific API's.
Some things that come to mind include perhaps persisting specific repos like ones saved by the user or perhaps the already
highlighted Android-related apps. In the interest of time, I avoided building any additional UI beyond the list and detail screen,
opting instead in favor for supporting launching a custom chrome tab to view the full repository.

There appears to be an issue with calling ```appNavigator.navigateUp()``` that is likely caused by the
MutableViewEvent stream continuing to fire after the initial emit of a view event. In the interest of time, I'm
explicitly calling ` navController.navigate(Screen.Home.route)` but this really scales better for a larger app with
```appNavigator.navigateUp```

Similarly, the decision to store the selected repo within the AppNavigator would not scale well for an app that has more screens and 
flows beyond List/Detail screen

To-do: Consider using oAuth token to prevent rate limits (Gradle secrets is already available)
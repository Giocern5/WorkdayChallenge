# WorkdayChallenge
<h1>How to run: </h1>
Clone the app and test on device of chose. I used Pixel 3A and Pixel 6 Pro for testing. Click play button inorder to install and run the app.

<h1>Usage: </h1>
Users can scroll through a list of pokemon and click on an item to navigate to a details pages. A user can also search for a valid Pokemon and see more details.
<p float="left">
  <img src="https://github.com/Giocern5/WorkdayChallenge/assets/38301046/9205329d-be00-491a-a8d1-f23382525d95" alt="Screen Recording 2024-04-26 at 7 44 43 PM 4" width="240" height="450" />
  <img src="https://github.com/Giocern5/WorkdayChallenge/assets/38301046/ecb306e8-2a88-4c4f-947a-d010a44c3ad2" alt="Screen Recording 2024-04-26 at 7 44 43 PM 2" width="240" height="450" />
</p>

<h1>Project Overview:</h1>

Utilized MVVM architecture entirely in Jetpack Compose.

Implemented a shared ViewModel between the two screens.

Incorporated Hilt for Dependency Injection.

Added custom pagination for infinite scrolling

Used Navigation to help with app navigation of screens.

<h2>API Call Optimization:</h2>

Avoided chaining API calls to retrieve detailed Pokémon information to reduce network load.

API had limitations on how to get more details after detailed information was given. Multiple other api calls would be needed. So chose to implement 2 individual network request and use available information to increase user experience. API would need to be refactored to include neccessary data for list screen and details screen.

Implemented a placeholder image. Clicking on an item or searching for a Pokémon triggers a separate API call.


<h2>Application Flow:</h2>
PokemonListScreen: Initial screen displaying a list of Pokémon.

PokemonDetailScreen: Screen displaying detailed information about a selected Pokémon.


<h1>Built Using</h1>
<li>Retrofit: Used to easily and safely make HTTP requests to RESTFUL Apis </li>
<li>Hilt: Used for dependency injection to help moduralize the code for testability and readability</li>
<li>Coroutines: Useed for its ansynchronous porgamming</li>
<li>Jetpack Compose: Used per project requirements</li>
<li>Jetpack Navigation: Used to simplify navigation</li>
<li>Glide for image loading: Helps with image caching and loading</li>

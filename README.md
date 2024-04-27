# WorkdayChallenge

Project demo of getting a list of Pokemon and searching for a specific Pokemon
<p float="left">
  <img src="https://github.com/Giocern5/WorkdayChallenge/assets/38301046/c6da0875-7e0c-4d0d-a905-ca533222ee08" alt="Screen Recording 2024-04-26 at 7 44 43 PM 4" width="240" height="450" />
  <img src="https://github.com/Giocern5/WorkdayChallenge/assets/38301046/25a4ff76-ff36-4454-b98b-98f3a597b0a7" alt="Screen Recording 2024-04-26 at 7 44 43 PM 2" width="240" height="450" />
</p>

<h1>Project Overview:</h1>

Utilized MVVM architecture entirely in Jetpack Compose.

Implemented a shared ViewModel between the two screens.

Incorporated Hilt for Dependency Injection.

Added custom pagination for infinite scrolling

<h2>API Call Optimization:</h2>

Avoided chaining API calls to retrieve detailed Pokémon information to reduce network load.

Implemented a placeholder image. Clicking on an item or searching for a Pokémon triggers a separate API call.


<h2>Application Flow:</h2>
PokemonListScreen: Initial screen displaying a list of Pokémon.

PokemonDetailScreen: Screen displaying detailed information about a selected Pokémon.


<h1>Built Using</h1>
<li>Retrofit</li>
<li>Coroutines</li>
<li>Jetpack Compose</li>
<li>Jetpack Navigation</li>
<li>Coil for image loading</li>

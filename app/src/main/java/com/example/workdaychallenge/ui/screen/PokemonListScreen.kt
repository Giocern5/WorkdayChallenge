package com.example.workdaychallenge.ui.screen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.workdaychallenge.R
import com.example.workdaychallenge.data.model.PokemonQuery
import com.example.workdaychallenge.ui.CircularProgressBar
import com.example.workdaychallenge.ui.ErrorMessage
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PokemonListScreen(navController: NavHostController, viewModel: PokemonViewModel) {

    val pokemonList = viewModel.pokemonList.observeAsState(initial = emptyList())
    val pokemonDetails = viewModel.pokemonDetails.observeAsState(initial = null)
    val isLoading = viewModel.isLoading.observeAsState(initial = false)
    val errorMessage = viewModel.errorMessage.observeAsState(initial = "")
    val context = LocalContext.current
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)

    // Only need this to run once
    LaunchedEffect(Unit) {
            viewModel.setPokemonList()
    }

    // Navigating to details page on either search input or item click
    LaunchedEffect(pokemonDetails.value) {
        if (pokemonDetails.value != null) {
            navController.navigate(Screen.PokemonDetail.route)
        }
    }

    // Showing error message
    LaunchedEffect(key1 = errorMessage.value) {
        if(errorMessage.value.isNotEmpty()) {
            Toast.makeText(context,errorMessage.value, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMessage()
        }
    }

    SwipeRefresh(
        state = refreshState,
        onRefresh = { viewModel.setPokemonList() },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Screen set up
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(viewModel)
            when {
                isLoading.value && pokemonList.value.isEmpty() -> {
                    CircularProgressBar(isLoading = isLoading.value)
                }
                pokemonList.value.isNotEmpty() -> {
                    PokemonList(pokemonList, viewModel, isLoading)
                }
                else -> {
                    ErrorMessage()
                }
            }

        }
    }
}

@Composable
fun SearchBar(viewModel: PokemonViewModel) {
    var searchBarText by remember { mutableStateOf("") }

    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)) {

        OutlinedTextField(value = searchBarText,
            onValueChange = { text -> searchBarText = text },
            modifier = Modifier
            .height(45.dp)
            .weight(1f)
            .background(Color.Gray))
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            if(searchBarText.isNotEmpty() ) {
                viewModel.setPokemonDetails(searchBarText)
            }
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            modifier = Modifier.height(45.dp)) {
            Text(text = "Search", color = Color.White)
        }
    }
}

@Composable
fun PokemonList(pokemonList: State<List<PokemonQuery>>,
                viewModel: PokemonViewModel, isLoading:State<Boolean>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)) {
        items(pokemonList.value.size) { index ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .clickable {
                        viewModel.setPokemonDetails(pokemonList.value[index].name)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.pokemon_ball_foreground),
                    contentDescription = pokemonList.value[index].name,
                    modifier = Modifier.size(170.dp)
                )
                Text(
                    text = pokemonList.value[index].name,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp,
                    color = Color.White,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            // Load more items when reaching the end of the list
            if (index == pokemonList.value.size - 1 && !isLoading.value) {
                viewModel.setPokemonList()
            }
        }
    }
}

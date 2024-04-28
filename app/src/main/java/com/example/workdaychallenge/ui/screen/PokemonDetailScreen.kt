package com.example.workdaychallenge.ui.screen

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.workdaychallenge.data.model.PokemonDetails
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.workdaychallenge.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.workdaychallenge.data.model.Types
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.workdaychallenge.ui.ErrorMessage
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(navController: NavHostController, viewModel: PokemonViewModel) {

    val pokemonDetails = viewModel.pokemonDetails.observeAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current

    // Screen set up
    Column( modifier = Modifier.fillMaxSize()) {
        when{
            pokemonDetails.value != null -> {
                PokemonImage( name = pokemonDetails.value?.name,
                    url = pokemonDetails.value?.sprites?.other?.home?.front_default)
                PokemonAboutSection(pokemonDetails)
            }
            else -> {
                ErrorMessage()
            }
        }

        // Used to clear current item due to how we navigate to this screen
        DisposableEffect(key1 = backDispatcher) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.clearPokemonDetails()
                    navController.popBackStack()
                }
            }
            backDispatcher?.onBackPressedDispatcher?.addCallback(lifecycleOwner, callback)
            onDispose {
                callback.remove()
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonImage(name: String?, url: String?) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(270.dp)) {
        GlideImage(model =url ,
            contentDescription = "Image for $name",
            modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun PokemonAboutSection(pokemonDetails: State<PokemonDetails?>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())) {
            pokemonDetails.value?.let { details ->
                Text(text = details.name,
                    color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                DividerLine()
                DescriptionCell(description = stringResource(id = R.string.id),
                    value = details.id)
                DescriptionCell(description = stringResource(id = R.string.height),
                    value = stringResource(R.string.heightValue ,details.height))
                DescriptionCell(description = stringResource(id = R.string.weight),
                    value =  stringResource(R.string.weightValue ,details.weight))
                TypesCell( types = details.types)
                details.stats.let { stats->
                    stats.forEach{ stat->
                        DescriptionCell(description = stat.stat.name, value =  stat.base_stat) }
                }
            }
        }
}

@Composable
fun DividerLine() {
    Divider(
        color = Color.White,
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(bottom = 8.dp)
    )
}
@Composable
fun DescriptionCell(description: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = description,  color = Color.White, fontSize = 22.sp)
        Text(text = value,  color = Color.White, fontSize = 22.sp)
    }
}

@Composable
fun TypesCell(types: List<Types>) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
        ) {
        Text(text = stringResource(id = R.string.types), color = Color.White, fontSize = 22.sp)
        Spacer(modifier = Modifier.weight(1f))
            types.forEach { type ->
                Text(
                    text = " ${type.type.name}",
                    color = Color.White,
                    fontSize = 22.sp,
                )
            }
        }
}


package com.example.workdaychallenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.workdaychallenge.R

@Composable
fun ErrorMessage(){
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.errorMessage1),
            color= Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
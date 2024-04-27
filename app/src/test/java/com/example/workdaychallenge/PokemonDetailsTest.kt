package com.example.workdaychallenge

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText

import com.example.workdaychallenge.ui.screen.showError
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(JUnit4::class)
//class PokemonDetailsScreenTest {
//
////    @get:Rule
////    var composeTestRule: ComposeContentTestRule = createAndroidComposeRule<MainActivity>()
////
////    @Test
////    fun testShowError() {
////        composeTestRule.setContent {
////            showError()
////        }
////
////        // Verify that the error message is displayed
////        composeTestRule
////            .onNodeWithText("Oops, looks like something went wrong!", useUnmergedTree = true)
////            .assertExists()
////    }
//}


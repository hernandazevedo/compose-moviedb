package com.hernandazevedo.composemovies.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hernandazevedo.composemovies.R

@Composable
fun CustomEmptyStateScreen(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    description: String
    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = image), contentDescription = null)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.googlesans_bold)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = title,
                    )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.googlesans_regular)),
                    fontWeight = FontWeight(400),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    text = description,
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}


@Preview
@Composable
fun EmptyMoviesScreenPrev() {
    CustomEmptyStateScreen(

        image = R.drawable.background_empty_state,
        title = "No results found",
        description = stringResource(id = R.string.empty_screen_description_no_results, "crepusculo")
    )
}

@Preview
@Composable
fun NoInternetConnectionPrev() {
    CustomEmptyStateScreen(
        image = R.drawable.background_no_internet_connection,
        title = "No internet",
        description = "Check your internet connection and try again"
    )
}

@Preview
@Composable
fun FavoriteEmptyPrev() {
    CustomEmptyStateScreen(
        image = R.drawable.background_box_empty_state,
        title = "No favorites found",
        description = "Favorite movies to see them here"
    )
}
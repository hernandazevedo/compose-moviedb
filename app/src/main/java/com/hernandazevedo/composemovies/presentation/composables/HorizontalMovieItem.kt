package com.hernandazevedo.composemovies.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.hernandazevedo.composemovies.R
import com.hernandazevedo.composemovies.convertDateToFormattedString
import com.hernandazevedo.composemovies.ui.theme.Green40

@Composable
fun HorizontalMovieItem(
    title: String,
    imageUrl: String,
    rating: Float,
    releaseDate: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        //elevation = 4.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier
                .height(150.dp)
                .align(CenterVertically)
                .fillMaxWidth(0.3f),
                shape = RoundedCornerShape(0.dp),
                /*colors = CardDefaults.cardColors(
                    containerColor = Color.Gray.copy(alpha = 0.5f),
                    contentColor = Color.Gray.copy(alpha = 0.5f)
                )*/
            ){
                Column(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                    )
                }

            }

            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.release_date),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                //released date -- YYYY-MM-DD to
                Text(text = convertDateToFormattedString(releaseDate))

                Spacer(modifier = Modifier.height(8.dp))
                //starts
                RatingBar(
                    numOfStars = 10,
                    size = 10.dp,
                    stepSize = StepSize.ONE,
                    value = rating,
                    style = RatingBarStyle.Fill(
                        //Yellow
                        activeColor = Green40,
                        //Gray with alpha
                        inActiveColor =  androidx.compose.ui.graphics.Color.Gray.copy(alpha = 0.5f),
                    ),
                    onValueChange = {},
                    onRatingChanged = {})

            }
        }

    }
}

@Preview
@Composable
fun HorizontalMovieItemPrev(){
    HorizontalMovieItem(
        title = "Fast & Furious X",
        imageUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        rating = 3.5f,
        onClick = {},
        releaseDate = "2021-05-19"
    )
}
package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.character.charatersapp.R
import com.character.charatersapp.characters_feature.presentation.ui.ACTOR_IMAGE_CONTENT_DESC

@Composable
fun DrawImageComposable(modifier: Modifier = Modifier, imageUrl : String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.baseline_image_24),
        error = painterResource(id = R.drawable.baseline_broken_image_24)
    )

    Image(
        painter = painter,
        contentDescription = ACTOR_IMAGE_CONTENT_DESC,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    )
}
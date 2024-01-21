package com.sapi.presentation.uicomponents

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.sapi.presentation.R

@Composable
internal fun DisplayCocktailImageFromUrl(
    imageUrl: String,
    modifier: Modifier,
    @DrawableRes placeholderImage: Int = R.drawable.ic_placeholder,
    @DrawableRes errorImage: Int = R.drawable.ic_placeholder,
    scale: Scale = Scale.FILL,
    contentScale : ContentScale = ContentScale.Fit,
    contentDescription : String = stringResource(R.string.cocktail_image),
    diskCachePolicy : CachePolicy = CachePolicy.ENABLED,
    ) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        val cocktailImage = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    error(errorImage)
                    placeholder(placeholderImage)
                    scale(scale)
                    diskCachePolicy(diskCachePolicy)
                }).build()
        )

        Image(
            painter = cocktailImage,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
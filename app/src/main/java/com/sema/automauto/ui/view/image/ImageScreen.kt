package com.sema.automauto.ui.view.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sema.automauto.R
import com.sema.component.LoadingBar

@Composable
fun ImageScreen(imagesViewModel: ImageViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.image_screen_title)) },
                backgroundColor = MaterialTheme.colors.surface,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        ImagesContent(vm = imagesViewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(images: List<String>) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        val pagerState = rememberPagerState()

        images.let {
            HorizontalPager(
                count = it.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) { page ->
                CarImage(item = images.get(page))
            }
        }
    }
}

@Composable
fun ImagesContent(vm: ImageViewModel) {
    vm.state.value.let { state ->
        when (state) {
            is Loading -> LoadingBar()
            is ImagesUiStateReady -> ImagePager(state.images)
            else -> {}
        }
    }
}

@Composable
fun CarImage(item: String) {
    item.let {
        Image(
            painter = rememberAsyncImagePainter(it),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
    }
}
package com.example.composeexample.presentation.detailhero

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.presentation.common_composables.CustomTopAppBar
import com.example.composeexample.ui.theme.ComposeExampleTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailHeroScreen(
    viewModel: HeroDetailsViewModel = koinViewModel(),
    heroId:String,
    goBack: (() -> Unit)?
) {
    viewModel.getData(heroId)

    Scaffold (
        topBar = {CustomTopAppBar("Hero Details",goBack, contentDescription = "Atrás. Botón ir al listado de personajes")}
    ){
        it
        val heroState = viewModel.heroLiveData.collectAsState()
        val locationInfoState = viewModel.location.collectAsState()
        val scrollState = rememberScrollState()

        Column (
            Modifier
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
                .background(Color(0xFFF1A737))
                .fillMaxHeight()
                ){

            heroState.value?.let {hero->

                BuildHeader(hero)

                Spacer(modifier = Modifier.height(8.dp))

                BuildContent(hero,locationInfoState.value)

            } ?: CircularProgressIndicator()
        }


    }

}

@Composable
fun BuildContent(hero: Hero, locationInfoState: String?) {
    Column(Modifier.padding(16.dp)) {

        locationInfoState?.let {
            Text(locationInfoState,fontSize = 32.sp,color=Color.Black, fontWeight = FontWeight.Bold)
        }
        Text(hero.name, fontSize = 32.sp,color=Color.White, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(hero.description, fontSize = 16.sp,color=Color.White)
    }
}

@Composable
fun BuildHeader(hero: Hero,) {
    AsyncImage(
        model = hero.photo,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .clip(shape = CustomShape())
            .border(2.dp, Color.Black, shape = CustomShape2())
    )

}

@Preview
@Composable
fun Preview(){
    BuildHeader(Hero.dummy())
}

class CustomShape : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            // Moves to top center position
            moveTo(0f , size.height)// bottom left
            lineTo(x = size.width, y = size.height/1.5f) // bottom right
            lineTo(x = size.width, y = 0f) // top right
            lineTo(x = 0f, y = 0f) // top right

        }
        return Outline.Generic(path = trianglePath)
    }
}

class CustomShape2 : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            // Moves to top center position
            moveTo(0f , size.height)// bottom left
            lineTo(x = size.width, y = size.height/1.5f) // bottom right
            lineTo(x = size.width, y = size.height/1.6f) // top right


        }
        return Outline.Generic(path = trianglePath)
    }
}
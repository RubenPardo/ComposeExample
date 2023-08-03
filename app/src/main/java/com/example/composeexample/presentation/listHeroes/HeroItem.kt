package com.example.composeexample.presentation.listHeroes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeexample.domain.model.Hero


@Composable
fun HeroItem(hero: Hero, onClickCallback: () -> Unit, ) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp)
                        .height(100.dp)
            .clearAndSetSemantics {
                contentDescription = "Item ${hero.name}. Pulsa para ir al detalle" }
            .border(1.5.dp, Color.Black)
            .clickable { onClickCallback.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1A737)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(

                text = hero.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
                //fontFamily = FontFamily.Default
            )

            AsyncImage(
                model = hero.photo,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .padding(0.dp)
                        .clip(shape = MyFirstShape())
                        .border(1.5.dp,Color.Black, shape = MyFirstShapeReversed())

                )


        }
    }
}

@Composable
@Preview
fun HeroItemPreview(){
    HeroItem(hero = Hero.dummy(), onClickCallback = {})
}

class MyFirstShape : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            // Moves to top center position
            moveTo(0f , 0f)// top left
            lineTo(x = size.width, y = 0f) // top right
            lineTo(x = size.width, y = size.height) // bottom right
            lineTo(x = size.width/2, y = size.height) // bottom center
            //lineTo(x = size.width/2.5f, y = 0f)

        }
        return Outline.Generic(path = trianglePath)
    }
}
class MyFirstShapeReversed : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            // Moves to top center position
            moveTo(0f , 0f)// top left
            lineTo(x = size.width/2, y = size.height) // bottom center
            lineTo(x = size.width, y = size.height) // bottom right
            lineTo(x = size.width, y = 0f) // top right

            //lineTo(x = size.width/2.5f, y = 0f)

        }
        return Outline.Generic(path = trianglePath)
    }
}

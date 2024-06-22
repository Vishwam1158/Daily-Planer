package com.viz.to_do_listapp.animation

import com.viz.to_do_listapp.R
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viz.to_do_listapp.NavPage
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.transform
import com.viz.to_do_listapp.ui.theme.PrimaryTint
import com.viz.to_do_listapp.ui.theme.SecondaryTint
import kotlin.math.PI
import kotlin.math.sin



// BottomBar container and list of Icons
@Composable
fun BottomAppBar(selectedRoute: String = Routes.Home.route, onChange: (String)->Unit = {}) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(90.dp)
            .paint(
                painter = painterResource(R.drawable.bottom_bar_container),
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center
            )
            .padding(horizontal = 40.dp)

    ) {
//        listOf( Icons.Filled.Home,Icons.Filled.Call).map { image ->
//            IconButton(onClick = { }) {
//                Icon(imageVector = image, contentDescription = null, tint = Color.White)
//            }
//        }
        for (page in Routes.pages) {
            NavBarItem(
                page = page,
                selected = selectedRoute == page.route,
                modifier = Modifier
                    .clickable {
                        onChange(page.route)
                    }
            )
        }
    }
}

@Composable
fun NavBarItem(modifier: Modifier = Modifier, page: NavPage, selected: Boolean = false ) {
    Column(
        modifier = modifier.padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(page.icon),
            contentDescription = page.name,
            colorFilter = ColorFilter.tint(
                if (selected) PrimaryTint else SecondaryTint
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(32.dp)
        )

        Text(page.name,
            fontSize = 12.sp,
            color = if (selected) PrimaryTint else SecondaryTint

        )
    }
}


// Contain only floating action button button at center
@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.tertiary,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = backgroundColor ,
        modifier = modifier.scale(1.25f),// .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(50.dp)),
        shape = RoundedCornerShape(50.dp),

    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}


// Floating Action button that open with three other button
@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { },
//    viewModel: TaskViewModel
//    navController: NavController
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 28.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        AnimatedFab(
            icon = Icons.Default.Category,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 64.dp* FastOutSlowInEasing.transform(0f, 1.0f, animationProgress), //72 and 0 to 0.8
                        end = 120.dp* FastOutSlowInEasing.transform(0f, 1.0f, animationProgress)  //210 and 0 to 0.8
                    )
                ),
            opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress),
            onClick = { }
        )

//        AnimatedFab(
//            icon = Icons.Default.Settings,
//            modifier = Modifier.padding(
//                PaddingValues(
//                    bottom = 88.dp* FastOutSlowInEasing.transform(0.2f, 0.9f, animationProgress),
//                )
//            ),
//            opacity = LinearEasing.transform(0.3f, 0.8f, animationProgress)
//        )

        AnimatedFab(
            icon = Icons.Default.Checklist,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 64.dp * FastOutSlowInEasing.transform(0.2f, 1.2f, animationProgress), // 0.2 to 1.0
                    start = 120.dp* FastOutSlowInEasing.transform(0.2f, 1.2f, animationProgress)   // 0.2 to 1.0
                )
            ),
            opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress),
          //  onClick = {  navController.navigate(Routes.Home.route)}
        )


        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing
                        .transform(0.35f, 0.65f, animationProgress)
                ),
            onClick = toggleAnimation,
            backgroundColor = Color.Transparent
        )

    }
}

// Animation effect on FloatingActionButton when clicked
@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(30.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}


@Composable
@Preview(showBackground = true)
private fun CustomBottomNavigationPreview() {
    BottomAppBar(onChange = {})
//    AnimatedFab(modifier = Modifier.scale(1.25f),)
//    FabGroup()
}




package com.viz.to_do_listapp.animation


import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

//Animation Effect
@Composable
fun AnimationScreen(
    navController: NavController,
//    viewModel: TaskViewModel
) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }
        AnimationScreen(
            navController = navController,
//            viewModel = viewModel,
            renderEffect = renderEffect,
            fabAnimationProgress = fabAnimationProgress,
            clickAnimationProgress = clickAnimationProgress,
            toggleAnimation = { isMenuExtended.value = isMenuExtended.value.not() }
        ) //{
            //isMenuExtended.value = isMenuExtended.value.not()  // Toggle the FloatActionButton  and Provide Option
        //}

}


@Composable
fun AnimationScreen(
    navController: NavController,
//    viewModel: TaskViewModel,
//    modifier: Modifier = modifier,
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

//        val selectedRoute = remember { mutableStateOf(Routes.Home.route) }
//        Column() {
//            when (selectedRoute.value) {
//                Routes.Home.route -> HomePage(viewModel, navController)
//                Routes.Calendar.route -> Calendar(navController)
//
//            }
//        }
//
//        BottomAppBar(
//            selectedRoute = selectedRoute.value,
//            onChange = { route ->
//                selectedRoute.value = route
//            }
//        )

        FabGroup(navController = navController, renderEffect = renderEffect, animationProgress = fabAnimationProgress)
        FabGroup(
            navController = navController,
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation
        )

        Circle(
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        FabGroup(navController = navController, renderEffect = renderEffect, animationProgress = fabAnimationProgress)
        FabGroup(
            navController = navController,
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation
        )

        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}




@Preview(showBackground = true)
@Composable
private fun AnimationScreenPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
//        horizontalArrangement = Arrangement.Center
    ) {
        BottomAppBar()
    }
    AnimationScreen(navController = NavController(LocalContext.current))
}


//ChatGpt//


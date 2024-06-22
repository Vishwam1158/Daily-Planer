package com.viz.to_do_listapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viz.to_do_listapp.NavPage
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.ui.theme.PrimaryTint
import com.viz.to_do_listapp.ui.theme.SecondaryTint

@Composable
fun BottomAppBar(selectedRoute: String = Routes.Home.route, onChange: (String)->Unit = {}) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 18.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
fun NavBarItem(modifier: Modifier = Modifier,page: NavPage, selected: Boolean = false ) {
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


@Preview
@Composable
private fun BottomAppBarPreview() {
    BottomAppBar(onChange = {})
}
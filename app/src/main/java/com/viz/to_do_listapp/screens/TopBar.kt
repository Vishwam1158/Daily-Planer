package com.viz.to_do_listapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.R

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 36.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "menu button",
            modifier = Modifier.size(32.dp, 32.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "menu button",
            modifier = Modifier.size(32.dp, 32.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    TopAppBar()
}
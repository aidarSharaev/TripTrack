package com.example.triptrack.presentation.navgraph

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class NavItemList(
  val icon: ImageVector,
  val title: String
)

@Composable
fun BottomBar(
  navItemList: List<NavItemList>,
  selectedItem: Int,
  onItemClick: (Int) -> Unit
) {

  NavigationBar(
    modifier = Modifier.height(60.dp),
    containerColor = MaterialTheme.colorScheme.primaryContainer
  ) {
    navItemList.forEachIndexed { index, item ->
      NavigationBarItem(
        selected = index == selectedItem,
        onClick = { onItemClick(index) },
        icon = {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
              imageVector = item.icon,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.secondary
            )
            Text(text = item.title)
          }
        }
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun BottomBarPreview() {
  val navItemList = listOf<NavItemList>(
    NavItemList(Icons.Default.Home, "Главная"),
    NavItemList(Icons.Default.Info, "Подробности"),
    NavItemList(Icons.Default.AccountCircle, "Профиль")
  )
  BottomBar(navItemList = navItemList, 2, {})
}


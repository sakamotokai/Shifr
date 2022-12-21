package com.example.shifr.navigation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

enum class TabPage {
    HOME, History
}

@Composable
fun HomeTabBar(
    navController: NavHostController = rememberNavController(),
    backgroundColor: Color,
    onTabSelected: (tabPage: TabPage) -> Unit,
    tabPage: TabPage,
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = backgroundColor,
        indicator = { tabPositions ->
            HomeTabIndicator(tabPositions, tabPage)
        }
    ) {
        HomeTab(
            icon = Icons.Default.Home,
            title = "HOME",
            onClick = {
                navController.navigate(Route.MainScreen.route)
                onTabSelected(TabPage.HOME)
            }
        )
        HomeTab(
            icon = Icons.Default.AccountBox,
            title = "History",
            onClick = {
                navController.navigate(Route.HistoryScreen.route)
                onTabSelected(TabPage.History)
            }
        )
    }
}

@Composable
fun HomeTabIndicator(
    tabPositions: List<TabPosition>,
    tabPage: TabPage
) {
    val transition = updateTransition(tabPage, label = "Tab indicator")
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (TabPage.HOME isTransitioningTo TabPage.History) {
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Indicator left"
    ) { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (TabPage.History isTransitioningTo TabPage.HOME) {
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        }, label = "Indicator right"
    ) { page ->
        tabPositions[page.ordinal].right
    }
    val color by transition.animateColor(label = "Border color") { page ->
        //if (page == TabPage.Home) Purple700 else Green
        if (page == TabPage.HOME) Color.Green else Color.Green
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(4.dp)
            )
    )
}

@Composable
fun HomeTab(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
    }
}
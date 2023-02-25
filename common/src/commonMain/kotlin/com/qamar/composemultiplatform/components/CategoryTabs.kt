package com.qamar.composemultiplatform.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.qamar.composemultiplatform.components.item.TabItem
import com.qamar.composemultiplatform.model.Plant

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun CategoryTabs() {
    val pagerState = rememberPagerState()
    val pages = Plant.getPlantList()
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .padding(top = 33.dp, start = 37.dp)
                .height(30.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = indicator,
            backgroundColor = Color.White,
            edgePadding = 0.dp,
            divider = {
                Divider(color = Color.Transparent)
            }
        ) {
            pages.forEachIndexed { index, plant ->
                TabItem(pagerState, index, plant)
            }
        }
        ItemPager(pagerState, pages)
    }
}

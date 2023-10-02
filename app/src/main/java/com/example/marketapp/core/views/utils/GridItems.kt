package com.example.marketapp.core.views.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun <T> LazyListScope.gridItems(
    data: List<T>,
    columnCount: Int,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalSpacing: Dp = 0.dp,
    horizontalSpacing: Dp = 0.dp,
    itemContent: @Composable BoxScope.(T) -> Unit,

) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = { it.hashCode() }) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier.then(Modifier.padding(top = verticalSpacing)) // Apply vertical spacing
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    val isLastItemInRow = columnIndex == columnCount - 1
                    Box(
                        modifier = Modifier
                            .weight(1F, fill = true)
                            .then(
                                if (isLastItemInRow)
                                    Modifier.padding(end = 0.dp) // No horizontal spacing for the last item in the row
                                else
                                    Modifier.padding(end = horizontalSpacing) // Apply horizontal spacing for other items
                            ),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}



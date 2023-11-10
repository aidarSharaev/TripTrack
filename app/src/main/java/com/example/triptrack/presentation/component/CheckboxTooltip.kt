package com.example.triptrack.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.triptrack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxTooltip(
    tooltipState: TooltipState,
    checkedUpdate: (Boolean) -> Unit,
    checked: Boolean,
    text: String,
) {
    Surface(shape = RoundedCornerShape(10.dp), shadowElevation = 5.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RectangleShape,
            ),
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checkedUpdate(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.check_box),
                ),
            )
            Text(text = text)
            TooltipBox(
                modifier = Modifier.padding(end = 8.dp),
                positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                tooltip = {
                    RichTooltip(
                        text = { Text(text = stringResource(R.string.profit_calc_and_for_new_version)) },
                        action = {},
                        title = { stringResource(R.string.tax_payment) },
                    )
                },
                state = tooltipState,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    tint = colorResource(id = R.color.tool_tip),
                )
            }
        }
    }
}

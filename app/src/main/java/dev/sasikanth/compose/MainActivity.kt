package dev.sasikanth.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.ListItem
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.TopAppBar
import androidx.ui.material.themeColor
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FlexColumn {
                    inflexible {
                        TopAppBar(title = {
                            Text("Jetpack Compose Sample")
                        })
                    }
                    flexible(flex = 1f) {
                        ListView()
                    }
                }
            }
        }
    }
}

@Composable
fun ListView() {
    var showDialog by +state { false }
    var clickedIndex by +state { -1 }

    if (showDialog) {
        ListItemClickPopup(clickedIndex = clickedIndex, dismiss = {
            showDialog = false
        })
    }

    VerticalScroller {
        Column {
            (0..100).forEach {
                ListItem(text = "Item $it", onClick = {
                    clickedIndex = it
                    showDialog = true
                })
                Divider(color = (+themeColor { onSurface }).copy(alpha = 0.08f))
            }
        }
    }
}

@Composable
fun ListItemClickPopup(clickedIndex: Int, dismiss: () -> Unit) {
    AlertDialog(onCloseRequest = dismiss, text = {
        Text(text = "You have clicked $clickedIndex")
    }, confirmButton = {
        Button(text = "OK", style = TextButtonStyle(), onClick = dismiss)
    })
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        ListView()
    }
}

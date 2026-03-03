package io.github.markyav.drawbox.android.drawing

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.markyav.drawbox.android.R
import io.github.markyav.drawbox.box.DrawBox
import io.github.markyav.drawbox.controller.DrawBoxSubscription
import io.github.markyav.drawbox.controller.DrawController

@Composable
internal fun ExpandedDrawingScreen(
    drawController: DrawController,
) {
    val bitmap by remember { drawController.getBitmap(500, DrawBoxSubscription.FinishDrawingUpdate) }.collectAsState()

    Column {
        Image(bitmap = bitmap, modifier = Modifier
            .size(250.dp)
            .border(1.dp, Color.Red), contentDescription = null)

        Column(modifier = Modifier.weight(4.5f, false)) {
            Log.i("TAG_aaa", "ExpandedDrawingScreen: $bitmap")
            DrawBox(
                controller = drawController,
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(8.dp)
                    .border(width = 1.dp, color = Color.Blue)
                    .weight(1f, fill = false),
            )
            Row {
                val enableUndo by remember { derivedStateOf { drawController.undoCount.value > 0 } }
                val enableRedo by remember { derivedStateOf { drawController.redoCount.value > 0 } }
                IconButton(onClick = drawController::undo, enabled = enableUndo) {
                    Icon(painterResource(R.drawable.arrow_back_24dp), contentDescription = "undo")
                }
                IconButton(onClick = drawController::redo, enabled = enableRedo) {
                    Icon(painterResource(R.drawable.arrow_forward_24dp), contentDescription = "redo")
                }
                IconButton(onClick = drawController::reset, enabled = enableUndo || enableRedo) {
                    Icon(painterResource(R.drawable.close_24dp), contentDescription = "reset")
                }
            }
        }
    }
}

/*@AndroidPreviewDevices
@Composable
fun ExpandedDrawingScreenPreview() {
    MobiSketchTheme {
        ExpandedDrawingScreen()
    }
}*/

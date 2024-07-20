package com.example.samplecompose.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.samplecompose.domain.model.IImage
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.domain.model.Command
import com.example.samplecompose.domain.model.ILabel
import com.example.samplecompose.domain.model.ILoginAgent

@Composable
fun ComponentView(item: Any, onCommand: (Command) -> Unit = {}) {
    return when (item) {
        is ILabel -> LabelView(item)
        is ILoginAgent -> AgentView(item, onCommand)
        is IImage -> AppImageView(item)
        else -> UnknownView(item)
    }
}

@Composable
fun UnknownView(item: Any) {
    val text = item.observe()
    return Text(text = text.toString())
}

@Composable
fun LabelView(item: ILabel) {
    val text = item.observe()
    return Text(text = text.toString())
}

@Composable
fun AppImageView(item: IImage) {
    val text = item.observe()
    return Text(text = text.toString())
}


@Composable
fun AgentView(it: ILoginAgent, onCommand: (Command) -> Unit = {}) {
    val agent = it.observe()
    Text(
        text = agent.name.toString(),
        Modifier.clickable(onClick = { onCommand(Command.Click(it)) })
    )
}

@Composable
fun ActionBarView(text: String, onCommand: (Command) -> Unit) {
    Text(
        text = text,
        color = Color.White,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.Blue)
            .clickable {
                onCommand(Command.ActionBarBack)
            }
    )
}
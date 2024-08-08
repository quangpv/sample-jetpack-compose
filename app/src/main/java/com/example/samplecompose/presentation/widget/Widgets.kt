package com.example.samplecompose.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.samplecompose.R
import com.example.samplecompose.domain.model.Command
import com.example.samplecompose.domain.model.HasIsValid
import com.example.samplecompose.domain.model.IImage
import com.example.samplecompose.domain.model.ILabel
import com.example.samplecompose.domain.model.ILoginAgent
import com.example.samplecompose.presentation.extensions.cast
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.presentation.extensions.observeInput
import com.example.samplecompose.presentation.theme.Colors

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
fun ActionBarTitleView(text: String = "", onCommand: (Command) -> Unit = {}) {
    Box(
        contentAlignment = Alignment.CenterStart, modifier = Modifier
            .height(56.dp)
            .background(Colors.Green)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = Colors.White,
            modifier = Modifier
                .clickable {
                    onCommand(Command.ActionBarBack)
                }
                .padding(16.dp)
        )
    }
}

@Composable
fun ActionBarBackAndTitleView(text: String = "", onCommand: (Command) -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .height(56.dp)
            .background(Colors.Green)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            colorFilter = ColorFilter.tint(Colors.White),
            contentDescription = "",
            modifier = Modifier
                .clickable { onCommand(Command.ActionBarBack) }
                .padding(16.dp)
        )
        Text(
            text = text,
            color = Colors.White,
            modifier = Modifier
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText(value: CharSequence, placeHolder: String = "") {
    val text = value.observeInput()

    TextField(
        value = text.value,
        onValueChange = text,
        placeholder = {
            Text(text = placeHolder)
        },
        isError = !(text.rawValue.cast<HasIsValid>()?.isValid ?: true),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun Space(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun LoadingView() {
    Text(text = "Loading...")
}

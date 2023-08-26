import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun App() {
    MaterialTheme {
        var text by remember { mutableStateOf("Hello, Philip!") }
        var showImage by remember { mutableStateOf(false) }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                showImage = !showImage
                text = if (showImage) {
                    "Hello, ${getPlatformName()}"
                } else {
                    "Hello, Philip!"
                }
            }) {
                Text(text)
            }
            //https://sebi.io/demo-image-api/pictures.json
            //https://github.com/Kotlin/kotlinx.serialization
            //https://ktor.io/docs/serialization-client.html#register_protobuf
            AnimatedVisibility(visible = showImage){
                KamelImage(
                    asyncPainterResource("https://sebi.io/demo-image-api/pigeon/" +
                            "vladislav-nikonov-yVYaUSwkTOs-unsplash.jpg"),
                    contentDescription = null
                )
            }
        }
    }
}

expect fun getPlatformName(): String
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdImage

data class BirdsUIState(
    val images: List<BirdImage> = emptyList()
)
class BirdsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<BirdsUIState>(BirdsUIState())
    val uiState: StateFlow<BirdsUIState> = _uiState.asStateFlow()

    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        upDateIMages()
    }

    override fun onCleared() {
        httpClient.close()
    }

    fun upDateIMages(){
        viewModelScope.launch {
            val images = getImages()
            _uiState.update {
                it.copy(images = images)
            }
        }
    }

    private suspend fun getImages(): List<BirdImage> {
        return httpClient
            .get("https://sebi.io/demo-image-api/pictures.json")
            .body()
    }
}
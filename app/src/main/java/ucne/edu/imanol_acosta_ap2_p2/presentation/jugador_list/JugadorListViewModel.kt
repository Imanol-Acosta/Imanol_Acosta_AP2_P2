package ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.imanol_acosta_ap2_p2.data.remote.Resource
import ucne.edu.imanol_acosta_ap2_p2.domain.usecases.GetJugadoresUseCase
import javax.inject.Inject

@HiltViewModel
class JugadorListViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JugadorListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getJugadores()
    }

    fun getJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                jugadores = result.data ?: emptyList(),
                                errorMessage = null
                            ) 
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            ) 
                        }
                    }
                }
            }
        }
    }
}

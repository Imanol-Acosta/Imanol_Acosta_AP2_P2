package ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.imanol_acosta_ap2_p2.data.remote.Resource
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.imanol_acosta_ap2_p2.domain.usecases.GetJugadorUseCase
import ucne.edu.imanol_acosta_ap2_p2.domain.usecases.SaveJugadorUseCase
import ucne.edu.imanol_acosta_ap2_p2.domain.usecases.UpdateJugadorUseCase
import ucne.edu.imanol_acosta_ap2_p2.presentation.navigation.Screen
import androidx.navigation.toRoute
import javax.inject.Inject

@HiltViewModel
class JugadorFormViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getJugadorUseCase: GetJugadorUseCase,
    private val saveJugadorUseCase: SaveJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JugadorFormUiState())
    val uiState = _uiState.asStateFlow()

    init {
        try {
            val args = savedStateHandle.toRoute<Screen.JugadorForm>()
            if (args.id > 0) {
                loadJugador(args.id)
            }
        } catch (e: Exception) {
            // Error al leer los argumentos (ej. si navegó sin ID o el tipo falló).
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let { jugador ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    jugadorId = jugador.jugadorId,
                                    nombres = jugador.nombres,
                                    email = jugador.email
                                )
                            }
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

    fun onEvent(event: JugadorFormUiEvent) {
        when (event) {
            is JugadorFormUiEvent.NombresChanged -> {
                _uiState.update { it.copy(nombres = event.nombres, nombresError = null) }
            }
            is JugadorFormUiEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email, emailError = null) }
            }
            JugadorFormUiEvent.Save -> {
                save()
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        val state = _uiState.value

        if (state.nombres.isBlank()) {
            _uiState.update { it.copy(nombresError = "El nombre no puede estar vacío") }
            isValid = false
        }

        if (state.email.isBlank()) {
            _uiState.update { it.copy(emailError = "El email es obligatorio") }
            isValid = false
        } else if (!state.email.contains("@")) {
            _uiState.update { it.copy(emailError = "El formato de email no es válido") }
            isValid = false
        }

        return isValid
    }

    private fun save() {
        if (!validate()) return

        val state = _uiState.value
        val request = JugadorRequest(nombres = state.nombres, email = state.email)

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            val result = if (state.jugadorId != null) {
                updateJugadorUseCase(state.jugadorId, request)
            } else {
                saveJugadorUseCase(request)
            }

            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                is Resource.Loading -> {
                    // Handled synchronously in this case based on RepositoryImpl logic
                }
            }
        }
    }
}

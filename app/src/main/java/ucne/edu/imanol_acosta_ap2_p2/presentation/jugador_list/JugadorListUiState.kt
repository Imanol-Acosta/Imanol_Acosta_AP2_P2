package ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_list

import ucne.edu.imanol_acosta_ap2_p2.domain.model.Jugador

data class JugadorListUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val errorMessage: String? = null
)

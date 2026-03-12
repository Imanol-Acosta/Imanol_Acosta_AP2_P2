package ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_form

sealed interface JugadorFormUiEvent {
    data class NombresChanged(val nombres: String) : JugadorFormUiEvent
    data class EmailChanged(val email: String) : JugadorFormUiEvent
    object Save : JugadorFormUiEvent
}

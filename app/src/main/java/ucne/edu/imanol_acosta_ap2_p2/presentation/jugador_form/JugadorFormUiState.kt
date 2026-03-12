package ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_form

data class JugadorFormUiState(
    val jugadorId: Int? = null,
    val nombres: String = "",
    val nombresError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

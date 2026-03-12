package ucne.edu.imanol_acosta_ap2_p2.data.remote.dto

import ucne.edu.imanol_acosta_ap2_p2.domain.model.Jugador

data class JugadorResponse(
    val jugadorId: Int? = null,
    val nombres: String? = null,
    val email: String? = null
)

fun JugadorResponse.toDomain(): Jugador {
    return Jugador(
        jugadorId = jugadorId,
        nombres = nombres ?: "",
        email = email ?: ""
    )
}

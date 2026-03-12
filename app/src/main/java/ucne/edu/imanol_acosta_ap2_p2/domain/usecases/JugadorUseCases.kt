package ucne.edu.imanol_acosta_ap2_p2.domain.usecases

import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p2.data.remote.Resource
import ucne.edu.imanol_acosta_ap2_p2.domain.model.Jugador
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.imanol_acosta_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Jugador>>> {
        return repository.getJugadores()
    }
}

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Jugador>> {
        return repository.getJugador(id)
    }
}

class SaveJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(jugador: JugadorRequest): Resource<Jugador> {
        return repository.saveJugador(jugador)
    }
}

class UpdateJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int, jugador: JugadorRequest): Resource<Jugador> {
        return repository.updateJugador(id, jugador)
    }
}

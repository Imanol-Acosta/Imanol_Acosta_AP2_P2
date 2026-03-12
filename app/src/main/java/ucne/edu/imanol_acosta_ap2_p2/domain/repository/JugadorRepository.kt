package ucne.edu.imanol_acosta_ap2_p2.domain.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p2.data.remote.Resource
import ucne.edu.imanol_acosta_ap2_p2.domain.model.Jugador
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest

interface JugadorRepository {
    suspend fun getJugadores(): Flow<Resource<List<Jugador>>>
    suspend fun getJugador(id: Int): Flow<Resource<Jugador>>
    suspend fun saveJugador(jugador: JugadorRequest): Resource<Jugador>
    suspend fun updateJugador(id: Int, jugador: JugadorRequest): Resource<Jugador>
}

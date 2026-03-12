package ucne.edu.imanol_acosta_ap2_p2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ucne.edu.imanol_acosta_ap2_p2.data.remote.Resource
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.toDomain
import ucne.edu.imanol_acosta_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import ucne.edu.imanol_acosta_ap2_p2.domain.model.Jugador
import ucne.edu.imanol_acosta_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override suspend fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugadores()
        response.onSuccess { jugadoresResponse ->
            emit(Resource.Success(jugadoresResponse.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override suspend fun getJugador(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugador(id)
        response.onSuccess { jugadorResponse ->
            emit(Resource.Success(jugadorResponse.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override suspend fun saveJugador(jugador: JugadorRequest): Resource<Jugador> {
        val response = remoteDataSource.saveJugador(jugador)
        return if (response.isSuccess) {
            val data = response.getOrNull()
            if (data != null) Resource.Success(data.toDomain())
            else Resource.Error("Respuesta exitosa pero datos nulos")
        } else {
            Resource.Error(response.exceptionOrNull()?.message ?: "Error desconocido al guardar")
        }
    }

    override suspend fun updateJugador(id: Int, jugador: JugadorRequest): Resource<Jugador> {
        val response = remoteDataSource.updateJugador(id, jugador)
        return if (response.isSuccess) {
            val data = response.getOrNull()
            if (data != null) Resource.Success(data.toDomain())
            else Resource.Error("Respuesta exitosa pero datos nulos")
        } else {
            Resource.Error(response.exceptionOrNull()?.message ?: "Error desconocido al actualizar")
        }
    }
}

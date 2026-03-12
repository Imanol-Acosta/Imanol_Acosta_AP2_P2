package ucne.edu.imanol_acosta_ap2_p2.data.remote.remotedatasource

import retrofit2.HttpException
import ucne.edu.imanol_acosta_ap2_p2.data.remote.JugadoresApi
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorResponse
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadoresApi
) {
    suspend fun getJugadores(): Result<List<JugadorResponse>> {
        return try {
            val response = api.getJugadores()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error en la red: ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.message}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.message}", e))
        }
    }

    suspend fun getJugador(id: Int): Result<JugadorResponse> {
        return try {
            val response = api.getJugador(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en la red: ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.message}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.message}", e))
        }
    }

    suspend fun saveJugador(request: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.saveJugador(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en la red: ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.message}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.message}", e))
        }
    }

    suspend fun updateJugador(id: Int, request: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.updateJugador(id, request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en la red: ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.message}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.message}", e))
        }
    }
}

package ucne.edu.imanol_acosta_ap2_p2.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.JugadorResponse

interface JugadoresApi {
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<JugadorResponse>>

    @GET("api/Jugadores/{id}")
    suspend fun getJugador(@Path("id") id: Int): Response<JugadorResponse>

    @POST("api/Jugadores")
    suspend fun saveJugador(@Body request: JugadorRequest): Response<JugadorResponse>

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(@Path("id") id: Int, @Body request: JugadorRequest): Response<JugadorResponse>
}

package ucne.edu.imanol_acosta_ap2_p2.data.remote.remotedatasource

import ucne.edu.imanol_acosta_ap2_p2.data.remote.dto.CharacterResponseDto
import javax.inject.Inject


class CharacterRemoteDataSource @Inject constructor(
) {

    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Result<CharacterResponseDto> {
        return Result.failure(Exception("Not implemented"))
    }

    suspend fun getCharacterDetail(id: Int): Result<Any> {
        return Result.failure(Exception("Not implemented"))
    }
}

package com.poke.data.datasource

import com.poke.data.response.PokemonDetailInfoResponse
import com.poke.network.NetworkResult
import com.poke.network.api.PokeApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonDetailInfoDataSource @Inject constructor(
    private val pokeApi: PokeApi
) {
    suspend fun getPokemonDetailInfo(id: Int): NetworkResult<PokemonDetailInfoResponse> {
        return try {
            NetworkResult.Success(pokeApi.getPokemonDetailInfoAsync(id))
        } catch (e: IOException) {
            NetworkResult.Failure(e)
        } catch (e: HttpException) {
            NetworkResult.Failure(e)
        } catch (e: Exception) {
            NetworkResult.Failure(e)
        }
    }
}
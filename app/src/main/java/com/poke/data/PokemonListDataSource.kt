package com.poke.data

import com.poke.data.response.PokemonListResponse
import com.poke.data.response.PokemonLocationListResponse
import com.poke.network.NetworkResult
import com.poke.network.api.DemoApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
    private val demoApi: DemoApi
) {
    suspend fun getPokemonList() : NetworkResult<PokemonListResponse>{
        return try{
            NetworkResult.Success(demoApi.getPokemonListAsync())
        }catch (e: IOException) {
            NetworkResult.Failure(e)
        } catch (e: HttpException) {
            NetworkResult.Failure(e)
        } catch (e: Exception) {
            NetworkResult.Failure(e)
        }
    }

    suspend fun getLocationList() : NetworkResult<PokemonLocationListResponse>{
        return try{
            NetworkResult.Success(demoApi.getPokemonLocationAsync()
            )
        }catch (e: IOException) {
            NetworkResult.Failure(e)
        } catch (e: HttpException) {
            NetworkResult.Failure(e)
        } catch (e: Exception) {
            NetworkResult.Failure(e)
        }
    }
}
package com.poke.data.datasource

import com.poke.data.repository.PokemonListRepository
import com.poke.data.response.PokemonListResponse
import com.poke.data.response.PokemonLocationListResponse
import com.poke.network.NetworkResult
import com.poke.network.api.DemoApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class PokemonListDataSource @Inject constructor(
    private val demoApi: DemoApi
) : PokemonListRepository.PokemonListRepositoryComponent {
    override suspend fun getPokemonList() : NetworkResult<PokemonListResponse>{
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

    override suspend fun getLocationList() : NetworkResult<PokemonLocationListResponse>{
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
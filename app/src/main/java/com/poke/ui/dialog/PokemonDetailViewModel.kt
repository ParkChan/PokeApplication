package com.poke.ui.dialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.common.viewmodel.SingleLiveData
import com.poke.data.repository.PokemonDetailInfoRepository
import com.poke.data.response.PokemonDetailInfoResponse
import com.poke.data.response.mapToModel
import com.poke.network.NetworkResult
import com.poke.ui.main.model.PokemonModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PokemonDetailViewModel @ViewModelInject constructor(
    private val pokemonDetailInfoRepository: PokemonDetailInfoRepository
) : ViewModel() {

    private val _pokemonModel = MutableLiveData<PokemonModel>()
    val pokemonModel: LiveData<PokemonModel> get() = _pokemonModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errMsg = SingleLiveData<String>()
    val errMsg: LiveData<String> get() = _errMsg

    fun getPokemonDetailInfo(pokemonModel: PokemonModel) = viewModelScope.launch {
        showLoading()
        var pokemonDetailInfoResponse: PokemonDetailInfoResponse? = null
        val pokemonDetailInfoDeferred =
            async { pokemonDetailInfoRepository.getPokemonDetailInfo(pokemonModel.id) }

        when (val networkResult = pokemonDetailInfoDeferred.await()) {
            is NetworkResult.Success -> {
                pokemonDetailInfoResponse = networkResult.data
            }
            is NetworkResult.Failure -> {
                _errMsg.value = networkResult.exception.message
            }
        }

        pokemonDetailInfoResponse?.let {
            pokemonModel.detailInfo = pokemonDetailInfoResponse.mapToModel()
        }

        _pokemonModel.value = pokemonModel
        hideLoading()
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }
}
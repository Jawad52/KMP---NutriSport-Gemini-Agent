package com.jucode.nutrisport.dashboard.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jucode.nutrisport.dashboard.domain.Banner
import com.jucode.nutrisport.dashboard.domain.GetBannersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class DashboardUiState(
    val banners: List<Banner> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class DashboardViewModel(
    private val getBannersUseCase: GetBannersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadBanners()
    }

    private fun loadBanners() {
        viewModelScope.launch {
            getBannersUseCase()
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = exception.message)
                }
                .collect { banners ->
                    _uiState.value = _uiState.value.copy(isLoading = false, banners = banners)
                }
        }
    }
}

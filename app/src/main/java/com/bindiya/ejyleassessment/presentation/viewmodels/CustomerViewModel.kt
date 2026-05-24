package com.bindiya.ejyleassessment.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bindiya.ejyleassessment.core.CustomerDetails
import com.bindiya.ejyleassessment.core.Resource
import com.bindiya.ejyleassessment.domain.CreateCustomerUseCase
import com.bindiya.ejyleassessment.domain.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FormUiState(
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val city: String = "",
    val isFormValid: Boolean = false,
    val submissionStatus: Resource<CustomerDetails> = Resource.Idle
)

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val createCustomer: CreateCustomerUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name, nameError = FormValidator.executeName(name)) }
        validateForm()
    }

    fun onEmailChanged(email: String) {

        _uiState.update { it.copy(email = email, emailError = FormValidator.executeEmail(email)) }
        validateForm()
    }

    fun onPhoneChanged(phone: String) {
        _uiState.update { it.copy(phone = phone, phoneError = FormValidator.executePhone(phone)) }
        validateForm()
    }

    fun onCityChanged(city: String) {
        _uiState.update { it.copy(city = city) }
        validateForm()
    }

    private fun validateForm() {
        val state = _uiState.value
        val isValid = FormValidator.executeName(state.name).isNullOrEmpty() &&
                FormValidator.executeEmail(state.email).isNullOrEmpty() &&
                FormValidator.executePhone(state.phone).isNullOrEmpty() &&
                FormValidator.executeCity(state.city).isNullOrEmpty()

        _uiState.update { it.copy(isFormValid = isValid) }
    }

    fun submitForm() {
        if (!_uiState.value.isFormValid) return

        viewModelScope.launch {
            _uiState.update { it.copy(submissionStatus = Resource.Loading) }

            val details = CustomerDetails(
                name = _uiState.value.name,
                email = _uiState.value.email,
                phone = _uiState.value.phone,
                city = _uiState.value.city
            )

            val result = createCustomer(details)
            _uiState.update { it.copy(submissionStatus = result) }
        }
    }

    fun resetSubmission() {
        _uiState.update { FormUiState() }
    }
}
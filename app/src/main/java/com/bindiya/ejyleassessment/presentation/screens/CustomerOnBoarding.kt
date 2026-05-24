package com.bindiya.ejyleassessment.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bindiya.ejyleassessment.R
import com.bindiya.ejyleassessment.core.CustomerDetails
import com.bindiya.ejyleassessment.core.Resource
import com.bindiya.ejyleassessment.presentation.viewmodels.CustomerViewModel
import com.bindiya.ejyleassessment.presentation.viewmodels.FormUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerOnBoarding(viewModel: CustomerViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.submissionStatus) {
        val status = state.submissionStatus
        if (status is Resource.Error) {
            snackbarHostState.showSnackbar(
                message = status.message,
                actionLabel = "Dismiss"
            )
        }
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.label_customer_registration)) }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (state.submissionStatus) {
                is Resource.Success -> {
                    CustomerDetailScreen(
                        customerDetails = (state.submissionStatus as Resource.Success<CustomerDetails>).data,
                        onBack = { viewModel.resetSubmission() }
                    )
                }

                else -> {
                    FormScreen(
                        state = state,
                        onNameChange = viewModel::onNameChanged,
                        onEmailChange = viewModel::onEmailChanged,
                        onPhoneChange = viewModel::onPhoneChanged,
                        onCityChange = viewModel::onCityChanged,
                        onSubmit = viewModel::submitForm
                    )

                    if (state.submissionStatus is Resource.Loading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    state: FormUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val cities = LocalContext.current.resources.getStringArray(R.array.cities)
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {
        OutlinedTextField(
            value = state.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(R.string.label_full_name)) },
            isError = state.nameError != null,
            supportingText = {
                state.nameError?.let {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.label_email_address)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = state.emailError != null,
            supportingText = {
                state.emailError?.let {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.phone,
            onValueChange = onPhoneChange,
            label = { Text(stringResource(R.string.label_phone_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = state.phoneError != null,
            supportingText = {
                state.phoneError?.let {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown (Spinner Equivalent)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = state.city.ifBlank { stringResource(R.string.label_select_city) },
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.label_city)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city) },
                        onClick = {
                            onCityChange(city)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSubmit,
            enabled = state.isFormValid && state.submissionStatus !is Resource.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

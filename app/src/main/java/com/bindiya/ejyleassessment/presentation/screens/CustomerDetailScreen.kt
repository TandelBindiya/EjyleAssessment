package com.bindiya.ejyleassessment.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bindiya.ejyleassessment.R
import com.bindiya.ejyleassessment.core.CustomerDetails

@Composable
fun CustomerDetailScreen(
    customerDetails: CustomerDetails,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.label_submission_successful),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.label_summary_details), style = MaterialTheme.typography.titleMedium)
                HorizontalDivider()
                Text(stringResource(R.string.label_name, customerDetails.name))
                Text(stringResource(R.string.label_email, customerDetails.email))
                Text(stringResource(R.string.label_phone, customerDetails.phone))
                Text(stringResource(R.string.label_city, customerDetails.city))
            }
        }

        Button(onClick = onBack) {
            Text(stringResource(R.string.label_register_another_customer))
        }
    }
}
package com.bindiya.ejyleassessment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bindiya.ejyleassessment.presentation.route.NavGraph
import com.bindiya.ejyleassessment.presentation.ui.theme.EjyleAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjyleAssessmentTheme {
                    NavGraph()
            }
        }
    }
}
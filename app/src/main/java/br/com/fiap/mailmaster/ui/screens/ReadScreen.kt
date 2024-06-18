package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import br.com.fiap.mailmaster.models.views.ReadViewModel
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.EmailService

@Composable
fun ReadScreen(
    navController: NavHostController,
    viewModel: ViewModel,
    readViewModel: ReadViewModel
) {
    val context = LocalContext.current
    val email by readViewModel.emailRead.observeAsState()
    val userLoged by viewModel.userLoged.observeAsState()

    val emailService = EmailService(context)

    userLoged?.let { email?.let { it1 -> emailService.updateStatus(it1.id, it.id) } }

    Column {
        email.toString()
    }
}
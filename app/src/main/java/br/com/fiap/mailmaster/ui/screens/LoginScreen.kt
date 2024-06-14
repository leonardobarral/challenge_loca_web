package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService

@Composable
fun LoginScreen(navController: NavController,viewModel: ViewModel) {

    //val userLoged by viewModel.userLoged.observeAsState(initial = UserExibitionDto(0,"",""))
    val userLoginDto by viewModel.userLoginDto.observeAsState(initial = UserLoginDto("", ""))

    val userEmail by viewModel.userEmail.observeAsState(initial = "")
    val userSenha by viewModel.userSenha.observeAsState(initial = "")

    val context = LocalContext.current
    val userService = UserService(context)
//    val userRepository = UserRepository(context)

    Column {
        Row {
            Text(text = "email")
            OutlinedTextField(value = "", onValueChange = {
                viewModel.updateUserEmail(it)
            })
        }
        Row {
            Text(text = "senha")
            OutlinedTextField(value = "", onValueChange = {
                viewModel.updateUserSenha(it)
            })
        }

        Row {
            Button(
                onClick = {
                    navController.navigate("third")
                }) {
                Text(text = "Cadastro")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    viewModel.updateUserLoginDto(userEmail,userSenha)
                    viewModel.updateLogedUser(userService.login(userLoginDto))
                    navController.navigate("second")
                }) {
                Text(text = "Login")
            }
        }
    }
}
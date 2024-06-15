package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.models.User
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService

@Composable
fun CreateUserScreen(navController: NavController, viewModel: ViewModel) {

    //val userLoged by viewModel.userLoged.observeAsState(initial = UserExibitionDto(0,"",""))
    val userCadastroDto by viewModel.userCadastroDto.observeAsState(
        initial = UserCadastroDto(
            "",
            "",
            "",
            ""
        )
    )

    val userEmail by viewModel.userEmail.observeAsState(initial = "")
    val userSenha by viewModel.userSenha.observeAsState(initial = "")
    val userSenha1 by viewModel.userSenha1.observeAsState(initial = "")
    val userNome by viewModel.userNome.observeAsState(initial = "")

    val context = LocalContext.current
    val userService = UserService(context)
//    val userRepository = UserRepository(context)

    Column {
        Row {
            Text(text = "email")
            OutlinedTextField(value = userEmail, onValueChange = {
                viewModel.updateUserEmail(it)
            })
        }
        Row {
            Text(text = "nome")
            OutlinedTextField(value = userNome, onValueChange = {
                viewModel.updateUserNome(it)
            })
        }
        Row {
            Text(text = "senha")
            OutlinedTextField(value = userSenha, onValueChange = {
                viewModel.updateUserSenha(it)
            })
        }

        Row {
            Text(text = "confirmar senha")
            OutlinedTextField(value = userSenha1, onValueChange = {
                viewModel.updateUserSenha1(it)
            })
        }

        Row {
            Button(
                onClick = {

                    userService.insert(
                        UserCadastroDto(
                            email = userEmail,
                            senha = userSenha,
                            senha1 = userSenha1,
                            nome = userNome
                        )
                    )
                    navController.navigate("first")
                }) {
                Text(text = "Criar")
            }
        }
    }
}
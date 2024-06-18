package br.com.fiap.mailmaster.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService


@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModel) {

    val userLoged by viewModel.userLoged.observeAsState(initial = UserExibitionDto(0, "", ""))
    val userLoginDto by viewModel.userLoginDto.observeAsState(initial = UserLoginDto("", ""))

    val userEmail by viewModel.userEmail.observeAsState(initial = "")
    val userSenha by viewModel.userSenha.observeAsState(initial = "")

    val context = LocalContext.current
    val userService = UserService(context)

    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row {
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = {
                        viewModel.updateUserEmail(it)
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row {
                OutlinedTextField(
                    value = userSenha,
                    onValueChange = {
                        viewModel.updateUserSenha(it)
                    },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = {
                        navController.navigate("third")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cadastro")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        userService.login(
                            UserLoginDto(
                                email = userEmail,
                                senha = userSenha
                            )
                        )?.let {
                            viewModel.updateLogedUser(
                                it
                            )
                            userLoged.let { Log.wtf("LEO - ID", it.nome) }
                        }
                        navController.navigate("second")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}
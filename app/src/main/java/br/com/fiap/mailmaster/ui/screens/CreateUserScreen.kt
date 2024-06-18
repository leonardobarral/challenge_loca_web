package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService


@Composable
fun CreateUserScreen(navController: NavController, viewModel: ViewModel) {


    val userEmail by viewModel.userEmail.observeAsState(initial = "")
    val userSenha by viewModel.userSenha.observeAsState(initial = "")
    val userSenha1 by viewModel.userSenha1.observeAsState(initial = "")
    val userNome by viewModel.userNome.observeAsState(initial = "")

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastro",
                style = TextStyle(
                    fontSize = 32.sp
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = userEmail,
                onValueChange = {
                    viewModel.updateUserEmail(it)
                }, label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = userNome, onValueChange = {
                    viewModel.updateUserNome(it)
                },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = userSenha, onValueChange = {
                    viewModel.updateUserSenha(it)
                },
                label = { Text(text = "Senha") },
                modifier = Modifier.fillMaxWidth()
            )



            OutlinedTextField(
                value = userSenha1, onValueChange = {
                    viewModel.updateUserSenha1(it)
                },
                label = { Text(text = "Confirmar Senha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row {
                Button(
                    onClick = {
                        navController.navigate("first")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Voltar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(10.dp))
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

                        viewModel.updateUserEmail("")
                        viewModel.updateUserSenha("")
                        viewModel.updateUserSenha1("")
                        viewModel.updateUserNome("")

                        navController.navigate("first")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Criar", color = Color.White)
                }
            }

        }
    }
}
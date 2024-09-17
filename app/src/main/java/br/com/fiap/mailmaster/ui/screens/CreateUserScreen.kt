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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CreateUserScreen(navController: NavController,viewModel: ViewModel) {


    val userEmail = remember { mutableStateOf("") }
    val userSenha = remember { mutableStateOf("") }
    val userSenha1 = remember { mutableStateOf("") }
    val userNome = remember { mutableStateOf("") }

    val context = LocalContext.current
    val userService = UserService(context)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF),
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
                value = userEmail.value,
                onValueChange = {
                    userEmail.value = it
                }, label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = userNome.value,
                onValueChange = {
                    userNome.value = it
                },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = userSenha.value,
                onValueChange = {
                    userSenha.value = it
                },
                label = { Text(text = "Senha") },
                modifier = Modifier.fillMaxWidth()
            )



            OutlinedTextField(
                value = userSenha1.value,
                onValueChange = {
                    userSenha1.value =it
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
//                        userService.insert(
//                            UserCadastroDto(
//                                email = userEmail,
//                                password1 = userSenha,
//                                password2 = userSenha1,
//                                name = userNome
//                            )
//                        )

                        userEmail.value = ""
                        userSenha.value = ""
                        userSenha1.value = ""
                        userNome.value = ""

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
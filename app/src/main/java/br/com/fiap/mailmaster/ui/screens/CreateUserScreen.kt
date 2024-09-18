package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.models.checks.InputChecking
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService


@Composable
fun CreateUserScreen(navController: NavController, viewModel: ViewModel) {

    val validator = InputChecking()
    val userEmail = remember { mutableStateOf("") }
    val userSenha = remember { mutableStateOf("") }
    val userSenha1 = remember { mutableStateOf("") }
    val userNome = remember { mutableStateOf("") }
    val alertpassword = remember { mutableStateOf(false) }
    val alertpasswordDistict = remember { mutableStateOf(false) }
    val alertEmail = remember { mutableStateOf(false) }


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

            if(alertpassword.value ||  alertEmail.value || alertpasswordDistict.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(8.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                    ){


                        if (alertEmail.value) {
                            Text(
                                text = "E-mail inválido!",
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                color = Color.Red

                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }

                        if (alertpassword.value) {
                            Text(
                                text = "Senha inválida!",
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        if (alertpasswordDistict.value) {
                            Text(
                                text = "As senhas informadas não são iguais!",
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
            Text(
                text = "Cadastro",
                style = TextStyle(
                    fontSize = 32.sp
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = userEmail.value,
                onValueChange = {
                    userEmail.value = it
                    alertEmail.value = false
                }, label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,

                    ),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = userNome.value,
                onValueChange = {
                    userNome.value = it
                },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,

                    ),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = userSenha.value,
                onValueChange = {
                    userSenha.value = it
                    alertpassword.value = false
                    alertpasswordDistict.value = false
                },
                label = { Text(text = "Senha") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,

                    ),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))


            TextField(
                value = userSenha1.value,
                onValueChange = {
                    userSenha1.value = it
                    alertpassword.value = false
                    alertpasswordDistict.value = false
                },
                label = { Text(text = "Confirmar Senha") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,

                    ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row {
                Button(
                    onClick = {
                        navController.navigate("first")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF757575)),
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

                        if(validator.isValidPassWord(userSenha.value)) alertpassword.value = false else alertpassword.value = true
                        if(validator.isValidEmail(userEmail.value)) alertEmail.value = false else alertEmail.value = true
                        if( userSenha.value == userSenha1.value) alertpasswordDistict.value = false else alertpasswordDistict.value = true


                        if(!alertpassword.value &&  !alertEmail.value && !alertpasswordDistict.value){

                            userService.insert(
                                UserCadastroDto(
                                    email = userEmail.value,
                                    password1 = userSenha.value,
                                    password2 = userSenha1.value,
                                    name = userNome.value
                                )
                            )
                            navController.navigate("first")
                        }else{
//                            userSenha.value = ""
//                            userSenha1.value = ""
                        }

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006400)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Criar", color = Color.White)
                }
            }

        }
    }
}
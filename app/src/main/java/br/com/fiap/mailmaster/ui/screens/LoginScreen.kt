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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService


@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModel) {


    val userEmail = remember { mutableStateOf("") }
    val userSenha = remember { mutableStateOf("") }

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
                text = "Login",
                style = TextStyle(
                    fontSize = 32.sp
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row {
                TextField(
                    value = userEmail.value,
                    onValueChange = {
                        userEmail.value = it
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("example@domain.com") },
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
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                TextField(
                    value = userSenha.value,
                    onValueChange = {
                        userSenha.value = it
                    },

                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("123abc$%") },
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
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate("third")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF757575),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cadastro")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
//                        userService.login(
//                            UserLoginDto(
//                                email = userEmail,
//                                password = userSenha
//                            )
//                        )?.let {
//                            viewModel.updateUserEmail("")
//                            viewModel.updateUserSenha("")
                        viewModel.updateLogedUser()
//                        }
                        navController.navigate("second")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF006400),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}
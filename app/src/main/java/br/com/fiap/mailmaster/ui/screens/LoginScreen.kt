package br.com.fiap.mailmaster.ui.screens


import android.util.Log
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
import br.com.fiap.mailmaster.Retrofit.RetrofitFactory
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.User
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModel) {


    val userEmail = remember { mutableStateOf("") }
    val userSenha = remember { mutableStateOf("") }

    val sucefful = remember { mutableStateOf(false) }
    val alerta = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userService = UserService(context)

    fun loginOnline() {
        val call = RetrofitFactory()
            .getRetrofiteService()
            .getUserLoginToken(
                UserLoginDto(
                    email = userEmail.value,
                    password = userSenha.value
                )
            )
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let { viewModel.updateLogedUser(it) }
                    sucefful.value = true
                }else{
                    sucefful.value = false
                    alerta.value = true
                    Log.i("FIAP","onResponse${response.body()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                alerta.value = true
                Log.i("FIAP","onResponse${t.message}")
            }
        })
    }



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
            if (alerta.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {



                            Text(
                                text = "E-mail ou senha inválidos!",
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                color = Color.Red

                            )
                        Spacer(modifier = Modifier.height(5.dp))


                    }
                }
            }
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
//                        loginOnline()
//
//                        if(sucefful.value){
//                            viewModel.updateStatusConection("ONLINE")
//                            navController.navigate("second")
//                        }

                        viewModel.updateLogedUser()
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
package br.com.fiap.mailmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.models.views.WriteViewModel
import br.com.fiap.mailmaster.ui.screens.BoxScreen
import br.com.fiap.mailmaster.ui.screens.CreateUserScreen
import br.com.fiap.mailmaster.ui.screens.LoginScreen
import br.com.fiap.mailmaster.ui.screens.WriteScreen
import br.com.fiap.mailmaster.ui.theme.MailMasterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MailMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val viewModel: ViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "first") {

                        composable("first") {
                            LoginScreen(navController, ViewModel())
                        }

                        composable("second") {
                            val userLoged by viewModel.userLoged.observeAsState()
                            userLoged?.let { BoxScreen(navController, ViewModel()) }
                        }

                        composable("third") {
                            CreateUserScreen(navController, ViewModel())
                        }

                        composable("fourth") {
                            val userLoged by viewModel.userLoged.observeAsState()
                            userLoged?.let {
                                WriteScreen(
                                    navController,
                                    ViewModel(),
                                    WriteViewModel()
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}




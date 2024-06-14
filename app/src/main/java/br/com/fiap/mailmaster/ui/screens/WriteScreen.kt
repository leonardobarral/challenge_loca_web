package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.EmailCriacaoDto
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.models.views.WriteViewModel
import br.com.fiap.mailmaster.services.EmailService
import br.com.fiap.mailmaster.services.UserService
import java.time.LocalDate

@Composable
fun WriteScreen(navController: NavController, viewModel: ViewModel, writeViewModel: WriteViewModel) {

    val userLoged by viewModel.userLoged.observeAsState()

    val paraEmail by writeViewModel.paraEmail.observeAsState(initial = "")
    val paraEmailList by writeViewModel.paraEmailList.observeAsState(emptyList())

    val ccEmail by writeViewModel.ccEmail.observeAsState(initial = "")
    val ccEmailList by writeViewModel.ccEmailList.observeAsState(emptyList())

    val ccoEmail by writeViewModel.ccoEmail.observeAsState(initial = "")
    val ccoEmailList by writeViewModel.ccoEmailList.observeAsState(emptyList())

    val assunto by writeViewModel.assunto.observeAsState(initial = "")

    val body by writeViewModel.body.observeAsState(initial = "")

    val userSenha by viewModel.userSenha.observeAsState(initial = "")
    val userSenha1 by viewModel.userSenha1.observeAsState(initial = "")
    val userNome by viewModel.userNome.observeAsState(initial = "")

    val context = LocalContext.current
    val userService = UserService(context)
//    val userRepository = UserRepository(context)

    Column {
        Row {
            Text(text = "Para")
            OutlinedTextField(
                value = paraEmail,
                onValueChange = {
                    writeViewModel.updateParaEmail(it)
                },
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Enter || (event.type == KeyEventType.KeyUp && event.key == Key.Comma)) {
                        writeViewModel.appendParaEmailList(paraEmail)
                        true
                    } else {
                        false
                    }
                }
            )
        }
        Row {
            Text(text = paraEmailList.toString())
        }

        Row {
            Text(text = "CC")
            OutlinedTextField(
                value = ccEmail,
                onValueChange = {
                    writeViewModel.updateCcEmail(it)
                },
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Enter || (event.type == KeyEventType.KeyUp && event.key == Key.Comma)) {
                        writeViewModel.appendCcEmailList(ccEmail)
                        true
                    } else {
                        false
                    }
                }
            )
        }
        Row {
            Text(text = ccEmailList.toString())
        }
        Row {
            Text(text = "CCO")
            OutlinedTextField(
                value = ccoEmail,
                onValueChange = {
                    writeViewModel.updateCcoEmail(it)
                },
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Enter || (event.type == KeyEventType.KeyUp && event.key == Key.Comma)) {
                        writeViewModel.appendCcoEmailList(ccoEmail)
                        true
                    } else {
                        false
                    }
                }
            )
        }
        Row {
            Text(text = ccoEmailList.toString())
        }

        Row {
            Text(text = "Assunto")
            OutlinedTextField(value = assunto, onValueChange = {
                writeViewModel.updateAssunto(it)
            })
        }

        Row {
            Text(text = "Body")
            OutlinedTextField(value = body, onValueChange = {
                writeViewModel.updateBody(it)
            })
        }

        Row {
            Button(
                onClick = {
                    EmailService.insert(
                        userLoged?.let {
                            EmailCriacaoDto(
                                assunto = assunto,
                                idRemetente =  it.id,
                                body = body,
                                dataEnvio = LocalDate.now(),
                                destinatarios = paraEmailList,
                                ccs = ccEmailList,
                                ccos = ccoEmailList
                            )
                        }
                    )
                    navController.navigate("second")
                }) {
                Text(text = "Enviar")
            }
        }
    }
}



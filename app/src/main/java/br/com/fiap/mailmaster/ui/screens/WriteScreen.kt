package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import br.com.fiap.mailmaster.dtos.EmailEReceiverCriacaoDto
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.models.views.WriteViewModel
import br.com.fiap.mailmaster.services.EmailService
import java.time.LocalDate

@Composable
fun WriteScreen(
    navController: NavController,
    viewModel: ViewModel,
    writeViewModel: WriteViewModel
) {

    val userLoged by viewModel.userLoged.observeAsState()

    val paraEmail by writeViewModel.paraEmail.observeAsState(initial = "")
    val paraEmailList by writeViewModel.paraEmailList.observeAsState()

    val ccEmail by writeViewModel.ccEmail.observeAsState(initial = "")
    val ccEmailList by writeViewModel.ccEmailList.observeAsState()

    val ccoEmail by writeViewModel.ccoEmail.observeAsState(initial = "")
    val ccoEmailList by writeViewModel.ccoEmailList.observeAsState()

    val assunto by writeViewModel.assunto.observeAsState(initial = "")

    val body by writeViewModel.body.observeAsState(initial = "")

    val context = LocalContext.current
    val emailService = EmailService(context)

    Column {
        Row {
            Text(text = "Para")
            OutlinedTextField(
                value = paraEmail,
                onValueChange = {
                    writeViewModel.updateParaEmail(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        writeViewModel.appendParaEmailList(paraEmail)
                    }
                )
            )
        }
        Row {
            paraEmailList?.let { Text(text = it.joinToString(", ")) }
        }


        Row {
            Text(text = "CC")
            OutlinedTextField(
                value = ccEmail,
                onValueChange = {
                    writeViewModel.updateCcEmail(it)
                },
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Enter || event.key == Key.Comma) {
                        if (event.type == KeyEventType.KeyUp) {
                            writeViewModel.appendCcEmailList(ccEmail)
                        }
                        true
                    } else {
                        false
                    }
                }
            )
        }
        Row {
            ccEmailList?.let { Text(text = it.joinToString(", ")) }
        }
        Row {
            Text(text = "CCO")
            OutlinedTextField(
                value = ccoEmail,
                onValueChange = {
                    writeViewModel.updateCcoEmail(it)
                },
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Enter || event.key == Key.Comma) {
                        if (event.type == KeyEventType.KeyUp) {
                            writeViewModel.appendCcoEmailList(ccoEmail)
                        }
                        true
                    } else {
                        false
                    }
                }
            )
        }
        Row {
            ccoEmailList?.let { Text(text = it.joinToString(", ")) }
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
                    userLoged?.let {
                        writeViewModel.appendParaEmailList(paraEmail)
                        writeViewModel.appendCcEmailList(ccEmail)
                        writeViewModel.appendCcoEmailList(ccoEmail)
                        EmailEReceiverCriacaoDto(
                            assunto = assunto,
                            remetente = it.id,
                            body = body,
                            dataEnvio = LocalDate.now(),
                            destinatarios = paraEmailList,
                            ccs = ccEmailList,
                            ccos = ccoEmailList
                        )
                    }?.let {
                        emailService.insert(
                            it
                        )
                    }
                    navController.navigate("second")
                }) {
                Text(text = "Enviar")
            }
        }
    }
}



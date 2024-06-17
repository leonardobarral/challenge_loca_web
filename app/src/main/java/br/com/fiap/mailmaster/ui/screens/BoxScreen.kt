package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.EmailService
import br.com.fiap.mailmaster.ui.componentes.ItemLinhaComponente


@Composable
fun BoxScreen(navController: NavController, viewModel: ViewModel) {

    val context = LocalContext.current
    val emailService = EmailService(context)

    val userLoged by viewModel.userLoged.observeAsState()
    val boxFolder by viewModel.boxFolder.observeAsState(initial = BoxFolderEnum.BOX)

    val listEmail by viewModel.listEmail.observeAsState(initial = emailService.findByIDUserReceiver(userLoged!!.id))

    fun atualizarBoxFolder (){
        if (boxFolder == BoxFolderEnum.SENT) {
            userLoged?.let { emailService.findByIDUserSent(it.id) }
                ?.let { viewModel.updateListEmail(it) }
        } else {
            userLoged?.let { emailService.findByIDUserReceiver(it.id) }
                ?.let { viewModel.updateListEmail(it) }
        }
    }

    Column {
        Row {
            Column {
                for (i in BoxFolderEnum.entries) {
                    Button(
                        onClick = {
                            viewModel.updateBoxFolder(i)
                            atualizarBoxFolder()
                        }) {
                        Text(text = i.name)
                    }
                }
            }
            Column {


                Row {
                    Column {
                        Row {
                            Text(text = boxFolder.name)
                            Spacer(modifier = Modifier.width(10.dp))
                            userLoged?.let { Text(text = it.nome) }
                        }
                        Row {
                            userLoged?.let { Text(text = it.email) }
                        }
                    }
                }
                Row {
                    Button(
                        onClick = {
                            navController.navigate("fourth")
                        }) {
                        Text(text = "Novo Email")
                    }
                    //            Image(
                    //                painter = painterResource(id = R.drawable.image4),
                    //                contentDescription = "Gato feliz",
                    //                modifier = Modifier.fillMaxWidth().height(440.dp)
                    //            )
//                    Text(text = "Buscar")
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(userScrollEnabled = true) {
                    items(listEmail) {
                        ItemLinhaComponente(
                            email = it
                        )
                    }
                }

//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row {
//                    Text(text = "Footer")
//                }
            }

        }
    }
}

package br.com.fiap.mailmaster.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.views.ReadViewModel
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.MessageService
import br.com.fiap.mailmaster.ui.componentes.BoxShowBar
import br.com.fiap.mailmaster.ui.componentes.Header
import br.com.fiap.mailmaster.ui.componentes.ItemLinhaComponenteSent

@SuppressLint("PrivateResource")
@Composable
fun BoxScreen(
    navController: NavController,
    viewModel: ViewModel,
    readViewModel: ReadViewModel,
//    boxFolderEnum: BoxFolderEnum
) {
    val context = LocalContext.current
    val messageService = MessageService(context)

    val userLoged by viewModel.userLoged.observeAsState()
    val boxFolder by viewModel.boxFolder.observeAsState(initial = BoxFolderEnum.BOX)
    val listMessage by viewModel.listMessage.observeAsState(
        initial = messageService.findByBoxFolder(
            userLoged!!.id, boxFolder.toString()
        )
    )

    var showFolders by remember { mutableStateOf(false) }
    var page by remember { mutableStateOf(boxFolder.getDescription()) }

    fun atualizarBoxFolder() {
        userLoged?.let {
            messageService.findByBoxFolder(it.id, boxFolder.toString())
        }?.let { viewModel.updateListEmail(it) }
        page = boxFolder.getDescription()
    }
    atualizarBoxFolder()
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFFFFFFFF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Header(
                        onClickShowFolders = {
                            showFolders = !showFolders
                        }, page
                    )

                    Column(modifier = Modifier.fillMaxSize()) {
                        Box {


                            // bory
                            Column {
                                LazyColumn {
                                    items(listMessage) { message ->
                                        userLoged?.let {
                                            ItemLinhaComponenteSent(message = message,
                                                user = it,
                                                onClick = {


                                                    if(message.boxFolder == "DRAFT") {
                                                        navController.navigate("fourth/${message.id}/DRAFT")
                                                    }else{
                                                        if(!message.statusLeitura) {
                                                            message.statusLeitura = true
                                                            messageService.update(message)
                                                        }
                                                        navController.navigate("fiftieth/" + message.id)
                                                    }
                                                })
                                            Spacer(modifier = Modifier.height(5.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

            //showbar
            if (showFolders) {
                BoxShowBar(userLoged, onClickShowFolders = {
                    showFolders = !showFolders
                }, onClickNewMessage = {
                    viewModel.updateListEmail(emptyList())
                    navController.navigate("fourth")
                    showFolders = !showFolders
                }, onClickBox = {
                    viewModel.updateBoxFolder(it)
                    atualizarBoxFolder()
                    showFolders = !showFolders
                }, onClickSettings = {
                    /*navController.navigate("fourth")*/
                    showFolders = !showFolders
                })
            }
            if (!showFolders) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("fourth/0/WRITE")
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)

                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_edit_square_24),
                                contentDescription = "Toggle Folders",
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Novo",
                                color = Color.Black,
                                maxLines = 1,
                                modifier = Modifier.wrapContentWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}




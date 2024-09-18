package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.MessageService
import br.com.fiap.mailmaster.ui.componentes.BoxShowBar
import br.com.fiap.mailmaster.ui.componentes.HeaderReadEmailBox
import br.com.fiap.mailmaster.ui.componentes.HeaderReadEmailSent

@Composable
fun ReadScreen(
    navController: NavHostController,
    viewModel: ViewModel,
    id: String
) {
    val context = LocalContext.current
    val messageService = MessageService(context)
    val message by remember { mutableStateOf(messageService.findById(id = id)) }
    val userLoged by viewModel.userLoged.observeAsState()
    var showFolders by remember { mutableStateOf(false) }
    var showCcCco by remember { mutableStateOf(false) }
    val page by remember { mutableStateOf("Leitura") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box() {
            Box() {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    if (message.type == "DE") {
                        HeaderReadEmailSent(
                            onClickShowFolders = {
                                showFolders = !showFolders
                            },
                            onclickUpdateDelete = {
                                message.delete = true
                                messageService.update(message)
                            },
                            page
                        )
                    } else {
                        HeaderReadEmailBox(
                            onClickShowFolders = {
                                showFolders = !showFolders
                            },
                            onClickUpdateDelete = {},
                            onClickUpdateStatus = {
                                message.statusLeitura = false
                                messageService.update(message)
                                navController.navigate("second")
                                viewModel.updateBoxFolder(BoxFolderEnum.BOX)
                            },
                            page
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().height(25.dp)
                        ) {
                            Text(
                                text = message.assunto,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    color = Color.Black
                                ),
                                fontWeight = FontWeight.ExtraBold
                            )

                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().height(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = message.nomeRemetente,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.DarkGray
                                ),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = message.dataEnvio.toString(),
                                maxLines = 1,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                ),
                                fontWeight = FontWeight.Normal
                            )

                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().height(16.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Para: " + message.para,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                ),
                                fontWeight = FontWeight.Normal
                            )

                            if ((message.cc.isNotBlank() || message.cc.isNotBlank()) && !showCcCco) {
                                IconButton(
                                    onClick = {
                                        showCcCco = !showCcCco
                                    },
                                    modifier = Modifier
                                        .size(50.dp)
                                ) {

                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                        contentDescription = "Toggle Folders",
                                        tint = Color.Black,
                                        modifier = Modifier.size(35.dp)
                                    )
                                }
                            }
                            if (showCcCco) {
                                IconButton(
                                    onClick = {
                                        showCcCco = !showCcCco
                                    },
                                    modifier = Modifier
                                        .size(50.dp)
                                ) {

                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                                        contentDescription = "Toggle Folders",
                                        tint = Color.Black,
                                        modifier = Modifier.size(35.dp)
                                    )
                                }
                            }
                        }

                        
                        if (showCcCco) {
                            if(message.cc.isNotBlank()) {
                                Spacer(modifier = Modifier.height(3.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth().height(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Cc: " + message.cc,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.DarkGray
                                        ),
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            if(message.cco.isNotBlank()) {

                                Spacer(modifier = Modifier.height(3.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth().height(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Cco: " + message.cco,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.DarkGray
                                        ),
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .verticalScroll(
                                    state = ScrollState(0)
                                ),
                            verticalAlignment = Alignment.Top,

                        ) {
                            Text(
                                text = message.body,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                ),
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                }
            }


            //showbar
            if (showFolders) {
                BoxShowBar(
                    userLoged,
                    onClickShowFolders = {
                        showFolders = !showFolders
                    },
                    onClickNewMessage = {
                        showFolders = !showFolders
                    },
                    onClickBox = {
                        viewModel.updateBoxFolder(it)
                        navController.navigate("second")
                        showFolders = !showFolders
                    },
                    onClickSettings = {
                        /*navController.navigate("fourth")*/
                        showFolders = !showFolders
                    }
                )
            }
            if (!showFolders) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate("fourth/${message.id}/RESPONSETO")
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
                                    painter = painterResource(id = R.drawable.baseline_reply_24),
                                    contentDescription = "Repply button",
                                    tint = Color.Black,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = {
                                navController.navigate("fourth/${message.id}/RESPONSETOALL")
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
                                    painter = painterResource(id = R.drawable.baseline_reply_all_24),
                                    contentDescription = "All repply button",
                                    tint = Color.Black,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = {
                                navController.navigate("fourth/${message.id}/REDIRECTO")
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
                                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                                    contentDescription = "Repply button",
                                    tint = Color.Black,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
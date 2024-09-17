package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavHostController
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.views.ReadViewModel
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.MessageService
import br.com.fiap.mailmaster.ui.componentes.BoxShowBar
import br.com.fiap.mailmaster.ui.componentes.HeaderNewEmail
import br.com.fiap.mailmaster.ui.componentes.HeaderReadEmail

@Composable
fun ReadScreen(
    navController: NavHostController,
    viewModel: ViewModel,
//    readViewModel: ReadViewModel,
//    message: Messag
) {
    val context = LocalContext.current
//    val email by readViewModel.messageRead.observeAsState()
    val userLoged by viewModel.userLoged.observeAsState()
    var showFolders by remember { mutableStateOf(false) }
    val messageService = MessageService(context)
    val page by remember { mutableStateOf("Leitura") }

//    userLoged?.let { email?.let { it1 -> messageService.updateStatus(it1.id, it.id) } }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(8.dp)
    ){
        Box(){
            Box(){
                HeaderReadEmail(
                    onClickShowFolders = {
                        showFolders = !showFolders
                    },
                    page
                )
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
                                navController.navigate("fourth")
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
                                navController.navigate("fourth")
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
                                navController.navigate("fourth")
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
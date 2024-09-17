package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.User
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum

@Composable
fun BoxShowBar(
    userLoged: User?,
    onClickShowFolders: () -> Unit,
    onClickNewMessage: () -> Unit,
    onClickBox: (BoxFolderEnum) -> Unit,
    onClickSettings : ()-> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .zIndex(1f)
            .fillMaxHeight()
            .background(color = Color.DarkGray)
            .pointerInput(Unit) {
                detectTapGestures { }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

            )
            {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {


                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        userLoged?.let {
                            Text(text = it.name, color = Color.White)
                        }
                        IconButton(onClick = {
                            onClickShowFolders()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_menu_open_24),
                                contentDescription = "Toggle Folders",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    IconButton(
                        onClick = {
                            onClickNewMessage()

                        },
                        modifier = Modifier
                            .fillMaxWidth()
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(
//                                    0xFF00796B
//                                )
//                            )

//                            shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_edit_square_24),
                                contentDescription = "Toggle Folders"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Novo Email", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    for (i in BoxFolderEnum.entries) {

                        IconButton(
                            onClick = {
                                onClickBox(i)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = i.getIcon()),
                                    contentDescription = "Toggle Folders"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = i.getDescription(), color = Color.White)

                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    IconButton(
                        onClick = {
                            onClickSettings()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "Toggle Folders"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Settings", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.checks.EmailChecking
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.models.views.WriteViewModel
import br.com.fiap.mailmaster.ui.componentes.BoxShowBar
import br.com.fiap.mailmaster.ui.componentes.HeaderNewEmail
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun WriteScreen(
    navController: NavController,
    viewModel: ViewModel,
    writeViewModel: WriteViewModel
) {

    val paraEmail = remember { mutableStateOf("") }
    val paraEmailList = remember { mutableStateListOf<String>() }

    val ccEmail = remember { mutableStateOf("") }
    val ccEmailList = remember { mutableStateListOf<String>() }

    val ccoEmail = remember { mutableStateOf("") }
    val ccoEmailList = remember { mutableStateListOf<String>() }

    val assunto = remember { mutableStateOf("") }

    val body = remember { mutableStateOf("") }

    val context = LocalContext.current

    var showFolders by remember { mutableStateOf(false) }
    var showCcCco by remember { mutableStateOf(false) }
    val page by remember { mutableStateOf("Novo E-mail") }
    val userLoged by viewModel.userLoged.observeAsState()

    val validator = EmailChecking()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box() {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                HeaderNewEmail(
                    onClickShowFolders = {
                        showFolders = !showFolders
                    },
                    page
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp)
                ) {
                    Text(
                        text = "para:",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    TextField(
                        value = paraEmail.value,
                        onValueChange = {
                            if (it.endsWith(",") && paraEmail.value.isNotBlank()) {
                                val newEmail = it.dropLast(1).trim()
                                if (validator.isValidEmail(newEmail)) {
                                    paraEmailList.add(newEmail)
                                    paraEmail.value = ""
                                }
                            } else paraEmail.value = it
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 2.dp),

                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedPlaceholderColor = Color.LightGray,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                val email = paraEmail.value.trim()
                                if (validator.isValidEmail(email)) {
                                    paraEmailList.add(email)
                                    paraEmail.value = ""
                                }
                            }
                        ),
                        placeholder = { Text("example@domain.com") },

                        )
                    if (!showCcCco) {
                        Box{
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
                    }
                }

                if (paraEmailList.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FlowRow (
                            mainAxisSpacing = 3.dp,
                            crossAxisSpacing = 3.dp
                        ){
                            paraEmailList.forEach { item ->
                                Box(
                                    modifier = Modifier.wrapContentSize()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(color = Color.LightGray)
                                            .padding(8.dp,0.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            fontSize = 16.sp,
                                            color = Color.DarkGray
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        IconButton(
                                            onClick = {
                                                paraEmailList.remove(item)
                                            },
                                            modifier = Modifier
                                                .size(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_close_24),
                                                contentDescription = "Close Button",
                                                tint = Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(10.dp))



                if (showCcCco) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(0.dp)
                    ) {
                        Text(
                            text = "cc:",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        TextField(
                            value = ccEmail.value,
                            onValueChange = {
                                if (it.endsWith(",") && ccEmail.value.isNotBlank()) {
                                    val newEmail = it.dropLast(1).trim()
                                    if (validator.isValidEmail(newEmail)) {
                                        ccEmailList.add(newEmail)
                                        ccEmail.value = ""
                                    }
                                } else ccEmail.value = it
                            },
                            modifier = Modifier
                                .height(60.dp)
                                .align(Alignment.CenterVertically)
                                .padding(0.dp, 2.dp),

                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            textStyle = TextStyle(
                                color = Color.DarkGray,
                                fontSize = 18.sp
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedPlaceholderColor = Color.LightGray,
                                focusedPlaceholderColor = Color.LightGray,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    val email = ccEmail.value.trim()
                                    if (validator.isValidEmail(email)) {
                                        ccEmailList.add(email)
                                        ccEmail.value = ""
                                    }
                                }
                            ),
                            placeholder = { Text("example@domain.com") },
                        )
                    }
                    if (ccEmailList.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FlowRow (
                                mainAxisSpacing = 3.dp,
                                crossAxisSpacing = 3.dp
                            ){
                                ccEmailList.forEach { item ->
                                    Box(
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(color = Color.LightGray)
                                                .padding(8.dp,0.dp)
                                        ) {
                                            Text(
                                                text = item,
                                                fontSize = 16.sp,
                                                color = Color.DarkGray
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            IconButton(
                                                onClick = {
                                                    ccEmailList.remove(item)
                                                },
                                                modifier = Modifier
                                                    .size(16.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_close_24),
                                                    contentDescription = "Close Button",
                                                    tint = Color.Gray
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.DarkGray)
                    )
                    Spacer(modifier = Modifier.height(5.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(0.dp)
                    ) {
                        Text(
                            text = "cco:",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                        TextField(
                            value = ccoEmail.value,
                            onValueChange = {
                                if (it.endsWith(",") && ccoEmail.value.isNotBlank()) {
                                    val newEmail = it.dropLast(1).trim()
                                    if (validator.isValidEmail(newEmail)) {
                                        ccoEmailList.add(newEmail)
                                        ccoEmail.value = ""
                                    }
                                } else ccoEmail.value = it
                            },
                            modifier = Modifier
                                .height(60.dp)
                                .align(Alignment.CenterVertically)
                                .padding(0.dp, 2.dp),

                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            textStyle = TextStyle(
                                color = Color.DarkGray,
                                fontSize = 18.sp
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedPlaceholderColor = Color.LightGray,
                                focusedPlaceholderColor = Color.LightGray,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    val email = ccoEmail.value.trim()
                                    if (validator.isValidEmail(email)) {
                                        ccoEmailList.add(email)
                                        ccoEmail.value = ""
                                    }
                                }
                            ),
                            placeholder = { Text("example@domain.com") },

                            )
                    }

                    if (ccoEmailList.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FlowRow (
                                mainAxisSpacing = 3.dp,
                                crossAxisSpacing = 3.dp
                            ){
                                ccoEmailList.forEach { item ->
                                    Box(
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(color = Color.LightGray)
                                                .padding(8.dp,0.dp)
                                        ) {
                                            Text(
                                                text = item,
                                                fontSize = 16.sp,
                                                color = Color.DarkGray
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            IconButton(
                                                onClick = {
                                                    ccoEmailList.remove(item)
                                                },
                                                modifier = Modifier
                                                    .size(16.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_close_24),
                                                    contentDescription = "Close Button",
                                                    tint = Color.Gray
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.DarkGray)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }




                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                ) {
                    TextField(
                        value = assunto.value,
                        onValueChange = {
                            assunto.value = it
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 2.dp),
                        textStyle = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedPlaceholderColor = Color.LightGray,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Assunto") },

                        )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(5.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                ) {

                    TextField(
                        value = body.value,
                        onValueChange = {
                            body.value = it
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 2.dp),

                        textStyle = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedPlaceholderColor = Color.LightGray,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Mensagem") },

                        )
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

        }
    }
}




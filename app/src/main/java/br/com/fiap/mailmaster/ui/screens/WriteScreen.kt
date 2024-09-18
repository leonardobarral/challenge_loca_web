package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.checks.InputChecking
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.models.views.WriteViewModel
import br.com.fiap.mailmaster.services.MessageService
import br.com.fiap.mailmaster.ui.componentes.BoxShowBar
import br.com.fiap.mailmaster.ui.componentes.HeaderNewEmail
import com.google.accompanist.flowlayout.FlowRow
import java.util.UUID


@Composable
fun WriteScreen(
    navController: NavController,
    viewModel: ViewModel,
    writeViewModel: WriteViewModel,
    id: String,
    typeoperation: String
) {
    val context = LocalContext.current
    val messageService = MessageService(context)


    val paraEmail = remember { mutableStateOf("") }
    val paraEmailList = remember { mutableStateListOf<String>() }

    val ccEmail = remember { mutableStateOf("") }
    val ccEmailList = remember { mutableStateListOf<String>() }

    val ccoEmail = remember { mutableStateOf("") }
    val ccoEmailList = remember { mutableStateListOf<String>() }

    val assunto = remember { mutableStateOf("") }

    val body = remember { mutableStateOf("") }

    val priority = remember { mutableStateOf(PriorityEnum.NORMAL) }

    val idNewEmail = remember { mutableStateOf("") }
    val deleteStatus = remember { mutableStateOf(false) }

    val idNewResponse = remember { mutableStateOf("") }

    var showFolders by remember { mutableStateOf(false) }
    var showCcCco by remember { mutableStateOf(false) }
    val page by remember { mutableStateOf("Novo E-mail") }
    val userLoged by viewModel.userLoged.observeAsState()

    fun string(message: Message): String {
        var data = message.dataEnvio.toString()
        var nome = message.nomeRemetente
        var email = message.emailRemente

        return """
            
            
            
            --
            ${userLoged?.name}
            
            ___________________________________
            Em $data, $nome<$email> escreveu 
            -
            """.trimIndent()
    }

    LaunchedEffect(id, typeoperation) {

        if (id != "" && typeoperation == "DRAFT") {
            var messageOriginal = messageService.findById(id)

            idNewEmail.value = messageOriginal.id
            paraEmailList.addAll(messageOriginal.para.split(",").toMutableList())
            ccEmailList.addAll(messageOriginal.cc.split(",").toMutableList())
            ccoEmailList.addAll(messageOriginal.cco.split(",").toMutableList())
            body.value = messageOriginal.body
            assunto.value = messageOriginal.assunto
            priority.value = PriorityEnum.fromName(messageOriginal.prioridade)!!
            idNewResponse.value = messageOriginal.idMessageResponse
        } else if (typeoperation == "RESPONSETO") {
            var messageOriginal = messageService.findById(id)

            idNewEmail.value = UUID.randomUUID().toString()
            paraEmailList.add(messageOriginal.emailRemente)
            body.value = string(messageOriginal) + messageOriginal.body
            assunto.value = "RE: " + messageOriginal.assunto
            priority.value = PriorityEnum.fromName(messageOriginal.prioridade)!!
            idNewResponse.value = messageOriginal.id
        } else if (typeoperation == "RESPONSETOALL") {
            var messageOriginal = messageService.findById(id)

            idNewEmail.value = UUID.randomUUID().toString()
            paraEmailList.add(messageOriginal.emailRemente)
            ccEmailList.addAll(messageOriginal.para.split(",").toMutableList())
            ccEmailList.addAll(messageOriginal.cc.split(",").toMutableList())
            body.value = string(messageOriginal) + messageOriginal.body
            assunto.value = "RE: " + messageOriginal.assunto
            priority.value = PriorityEnum.fromName(messageOriginal.prioridade)!!
            idNewResponse.value = messageOriginal.id
        } else if (typeoperation == "REDIRECTO") {
            var messageOriginal = messageService.findById(id)

            idNewEmail.value = UUID.randomUUID().toString()
            body.value = messageOriginal.body
            assunto.value = "RE: " + messageOriginal.assunto
            priority.value = PriorityEnum.fromName(messageOriginal.prioridade)!!
            idNewResponse.value = messageOriginal.id
        } else {
            idNewEmail.value = UUID.randomUUID().toString()
        }
    }


    val validator = InputChecking()

    fun saveMessage(box: String) {

        paraEmail.value = ""
        ccEmail.value = ""
        ccoEmail.value = ""


        val boxFolder: BoxFolderEnum = if (box == "SENT") BoxFolderEnum.SENT
        else BoxFolderEnum.DRAFT

        userLoged?.let {
            Message(
                id = idNewEmail.value,
                idUser = it.id,
                emailRemente = userLoged!!.email,
                nomeRemetente = userLoged!!.name,
                assunto = assunto.value,
                body = body.value,
                prioridade = priority.value.toString(),
                boxFolder = boxFolder.toString(),
                para = paraEmailList.joinToString(","),
                cc = ccEmailList.joinToString(","),
                cco = ccoEmailList.joinToString(","),
                dataRecebimento = null,
                updated_at = null,
                idMessageResponse = idNewResponse.value,
                statusLeitura = true,
                delete = deleteStatus.value
            )
        }?.let {
            if (messageService.findById(idNewEmail.value)?.id.isNullOrEmpty()) messageService.insertNew(
                it
            )
            else messageService.update(it)
        }

    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box() {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                HeaderNewEmail(
                    onClickUpdateDelete = {

                        deleteStatus.value = true
                        saveMessage("DRAFT")
                        viewModel.updateBoxFolder(BoxFolderEnum.DRAFT)
                        navController.navigate("second")

                    },
                    onClickShowFolders = { showFolders = !showFolders },
                    onClickSend = {
                        if (!showFolders && body.value.isNotBlank() && assunto.value.isNotBlank() && (paraEmailList.isNotEmpty() || ccEmailList.isNotEmpty() || ccoEmailList.isNotEmpty())) {
                            saveMessage("SENT")
                            viewModel.updateBoxFolder(BoxFolderEnum.BOX)
                            navController.navigate("second")
                        }
                    },
                    pag = page
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
                        text = "para:", color = Color.Black, fontSize = 18.sp
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
                            color = Color.DarkGray, fontSize = 18.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedPlaceholderColor = Color.LightGray,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            val email = paraEmail.value.trim()
                            if (validator.isValidEmail(email)) {
                                paraEmailList.add(email)
                                paraEmail.value = ""
                            }
                        }),
                        placeholder = { Text("example@domain.com") },

                        )
                    if (!showCcCco && ccEmailList.isEmpty() && ccoEmailList.isEmpty()) {
                        Box {
                            IconButton(
                                onClick = {
                                    showCcCco = !showCcCco
                                }, modifier = Modifier.size(50.dp)
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
                        FlowRow(
                            mainAxisSpacing = 3.dp, crossAxisSpacing = 3.dp
                        ) {
                            paraEmailList.forEach { item ->
                                Box(
                                    modifier = Modifier.wrapContentSize()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(color = Color.LightGray)
                                            .padding(8.dp, 0.dp)
                                    ) {
                                        Text(
                                            text = item, fontSize = 16.sp, color = Color.DarkGray
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        IconButton(
                                            onClick = {
                                                paraEmailList.remove(item)
                                            }, modifier = Modifier.size(16.dp)
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



                if (showCcCco || ccEmailList.isNotEmpty() || ccoEmailList.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(0.dp)
                    ) {
                        Text(
                            text = "cc:", color = Color.Black, fontSize = 18.sp
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
                                color = Color.DarkGray, fontSize = 18.sp
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedPlaceholderColor = Color.LightGray,
                                focusedPlaceholderColor = Color.LightGray,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                val email = ccEmail.value.trim()
                                if (validator.isValidEmail(email)) {
                                    ccEmailList.add(email)
                                    ccEmail.value = ""
                                }
                            }),
                            placeholder = { Text("example@domain.com") },
                        )
                    }
                    if (ccEmailList.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FlowRow(
                                mainAxisSpacing = 3.dp, crossAxisSpacing = 3.dp
                            ) {
                                ccEmailList.forEach { item ->
                                    Box(
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(color = Color.LightGray)
                                                .padding(8.dp, 0.dp)
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
                                                }, modifier = Modifier.size(16.dp)
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
                            text = "cco:", color = Color.Black, fontSize = 18.sp
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
                                color = Color.DarkGray, fontSize = 18.sp
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedPlaceholderColor = Color.LightGray,
                                focusedPlaceholderColor = Color.LightGray,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                val email = ccoEmail.value.trim()
                                if (validator.isValidEmail(email)) {
                                    ccoEmailList.add(email)
                                    ccoEmail.value = ""
                                }
                            }),
                            placeholder = { Text("example@domain.com") },

                            )
                    }

                    if (ccoEmailList.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FlowRow(
                                mainAxisSpacing = 3.dp, crossAxisSpacing = 3.dp
                            ) {
                                ccoEmailList.forEach { item ->
                                    Box(
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(color = Color.LightGray)
                                                .padding(8.dp, 0.dp)
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
                                                }, modifier = Modifier.size(16.dp)
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
                            .padding(0.dp)
                            .align(Alignment.CenterVertically),
                        textStyle = TextStyle(
                            color = Color.DarkGray, fontSize = 18.sp
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
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start,
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
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(0.dp)
                            .align(Alignment.Top),


                        textStyle = TextStyle(
                            color = Color.DarkGray, fontSize = 15.sp, textAlign = TextAlign.Start

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
                BoxShowBar(userLoged, onClickShowFolders = {
                    showFolders = !showFolders
                }, onClickNewMessage = {
                    saveMessage("DRAFT")
                    showFolders = !showFolders
                }, onClickBox = {
                    saveMessage("DRAFT")
                    viewModel.updateBoxFolder(it)
                    navController.navigate("second")
                    showFolders = !showFolders
                }, onClickSettings = {
                    saveMessage("DRAFT")/*navController.navigate("fourth")*/
                    showFolders = !showFolders
                })
            }

        }
    }
}




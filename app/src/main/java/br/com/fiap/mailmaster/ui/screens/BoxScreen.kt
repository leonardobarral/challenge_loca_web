package br.com.fiap.mailmaster.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.views.ReadViewModel
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.EmailReceiverUserService
import br.com.fiap.mailmaster.services.EmailService
import br.com.fiap.mailmaster.services.UserService
import br.com.fiap.mailmaster.ui.componentes.ItemLinhaComponente

@SuppressLint("PrivateResource")
@Composable
fun BoxScreen(navController: NavController, viewModel: ViewModel, readViewModel: ReadViewModel) {
    val context = LocalContext.current
    val emailService = EmailService(context)
    val emailReceiverUser = EmailReceiverUserService(context)
    val userService = UserService(context)

    val userLoged by viewModel.userLoged.observeAsState()
    val boxFolder by viewModel.boxFolder.observeAsState(initial = BoxFolderEnum.BOX)
    val listEmail by viewModel.listEmail.observeAsState(
        initial = emailService.findByIDUserReceiver(
            userLoged!!.id,
            boxFolder.toString()
        )
    )

    var showFolders by remember { mutableStateOf(false) }

    fun atualizarBoxFolder() {
        if (boxFolder == BoxFolderEnum.SENT) {
            userLoged?.let { emailService.findByIDUserSent(it.id) }
                ?.let { viewModel.updateListEmail(it) }
        } else {
            userLoged?.let { emailService.findByIDUserReceiver(it.id, boxFolder.toString()) }
                ?.let { viewModel.updateListEmail(it) }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { showFolders = !showFolders }) {
                    Icon(
                        painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                        contentDescription = "Toggle Folders"
                    )
                }
                Text(
                    text = boxFolder.name,
                    fontSize = 24.sp,
                    color = Color(0xFF00796B)
                )
                Spacer(modifier = Modifier.width(10.dp))
                userLoged?.let {
                    Text(text = it.nome, color = Color(0xFF00796B))
                }
            }

            Row(modifier = Modifier.fillMaxSize()) {
                if (showFolders) {
                    Column(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(end = 16.dp)
                    ) {
                        for (i in BoxFolderEnum.entries) {
                            Button(
                                onClick = {
                                    viewModel.updateBoxFolder(i)
                                    atualizarBoxFolder()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF00796B
                                    )
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(text = i.name, color = Color.White)
                            }
                        }
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("fourth") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(text = "Novo Email", color = Color.White)
                        }
                    }
                    LazyColumn {
                        items(listEmail) { email ->
                            if (boxFolder == BoxFolderEnum.SENT) {
                                true
                            } else {
                                userService.selecteById(email.remetente)?.let { remetente ->
                                    userLoged?.let { userLoged ->
                                        emailReceiverUser.findByIdUserIdEmail(
                                            email.id, userLoged.id
                                        ).let { receiver ->
                                            ItemLinhaComponente(
                                                email = email,
                                                remetente = remetente,
                                                receiver = receiver,
                                                onClick = {
                                                    readViewModel.updateEmalRead(email)
                                                    navController.navigate("second")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




package br.com.fiap.mailmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import br.com.fiap.mailmaster.ui.componentes.ItemLinhaComponente
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.views.ViewModel
import br.com.fiap.mailmaster.services.EmailService


@Composable
fun BoxScreen(navController: NavController, viewModel: ViewModel) {

    val userLoged by viewModel.userLoged.observeAsState()
    val context = LocalContext.current
    val emailService = EmailService(context)

    val listEmails = userLoged?.let {
        emailService.findByIDUser(it)
    } ?: emptyList()

    val boxFolder by viewModel.boxFolder.observeAsState(initial = BoxFolderEnum.BOX )
    val listEmail  by viewModel.listEmail.observeAsState(initial = listEmails  )

    Column {
        Row {
            Text(text = boxFolder.name)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = boxFolder.name)
        }
        Row {
            Button(
                onClick = {
                    navController.navigate("")
                }) {
                Text(text = "Novo Email")
            }
//            Image(
//                painter = painterResource(id = R.drawable.image4),
//                contentDescription = "Gato feliz",
//                modifier = Modifier.fillMaxWidth().height(440.dp)
//            )
            Text(text = "Buscar")
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(userScrollEnabled = true) {
            items(listEmail) {
                ItemLinhaComponente(
                    email = it
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "Footer")
        }
    }
}

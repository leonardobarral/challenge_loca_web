package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser

@Composable
fun ItemLinhaComponenteSent(
    email: Email,
    remetente: UserExibitionDto,
    receivers: List<EmailReceiverUser>,
    onClick: (Email) -> Unit
) {
    //Image(painter = , contentDescription = )
    Box()
    {
        Row {
            Column {

            }
            Column {

                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(text = remetente.nome)
//                        Text(text = receiver.dataRecebimento.toString())
                    }
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = email.assunto,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = email.body,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Modifier.clickable { onClick(email) }
    }
}


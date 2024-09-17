package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.User


@Composable
fun ItemLinhaComponenteSent(
    message: Message,
    user: User,
    onClick: (Message) -> Unit
) {
    //Image(painter = , contentDescription = )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(message) },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(21.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    if (message.statusLeitura) {
                        Text(
                            text = "Para: " + message.para,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            ),
                            fontWeight = FontWeight.Normal
                        )
                    } else {
                        Text(
                            text = "Para: " + message.para,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Column {
                    Text(
                        text = message.dataEnvio.toString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (message.statusLeitura) {
                    Text(
                        text = message.assunto,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        ),
                        fontWeight = FontWeight.Normal
                    )
                } else {
                    Text(
                        text = message.assunto,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        fontWeight = FontWeight.ExtraBold
                    )
                }


            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = message.body,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                )
            }
        }

        Modifier.clickable { onClick(message) }
    }
}



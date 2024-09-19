package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemLinhaComponenteSent(
    message: Message,
    onClick: (Message) -> Unit,
    filterSelected: (Boolean) -> Unit,
    anyFilter: Boolean
) {
    var filtered = remember { mutableStateOf(false) }
    //Image(painter = , contentDescription = )
    Card(
//        onClick = {onClick(message)},
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(message) }
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        filterSelected(true)
                        filtered.value = true
                    },
                    onTap = {
                        onClick(message)
                    }
                )
            },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (filtered.value || anyFilter) {
                Column {
                    if (filtered.value) {
                        IconButton(
                            onClick = {
                                filtered.value = false
                                filterSelected(false)
                                      },
                            modifier = Modifier.size(50.dp)
                        )
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_box_24),
                                contentDescription = "Toggle Folders",
                                tint = Color.DarkGray
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                filtered.value = true
                                filterSelected(true)
                            },
                            modifier = Modifier.size(50.dp)
                        )
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_box_outline_blank_24),
                                contentDescription = "Toggle Folders",
                                tint = Color.DarkGray
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
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
        }
    }
}



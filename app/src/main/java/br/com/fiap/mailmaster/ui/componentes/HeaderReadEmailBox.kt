package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.fiap.MailMaster.R

@Composable

fun HeaderReadEmailBox(
    onClickShowFolders: () -> Unit,
    onClickUpdateDelete: () -> Unit,
    onClickUpdateStatus: () -> Unit,
    pag: String
) {
    //cabecalho
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            IconButton(
                onClick = { onClickShowFolders()},
                modifier = Modifier.size(50.dp)
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Toggle Folders",
                    tint = Color.DarkGray
                )
            }

        }

        Column {
            Row {


                IconButton(
                    onClick = {onClickUpdateDelete()},
                    modifier = Modifier.size(50.dp)

                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = "Send anexo",
                        tint = Color.DarkGray,
                    )

                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = {onClickUpdateStatus()},
                    modifier = Modifier.size(50.dp)

                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mark_email_unread_24),
                        contentDescription = "Send Button",
                        tint = Color.DarkGray,
                    )

                }
            }

        }
//        if (!showFolders && body.value.isNotBlank() && assunto.value.isNotBlank() && (paraEmailList.isNotEmpty() || ccEmailList.isNotEmpty() || ccoEmailList.isNotEmpty())) {


    }
    Spacer(modifier = Modifier.height(5.dp))
}

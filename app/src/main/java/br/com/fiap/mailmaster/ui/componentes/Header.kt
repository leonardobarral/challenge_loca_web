package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.MailMaster.R
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum

@Composable

fun Header(
    onClickShowFolders: () -> Unit,
    pag: String,
    onClickUpdateDelete: ()-> Unit,
    filter : Boolean
    ){
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
                onClick = { onClickShowFolders() },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Toggle Folders",
                    tint = Color.DarkGray
                )
            }


        }
        Column {

            if(filter) {
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = { onClickUpdateDelete() },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = "Send anexo",
                        tint = Color.DarkGray,
                    )
                }
            }else {
                Text(
                    text = pag,
                    fontSize = 20.sp,
                    color = Color.DarkGray

                )
            }
        }

    }

}

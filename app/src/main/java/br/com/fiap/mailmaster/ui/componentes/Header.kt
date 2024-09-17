package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    pag: String
    ){
    //cabecalho
    Row(
    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {onClickShowFolders()},
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Toggle Folders",
                tint = Color.DarkGray
            )
        }

        Text(
            text = pag,
            fontSize = 20.sp,
            color = Color.DarkGray

        )

    }
    Spacer(modifier = Modifier.height(5.dp))
}

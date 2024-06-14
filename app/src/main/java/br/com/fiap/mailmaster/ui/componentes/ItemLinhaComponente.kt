package br.com.fiap.mailmaster.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.fiap.mailmaster.models.Email

@Composable
fun ItemLinhaComponente(
    email: Email
) {
    //Image(painter = , contentDescription = )

    Row(
        Modifier.fillMaxWidth()
    ){
        Column {
            Text(text = email.remetente.nome )
            Text(text = email.assunto)
        }
        Column {
            Text(text = email.dataRecebimento.toString())
        }

    }
    
}
package br.com.fiap.mailmaster.models.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.mailmaster.models.Message

class ReadViewModel : ViewModel() {

    //Para
    //Email
    private val _Send_emailRead = MutableLiveData<Message>()
    val messageRead: LiveData<Message> = _Send_emailRead

    fun updateEmalRead(novoValor: Message) {
        _Send_emailRead.value = novoValor
    }

}


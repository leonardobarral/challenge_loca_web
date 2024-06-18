package br.com.fiap.mailmaster.models.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.mailmaster.models.Email

class ReadViewModel : ViewModel() {

    //Para
    //Email
    private val _emailRead = MutableLiveData<Email>()
    val emailRead: LiveData<Email> = _emailRead

    fun updateEmalRead(novoValor: Email) {
        _emailRead.value = novoValor
    }

}


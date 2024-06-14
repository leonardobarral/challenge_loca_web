package br.com.fiap.mailmaster.models.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum

class ViewModel : ViewModel() {

    //listEmail
    private val _listEmail = MutableLiveData<List<Email>>()
    val listEmail: LiveData<List<Email>> = _listEmail

    fun updateListEmail(novoValor: List<Email>) {
        _listEmail.value = novoValor
    }


    //boxFolder
    private val _boxFolder = MutableLiveData<BoxFolderEnum>()
    val boxFolder: LiveData<BoxFolderEnum> = _boxFolder

    fun updateBoxFolder(novoValor: BoxFolderEnum) {
        _boxFolder.value = novoValor
    }


    //userLoged
    private val _userLoged = MutableLiveData<UserExibitionDto>()
    val userLoged: LiveData<UserExibitionDto> = _userLoged

    fun updateLogedUser(novoValor: UserExibitionDto) {
        _userLoged.value = novoValor
    }


    //userLoginDto
    private val _userLoginDto = MutableLiveData<UserLoginDto>()
    val userLoginDto: LiveData<UserLoginDto> = _userLoginDto

    fun updateUserLoginDto(novoValorEmail: String, novoValorSenha: String) {
        _userLoginDto.value = UserLoginDto(
            email = novoValorEmail,
            senha = novoValorSenha
        )
    }

    //userCreateDto
    private val _userCadastroDto = MutableLiveData<UserCadastroDto>()
    val userCadastroDto: LiveData<UserCadastroDto> = _userCadastroDto

    fun updateUserCadastroDto(
            novoValorEmail: String,
            novoValorNome:String,
            novoValorSenha: String,
            novoValorSenha1:String,
        ) {
        _userCadastroDto.value = UserCadastroDto(
            email = novoValorEmail,
            nome = novoValorNome,
            senha = novoValorSenha,
            senha1 = novoValorSenha1,
        )
    }


    //userEmail
    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    fun updateUserEmail(novoValor: String) {
        _userEmail.value = novoValor
    }


    //userNome
    private val _userNome = MutableLiveData<String>()
    val userNome: LiveData<String> = _userNome

    fun updateUserNome(novoValor: String) {
        _userNome.value = novoValor
    }


    //userSenha
    private val _userSenha = MutableLiveData<String>()
    val userSenha: LiveData<String> = _userSenha

    fun updateUserSenha(novoValor: String) {
        _userSenha.value = novoValor
    }


    //userConfirmeSenha
    private val _userSenha1 = MutableLiveData<String>()
    val userSenha1: LiveData<String> = _userSenha1

    fun updateUserSenha1(novoValor: String) {
        _userSenha1.value = novoValor
    }

    //write
    //EmailCriacaoDto



}


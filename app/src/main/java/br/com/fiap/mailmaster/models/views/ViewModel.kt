package br.com.fiap.mailmaster.models.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.User
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum

class ViewModel : ViewModel() {

    //listEmail
    private val _listMessage = MutableLiveData<List<Message>>()
    val listMessage: LiveData<List<Message>> = _listMessage

    fun updateListEmail(novoValor: List<Message>) {
        _listMessage.value = novoValor
    }


    //boxFolder
    private val _boxFolder = MutableLiveData<BoxFolderEnum>()
    val boxFolder: LiveData<BoxFolderEnum> = _boxFolder

    fun updateBoxFolder(novoValor: BoxFolderEnum) {
        _boxFolder.value = novoValor
    }


    //userLoged
    private val _userLoged = MutableLiveData<User>()
    val userLoged: LiveData<User> = _userLoged
    fun updateLogedUser(novoValor: User) {
        _userLoged.value = novoValor
    }
//    fun updateLogedUserLocal(novoValor: UserExibitionDto) {
//        _userLoged.value = User(
//            novoValor.id,
//            novoValor.name,
//            novoValor.tema,
//            novoValor.email,
//            novoValor.cor
//    }
    fun updateLogedUser() {
        _userLoged.value = User(
            id = "3ae32c41-a1c8-49fb-a838-14c3a849857c",
            name = "Leonardo",
            email = "leo@gmail.com",
            cor = "RED",
            tema = "DARK"

        )
    }


    //userLoged
    private val _statusConection = MutableLiveData<String>()
    val statusConection: LiveData<String> = _statusConection

    fun updateStatusConection(novoValor: String) {
        _statusConection.value = novoValor
    }







    //userLoginDto
    private val _userLoginDto = MutableLiveData<UserLoginDto>()
    val userLoginDto: LiveData<UserLoginDto> = _userLoginDto

    fun updateUserLoginDto(novoValorEmail: String, novoValorSenha: String) {
        _userLoginDto.value = UserLoginDto(
            email = novoValorEmail,
            password = novoValorSenha
        )
    }

    //userCreateDto
    private val _userCadastroDto = MutableLiveData<UserCadastroDto>()
    val userCadastroDto: LiveData<UserCadastroDto> = _userCadastroDto

    fun updateUserCadastroDto(
        novoValorEmail: String,
        novoValorNome: String,
        novoValorSenha: String,
        novoValorSenha1: String,
    ) {
        _userCadastroDto.value = UserCadastroDto(
            email = novoValorEmail,
            name = novoValorNome,
            password1 = novoValorSenha,
            password2 = novoValorSenha1,
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


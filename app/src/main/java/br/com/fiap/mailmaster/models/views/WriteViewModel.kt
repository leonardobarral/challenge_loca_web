package br.com.fiap.mailmaster.models.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {

    //Para
    //Email
    private val _paraEmail = MutableLiveData<String>()
    val paraEmail: LiveData<String> = _paraEmail

    fun updateParaEmail(novoValor: String) {
        _paraEmail.value = novoValor
    }

    private val _paraEmailList = MutableLiveData<List<String>>(emptyList())
    val paraEmailList: LiveData<List<String>> = _paraEmailList

    fun appendParaEmailList(novoValor: String) {
        if (novoValor.isNotBlank()) {
            val currentList = _paraEmailList.value?.toMutableList() ?: mutableListOf()
            currentList.add(novoValor)
            _paraEmailList.value = currentList
            _paraEmail.value = ""
        }
    }


    //CC
    //Email
    private val _ccEmail = MutableLiveData<String>()
    val ccEmail: LiveData<String> = _ccEmail

    fun updateCcEmail(novoValor: String) {
        _ccEmail.value = novoValor
    }

    //listEmail
    private val _ccEmailList = MutableLiveData<List<String>>(emptyList())
    val ccEmailList: LiveData<List<String>> = _ccEmailList

    fun appendCcEmailList(novoValor: String) {
        if (novoValor.isNotBlank()) {
            val currentList = _ccEmailList.value?.toMutableList() ?: mutableListOf()
            currentList.add(novoValor)
            _ccEmailList.value = currentList
            _ccEmail.value = ""
        }
    }

    //CCO
    //Email
    private val _ccoEmail = MutableLiveData<String>()
    val ccoEmail: LiveData<String> = _ccoEmail

    fun updateCcoEmail(novoValor: String) {
        _ccoEmail.value = novoValor
    }

    //listEmail
    private val _ccoEmailList = MutableLiveData<List<String>>(emptyList())
    val ccoEmailList: LiveData<List<String>> = _ccoEmailList

    fun appendCcoEmailList(novoValor: String) {
        if (novoValor.isNotBlank()) {
            val currentList = _ccoEmailList.value?.toMutableList() ?: mutableListOf()
            currentList.add(novoValor)
            _ccoEmailList.value = currentList
            _ccoEmail.value = ""
        }
    }

    //assunto
    private val _assunto = MutableLiveData<String>()
    val assunto: LiveData<String> = _assunto

    fun updateAssunto(novoValor: String) {
        _assunto.value = novoValor
    }


    //body
    private val _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    fun updateBody(novoValor: String) {
        _body.value = novoValor
    }

//
//
//
//
//
//
//    //boxFolder
//    private val _boxFolder = MutableLiveData<BoxFolderEnum>()
//    val boxFolder: LiveData<BoxFolderEnum> = _boxFolder
//
//    fun updateBoxFolder(novoValor: BoxFolderEnum) {
//        _boxFolder.value = novoValor
//    }
//
//
//    //userLoged
//    private val _userLoged = MutableLiveData<UserExibitionDto>()
//    val userLoged: LiveData<UserExibitionDto> = _userLoged
//
//    fun updateLogedUser(novoValor: UserExibitionDto) {
//        _userLoged.value = novoValor
//    }
//
//
//    //userLoginDto
//    private val _userLoginDto = MutableLiveData<UserLoginDto>()
//    val userLoginDto: LiveData<UserLoginDto> = _userLoginDto
//
//    fun updateUserLoginDto(novoValorEmail: String, novoValorSenha: String) {
//        _userLoginDto.value = UserLoginDto(
//            email = novoValorEmail,
//            senha = novoValorSenha
//        )
//    }
//
//    //userCreateDto
//    private val _userCadastroDto = MutableLiveData<UserCadastroDto>()
//    val userCadastroDto: LiveData<UserCadastroDto> = _userCadastroDto
//
//    fun updateUserCadastroDto(
//            novoValorEmail: String,
//            novoValorNome:String,
//            novoValorSenha: String,
//            novoValorSenha1:String,
//        ) {
//        _userCadastroDto.value = UserCadastroDto(
//            email = novoValorEmail,
//            nome = novoValorNome,
//            senha = novoValorSenha,
//            senha1 = novoValorSenha1,
//        )
//    }
//
//
//    //userEmail
//    private val _userEmail = MutableLiveData<String>()
//    val userEmail: LiveData<String> = _userEmail
//
//    fun updateUserEmail(novoValor: String) {
//        _userEmail.value = novoValor
//    }
//
//
//    //userNome
//    private val _userNome = MutableLiveData<String>()
//    val userNome: LiveData<String> = _userNome
//
//    fun updateUserNome(novoValor: String) {
//        _userNome.value = novoValor
//    }
//
//
//    //userSenha
//    private val _userSenha = MutableLiveData<String>()
//    val userSenha: LiveData<String> = _userSenha
//
//    fun updateUserSenha(novoValor: String) {
//        _userSenha.value = novoValor
//    }
//
//
//    //userConfirmeSenha
//    private val _userSenha1 = MutableLiveData<String>()
//    val userSenha1: LiveData<String> = _userSenha1
//
//    fun updateUserSenha1(novoValor: String) {
//        _userSenha1.value = novoValor
//    }


}


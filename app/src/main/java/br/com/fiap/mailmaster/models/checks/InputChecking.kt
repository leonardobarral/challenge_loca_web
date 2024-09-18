package br.com.fiap.mailmaster.models.checks

class InputChecking(

    private val emailRegex: Regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"),
    private val passworRegex: Regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")


){
    fun isValidEmail(email: String): Boolean {
        return email.matches(this.emailRegex)
    }
    fun isValidPassWord(email: String): Boolean {
        return email.matches(this.passworRegex)
    }
}

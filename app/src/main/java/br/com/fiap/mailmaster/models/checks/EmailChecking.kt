package br.com.fiap.mailmaster.models.checks

class EmailChecking{

    private val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex)
    }
}

package br.com.fiap.mailmaster.models.enums

import br.com.fiap.MailMaster.R

enum class BoxFolderEnum(private val description: String) {
    BOX("Caixa de Entrada"),
    SENT( "Enviados"),
    DRAFT("Rascunho"),
    SPAM("Spam");
    fun getDescription(): String {
        return description
    }

    fun getIcon(): Int {
        return when (this) {
            BOX -> R . drawable . baseline_email_24
            SENT -> R . drawable . baseline_send_24
            DRAFT ->  R.drawable.baseline_insert_drive_file_24
            SPAM -> R.drawable.baseline_privacy_tip_24
        }
    }
    companion object {
        fun fromName(name: String): BoxFolderEnum? {
            return values().find { it.name == name }
        }
    }
}
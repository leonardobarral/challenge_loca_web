package br.com.fiap.mailmaster.models.enums

enum class ReceiverTypeEnum(private val description: String) {
    PARA("Destinatário"),
    CC("Copia para"),
    CCO("Copia oculta para"),
    DE("REMETENTE");
    fun getDescription(): String {
        return description
    }
}
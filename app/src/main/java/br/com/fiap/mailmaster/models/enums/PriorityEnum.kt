package br.com.fiap.mailmaster.models.enums

enum class PriorityEnum(private val description: String) {
    ALTA("Prioridade alta"),
    NORMAL("Prioridade normal"),
    BAIXA("Prioridade baixa");
    fun getDescription(): String {
        return description
    }
}
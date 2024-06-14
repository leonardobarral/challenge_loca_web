package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.EmailRepository
import br.com.fiap.mailmaster.dtos.EmailCriacaoDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.models.Email

class EmailService(context: Context) {

    private val emailRepository = EmailRepository(context)

    fun findByIDUser(user: UserExibitionDto ): List<Email> {
        return emailRepository.findByIDUser(user = user)
    }

    fun insert(emal: EmailCriacaoDto):Long{
        return emailRepository.findByIDUser(user = user)
    }
}
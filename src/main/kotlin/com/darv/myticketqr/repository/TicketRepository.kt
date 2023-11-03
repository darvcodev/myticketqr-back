package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TicketRepository : MongoRepository<Ticket, ObjectId> {
    fun findOneByNBoleta(nBoleta: String): Ticket
    fun findOneByUserId(userId: ObjectId): Ticket
    fun findOneByUserIdAndNBoleta(userId: ObjectId, nBoleta: String): Ticket
    fun findAllByUserId(userId: ObjectId): List<Ticket>
    fun findAllByUserIdAndEstado(userId: ObjectId, estado: String): List<Ticket>
    fun findAllByUserIdAndEstadoAndValidada(userId: ObjectId, estado: String, validada: Boolean): List<Ticket>
    fun findAllByUserIdAndValidada(userId: ObjectId, validada: Boolean): List<Ticket>
    fun findAllByEstado(estado: String): List<Ticket>
    fun findAllByEstadoAndValidada(estado: String, validada: Boolean): List<Ticket>
    fun findAllByValidada(validada: Boolean): List<Ticket>
}
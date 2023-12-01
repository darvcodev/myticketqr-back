package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface TicketRepository : MongoRepository<Ticket, ObjectId> {
    //findById
    override fun findById(id: ObjectId): Optional<Ticket>

    //findByUserId
    fun findByUserId(userId: ObjectId): List<Ticket>
}

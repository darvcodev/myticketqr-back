package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TicketRepository : MongoRepository<Ticket, ObjectId> {
    fun findAllByUserId(userId: String): List<Ticket>
}

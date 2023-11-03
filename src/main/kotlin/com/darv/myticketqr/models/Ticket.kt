package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Ticket(
    val userId: ObjectId,
    val celular: String,
    val comprador: String,
    val estado: String,
    val nBoleta: String,
    val precio: Number,
    val validada: Boolean,
    val createdDate: LocalDateTime,
    val modifiedDate: LocalDateTime
)


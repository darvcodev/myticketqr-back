package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Ticket(
    val id: ObjectId,
    val userId: ObjectId,
    var celular: String,
    var comprador: String,
    var estado: String,
    val precio: Number,
    val validada: Boolean,
    val nBoleta: String,
    val createdDate: LocalDateTime,
    val modifiedDate: LocalDateTime
)


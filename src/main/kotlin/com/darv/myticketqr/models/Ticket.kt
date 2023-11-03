package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Ticket (
    @Id
    val id: ObjectId? = null,
    val userId: ObjectId,
    val celular: String,
    val comprador: String,
    val estado: String,
    val nBoleta: String,
    val precio: Number,
    val validada: Boolean,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
)

package com.darv.myticketqr

import org.bson.types.ObjectId

data class TicketRequest (
    val id: ObjectId? = null,
    val userId: ObjectId,
    val celular: String,
    val comprador: String,
    val estado: String,
    val nBoleta: String,
    val precio: Number,
    val validada: Boolean,
)
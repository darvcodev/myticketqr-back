package com.darv.myticketqr

import org.bson.types.ObjectId

data class TicketRequest (
    val userId: ObjectId,
    val celular: String,
    val comprador: String,
    val estado: String,
    val nBoleta: String,
    val precio: Number,
    val validada: Boolean,
)

data class TicketUpdateRequest(
    val celular: String,
    val comprador: String,
    val estado: String,
)

data class TicketValidateRequest(
    val validada: Boolean,
)

package com.darv.myticketqr

data class TicketResponse(
    val _id: String,
    val userId: String,
    val celular: String,
    val comprador: String,
    val estado: String,
    val precio: Number,
    val validada: Boolean,
    val createdDate: String,
    val modifiedDate: String,
    val nBoleta: String
)

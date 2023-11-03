package com.darv.myticketqr

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@RestController
@RequestMapping("/tickets")
class TicketController(
    private val ticketsRepository: TicketRepository
) {

    @GetMapping
    fun getAllTickets(): ResponseEntity<List<Ticket>> {
        val tickets = ticketsRepository.findAll()
        return ResponseEntity.ok(tickets)
    }

    @PostMapping
    fun createTicket(@RequestBody ticketRequest: TicketRequest): ResponseEntity<Ticket> {

        val ticket = Ticket(
            userId = ticketRequest.userId,
            celular = ticketRequest.celular,
            comprador = ticketRequest.comprador,
            estado = ticketRequest.estado,
            nBoleta = ticketRequest.nBoleta,
            precio = ticketRequest.precio,
            validada = ticketRequest.validada,
            createdDate = LocalDateTime.now(),
            modifiedDate = LocalDateTime.now()
        )

        val savedTicket = ticketsRepository.save(ticket)

        return ResponseEntity(savedTicket, HttpStatus.CREATED)
    }
}


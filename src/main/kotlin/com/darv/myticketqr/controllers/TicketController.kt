package com.darv.myticketqr

import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET, RequestMethod.POST])
class TicketController(
    private val ticketsRepository: TicketRepository
) {

    @GetMapping
    fun getAllTickets(): ResponseEntity<List<Ticket>> {
        val tickets = ticketsRepository.findAll()
        return ResponseEntity.ok(tickets)
    }

    @GetMapping("/{id}")
    fun getTicketsByUserId(@PathVariable("id") userId: String): ResponseEntity<List<Ticket>> {
        val tickets = ticketsRepository.findAllByUserId(userId)
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


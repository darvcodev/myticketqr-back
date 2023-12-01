package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
class TicketController(
    private val ticketsRepository: TicketRepository
) {

    @GetMapping
    fun getAllTickets(): ResponseEntity<List<TicketResponse>> {
        val tickets = ticketsRepository.findAll()
        val ticketResponses = tickets.map { ticket ->
            TicketResponse(
                _id = ticket.id.toString(),
                userId = ticket.userId.toString(),
                celular = ticket.celular,
                comprador = ticket.comprador,
                estado = ticket.estado,
                precio = ticket.precio,
                validada = ticket.validada,
                createdDate = ticket.createdDate.toString(),
                modifiedDate = ticket.modifiedDate.toString(),
                nBoleta = ticket.nBoleta
            )
        }
        return ResponseEntity.ok(ticketResponses)
    }

    @GetMapping("/{id}")
    fun getTicketsByUserId(@PathVariable("id") userId: String): ResponseEntity<List<TicketResponse>> {
        val tickets = ticketsRepository.findByUserId(ObjectId(userId))
        val ticketResponses = tickets.map { ticket ->
            TicketResponse(
                _id = ticket.id.toString(),
                userId = ticket.userId.toString(),
                celular = ticket.celular,
                comprador = ticket.comprador,
                estado = ticket.estado,
                precio = ticket.precio,
                validada = ticket.validada,
                createdDate = ticket.createdDate.toString(),
                modifiedDate = ticket.modifiedDate.toString(),
                nBoleta = ticket.nBoleta
            )
        }
        return ResponseEntity.ok(ticketResponses)
    }

    @GetMapping("/ticket/{id}")
    fun getOneTicketById(@PathVariable("id") ticketId: String): ResponseEntity<TicketResponse> {
        val ticket = ticketsRepository.findById(ObjectId(ticketId))
        if (ticket.isPresent) {
            val ticketResponse = TicketResponse(
                _id = ticket.get().id.toString(),
                userId = ticket.get().userId.toString(),
                celular = ticket.get().celular,
                comprador = ticket.get().comprador,
                estado = ticket.get().estado,
                precio = ticket.get().precio,
                validada = ticket.get().validada,
                createdDate = ticket.get().createdDate.toString(),
                modifiedDate = ticket.get().modifiedDate.toString(),
                nBoleta = ticket.get().nBoleta
            )
            return ResponseEntity.ok(ticketResponse)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createTicket(@RequestBody ticketRequest: TicketRequest): ResponseEntity<Ticket> {

        val ticket = Ticket(
            id = ObjectId.get(),
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

    @PutMapping("/ticket/{id}")
    fun updateTicket(
        @PathVariable("id") id: String,
        @RequestBody updateRequest: TicketUpdateRequest
    ): ResponseEntity<Ticket> {

        val existingTicket = ticketsRepository.findById(ObjectId(id))

        if (existingTicket.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val ticket = existingTicket.get()

        val updatedTicket = ticket.copy(
            celular = updateRequest.celular ?: ticket.celular,
            comprador = updateRequest.comprador ?: ticket.comprador,
            estado = updateRequest.estado ?: ticket.estado,
            modifiedDate = LocalDateTime.now()
        )

        val savedTicket = ticketsRepository.save(updatedTicket)

        return ResponseEntity.ok(savedTicket)
    }

    @PutMapping("/validate/{id}")
    fun validateTicket(
        @PathVariable("id") id: String,
        @RequestBody updateRequest: TicketValidateRequest
    ): ResponseEntity<Ticket> {

        val existingTicket = ticketsRepository.findById(ObjectId(id))

        if (existingTicket.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val ticket = existingTicket.get()

        val updatedTicket = ticket.copy(
            validada = updateRequest.validada,
            modifiedDate = LocalDateTime.now()
        )

        val savedTicket = ticketsRepository.save(updatedTicket)

        return ResponseEntity.ok(savedTicket)
    }

    @DeleteMapping("/ticket/{id}")
    fun deleteTicket(@PathVariable("id") id: String): ResponseEntity<String> {
        val existingTicket = ticketsRepository.findById(ObjectId(id))

        if (existingTicket.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket con ID $id no encontrado.")
        }

        ticketsRepository.deleteById(ObjectId(id))

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ticket con ID $id ha sido eliminado exitosamente.")
    }

}


package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RestController
@RequestMapping("/users")
class UserController(
    private val usersRepository: UserRepository
) {

    // Obtiene todos los usuarios
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = usersRepository.findAll()
        return ResponseEntity.ok(users)
    }

    // Obtiene un usuario por su id
    @GetMapping("/{id}")
    fun getOneUser(@PathVariable("id") id: String): ResponseEntity<User> {
        val user = usersRepository.findOneByUsername(username = id)
        return ResponseEntity.ok(user)
    }

    // Crea un usuario
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<User> {

        // Hashear la contraseña
        val passwordHash = BCryptPasswordEncoder().encode(userRequest.password)

        val user = User(
            username = userRequest.username,
            password = passwordHash,
            email = userRequest.email,
            createdDate = LocalDateTime.now(),
            modifiedDate = LocalDateTime.now()
        )

        val savedUser = usersRepository.save(user)

        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    // Actualiza un usuario
    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable("id") id: String,
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<User> {

        val user = usersRepository.findOneByUsername(username = id)

        val updatedUser = user.copy(
            username = userRequest.username,
            password = userRequest.password,
            email = userRequest.email,
            modifiedDate = LocalDateTime.now()
        )

        val savedUser = usersRepository.save(updatedUser)

        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    // Elimina un usuario
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String): ResponseEntity<Unit> {
        usersRepository.deleteById(ObjectId(id))
        return ResponseEntity.noContent().build()
    }
}
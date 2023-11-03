package com.darv.myticketqr

import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val userRepository: UserRepository) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Map<String, String>> {
        val username = loginRequest.username
        val password = loginRequest.password

        try {
            val user = userRepository.findOneByUsername(username)
            if (user != null) {
                if (BCryptPasswordEncoder().matches(password, user.password)) {
                    return ResponseEntity.ok(mapOf("status" to "success", "message" to "Login correcto"))
                } else {
                    return ResponseEntity.badRequest().body(mapOf("status" to "error", "message" to "Contraseña incorrecta"))
                }
            } else {
                return ResponseEntity.badRequest().body(mapOf("status" to "error", "message" to "Usuario no encontrado"))
            }
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(mapOf("status" to "error", "message" to "Error"))
        }

    }
    
}



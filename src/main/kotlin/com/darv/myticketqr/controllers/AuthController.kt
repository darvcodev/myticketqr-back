package com.darv.myticketqr

import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET, RequestMethod.POST])
class AuthController(private val userRepository: UserRepository) {

    data class LoginResponse(
        val status: String,
        val message: String,
        val userId: String?,
        val username: String?
    )

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val username = loginRequest.username
        val password = loginRequest.password

        try {
            val user = userRepository.findOneByUsername(username)
            if (user != null) {
                if (BCryptPasswordEncoder().matches(password, user.password)) {
                    // Inicio de sesión exitoso, devuelve ID y nombre de usuario.
                    return ResponseEntity.ok(LoginResponse("success", "Login correcto", user.id.toString(), user.username))
                } else {
                    return ResponseEntity.badRequest().body(LoginResponse("error", "Contraseña incorrecta", null, null))
                }
            } else {
                return ResponseEntity.badRequest().body(LoginResponse("error", "Usuario no encontrado", null, null))
            }
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(LoginResponse("error", "Error", null, null))
        }
    }
}




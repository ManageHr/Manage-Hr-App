package com.example.ManageHr_App.Config

import com.example.ManageHr_App.Dto.Jwt.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)

            try {
                if (jwtUtil.validateToken(token)) {
                    val email = jwtUtil.extractEmail(token)

                    // Crear autenticación (puedes agregar roles aquí)
                    val authentication = UsernamePasswordAuthenticationToken(
                        email, null, listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )

                    SecurityContextHolder.getContext().authentication = authentication
                }
            } catch (e: Exception) {
                // Token inválido - continuar sin autenticación
            }
        }

        filterChain.doFilter(request, response)
    }
}
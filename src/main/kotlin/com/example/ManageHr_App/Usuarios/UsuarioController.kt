package com.example.ManageHr_App.Usuarios

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsuarioController {
    @Autowired
    lateinit var usuariosServices: UsuariosServices
    @GetMapping("/usuarios")
    fun obtenerUsuario():List<UsuarioDto>{
        return usuariosServices.obtenerUsuarios()
    }
}
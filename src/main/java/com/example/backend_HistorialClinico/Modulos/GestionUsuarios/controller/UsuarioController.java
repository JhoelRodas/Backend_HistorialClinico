package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.RolesServices;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.UsuarioServices;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Seguro;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.SeguroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/users")
@RequiredArgsConstructor
// @CrossOrigin(origins =
// {"https://frontend-stylo-store.vercel.app/","http://localhost:5173"})
public class UsuarioController {
    private final UsuarioServices usuarioServices;
    private final RolesServices rolesServices;
    private final UserRepository userRepository;
    private final SeguroService seguroService;
    @GetMapping
    public ResponseEntity<List<User>> obtenerTodosLosRoles() {
        List<User> usuario = usuarioServices.obtenerTodosLosUsuario();
        return ResponseEntity.ok(usuario);
    }

    // Endpoint para asignar un rol a un usuario
    @PutMapping("/{userId}/assignRole/{roleId}")
    public ResponseEntity<User> asignarRolUsuario(@PathVariable Integer userId, @PathVariable Integer roleId) {
        Optional<User> userOpt = usuarioServices.obtenerUsuarioPorId(userId);
        Optional<Roles> roleOpt = rolesServices.obtenerRolPorId(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(roleOpt.get()); // Asigna el nuevo rol al usuario
            User usuarioActualizado = usuarioServices.guardarUsuario(user); // Guarda el usuario actualizado
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Integer userId, @RequestBody User userActualizado) {
        try {
            User usuarioActualizado = usuarioServices.actualizarUsuario(userId, userActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ci/{ci}")
    public ResponseEntity<User> obtenerUsuarioPorCI(@PathVariable String ci) {
        Optional<User> userOpt = userRepository.findByCi(ci);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{userId}/assignSeguro/{seguroId}")
    public ResponseEntity<User> asignarSeguroUsuario(@PathVariable Integer userId, @PathVariable Integer seguroId) {
        Optional<User> userOpt = usuarioServices.obtenerUsuarioPorId(userId);
        Optional<Seguro> seguroOpt = seguroService.obtenerSeguroPorId(seguroId);

        if (userOpt.isPresent() && seguroOpt.isPresent()) {
            User user = userOpt.get();
            user.setSeguro(seguroOpt.get()); // Asigna el seguro al usuario
            User usuarioActualizado = usuarioServices.guardarUsuario(user);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/conSeguro")
    public ResponseEntity<List<User>> obtenerUsuariosConSeguro() {
        List<User> usuariosConSeguro = usuarioServices.obtenerUsuariosConSeguro();
        return ResponseEntity.ok(usuariosConSeguro);
    }
}

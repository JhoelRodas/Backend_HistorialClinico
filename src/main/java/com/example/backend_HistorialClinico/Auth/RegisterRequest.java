package com.example.backend_HistorialClinico.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    // String username;
    // String password;
    // String firstname;
    // String lastname;
    // String telefono;

    String ci;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    String fecha_nacimiento;
    String username;
    String password;
    String telefono;
    String genero;
}

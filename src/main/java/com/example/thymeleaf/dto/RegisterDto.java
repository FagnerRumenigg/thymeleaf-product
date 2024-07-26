package com.example.thymeleaf.dto;

import com.example.thymeleaf.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto{
		@NotEmpty(message = "Nome de usuário é obrigatório")
		@Size(min = 3, max = 20, message = "Nome de usuário deve ter entre 3 e 20 caracteres")
		String username;

		@NotEmpty(message = "Email é obrigatório")
		@Email(message = "Email deve ser válido")
		String email;

		@NotEmpty(message = "Senha é obrigatória")
		@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
		@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
				message = "Senha deve conter no mínimo 1 letra maiúscula, 1 caractere especial e 1 número")
		String password;

		UserRoleEnum role;
}

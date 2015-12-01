package com.github.lennonjesus.rebocar.me.entity

import org.apache.commons.lang3.builder.ToStringBuilder
import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.NotNull

abstract class Usuario {

    @NotBlank
    String login

    @NotBlank
    String nome

    @NotBlank
    String email

    @NotBlank
    String celular

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("login", login)
                .append("nome", nome)
                .append("email", email)
                .append("celular", celular)
                .toString();
    }
}

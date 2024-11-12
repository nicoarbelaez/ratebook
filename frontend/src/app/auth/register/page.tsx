"use client";

import { AuthForm, AuthButton, PasswordInput, LabeledInput } from "@/components/auth";
import { useState } from "react";

export default function RegisterPage() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [confirmEmail, setConfirmEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  return (
    <AuthForm
      title="Registro"
      description="Crea una nueva cuenta"
      footerMessage="¿Ya tienes una cuenta? "
      footerLinkText="Inicia sesión"
      footerLinkHref="./login">
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <LabeledInput
          id="firstName"
          placeholder="Tu nombre"
          label="Nombre"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <LabeledInput
          id="lastName"
          placeholder="Tu apellido"
          label="Apellido"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
      </div>
      <LabeledInput
        id="email"
        placeholder="tu@ejemplo.com"
        label="Correo electrónico"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <LabeledInput
        id="confirm-email"
        placeholder="tu@ejemplo.com"
        label="Confirmar correo electrónico"
        value={confirmEmail}
        onChange={(e) => setConfirmEmail(e.target.value)}
      />
      <PasswordInput
        id="password"
        placeholder="Tu contraseña"
        label="Contraseña"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <PasswordInput
        id="confirm-password"
        placeholder="Confirma tu contraseña"
        label="Confirmar contraseña"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
      />
      <AuthButton>Registrarse</AuthButton>
    </AuthForm>
  );
}

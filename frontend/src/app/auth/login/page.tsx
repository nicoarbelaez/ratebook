"use client";

import { AuthButton, AuthForm, LabeledInput, PasswordInput } from "@/components/auth";
import { Button } from "@/components/ui/button";
import { loginUser } from "@/lib/session";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { FaGoogle } from "react-icons/fa";

export default function LoginPage() {
  const router = useRouter();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    setError("");

    try {
      await loginUser({ email, password });
      router.push("/");
    } catch {
      setError("Credenciales incorrectas o error de inicio de sessíon.");
    }
  };

  return (
    <AuthForm
      title="Bienvenido"
      description="Inicia sesión para acceder a tu cuenta"
      footerMessage="¿No tienes una cuenta? "
      footerLinkText="Regístrate"
      footerLinkHref="./register"
      outsideContent={
        <>
          <div className="text-center text-gray-500 my-4">o</div>
          <Button
            variant="outline"
            className="w-full border-gray-300 text-gray-700 hover:bg-gray-100">
            <FaGoogle className="mr-2 h-5 w-5" /> Iniciar sesión con Google
          </Button>
        </>
      }
      onSubmit={handleLogin}>
      <LabeledInput
        id="email"
        placeholder="tu@ejemplo.com"
        label="Correo electrónico"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <PasswordInput
        id="password"
        placeholder="Tu contraseña"
        label="Contraseña"
        showForgot
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      {error && <p className="text-red-500 text-sm">{error}</p>}
      <AuthButton>Iniciar sesión</AuthButton>
    </AuthForm>
  );
}

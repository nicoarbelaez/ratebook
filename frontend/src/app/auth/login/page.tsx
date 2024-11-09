import AuthButton from "@/components/auth/AuthButton";
import AuthForm from "@/components/auth/AuthForm";
import LabeledInput from "@/components/auth/LabeledInput";
import PasswordInput from "@/components/auth/PasswordInput";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { FaGoogle } from "react-icons/fa";

export default function LoginPage() {
  return (
    <div className="min-h-screen w-full bg-black text-white flex items-center justify-center p-4">
      <AuthForm
        title="Bienvenido"
        description="Inicia sesión para acceder a tu cuenta"
        footerMessage={
          <p className="text-center mt-4 text-gray-400">
            {"¿No tienes una cuenta? "}
            <Link href="./register" className="text-white hover:underline">
              Regístrate
            </Link>
          </p>
        }>
        <LabeledInput id="email" placeholder="tu@ejemplo.com">
          Correo electrónico
        </LabeledInput>
        <PasswordInput id="password" placeholder="Tu contraseña" showForgot>
          Contraseña
        </PasswordInput>
        <AuthButton>Iniciar sesión</AuthButton>
      <>
        <div className="text-center text-gray-500 my-4">o</div>
        <Button
          variant="outline"
          className="w-full border-gray-300 text-gray-700 hover:bg-gray-100">
          <FaGoogle className="mr-2 h-5 w-5" /> Iniciar sesión con Google
        </Button>
      </>
      </AuthForm>
    </div>
  );
}

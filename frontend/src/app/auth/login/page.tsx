import AuthButton from "@/components/auth/AuthButton";
import AuthForm from "@/components/auth/AuthForm";
import LabeledInput from "@/components/auth/LabeledInput";
import PasswordInput from "@/components/auth/PasswordInput";
import { Button } from "@/components/ui/button";
import { FaGoogle } from "react-icons/fa";

export default function LoginPage() {
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
      }>
      <LabeledInput id="email" placeholder="tu@ejemplo.com">
        Correo electrónico
      </LabeledInput>
      <PasswordInput id="password" placeholder="Tu contraseña" showForgot>
        Contraseña
      </PasswordInput>
      <AuthButton>Iniciar sesión</AuthButton>
    </AuthForm>
  );
}

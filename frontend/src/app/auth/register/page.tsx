import AuthButton from "@/components/auth/AuthButton";
import AuthForm from "@/components/auth/AuthForm";
import LabeledInput from "@/components/auth/LabeledInput";
import PasswordInput from "@/components/auth/PasswordInput";
import Link from "next/link";

export default function RegisterPage() {
  return (
    <div className="min-h-screen w-full bg-black text-white flex items-center justify-center p-4">
      <AuthForm
        title="Registro"
        description="Crea una nueva cuenta"
        footerMessage={
          <p className="text-center mt-4 text-gray-400">
            {"¿Ya tienes una cuenta? "}
            <Link href="./login" className="text-white hover:underline">
              Inicia sesión
            </Link>
          </p>
        }>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
          <LabeledInput id="firstName" placeholder="Tu nombre">
            Nombre
          </LabeledInput>
          <LabeledInput id="lastName" placeholder="Tu apellido">
            Apellido
          </LabeledInput>
        </div>
        <LabeledInput id="email" placeholder="tu@ejemplo.com">
          Correo electrónico
        </LabeledInput>
        <LabeledInput id="email" placeholder="tu@ejemplo.com">
          Confirmar correo electrónico
        </LabeledInput>
        <PasswordInput id="password" placeholder="Tu contraseña">
          Contraseña
        </PasswordInput>
        <PasswordInput id="password" placeholder="Confirma tu contraseña">
          Confirmar contraseña
        </PasswordInput>
        <AuthButton>Registrarse</AuthButton>
      </AuthForm>
    </div>
  );
}

import AuthButton from "@/components/auth/AuthButton";
import AuthForm from "@/components/auth/AuthForm";
import LabeledInput from "@/components/auth/LabeledInput";
import PasswordInput from "@/components/auth/PasswordInput";

export default function RegisterPage() {
  return (
    <AuthForm
      title="Registro"
      description="Crea una nueva cuenta"
      footerMessage="¿Ya tienes una cuenta? "
      footerLinkText="Inicia sesión"
      footerLinkHref="./login">
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
      <LabeledInput id="Confirmemail" placeholder="tu@ejemplo.com">
        Confirmar correo electrónico
      </LabeledInput>
      <PasswordInput id="password" placeholder="Tu contraseña">
        Contraseña
      </PasswordInput>
      <PasswordInput id="Confirmpassword" placeholder="Confirma tu contraseña">
        Confirmar contraseña
      </PasswordInput>
      <AuthButton>Registrarse</AuthButton>
    </AuthForm>
  );
}

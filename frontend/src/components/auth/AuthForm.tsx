// components/auth/AuthForm.tsx
import { ReactNode } from "react";

interface AuthFormProps {
  title: string;
  description: string;
  children: ReactNode;
  footerMessage: ReactNode;
}

export default function AuthForm({
  title,
  description,
  children,
  footerMessage,
}: AuthFormProps) {
  return (
    <div className="min-h-screen w-full bg-black text-white flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <h1 className="text-3xl font-bold mb-2">{title}</h1>
        <p className="text-gray-400 mb-8">{description}</p>
        
        <div className="bg-white text-black p-8 rounded-lg shadow-lg">
          <form noValidate className="space-y-4">
            {children}
          </form>
        </div>

        <p className="text-center mt-4 text-gray-400">{footerMessage}</p>
      </div>
    </div>
  );
}

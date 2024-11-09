'use client'

import { ReactNode, useState } from "react";
import InputAuth from "@/components/auth/LabeledInput";
import { Eye, EyeOff } from "lucide-react";

interface PasswordInputProps {
  id: string;
  placeholder: string;
  children?: ReactNode;
  showForgot?: boolean;
}

export default function PasswordInput({
  id,
  placeholder,
  children,
  showForgot = false,
}: PasswordInputProps) {
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => setShowPassword(!showPassword);

  return (
    <div>
      <InputAuth
        id={id}
        placeholder={placeholder}
        type={showPassword ? "text" : "password"}
        rightElement={
          <button
            type="button"
            onClick={togglePasswordVisibility}
            className="text-gray-400 hover:text-gray-600">
            {showPassword ? <EyeOff className="h-5 w-5" /> : <Eye className="h-5 w-5" />}
          </button>
        }>
        <div className="flex justify-between items-center">
          {children}
          {showForgot && (
            <a href="#" className="text-sm text-gray-500 hover:text-black">
              ¿Olvidaste?
            </a>
          )}
        </div>
      </InputAuth>
    </div>
  );
}

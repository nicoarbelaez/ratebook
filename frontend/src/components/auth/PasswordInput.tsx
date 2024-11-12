import { ReactNode, useState } from "react";
import LabeledInput from "@/components/auth/LabeledInput";
import { Eye, EyeOff } from "lucide-react";
import { InputProps } from "@/components/ui/input";

interface PasswordInputProps extends InputProps {
  label: ReactNode;
  showForgot?: boolean;
}

export default function PasswordInput({
  label,
  showForgot,
  className,
  ...inputProps
}: PasswordInputProps) {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <LabeledInput
      label={
        <div className="flex justify-between items-center">
          {label}
          {showForgot && (
            <a href="#" className="text-sm text-gray-500 hover:text-black">
              ¿Olvidaste?
            </a>
          )}
        </div>
      }
      className={className}
      type={showPassword ? "text" : "password"}
      rightElement={
        <button
          type="button"
          onClick={() => setShowPassword((prev) => !prev)}
          className="text-gray-400 hover:text-gray-600"
        >
          {showPassword ? <EyeOff className="h-5 w-5" /> : <Eye className="h-5 w-5" />}
        </button>
      }
      {...inputProps}
    />
  );
}

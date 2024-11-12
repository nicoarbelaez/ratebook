import { Input, InputProps } from "@/components/ui/input";
import { Label } from "@radix-ui/react-label";
import { ReactNode } from "react";

interface LabeledInputProps extends InputProps {
  label?: ReactNode;
  rightElement?: ReactNode;
}

export default function LabeledInput({
  label,
  rightElement,
  className,
  ...inputProps
}: LabeledInputProps) {
  return (
    <div>
      {label && (
        <Label htmlFor={inputProps.id} className="text-gray-700">
          {label}
        </Label>
      )}
      <div className="relative">
        <Input className={`mt-1 pr-10 ${className}`} {...inputProps} />
        {rightElement && (
          <div className="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-400 hover:text-gray-600">
            {rightElement}
          </div>
        )}
      </div>
    </div>
  );
}

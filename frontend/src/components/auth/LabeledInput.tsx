import { Input } from "@/components/ui/input";
import { Label } from "@radix-ui/react-label";
import { ReactNode } from "react";

interface LabeledInputProps {
  id: string;
  placeholder: string;
  type?: string;
  children?: ReactNode;
  rightElement?: ReactNode;
}

export default function LabeledInput({ id, placeholder, type = "text", children, rightElement }: LabeledInputProps) {
  return (
    <div>
      <Label htmlFor={id} className="text-gray-700">
        {children}
      </Label>
      <div className="relative">
        <Input id={id} name={id} type={type} placeholder={placeholder} className="mt-1 pr-10" required />
        {rightElement && (
          <div className="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-400 hover:text-gray-600">
            {rightElement}
          </div>
        )}
      </div>
      {false && <p className="text-red-500 text-sm mt-1">Incompleto</p>}
    </div>
  );
}

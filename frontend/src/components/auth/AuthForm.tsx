import Link from "next/link";
import { ReactNode } from "react";
import { Button } from "../ui/button";
import { ArrowLeft } from "lucide-react";

interface AuthFormProps {
  title: string;
  description: string;
  children: ReactNode;
  footerMessage?: string;
  footerLinkText?: string;
  footerLinkHref?: string;
  outsideContent?: ReactNode;
}

export default function AuthForm({
  title,
  description,
  children,
  footerMessage = "",
  footerLinkText = "",
  footerLinkHref = "#",
  outsideContent,
}: AuthFormProps) {
  return (
    <div className="w-full max-w-md">
      <h1 className="text-3xl font-bold mb-2">{title}</h1>
      <p className="text-gray-400 mb-8">{description}</p>
      <Button variant="default" className="mb-6 text-white border-white hover:bg-gray-800" asChild>
        <Link href="/">
          <ArrowLeft className="mr-2 h-4 w-4" />
          Volver al inicio
        </Link>
      </Button>

      <div className="bg-white text-black p-8 rounded-lg shadow-lg">
        <form noValidate className="space-y-4">
          {children}
        </form>
        {outsideContent}
      </div>

      <p className="text-center mt-4 text-gray-400">
        {footerMessage}
        <Link href={footerLinkHref} className="text-white hover:underline">
          {footerLinkText}
        </Link>
      </p>
    </div>
  );
}

import React from "react";

export default function AuthLayout({ children }: { children: React.ReactNode }) {
  return (
    <div className="min-h-screen w-full bg-black text-white flex items-center justify-center p-4">
      {children}
    </div>
  );
}

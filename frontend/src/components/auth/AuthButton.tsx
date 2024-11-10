import React from "react";

export default function AuthButtom({ children }: { children: React.ReactNode }) {
  return (
    <button type="submit" className="w-full bg-black text-white p-2 rounded hover:bg-gray-800">
      {children}
    </button>
  );
}

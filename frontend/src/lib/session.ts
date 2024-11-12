import Cookies from "js-cookie";
import { API_HOST, NODE_ENV } from "./config";

const TOKEN_COOKIE_NAME = "auth_cookie";

interface LoginRequest {
  email: string;
  password: string;
}

interface RegisterRequest extends LoginRequest {
  firstName: string;
  lastName: string;
}

export async function loginUser({ email, password }: LoginRequest) {
  fetchData("/api/auth/login", { email, password});
}

export function registerUser({ email, password, firstName, lastName }: RegisterRequest) {
  fetchData("/api/auth/login", { email, password, firstName, lastName });
}

export function logoutUser() {
  Cookies.remove(TOKEN_COOKIE_NAME, { path: "/" });
}

async function fetchData(endpoint: string, payload: LoginRequest | RegisterRequest) {
  try {
    const response = await fetch(`${API_HOST}${endpoint}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || "[POST] Error en la solicitud");
    }

    const result = await response.json();

    const { token } = result;

    setCookie(token);
  } catch (error) {
    console.error("[POST] Error durante la solicitud:", error);
  }
}

function setCookie(token: string): void {
  Cookies.set(TOKEN_COOKIE_NAME, token, {
    httpOnly: true,
    secure: NODE_ENV === "production",
    sameSite: "strict",
    expires: 1,
    path: "/",
  });
}

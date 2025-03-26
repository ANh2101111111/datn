import { jwtDecode } from "jwt-decode";


interface DecodedToken {
  sub: string; 
  roles: string[];
  iat: number;
  exp: number;
}

export const getUserFromToken = (): string | null => {
  const token = localStorage.getItem("token");
  if (!token) return null;

  try {
    const decoded: DecodedToken = jwtDecode(token);
    return decoded.sub; 
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
};

import { useEffect, useState } from "react";
import { getUserFromToken } from "./decodeToken";

export const useUser = () => {
  const [username, setUsername] = useState<string | null>(getUserFromToken());

  useEffect(() => {
    const handleStorageChange = () => {
      setUsername(getUserFromToken()); 
    };

    window.addEventListener("storage", handleStorageChange);
    return () => window.removeEventListener("storage", handleStorageChange);
  }, []);

  return username;
};

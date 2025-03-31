"use client";

import { LOCAL_STORAGE } from "@/lib/const";
import { createContext, useContext, useState } from "react";

const AuthContext = createContext<{
  userId: number | null;
  loginSuccess?: (data: { id: number; token: string }) => void;
  logout?: () => void;
  isLogged: boolean;
}>({ userId: null, isLogged: false });

import { ReactNode } from "react";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const initialUserId =
    typeof window !== "undefined" && localStorage.getItem(LOCAL_STORAGE.ID)
      ? Number(localStorage.getItem(LOCAL_STORAGE.ID))
      : null;

  const [userId, setUserId] = useState<number | null>(initialUserId);

  const loginSuccess = ({ id, token }: { id: number; token: string }) => {
    setUserId(id);
    localStorage.setItem(LOCAL_STORAGE.ID, String(id));
    localStorage.setItem(LOCAL_STORAGE.TOKEN, token);
  };

  const logout = () => {
    setUserId(null);
    localStorage.clear();
  };

  return (
    <AuthContext.Provider
      value={{ userId, loginSuccess, logout, isLogged: !!userId }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

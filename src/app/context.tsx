"use client";

import { LOCAL_STORAGE } from "@/lib/const";
import { createContext, useContext, useState } from "react";

const AuthContext = createContext<{
  userId: number | null;
  loginSuccess?: (id: number) => void;
  logout?: () => void;
}>({ userId: null });

import { ReactNode } from "react";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [userId, setUserId] = useState<number | null>(null);

  const loginSuccess = (id: number) => {
    setUserId(id);
    localStorage.setItem(LOCAL_STORAGE.ID, String(id));
  };

  const logout = () => {
    setUserId(null);
    localStorage.clear();
  };

  return (
    <AuthContext.Provider value={{ userId, loginSuccess, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

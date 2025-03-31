/* eslint-disable @typescript-eslint/no-explicit-any */
import type {
  AxiosError,
  AxiosResponse,
  InternalAxiosRequestConfig,
} from "axios";
import axios from "axios";

import { env, LOCAL_STORAGE } from "@/lib/const";
import { Route } from "@/types/route";
import toast from "react-hot-toast";

export const request = axios.create({
  baseURL: env.API_URL,
});

const handleSuccess = (res: AxiosResponse) => {
  return res;
};

const handleError = async (error: AxiosError) => {
  const data = error?.response?.data as any;

  if (error.status === 401) {
    toast.error("Unauthorized, please login again");

    setTimeout(() => {
      localStorage.clear();
      window.location.href = Route.LOGIN;
    }, 1000);
  }

  return Promise.reject(data?.meta || data || error);
};

request.interceptors.response.use(handleSuccess, handleError);

request.interceptors.request.use(
  async (config: InternalAxiosRequestConfig) => {
    // config.headers["ngrok-skip-browser-warning"] = "true";
    const token =
      typeof localStorage !== "undefined"
        ? localStorage.getItem(LOCAL_STORAGE.TOKEN)
        : null;
    console.log(token, "token");
    config.headers["ngrok-skip-browser-warning"] = "true";

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

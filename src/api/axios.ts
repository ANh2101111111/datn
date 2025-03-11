/* eslint-disable @typescript-eslint/no-explicit-any */
import type { AxiosResponse, InternalAxiosRequestConfig } from "axios";
import axios from "axios";

import { env } from "@/lib/const";

export const request = axios.create({
  baseURL: env.API_URL,
});

const handleSuccess = (res: AxiosResponse) => {
  return res;
};

const handleError = async (error: any) => {
  const data = error?.response?.data as any;
  return Promise.reject(data?.meta || data || error);
};

request.interceptors.response.use(handleSuccess, handleError);

request.interceptors.request.use(
  async (config: InternalAxiosRequestConfig) => {
    config.headers["ngrok-skip-browser-warning"] = "true";

    return config;
  },
  (error) => Promise.reject(error)
);

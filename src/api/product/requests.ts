import { request } from "../axios";
import { IProductDetail } from "./types";

export const getProducts = async (): Promise<IProductDetail[]> => {
  const { data } = await request({
    url: "/user/products/all",
    method: "GET",
  });

  return data;
};

export const getProductDetail = async (id: number): Promise<IProductDetail | null> => {
  const { data } = await request({
    url: `/user/products/${id}`,
    method: "GET",
  });

  return data ? data : null;
};




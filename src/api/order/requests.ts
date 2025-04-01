import { request } from "../axios";
import { ICreateOrder } from "./types";

export const getOrders = async (userId: string) => {
  const { data } = await request({
    url: `/api/user/orders/${userId}`,
    method: "GET",
  });

  return data;
};

export const createOrder = async (payload: ICreateOrder) => {
  const { data } = await request({
    url: `/api/user/orders`,
    method: "POST",
    data: payload,
  });

  return data;
};

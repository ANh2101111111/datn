import { request } from "../axios";
import { ICreateOrder } from "./types";

export const createOrder = async (payload: ICreateOrder) => {
  const { data } = await request({
    url: `/api/user/orders`,
    method: "POST",
    data: payload,
  });

  return data;
};

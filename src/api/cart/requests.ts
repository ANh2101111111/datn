import { request } from "../axios";
import { IAddCart, ICart } from "./types";

export const getCarts = async (userId: number): Promise<ICart> => {
  const { data } = await request({
    url: `/api/user/cart/${userId}`,
    method: "GET",
  });

  return data;
};

export const addCart = async ({
  userId,
  payload,
}: {
  userId: number;
  payload: IAddCart[];
}) => {
  const { data } = await request({
    url: `/api/user/cart/add/${userId}`,
    method: "POST",
    data: {
      cartDetails: payload,
    },
  });

  return data;
};

export const removeCart = async ({ userId }: { userId: number }) => {
  const { data } = await request({
    url: `/api/user/cart/${userId}/clear`,
    method: "DELETE",
  });

  return data;
};

export const updateQuantity = async ({
  userId,
  data,
}: {
  userId: number;
  data: IAddCart;
}) => {
  const { data: res } = await request({
    url: `/api/user/cart/update/${userId}`,
    method: "PUT",
    data,
  });

  return res;
};

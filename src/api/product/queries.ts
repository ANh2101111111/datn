import { createQuery } from "react-query-kit";
import {getProductDetail, getProducts } from "./requests";
import { IProductDetail } from "./types";

export const useGetProducts = createQuery<IProductDetail[]>({
  primaryKey: "/user/products/all",
  queryFn: getProducts,
});

export const useGetProductDetail = createQuery<IProductDetail | null, number>({
  primaryKey: "/user/product/:id",
  queryFn: ({ queryKey: [, id] }) => getProductDetail(id),
});



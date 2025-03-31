import { createQuery } from "react-query-kit";
import { ICart } from "./types";
import { getCarts } from "./requests";

export const useGetCarts = createQuery<ICart, number>({
  primaryKey: "/api/user/cart/:id",
  queryFn: ({ queryKey: [, id] }) => getCarts(id),
});

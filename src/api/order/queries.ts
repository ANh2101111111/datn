import { createQuery } from "react-query-kit";
import { IOrder } from "./types";
import { getOrders } from "./requests";

export const useGetOrders = createQuery<IOrder[], string>({
  primaryKey: "ORDERS",
  queryFn: ({ queryKey: [, id] }) => getOrders(id),
});

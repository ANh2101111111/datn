import { createQuery } from "react-query-kit";
import { getCategories } from "./requests";
import { ICategory } from "./type";

export const useGetCategories = createQuery<ICategory[]>({
  primaryKey: "/user/categories/all",
  queryFn: getCategories,
});
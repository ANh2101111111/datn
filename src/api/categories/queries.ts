import { createQuery } from "react-query-kit";
import { getCategories, getCategoryDetail } from "./requests";
import { ICategory } from "./type";

export const useGetCategories = createQuery<ICategory[]>({
  primaryKey: "/user/categories/all",
  queryFn: getCategories,
});

export const useGetCategoryDetail = createQuery<ICategory | null, number>({
  primaryKey: "/user/categories/:id",
  queryFn: ({ queryKey: [, id] }) => getCategoryDetail(id),
});

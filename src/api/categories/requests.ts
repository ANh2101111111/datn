import { request } from "../axios";
import { ICategory } from "./type";

export const getCategories = async (): Promise<ICategory[]> => {
  const { data } = await request({
    url: "/user/categories/all",
    method: "GET",
  });

  return data.map(({ categoryId, name }: ICategory) => ({ categoryId, name }));
};
export const getCategoryDetail = async (
  id: number
): Promise<ICategory | null> => {
  const { data } = await request({
    url: `/user/categories/${id}`,
    method: "GET",
  });
  return data ? data : null;
};

import { createQuery } from "react-query-kit";
import { getReviews } from "./requests";
import { IReview } from "./type";

export const useGetReviews = createQuery<IReview[]>({
  primaryKey: "/user/reviews",
  queryFn: getReviews,
});

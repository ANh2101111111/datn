import { createQuery } from "react-query-kit";
import { IUserProfileResponse } from "./types";
import { getUserInfo } from "./requests";

export const useGetUserInfo = createQuery<IUserProfileResponse | null, number>({
  primaryKey: "api/user/profile/:id",
  queryFn: ({ queryKey: [, id] }) => getUserInfo({id}),
});




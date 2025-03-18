import { request } from "../axios";
import { IUSerLoginRequest, IUserLoginResponse } from "./types";

export const login = async (params: IUSerLoginRequest): Promise<IUserLoginResponse> => {
  const { data } = await request({
    url: "api/user/login",
    method: "POST",
    data: params,
  });

  return data;
};

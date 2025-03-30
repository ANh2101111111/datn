import { request } from "../axios";
import {
  IUSerLoginRequest,
  IUserLoginResponse,
  IUserProfileRequest,
  IUserProfileResponse,
  IUserRegisterRequest,
  IUserRegisterResponse,
} from "./types";

export const login = async (
  params: IUSerLoginRequest
): Promise<IUserLoginResponse> => {
  const { data } = await request({
    url: "api/user/login",
    method: "POST",
    data: params,
  });

  return data;
};

export const register = async (
  params: IUserRegisterRequest
): Promise<IUserRegisterResponse> => {
  const { data } = await request({
    url: "api/user/register",
    method: "POST",
    data: params,
  });

  return data;
};

export const getUserInfo = async (
  params: IUserProfileRequest
): Promise<IUserProfileResponse> => {
  const { data } = await request({
    url: `api/user/profile/${params.id}`,
    method: "GET",
  });

  return data;
};

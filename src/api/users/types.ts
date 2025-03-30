export interface IUSerLoginRequest {
  username: string;
  password: string;
}

export interface IUserLoginResponse {
  token: string;
  userId: number;
}

// dang ki
export interface IUserRegisterRequest {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

export interface IUserRegisterResponse {
  message: string;
}

export interface IUserProfileRequest {
  id: number;
}

export interface IUserProfileResponse {
  id: number;
  phone: string;
  avatar: string;
  address: string;
  fullName: string;
  username: string;
}

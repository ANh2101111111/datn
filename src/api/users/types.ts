export interface IUSerLoginRequest {
  username: string;
  password: string;
}

export interface IUserLoginResponse {
  token: string;
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

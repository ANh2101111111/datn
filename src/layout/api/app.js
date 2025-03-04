import axios from "axios";
const API_URL = "http://127.0.0.1:8000/api/user"; 


// Tạo một instance Axios
const api = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// API Đăng nhập
export const loginUser = async (username, password) => {
  try {
    const response = await api.post("/login", { username, password });
    return response.data;
  } catch (error) {
    throw error.response?.data?.message || "Login failed";
  }
};

// API Đăng ký
export const registerUser = async (userData) => {
  try {
    const response = await api.post("/register", userData);
    return response.data;
  } catch (error) {
    throw error.response?.data?.message || "Registration failed";
  }
};

// API Reset Password
export const requestPasswordReset = async (email) => {
  try {
    const response = await api.post("/reset-password/request", { email });
    return response.data;
  } catch (error) {
    throw error.response?.data?.message || "Password reset request failed";
  }
};

export default api;

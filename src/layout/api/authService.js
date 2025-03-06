import userApi from "@/layout/api/userApi";
import adminApi from "@/layout/api/adminApi";

// Xử lý lỗi chung
const handleApiError = (error) => {
  return error.response?.data?.message || "Có lỗi xảy ra, vui lòng thử lại!";
};

export const loginUser = async (username, password) => {
  try {
    const response = await userApi.post("/login", { username, password });

    // Lưu token vào localStorage/sessionStorage
    localStorage.setItem("token", response.data.token);

    return response.data;
  } catch (error) {
    throw handleApiError(error);
  }
};


// ✅ API Đăng nhập Admin
export const loginAdmin = async (username, password) => {
  try {
    const response = await adminApi.post("/login", { username, password });
    return response.data; // { token: "xxx", role: "ADMIN" }
  } catch (error) {
    throw handleApiError(error);
  }
};
export const registerUser = async (username, email, password, confirmPassword) => {
  try {
    const response = await userApi.post("/register",
     { username, email, password, confirmPassword });
    return response.data;
  } catch (error) {
    console.error("Lỗi từ API:", error.response?.data || error.message);
    throw new Error(error.response?.data?.message || "Registration failed");
  }
};



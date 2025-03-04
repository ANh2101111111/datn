import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/user";
const userApi = {
  post: async (url, data) => {
    return await axios.post(`${API_BASE_URL}${url}`, data);
  },
};

export default userApi;

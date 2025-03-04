import axios from "axios";

const adminApi = axios.create({
  baseURL: "http://localhost:8080/api/admin",
  headers: {
    "Content-Type": "application/json",
  },
});

export default adminApi;

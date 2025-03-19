import { request } from "../axios";
import { IReview } from "./type";

export const getReviews = async (): Promise<IReview[]> => {
  try {
    const { data } = await request({
      url: "/api/user/reviews",
      method: "GET",
    });
    console.log("API response:", data); // Kiểm tra phản hồi từ API.
    return data; // Trả về danh sách review.
  } catch (error) {
    console.error("Error fetching reviews:", error);
    throw error;
  }
};

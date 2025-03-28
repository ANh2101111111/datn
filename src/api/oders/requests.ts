// import { request } from "../axios";
// import { Order } from "./types";

// // API Lấy đơn hàng theo userId
// export const getUserOrders = async (userId: number): Promise<Order[]> => {
//   const { data } = await request({
//     url: `/api/user/orders/${userId}`,
//     method: "GET",
//   });
//   return data;
// };



// // API Xóa sản phẩm khỏi giỏ hàng
// export const removeFromCart = async (orderDetailId: number) => {
//   const { data } = await request({
//     url: `/api/user/order-details/${orderDetailId}`,
//     method: "DELETE",
//   });
//   return data;
// };

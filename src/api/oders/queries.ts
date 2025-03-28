// import { createQuery, createMutation } from "react-query-kit";
// import { getUserOrders, removeFromCart } from "./requests";
// import { Order } from "./types";

// // Hook lấy danh sách đơn hàng theo userId
// export const useGetUserOrders = createQuery<Order[], number>({
//   primaryKey: "/api/user/orders",
//   queryFn: ({ queryKey: [, userId] }) => getUserOrders(userId),
// });

// // Hook để gọi API xóa sản phẩm khỏi giỏ hàng
// export const useRemoveFromCart = createMutation({
//   mutationFn: (orderDetailId: number) => removeFromCart(orderDetailId),
// });

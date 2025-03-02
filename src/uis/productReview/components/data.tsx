export interface Review {
  id: number;
  username: string;
  rating: number;
  comment: string;
}

export const initialReviews: Review[] = [
  {
    id: 1,
    username: "Nguyễn Văn A",
    rating: 5,
    comment: "Sản phẩm rất tốt, chất lượng tuyệt vời!",
  },
  {
    id: 2,
    username: "Trần Thị B",
    rating: 4,
    comment: "Hàng đúng mô tả, nhưng giao hàng hơi chậm.",
  },
];

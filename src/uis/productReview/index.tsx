"use client";

import React from "react";
import ReviewForm from "./components/ReviewForm";
import ReviewList from "./components/ReviewList";
import { useGetReviews } from "@/api/reviews";

const ProductReview: React.FC = () => {
  const { data: reviews, isLoading, error } = useGetReviews(); // Lấy dữ liệu từ hook.

  if (isLoading) {
    return <p>Đang tải đánh giá...</p>;
  }

  if (error) {
    return <p>Có lỗi xảy ra khi tải đánh giá.</p>;
  }

  return (
    <div className="max-w-2xl mx-auto p-6">
      <ReviewForm onSubmit={(newReview) => console.log(newReview)} />
      <ReviewList reviews={reviews || []} /> {/* Truyền dữ liệu vào ReviewList */}
    </div>
  );
};

export default ProductReview;

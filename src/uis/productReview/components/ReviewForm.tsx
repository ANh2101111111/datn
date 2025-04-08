import React, { useEffect, useState } from "react";
import RatingStars from "./RatingStars";
import Button from "@/uis/common/button";
import Input from "@/uis/common/input";
import { useGetUserInfo } from "@/hooks/useGetUserInfo";
import { useMutation } from "@tanstack/react-query";
import { useRouter, useSearchParams } from "next/navigation";
import { reviewProduct } from "@/api/order";
import { useAuth } from "@/app/context";

const ReviewForm: React.FC = () => {
  const { data } = useGetUserInfo();
  const { userId } = useAuth();

  const router = useRouter();

  const searchParams = useSearchParams();
  const productIds = searchParams.get("productIds");

  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState("");
  const [userName, setUserName] = useState("");

  useEffect(() => {
    if (data) {
      setUserName(data.username);
    }
  }, [data]);

  const reviewMutation = useMutation({
    mutationFn: async () => {
      const productIdsArray = productIds?.split(",").map((id) => Number(id));
      const reviewPromises = productIdsArray?.map((productId) =>
        reviewProduct({
          bicycleId: productId,
          userId: Number(userId),
          rating,
          comment,
        })
      );

      if (reviewPromises) {
        await Promise.all(reviewPromises);
        return true;
      }

      return false;
    },
    onSuccess: () => {
      alert("Đánh giá thành công!");

      router.back();
    },
    onError: (error) => {
      console.error("Error submitting review:", error);
      alert("Đã xảy ra lỗi khi gửi đánh giá.");
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (rating === 0 || comment.trim() === "") {
      alert("Vui lòng chọn số sao và nhập đánh giá.");
      return;
    }

    reviewMutation.mutate();
  };

  return (
    <div className="p-6 bg-white shadow-md rounded-lg">
      <h2 className="text-lg font-bold mb-4">Để lại đánh giá</h2>

      {/* Hiển thị username */}
      <Input
        type="text"
        className="w-full p-2 border rounded-md mb-3"
        placeholder="Nhập tên của bạn..."
        value={userName}
        readOnly
      />

      <RatingStars rating={rating} onRatingChange={setRating} />

      <textarea
        className="w-full p-3 border border-gray-300 bg-blue rounded-md mt-4 
              focus:ring-1 focus:ring-green-400 outline-none transition-all duration-200"
        placeholder="Nhập bình luận..."
        value={comment}
        onChange={(e) => setComment(e.target.value)}
      />

      <Button
        variant="primary"
        className="mt-4 text-white px-4 py-2 rounded-md"
        onClick={handleSubmit}
      >
        Gửi đánh giá
      </Button>
    </div>
  );
};

export default ReviewForm;

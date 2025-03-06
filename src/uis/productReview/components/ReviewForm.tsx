import React, { useState } from "react";
import RatingStars from "./RatingStars";
import { Review } from "./data";
import Button from "@/uis/common/button";
import Input from "@/uis/common/input";

interface ReviewFormProps {
  onSubmit: (review: Review) => void;
}

const ReviewForm: React.FC<ReviewFormProps> = ({ onSubmit }) => {
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState("");
  const [username, setUsername] = useState("Người dùng ẩn danh");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (rating === 0 || comment.trim() === "") {
      alert("Vui lòng chọn số sao và nhập đánh giá.");
      return;
    }

    const newReview: Review = {
      id: Date.now(),
      username,
      rating,
      comment,
    };

    onSubmit(newReview);
    setRating(0);
    setComment("");
  };

  return (
    <div className="p-6 bg-white shadow-md rounded-lg">
      <h2 className="text-lg font-bold mb-4">Để lại đánh giá</h2>

      {/* Hiển thị username */}
      <Input
        type="text"
        className="w-full p-2 border rounded-md mb-3"
        placeholder="Nhập tên của bạn..."
        value={username}
        onChange={(e) => setUsername(e.target.value)}
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

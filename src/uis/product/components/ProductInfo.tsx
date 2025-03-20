/* eslint-disable @next/next/no-img-element */
"use client";
import { IProductDetail } from "@/api/product";
import IconStar from "@/layout/assets/icons/IconStar";
import Button from "@/uis/common/button";
import { FC, useState, useEffect } from "react";
import { useGetReviews } from "@/api/reviews";

interface IProductInfoProps {
  data: IProductDetail;
}

const ProductInfo: FC<IProductInfoProps> = ({ data }) => {
  const [quantity, setQuantity] = useState(1);
  const [activeTab, setActiveTab] = useState<"description" | "review">(
    "description"
  );

  // Gọi API khi chuyển sang tab "Reviews"
  const { data: reviews, refetch, isLoading, isError } = useGetReviews();

  useEffect(() => {
    if (activeTab === "review") {
      refetch(); // Fetch dữ liệu khi chuyển sang tab review
    }
  }, [activeTab, refetch]);

  const handleQuantityChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value);
    setQuantity(value >= 1 ? value : 1);
  };

  const renderStars = (rating: number) => {
    return [...Array(5)].map((_, index) => (
      <IconStar
        key={index}
        className={`w-5 h-5 ${
          index < rating ? "text-brand-secondary" : "text-icon-star"
        }`}
        fill={index < rating ? "#FDC040" : "#CDCDCD"}
      />
    ));
  };

  return (
    <div className="max-w-[1100px] w-full mx-auto mt-12 mb-10 px-6">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-10">
        {/* Hình ảnh sản phẩm */}
        <div className="h-[500px] md:h-[600px] overflow-hidden rounded-lg shadow-md">
          <img
            src={data.image}
            alt={data.name}
            className="w-full h-full object-cover"
          />
        </div>

        {/* Thông tin sản phẩm */}
        <div className="flex flex-col justify-between">
          <div>
            <div className="bg-brand-primary text-white text-sm font-bold uppercase py-1 px-3 rounded-md mb-4 w-fit">
              {data.type}
            </div>
            <h2 className="text-3xl font-bold text-gray-800 mb-4">
              {data.name}
            </h2>

            {/* Đánh giá sao */}
            <div className="flex items-center space-x-1 mb-4">
              {renderStars(data.rating)}
            </div>

            {/* Giá sản phẩm */}
            <div className="flex items-center gap-4 mb-4">
              <p className="text-3xl text-brand-primary font-bold">
                ${data.discountedPrice}
              </p>
              <p className="text-xl text-gray-400 line-through">
                ${data.originalPrice}
              </p>
            </div>

            {/* Mô tả sản phẩm */}
            <div className="text-gray-600 text-base leading-relaxed h-[180px] overflow-hidden">
              {data.description}
            </div>
          </div>

          {/* Input số lượng + Nút thêm giỏ hàng */}
          <div className="flex items-center mt-6">
            <input
              type="number"
              className="w-[60px] text-center border border-gray-300 rounded-md px-2 py-1 mr-4"
              value={quantity}
              onChange={handleQuantityChange}
            />
            <Button
              size="medium"
              variant="primary"
              className="text-white px-6 py-2 rounded-md shadow-md transition hover:bg-brand-secondary"
            >
              Add To Cart
            </Button>
          </div>
        </div>
      </div>

      {/* Tabs chuyển đổi giữa mô tả & đánh giá */}
      <div className="border border-gray-200 p-8 rounded-2xl mt-12 shadow-md bg-white">
        <div className="flex border-b mb-6">
          <button
            className={`px-6 py-3 text-lg font-semibold transition ${
              activeTab === "description"
                ? "text-brand-primary border-b-2 border-brand-primary"
                : "text-gray-500 hover:text-gray-800"
            }`}
            onClick={() => setActiveTab("description")}
          >
            Description
          </button>
          <button
            className={`px-6 py-3 text-lg font-semibold transition ${
              activeTab === "review"
                ? "text-brand-primary border-b-2 border-brand-primary"
                : "text-gray-500 hover:text-gray-800"
            }`}
            onClick={() => setActiveTab("review")}
          >
            Reviews
          </button>
        </div>

        {/* Nội dung của từng tab */}
        {activeTab === "description" && (
          <div className="text-gray-700 text-base leading-relaxed">
            {data.description}
          </div>
        )}

        {activeTab === "review" && (
          <div className="p-6 bg-white shadow-md rounded-lg mt-6">
            {isLoading ? (
              <p className="text-gray-500">Đang tải đánh giá...</p>
            ) : isError ? (
              <p className="text-red-500">
                Lỗi khi tải đánh giá. Vui lòng thử lại!
              </p>
            ) : reviews && reviews.length > 0 ? (
              reviews.map((review) => (
                <div key={review.reviewId} className="border-b py-3">
                  <p className="font-semibold text-gray-800">
                    {review.username}
                  </p>
                  <div className="flex items-center space-x-1 mb-2">
                    {[...Array(5)].map((_, i) => (
                      <span
                        key={i}
                        className={`text-lg ${
                          i < review.rating
                            ? "text-yellow-400"
                            : "text-gray-300"
                        }`}
                      >
                        ★
                      </span>
                    ))}
                  </div>
                  <p className="text-gray-700">{review.comment}</p>
                </div>
              ))
            ) : (
              <p className="text-gray-500">Chưa có đánh giá nào.</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductInfo;

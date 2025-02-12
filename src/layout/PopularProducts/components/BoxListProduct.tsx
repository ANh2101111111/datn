import React, { FC } from "react";
import IconStar from "@/layout/assets/icons/IconStar";

interface IBoxListProductProps {
  image: React.ReactNode;
  name: string;
  brand: string;
  rating: number;
  weight: string;
  originalPrice: number;
  discountedPrice: number;
}

// Component BoxListProduct
const BoxListProduct: FC<IBoxListProductProps> = ({
  image,
  name,
  brand,
  rating,
  weight,
  originalPrice,
  discountedPrice,
}) => {
  // Hàm tạo sao
  const renderStars = (rating: number) => {
    return [...Array(5)].map((_, index) => (
      <IconStar
        key={index}
        className={`w-5 h-5 ${
          index < rating ? "text-yellow-500" : "text-gray-400"
        }`}
      />
    ));
  };

  return (
    <div className="bg-white w-[298px] h-[402px] border border-[#E5E5E5] rounded-[4px] overflow-hidden flex flex-col">
      <div className="flex justify-center mt-[65px] mb-4">{image}</div>

      {/* Chi tiết sản phẩm */}
      <div className="px-4 flex flex-col gap-1">
        <p className="text-xs font-lato font-normal text-text-body">{brand}</p>
        <h2 className="font-quicksand text-sm font-bold text-gray-800">
          {name}
        </h2>

        {/* Đánh giá sao */}
        <div className="flex items-center">
          <div className="flex items-center space-x-1 mr-2">
            {renderStars(rating)}{" "}
            {/* Render stars dynamically based on rating */}
          </div>
          <p className="text-text-body text-xs font-lato">
            ({rating.toFixed(1)})
          </p>{" "}
          {/* Hiển thị rating thực tế */}
        </div>

        {/* Trọng lượng */}
        <p className="text-sm text-text-body font-lato">{weight}</p>
      </div>

      {/* Nút Add */}
      <div className="flex items-center justify-between px-5 pb-5 mt-[11px]">
        <div className="flex items-center">
          <span className="text-xl font-semibold text-green-500">
            ${discountedPrice.toFixed(2)}
          </span>
          <span className="text-sm text-gray-500 line-through ml-2">
            ${originalPrice.toFixed(2)}
          </span>
        </div>
        <button className="bg-[#DEF9EC] text-[#3BB77E] text-sm font-bold py-2 px-4 rounded-[4px] hover:bg-green-600 transition-all duration-300">
          Add +
        </button>
      </div>
    </div>
  );
};

export default BoxListProduct;

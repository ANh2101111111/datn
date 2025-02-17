/* eslint-disable @next/next/no-img-element */
import React, { FC } from "react";
import IconStar from "@/layout/assets/icons/IconStar";
import Button from "@/uis/common/button";
import IconCart from "@/layout/assets/icons/IconCart";
import BoxBadge from "@/layout/Badge/BoxBadge";

interface IBoxListProductSellerProps {
  image: string;
  name: string;
  brand: string;
  rating: number;
  weight: string;
  originalPrice: number;
  discountedPrice: number;
  sold: number;
  stock: number;
  type?: string;
  textType?: string;
}

const BoxListProductSeller: FC<IBoxListProductSellerProps> = ({
  image,
  name,
  brand,
  rating,
  weight,
  originalPrice,
  discountedPrice,
  sold,
  stock,
  type,
  textType,
}) => {
  const renderStars = (rating: number) => {
    return [...Array(5)].map((_, index) => (
      <IconStar
        key={index}
        className={`w-5 h-5 ${
          index < rating ? "text-brand-secondary" : "text-icon-star"
        }`}
        // fill không thể dùng classname
        fill={index < 5 ? "#FDC040" : "#CDCDCD"}
      />
    ));
  };

  const getBadgeColor = (type: string) => {
    const colors: Record<string, string> = {
      HOT: "bg-badge-Danger",
      NEW: "bg-badge-brand-1",
      SALE: "bg-badge-brand-2",
    };
    return colors[type] || "bg-badge-Danger";
  };

  return (
    <div className="bg-white w-full h-[512px] border-border-color1 rounded-[4px] overflow-hidden flex flex-col relative">
      <div className="flex justify-center mt-[60px] mb-4">
        <img
          src={image}
          alt="product"
          className="w-[200px] h-[150px] object-cover"
        />
      </div>

      {/* Chi tiết sản phẩm */}
      <div className="mt-4">
        <div className="px-4 flex flex-col gap-1">
          <p className="text-xs font-lato font-normal text-text-body">
            {brand}
          </p>
          <h2 className="font-quicksand  text-sm font-bold text-text-heading">
            {name}
          </h2>

          {/* Đánh giá sao */}
          <div className="flex items-center">
            <div className="flex items-center space-x-1 mr-2">
              {renderStars(rating)}
            </div>
            <p className="text-text-body text-xs font-lato">
              ({rating.toFixed(1)})
            </p>
          </div>

          {/* Trọng lượng */}
          <p className="text-sm text-text-body font-lato">{weight}</p>
        </div>

        {/* Giá và tiến trình bán */}
        <div className="px-4 pb-2 mt-2">
          <div className="flex items-center">
            <span className="text-xl font-semibold text-badge-brand-1">
              ${discountedPrice.toFixed(2)}
            </span>
            <span className="text-sm text-text-body line-through ml-2">
              ${originalPrice.toFixed(2)}
            </span>
          </div>
          <div className="w-full bg-border-color1 h-2 rounded-md mt-2">
            <div
              className="bg-text-brand1 h-2 rounded-md"
              style={{ width: `${(sold / stock) * 100}%` }}
            ></div>
          </div>
          <div>
            <p className="text-xs mt-3 font-lato text-text-heading">
              Sold: {sold}/{stock}
            </p>
          </div>
        </div>
      </div>

      <div className="  mt-2 flex justify-center">
        <Button
          variant="primary"
          size="large"
          className="  h-[40px] w-[238px] hover:bg-colorButton-brand1hover text-brand-thrid "
        >
          <IconCart /> Add to cart
        </Button>
      </div>
      {type && (
        <BoxBadge text={textType || type} className={getBadgeColor(type)} />
      )}
    </div>
  );
};

export default BoxListProductSeller;

import IconStar from "@/layout/assets/icons/IconStar";
import { IProductDetail } from "@/layout/PopularProducts/components/data";
import Button from "@/uis/common/button";
import Input from "@/uis/common/input";
import { FC } from "react";

interface IProductInfoProps {
  data: IProductDetail;
}

const ProductInfo: FC<IProductInfoProps> = ({ data }) => {
  const renderStars = (rating: number) => {
    return [...Array(5)].map((_, index) => (
      <IconStar
        key={index}
        className={`w-5 h-5 ${
          index < rating ? "text-brand-secondary" : "text-icon-star"
        }`}
        // fill không thể dùng classname
        fill={index < 4 ? "#FDC040" : "#CDCDCD"}
      />
    ));
  };
  return (
    <div className="max-w-[1000px] w-full mx-auto mt-16 mb-10">
      <div className="grid grid-cols-2 gap-10">
        <div className="col-span-1 bg-[url(/login.png)] h-[600px] bg-cover"></div>
        <div className="col-span-1">
          <div className="bg-colorButton-brand1 text-text-small font-quicksand text-badge-brand-1 inline-block py-1 px-2 rounded mb-4">
            {data.textType}
          </div>
          <div className="text-heading-2 text-text-heading font-bold mb-4">
            {data.name}
          </div>
          <div className="flex items-center space-x-1 mr-2 mb-4">
            {renderStars(data.rating)}
          </div>
          <div className="flex items-center gap-5">
            <p className="text-display-2 text-text-brand1 font-quicksand font-bold">
              ${data.discountedPrice}
            </p>
            <p className="text-text-muted text-heading-3 font-quicksand font-bold line-through">
              ${data.originalPrice}
            </p>
          </div>
          <div className="text-text-large text-text-body font-lato mb-8 h-[230px] overflow-hidden">
            {data.description}
          </div>
          <div className="flex items-center gap-2">
            <Input
              type="number"
              className="w-[120px] pl-8"
              variant="focus"
              value={1}
              inputSize="medium"
            />
            <Button size="medium" variant="primary" className="text-white">
              Add To Cart
            </Button>
          </div>
        </div>
      </div>
      <div className="border border-border-grey p-10 rounded-2xl mt-10">
        <div className="text-text-heading text-heading-4 font-quicksand font-bold mb-4">
          Description
        </div>
        <div className="text-text-body text-text-medium font-lato">
          {data.description}
        </div>
      </div>
    </div>
  );
};

export default ProductInfo;

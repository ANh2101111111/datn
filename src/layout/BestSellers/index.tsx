import React from "react";
import BestSellerCard from "./components/BoxSellerShop";
import productSellerData from "./components/data";
import BoxListProductSeller from "./components/BoxBestSeller";

const BestSellers = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-12  items-start mt-5 mb-5 gap-[24px]">
      <div className="md:col-span-3 gap-[24px]">
        <BestSellerCard />
      </div>

      <div className="md:col-span-9 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-[24px]">
        {productSellerData.slice(0, 4).map((product) => (
          <BoxListProductSeller
            key={product.id}
            image={product.imageUrl}
            name={product.name}
            brand={product.brand}
            rating={product.rating}
            weight={product.weight}
            originalPrice={product.originalPrice}
            discountedPrice={product.discountedPrice}
            sold={product.sold}
            stock={product.stock}
            type={product.type}
            textType={product.textType}
          />
        ))}
      </div>
    </div>
  );
};

export default BestSellers;

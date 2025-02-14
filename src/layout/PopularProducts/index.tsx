import React from "react";
import BoxListProduct from "./components/BoxListProduct";
import productData from "./components/data";

const PopularProducts = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-[24px] mt-4 mb-4">
      {productData.map((product) => (
        <div key={product.id} className="flex justify-center">
          <BoxListProduct
            image={product.imageUrl}
            name={product.name}
            brand={product.brand}
            rating={product.rating}
            weight={product.weight}
            originalPrice={product.originalPrice}
            discountedPrice={product.discountedPrice}
            type={product.type}
            textType={product.textType}
          />
        </div>
      ))}
    </div>
  );
};

export default PopularProducts;

import React from "react";
import BoxListProductSeller from "./components/BoxBestSeller";
import productSellerData from "./components/data";

const BoxBestSellers = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-[24px] mt-4 mb-4">
      {productSellerData.map((product) => (
        <div key={product.id} className="flex justify-center">
          <BoxListProductSeller
            image={product.imageUrl}
            name={product.name}
            brand={product.brand}
            rating={product.rating}
            weight={product.weight}
            originalPrice={product.originalPrice}
            discountedPrice={product.discountedPrice}
            sold={product.sold} 
            stock={product.stock} 
          />
        </div>
      ))}
    </div>
  );
};

export default BoxBestSellers;

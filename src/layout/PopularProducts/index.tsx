import React from "react";
import BoxListProduct from "./components/BoxListProduct";
import productData from "./components/data";
import BoxListLable from "../lables/BoxListLable";

const PopularProducts = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-6 mt-4 mb-4">
      {/* Header */}
      <div className="col-span-full flex flex-row items-center justify-between w-full">
        <h2 className="text-heading-3 text-text-heading font-quicksand font-bold">
          Popular Products
        </h2>
        <BoxListLable />
      </div>

      {/* Product Grid */}
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
          />
        </div>
      ))}
    </div>
  );
};

export default PopularProducts;

import React from "react";
import productData from "./components/data";
import BoxListLable from "../Lables/BoxListLable";
import BoxProduct from "./components/BoxProduct";

const PopularProducts = () => {
  return (
    <div className="container mx-auto px-4 mb-4">
      <div className="flex flex-row items-center justify-between w-full ">
        <h2 className="text-heading-3 text-text-heading font-quicksand font-bold">
          Popular Products
        </h2>
        <BoxListLable />
      </div>

      {/* Hiển thị danh sách sản phẩm */}
      <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-6">
        {productData.map((product) => (
          <div key={product.id} className="flex justify-center">
            <BoxProduct
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
    </div>
  );
};

export default PopularProducts;

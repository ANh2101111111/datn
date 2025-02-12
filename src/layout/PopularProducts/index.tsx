import React from "react";
import BoxListProduct from "./components/BoxListProduct"; 
import productData from "./components/data";

const PopularProducts = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-6">
      {productData.map((product) => (
        <BoxListProduct
          key={product.id}
          image={product.imageUrl} 
          name={product.name}
          brand={product.brand}
          rating={product.rating} 
          weight={product.weight}
          originalPrice={product.originalPrice}
          discountedPrice={product.discountedPrice}
        />
      ))}
    </div>
  );
};

export default PopularProducts;

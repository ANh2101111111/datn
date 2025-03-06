import BoxProduct from "@/layout/PopularProducts/components/BoxProduct";
import productData from "@/layout/PopularProducts/components/data";

const ProductDashBoard = () => {
  return (
    <div>
      <h3 className="text-heading-3 font-bold text-start font-quicksand text-text-heading my-8">
        Your Product
      </h3>
      <div className="grid lg:grid-cols-4 grid-cols-1 sm:grid-cols-2 mb-10 gap-4">
        {productData.slice(0, 4).map((product) => {
          return (
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
          );
        })}
      </div>
    </div>
  );
};

export default ProductDashBoard;

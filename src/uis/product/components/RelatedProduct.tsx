import { useGetProducts } from "@/api/product";
import BoxProduct from "@/layout/PopularProducts/components/BoxProduct";

const RelatedProduct = () => {
    const { data = [] } = useGetProducts();
    const productData = data.slice(0, 4);
  return (
    <div>
      <h3 className="text-heading-3 font-bold text-center font-quicksand text-text-heading my-8">
        Related products
      </h3>
      <div className="grid lg:grid-cols-4 grid-cols-1 sm:grid-cols-2 mb-10 gap-4">
        {productData.slice(0, 4).map((product) => {
          return (
            <div key={product.bicycleId} className="flex justify-center">
              <BoxProduct
                id={product.bicycleId}
                image={product.image}
                name={product.name}
                brand={String(product.categoryId)}
                rating={product.rating}
                weight={String(product.quantity)}
                originalPrice={product.originalPrice}
                discountedPrice={product.discountedPrice}
                type={product.type}
                textType={product.type}
              />
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default RelatedProduct;

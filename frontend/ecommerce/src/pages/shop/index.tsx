import "../../types"
import Order from "@/components/order";
import Product from "@/components/product";
import Review from "@/components/review";



export default function Home() {
  const vacuumProd: product_t = {
    name: "Vacuum", 
    brand: "Sony",
    price: 50.00,
    rating: 4.7,
  };

  const vacuumOrder: order_t = {
    product: vacuumProd,
    orderDate: new Date(),
  };

  const review: review_t = {
    rating: 2.0,
    comment: "very bad product",
    username: "mario",
    date: new Date(),
  };

  return (
    <>
      <h1> SHOP </h1>
      <Product {...vacuumProd} />
      <Order {...vacuumOrder} />
      <Review {...review} />
    </>
  );
}

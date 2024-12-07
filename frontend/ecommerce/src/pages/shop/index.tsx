import "../../types"
import Order from "@/components/order";
import Product from "@/components/product";
import Review from "@/components/review";
import { useEffect, useState } from "react";


export default function Home() {
  let [products, setProducts] = useState<product_t[]>([]);
  let [orders, setOrders] = useState<order_t[]>([]);
  let [reviews, setReviews] = useState<review_t[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const req = await fetch('http://localhost:3000/products.json');
        const data = await req.json();

        setProducts(data);
      } catch (error) {
        console.error("Error fetching products: ", error);
      }
    };

    const fetchReviews = async () => {
      try {
        const req = await fetch('http://localhost:3000/reviews.json');
        const data = await req.json();

        let cleanedData: review_t[] = data;
        cleanedData.forEach((review) => review.datePosted = new Date(review.datePosted));
        setReviews(cleanedData);
      } catch (error) {
        console.error("Error fetching reviews: ", error);
      }
    };

    const fetchOrders = async () => {
      try {
        const req = await fetch('http://localhost:3000/orders.json');
        const data = await req.json();

        let cleanedData: order_t[] = data;
        cleanedData.forEach((order) => order.dateOrdered = new Date(order.dateOrdered));
        setOrders(cleanedData);
      } catch (error) {
        console.error("Error fetching orders: ", error);
      }
    };

    fetchProducts();
    fetchReviews();
    fetchOrders();
  }, []);

  return (
    <>
      <h1> SHOP </h1>
      {products.length > 0 && (
        products.map((product) => <Product key={product.productID} {...product} />)
      )}

      {reviews.length > 0 && (
        reviews.map((review) => <Review key={review.reviewID} {...review} />)
      )}

      {orders.length > 0 && (
        orders.map((order) => <Order key={order.orderID} {...order} />)
      )}
    </>
  );
}
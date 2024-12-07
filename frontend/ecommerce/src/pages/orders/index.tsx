import "../../types"
import Order from "@/components/order";
import { useEffect, useState } from "react";
import styles from "./Orders.module.css";


export default function Orders() {
  let [products, setProducts] = useState<product_t[]>([]);
  let [orders, setOrders] = useState<order_t[]>([]);
  let [reviews, setReviews] = useState<review_t[]>([]);

  useEffect(() => {
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

    fetchOrders();
  }, []);

  return (
    <>
      <h1 className={styles.header}> Your Orders </h1>
      {orders.length > 0 && (
        orders.map((order) => <Order key={order.orderID} {...order} />)
      )}
    </>
  );
}
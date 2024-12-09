import "../../types"
import Order from "@/components/order";
import { useEffect, useState } from "react";
import styles from "./Orders.module.css";


export default function Orders() {
  let [orders, setOrders] = useState<order_t[]>([]);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const req = await fetch('http://localhost:8080/api/orders');
        const data: order_t[] = await req.json();

        console.log(data);
        setOrders(data);
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
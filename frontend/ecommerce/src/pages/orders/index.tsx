import "../../types"
import Order from "@/components/order";
import { useEffect, useState } from "react";
import styles from "./Orders.module.css";
import { useUser } from "../../context/UserContext";
import axios from "axios";


export default function Orders() {
  let [orders, setOrders] = useState<order_t[]>([]);

  const { user, isLoggedIn } = useUser();

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        if (!isLoggedIn()) return;

        const userID = user?.userID;
        const res = await axios.get<order_t[]>(`http://localhost:8080/api/orders/user/${userID}`);
        setOrders(res.data);
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
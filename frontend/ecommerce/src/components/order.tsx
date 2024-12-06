import "../types";
import styles from "@/styles/Order.module.css";

export default function Order(props: order_t) {
    const { name, brand, price, rating } = props.product;

    return (
        <div className={styles.orderCard}>
            <h2 className={styles.orderTitle}>Order Summary</h2>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Product Name:</span> {name}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Brand:</span> {brand}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Price:</span> ${price.toFixed(2)}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Order Date:</span> {props.orderDate.toDateString()}
            </p>
        </div>
    );
}
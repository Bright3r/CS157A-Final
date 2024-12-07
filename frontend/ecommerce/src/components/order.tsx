import "../types";
import styles from "@/styles/Order.module.css";

export default function Order(
        { numProductsOrdered, dateOrdered, total, products, shippingAddress, user }: order_t) 
    {
    
    const prod: product_t | null = products.length > 0 ? products[0] : null;

    return (
        <div className={styles.orderCard}>
            <h2 className={styles.orderTitle}>Order Summary</h2>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Product Name:</span> {prod?.productName}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Brand:</span> {prod?.brand}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Price:</span> ${prod?.price.toFixed(2)}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Order Date:</span> {dateOrdered.toDateString()}
            </p>
        </div>
    );
}